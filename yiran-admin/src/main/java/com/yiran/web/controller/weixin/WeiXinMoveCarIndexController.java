package com.yiran.web.controller.weixin;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yiran.common.base.ResultWrapper;
import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.domain.WeixinMovingCar;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.entity.WeiXinOAuthCodeTokenVO;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.service.IWeixinMovingCarService;
import com.yiran.weixin.service.impl.WeixinSettingServiceImpl;
import com.yiran.weixin.servlet.WeixinContext;
import com.yiran.weixin.utils.ApplicationContextUtil;
import com.yiran.weixin.utils.WeiXinJsonUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Controller
@RequestMapping("/wxMoveCar")
public class WeiXinMoveCarIndexController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(WeiXinMoveCarIndexController.class);
	
	@Autowired
	private IWeixinMovingCarService weixinMovingCarService;
	
	@GetMapping("/applyForCarCode")
	public String applyForCarCode(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) {
		logger.info("申请扫码挪车二维码");
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		//1.判断这个用户是否注册，如果注册将用户信息存入session，跳转到主菜单页面
		String openId = null;
		//获取appid
		String appid = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
		logger.info("微信appId:"+appid);
		//获取秘钥
		String appsecret = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
		logger.info("微信秘钥appsecret:"+appsecret);
		String access_token = WeixinContext.getInstance().getAccessToken().getAccess_token();
		logger.info("微信token凭证access_token："+access_token);
		openId = (String) session.getAttribute("openId");
		logger.info("【session中的openId】："+openId);
		//授权后重定向的回调链接地址
		String redirect_url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPLY_MOVE_CAR_CODE_REDIRECT_URI).getWeixinValue();
		logger.info("授权后重定向的回调链接地址："+redirect_url);
		if(StringUtils.isBlank(openId) ){//session中存在
			logger.info("---------->>>>>>>>session不存在openId<<<<<<<--------------");
			String code = request.getParameter("code");
			logger.info("获取的code值是："+code);
			//获取通过code换取access_token以及OpenID API URL
			String codeToOpenIdAPI = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_CODE_OPENID).getWeixinValue();
			logger.info("获取通过code换取access_token以及OpenID的 API URL:"+codeToOpenIdAPI);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("APPID", appid);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("SECRET", appsecret);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("CODE", code);
			codeToOpenIdAPI = codeToOpenIdAPI.replaceAll("&amp;", "&");
			logger.info("最终codeToOpenIdAPI ："+codeToOpenIdAPI);
			//发送请求
			String json = WeixinBasicKit.sendGet(codeToOpenIdAPI);
			logger.info("发送请求后获取json对象："+json);
			if(WeixinBasicKit.checkRequestSucc(json)){//成功
				logger.info("开始将json转换为对象.......");
				WeiXinOAuthCodeTokenVO oAuthCodeTokenVO = (WeiXinOAuthCodeTokenVO) WeiXinJsonUtil.getInstance().json2obj(json,WeiXinOAuthCodeTokenVO.class);
				logger.info("将结果转换为json："+oAuthCodeTokenVO);
				openId = oAuthCodeTokenVO.getOpenid();
				session.setAttribute("openId", openId);
				logger.info("【根据code获取openId】 :"+openId);
			}
		}
		
		return "redirect:"+redirect_url+"?openid="+openId;
	}
	
	@GetMapping("/myCarCode")
	public String myCarCode(HttpServletRequest request, HttpServletResponse response,HttpSession session, Model model) {
		logger.info("我的挪车二维码");
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		//1.判断这个用户是否注册，如果注册将用户信息存入session，跳转到主菜单页面
		String openId = null;
		//获取appid
		String appid = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
		logger.info("微信appId:"+appid);
		//获取秘钥
		String appsecret = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
		logger.info("微信秘钥appsecret:"+appsecret);
		String access_token = WeixinContext.getInstance().getAccessToken().getAccess_token();
		logger.info("微信token凭证access_token："+access_token);
		openId = (String) session.getAttribute("openId");
		logger.info("【session中的openId】："+openId);
		//授权后重定向的回调链接地址
		String redirect_url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_MY_MOVE_CAR_CODE_REDIRECT_URI).getWeixinValue();
		logger.info("授权后重定向的回调链接地址："+redirect_url);
		if(StringUtils.isBlank(openId) ){//session中存在
			logger.info("---------->>>>>>>>session不存在openId<<<<<<<--------------");
			String code = request.getParameter("code");
			logger.info("获取的code值是："+code);
			//获取通过code换取access_token以及OpenID API URL
			String codeToOpenIdAPI = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_CODE_OPENID).getWeixinValue();
			logger.info("获取通过code换取access_token以及OpenID的 API URL:"+codeToOpenIdAPI);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("APPID", appid);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("SECRET", appsecret);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("CODE", code);
			codeToOpenIdAPI = codeToOpenIdAPI.replaceAll("&amp;", "&");
			logger.info("最终codeToOpenIdAPI ："+codeToOpenIdAPI);
			//发送请求
			String json = WeixinBasicKit.sendGet(codeToOpenIdAPI);
			logger.info("发送请求后获取json对象："+json);
			if(WeixinBasicKit.checkRequestSucc(json)){//成功
				logger.info("开始将json转换为对象.......");
				WeiXinOAuthCodeTokenVO oAuthCodeTokenVO = (WeiXinOAuthCodeTokenVO) WeiXinJsonUtil.getInstance().json2obj(json,WeiXinOAuthCodeTokenVO.class);
				logger.info("将结果转换为json："+oAuthCodeTokenVO);
				openId = oAuthCodeTokenVO.getOpenid();
				session.setAttribute("openId", openId);
				logger.info("【根据code获取openId】 :"+openId);
			}
		}
		
		return "redirect:"+redirect_url+"?openid="+openId;
	}
	
	@GetMapping("/notifyVehicleOwner/{openId}/{licensePlate}")
	public String notifyVehicleOwner(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId,
			@ApiParam(required = true, name = "licensePlate", value = "车牌号") @PathVariable String licensePlate) {
		logger.info("通知车主页面->参数：openId={},licensePlate={}",openId,licensePlate);
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		
		WeixinMovingCar mc = new WeixinMovingCar();
		mc.setOpenId(openId);//微信openId
		mc.setLicensePlate(licensePlate);//车牌号
		List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(mc);
		WeixinMovingCar carinfo = list.get(0);
		//授权后重定向的回调链接地址
		String redirect_url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_NOTIFY_VEHICLE_OWNER_REDIRECT_URI).getWeixinValue();
		logger.info("通知车主页面->重定向页面url:redirect:"+redirect_url+"?openid="+openId+"&licensePlate="+URLEncoder.encode(licensePlate));
		return "redirect:"+redirect_url+"?openid="+openId+"&phone="+carinfo.getPhoneNumber1()+"&licensePlate="+URLEncoder.encode(licensePlate);
	}
	
	

}
