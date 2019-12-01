package com.yiran.api.facade;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiran.api.request.MoveingCarRequest;
import com.yiran.api.request.NotifyOwnerRequest;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.config.FastDFSConfig;
import com.yiran.common.utils.RandomUtil;
import com.yiran.common.utils.TwoDimensionCode;
import com.yiran.common.utils.fastdft.FastDFSHelper;
import com.yiran.message.domain.NotifyMsgLog;
import com.yiran.message.domain.SendAuthCodeRequest;
import com.yiran.message.service.INotifyMsgLogService;
import com.yiran.message.service.ISmsSendService;
import com.yiran.message.util.SmsResultEnum;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.wechat.domain.WechatShoppingCart;
import com.yiran.weixin.domain.WeixinMovingCar;
import com.yiran.weixin.domain.WeixinMovingCarDetail;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.IPushMessageService;
import com.yiran.weixin.service.IWeixinMovingCarDetailService;
import com.yiran.weixin.service.IWeixinMovingCarService;
import com.yiran.weixin.service.IWeixinSettingService;
import com.yiran.weixin.service.IWeixinUserService;
import com.yiran.weixin.utils.ImgTools;
import com.yiran.weixin.utils.Pic;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/movingCar")
@Api(value="扫码挪车接口",description="扫码挪车")
public class WeiXinMovingCarFacade {
	private static final Logger logger = LoggerFactory.getLogger(WeiXinMovingCarFacade.class);
	public static final String FILEPATHTWODIMENSIONCODE_BG = "/opt/soa/qrcode/"; //二维码存放路径
	public static final String FILEPATHTWODIMENSIONCODE = "/opt/soa/qrcode/temp/"; //二维码存放路径
	@Autowired
	private IWeixinUserService weixinUserService;
	@Autowired
	private ISmsSendService smsSendService;
	@Autowired
	private IWeixinSettingService weixinSettingService;
	@Autowired
   	private FastDFSHelper fastDFSHelper;
	@Autowired
	private FastDFSConfig fastDFSTConfig;
	@Autowired
	private IWeixinMovingCarService weixinMovingCarService;
	@Autowired
	private IWeixinMovingCarDetailService weixinMovingCarDetailService;
	@Autowired
	private INotifyMsgLogService notifyMsgLogService;
	@Autowired
	private IPushMessageService pushMessageService;
	@PostMapping("/applyForCarCode")
    @ApiOperation("申请挪车码")
	public ResultWrapper<Map<String,Object>> applyForCarCode(@RequestBody @ApiParam(name="requestJson",value="请求参数json数据")String requestJson){
		logger.info("申请挪车码:{}",requestJson);
		MoveingCarRequest request = JSON.parseObject(requestJson, MoveingCarRequest.class);
    	logger.info("json转换MoveingCarRequest对象后数据:"+JSON.toJSONString(request));
    	try {
			//1.验证短信验证码
			SendAuthCodeRequest sr = new SendAuthCodeRequest();
			sr.setPhone(request.getPhoneNumber());
			sr.setVerifyCode(request.getVerificationCode());
			sr.setMsgOrderNo(request.getMsgOrderNo());
			//TODO:本地先屏蔽
			AjaxResult ajaxResult = smsSendService.verifyMobileAuthCode(sr);
			if(!"0000".equals(ajaxResult.get("code"))){
				logger.info("短信验证码验证失败");
				return ResultWrapper.newInstance((String)ajaxResult.get("code"), (String)ajaxResult.get("msg"));
			}
			
			WeixinMovingCar mc = new WeixinMovingCar();
			mc.setOpenId(request.getOpenId());
			mc.setLicensePlate(request.getLicensePlate());
			List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(mc);
			if(list.size() > 0){
				return ResultWrapper.other("该车牌已经申请过二维码，请直接到微信公众号下载二维码。");
			}
			//2.生成二维码，上传fastdfs获取url   WEIXIN_SWEEP_AND_MOVE_CAR  http://www.yirantrade.com?openId=OPENID
			String encoderImgId = this.get32UUID()+".png"; //encoderImgId此处二维码的图片名
			String moveCarUrl = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_SWEEP_AND_MOVE_CAR).getWeixinValue();
			moveCarUrl = moveCarUrl.replace("OPENID", request.getOpenId());
			moveCarUrl = moveCarUrl.replace("LICENSEPLATE", request.getLicensePlate());
			logger.info("二维码内容:{}",moveCarUrl);
			//合并图片fastDFS地址
			String newEncoderImgId =null;
			String filePath = FILEPATHTWODIMENSIONCODE + encoderImgId;  //存放路径
			logger.info("二维码存放的路径："+filePath);
			TwoDimensionCode.encoderQRCode(moveCarUrl, filePath, "png");
			//合并图片
			newEncoderImgId = hebingtupian(encoderImgId,weixinSettingService);
			String url = fastDFSTConfig.getInterNetHttpHost()+"/"+newEncoderImgId;
			logger.info("图片上传fastDFS后的路径：{}",newEncoderImgId);
			//3.保存数据到数据库
			mc.setPhoneNumber1(request.getPhoneNumber());
			WeixinUser weixinUser = weixinUserService.selectWeixinUserByOpenId(request.getOpenId());
			mc.setNickName(weixinUser.getNickname());
			mc.setQrCodeUrl(url);
			weixinMovingCarService.insertWeixinMovingCar(mc);
			return ResultWrapper.ok().putData(url);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultWrapper.error();
		}
		
	}
	
	private String hebingtupian(String encoderImgId, IWeixinSettingService weixinSettingService) {
		String pathUrl = null;
		encoderImgId = encoderImgId.substring(0,encoderImgId.indexOf("."));
		try {
			//获取随机码
			String random=RandomUtil.generateString(3);
			logger.info("获取随机码用户拼接图片名称："+random);
			String yuantulujin = FILEPATHTWODIMENSIONCODE+encoderImgId+".png";
			logger.info("压缩图片的原始路径："+yuantulujin);
			//将图片压缩成118*122
			File img = new File(yuantulujin);
	        //压缩后图片的名称
			String imgName=encoderImgId+random+".png";
			logger.info("压缩后图片的名称："+imgName);
			String yasuohoutupian = FILEPATHTWODIMENSIONCODE+imgName;
			logger.info("压缩后图片的路径："+yasuohoutupian);
			FileOutputStream fos = new FileOutputStream(yasuohoutupian);
			 logger.info(">>>>>>>>>>>>>>开始压缩图片<<<<<<<<<<<<<<<<<<<");
			 int erweima_width = Integer.parseInt(weixinSettingService.getValueByKey(WeixinSetting.KEY_ERWEIMA_WIDTH).getWeixinValue());
			 int erweima_height = Integer.parseInt(weixinSettingService.getValueByKey(WeixinSetting.KEY_ERWEIMA_HEIGHT).getWeixinValue());
	        //ImgTools.thumbnail_w_h(img, 200, 200, fos);
			 ImgTools.thumbnail_w_h(img, erweima_width, erweima_height, fos);
	        logger.info(">>>>>>>>>>>>>>图片压缩完毕<<<<<<<<<<<<<<<<<<<");
			//将图片合并到指定的位置
	        Pic tt = new Pic();  
	        logger.info("Pic对象："+tt);
	        String hebingyuanshitupian = FILEPATHTWODIMENSIONCODE_BG+"saomanuoche.jpg";
	        logger.info("合并原始图片："+hebingyuanshitupian);
	        BufferedImage d = tt.loadImageLocal(hebingyuanshitupian); 
	        String xuyaohebingtupian = FILEPATHTWODIMENSIONCODE+imgName;
	        logger.info("需要合并图片："+xuyaohebingtupian);
	        BufferedImage b = tt.loadImageLocal(xuyaohebingtupian);   
	        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	        String heBingTuPian = "saomanuoche"+format.format(new Date())+".jpg";
	        logger.info("合并后后图片的名称："+heBingTuPian);
	        //往图片上写文件    
	        String hebinghouxintupian = FILEPATHTWODIMENSIONCODE+heBingTuPian;
	        logger.info("合并后的图片："+hebinghouxintupian);
	        logger.info(">>>>>>>>>>>>>>开始图片合并<<<<<<<<<<<<<<<<<<<");
	        int img_x = Integer.parseInt(weixinSettingService.getValueByKey(WeixinSetting.KEY_IMG_X).getWeixinValue());
	        logger.info("图片左偏移位置："+img_x);
	        int img_y = Integer.parseInt(weixinSettingService.getValueByKey(WeixinSetting.KEY_IMG_Y).getWeixinValue());
	        logger.info("图片右偏移位置："+img_y);
			tt.writeImageLocal(hebinghouxintupian, tt.modifyImagetogeter(b, d,img_x,img_y)); 
	        logger.info(">>>>>>>>>>>>>>图片合并完毕<<<<<<<<<<<<<<<<<<<");
	        logger.info(">>>>>>>>>>>>>>上传图片到fastdfs<<<<<<<<<<<<<<<<<<<");
	        //TODO:本地先屏蔽
	        pathUrl = fastDFSHelper.uploadFile(new File(hebinghouxintupian));
	        logger.info(">>>>>>>>>>>>>>上传图片到fastdfs完毕<<<<<<<<<<<<<<<<<<<");
	        logger.info(">>>>>>>>>>>>>>删除临时文件<<<<<<<<<<<<<<<<<<<");
	        delAllFile(FILEPATHTWODIMENSIONCODE);
	        logger.info(">>>>>>>>>>>>>>删除临时文件完毕<<<<<<<<<<<<<<<<<<<");
	        return pathUrl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pathUrl;
	}

	@PostMapping("/getUserInfo/{openId}")
    @ApiOperation(value = "查询微信用户信息",notes="查询微信用户信息")
	public ResultWrapper<Map<String,Object>> getUserInfo(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		System.out.println("【微信用户openId】：" + openId);
		WeixinUser weixinUser = weixinUserService.selectWeixinUserByOpenId(openId);
		return ResultWrapper.ok().putData(weixinUser);
	}
	
	
	@PostMapping("/getVerificationCode/{phoneNumber}")
    @ApiOperation(value = "获取短信验证码",notes="获取短信验证码")
	public ResultWrapper<Map<String,Object>> getVerificationCode(@ApiParam(required = true, name = "phoneNumber", value = "phoneNumber") @PathVariable String phoneNumber){
		logger.info("获取短信验证码请求参数:{}",phoneNumber);
		SendAuthCodeRequest request = new SendAuthCodeRequest();
		request.setPhone(phoneNumber);
		request.setTemplateId("SMS_177538894");
		//TODO: MOCK数据
		AjaxResult ajaxResult = smsSendService.sendAuthCode(request);
		return ResultWrapper.ok().putData(ajaxResult);
		/*return ResultWrapper.ok().putData(AjaxResult.success(SmsResultEnum.getByCode("OK").getMessage())
		.put("msgOrderNo", "123456789"));*/
	}
	
	@PostMapping("/queryMoveCarQRCode/{openId}")
    @ApiOperation("获取用户的挪车码列表")
	public ResultWrapper<Map<String,Object>> queryMoveCarQRCode(@ApiParam(required = true, name = "openId", value = "openId") @PathVariable String openId){
		
		System.out.println("【微信用户openId】：" + openId);
		WeixinMovingCar mc = new WeixinMovingCar();
		mc.setOpenId(openId);
		List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(mc);
		return ResultWrapper.ok().putData(list);
	}
	
	
	@PostMapping("/queryMoveCarDetail/{openId}")
    @ApiOperation("获取用户的挪车明细列表")
	public ResultWrapper<Map<String,Object>> queryMoveCarDetail(@ApiParam(required = true, name = "openId", value = "openId") @PathVariable String openId){
		
		System.out.println("【微信用户openId】：" + openId);
		WeixinMovingCarDetail mc = new WeixinMovingCarDetail();
		mc.setOpenId(openId);
		List<WeixinMovingCarDetail> list = weixinMovingCarDetailService.selectWeixinMovingCarDetailList(mc);
		return ResultWrapper.ok().putData(list);
	}
	
	
	@PostMapping("/notifyOwner/{openId}/{notifyType}/{licensePlate}")
    @ApiOperation(value = "通知车主",notes="通知车主")
	public ResultWrapper<Map<String,Object>> notifyOwner(
			@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId,
			@ApiParam(required = true, name = "notifyType", value = "通知类型1微信通知2短信通知") @PathVariable String notifyType,
			@ApiParam(required = true, name = "licensePlate", value = "车牌号") @PathVariable String licensePlate){
		logger.info("通知车主请求参数:openId->{},licensePlate->{},notifyType->{}",openId,licensePlate,notifyType);
		
		WeixinMovingCar mc = new WeixinMovingCar();
		mc.setOpenId(openId);//微信openId
		mc.setLicensePlate(licensePlate);//车牌号
		List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(mc);
		if(list.size() == 0){
			return ResultWrapper.other("通知失败,没有车主信息。");
		}
		WeixinMovingCarDetail carDetail = new WeixinMovingCarDetail();
		WeixinMovingCar carinfo = list.get(0);
		//1.短息通知
		if("1".equals(notifyType)){
			//1.1 检查手机号码是否已经通知过车主，如果通知需要间隔10分钟后在发送，只能发送三条
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
			String beginDate = simpleFormat.format(new Date())+" 00:00:00";
			String endDate = simpleFormat.format(new Date())+" 23:59:59";
			//调用查询
			int smsCount = notifyMsgLogService.getSendSmSCountByPhoneType(beginDate, endDate, carinfo.getPhoneNumber1(),notifyType);
			logger.debug("手机号：{}，通知车主发送短信次数{}",carinfo.getPhoneNumber1(),smsCount);
			if(smsCount > 3){
				return ResultWrapper.other("已经通知车主三次,不能在通过短信的方式通知车主，请用微信方式通知车主。");
			}
			
	    	NotifyMsgLog notifyMsgLog = new NotifyMsgLog();
	    	notifyMsgLog.setPhone(carinfo.getPhoneNumber1());
	    	notifyMsgLog.setBusinessType("2");
	    	List<NotifyMsgLog> msgLogList = notifyMsgLogService.selectNotifyMsgLogList(notifyMsgLog);
	    	if(msgLogList.size() > 0){//说明这个号码已经发送过通知车主，取最新的一条数据的发送时间
	    		int minutes = 0;
	    		NotifyMsgLog msgLog = msgLogList.get(0);
				try {
					SimpleDateFormat simpleFormat1 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
					//短信发送时间
					String fromDate = simpleFormat1.format(msgLog.getSenddata());
					//当前时间
					String toDate = simpleFormat1.format(new Date());
					long from = simpleFormat1.parse(fromDate).getTime();
					long to = simpleFormat1.parse(toDate).getTime();
					minutes = (int) ((to - from)/(1000 * 60));
					if(minutes < 10){
						return ResultWrapper.other("已经通知车主");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
	    	}
	    	//
	    	SendAuthCodeRequest request = new SendAuthCodeRequest();
			request.setPhone(carinfo.getPhoneNumber1());
			request.setTemplateId("SMS_177538918");
			//TODO: MOCK数据
			Map<String, String> data =new HashMap<String, String>();
			data.put("licensePlate", licensePlate);
			data.put("address", "  ");
			request.setMap(data);
			AjaxResult ajaxResult = smsSendService.sendBizCode(request);
			carDetail.setOpenId(openId);
			carDetail.setLicensePlate(carinfo.getLicensePlate());
			carDetail.setPhoneNumber(carinfo.getPhoneNumber1());
			carDetail.setNotiyType(Integer.parseInt(notifyType));
			weixinMovingCarDetailService.insertWeixinMovingCarDetail(carDetail);
			return ResultWrapper.ok().putData(ajaxResult);
			//return ResultWrapper.ok();
		}else{
			//2.微信通知 推送消息
			Map<String,String> mapMessage = new HashMap<String,String>();
			mapMessage.put("openId", openId);
			mapMessage.put("licensePlate", carinfo.getLicensePlate());
			mapMessage.put("senddate", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			pushMessageService.pushMoveingCarNotify(MapUtil.mapToJson(mapMessage));
			carDetail.setOpenId(openId);
			carDetail.setLicensePlate(carinfo.getLicensePlate());
			carDetail.setPhoneNumber(carinfo.getPhoneNumber1());
			carDetail.setNotiyType(Integer.parseInt(notifyType));
			weixinMovingCarDetailService.insertWeixinMovingCarDetail(carDetail);
			return ResultWrapper.ok();
		}
		
	}
	
	
	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID(){
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
	//删除指定文件夹下的所有文件

	public static boolean delAllFile(String path) {  
	       boolean flag = false;  
	       File file = new File(path);  
	       if (!file.exists()) {  
	         return flag;  
	       }  
	       if (!file.isDirectory()) {  
	         return flag;  
	       }  
	       String[] tempList = file.list();  
	       File temp = null;  
	       for (int i = 0; i < tempList.length; i++) {  
	          if (path.endsWith(File.separator)) {  
	             temp = new File(path + tempList[i]);  
	          } else {  
	              temp = new File(path + File.separator + tempList[i]);  
	          }  
	          if (temp.isFile()) {  
	             temp.delete();  
	          }  
	          if (temp.isDirectory()) {  
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
	             flag = true;  
	          }  
	       }  
	       return flag;  
	  } 
	
}
