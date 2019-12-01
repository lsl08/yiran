package com.yiran.web.controller.weixin;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.weixin.domain.WeixinMovingCarDetail;
import com.yiran.weixin.service.IWeixinMovingCarDetailService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;
import com.yiran.framework.util.ShiroUtils;
/**
 * 挪车明细 信息操作处理
 * 
 * @author yiran
 * @date 2019-11-20
 */
@Controller
@RequestMapping("/weixin/weixinMovingCarDetail")
public class WeixinMovingCarDetailController extends BaseController
{
    private String prefix = "weixin/weixinMovingCarDetail";
	
	@Autowired
	private IWeixinMovingCarDetailService weixinMovingCarDetailService;
	
	@RequiresPermissions("weixin:weixinMovingCarDetail:view")
	@GetMapping()
	public String weixinMovingCarDetail()
	{
	    return prefix + "/weixinMovingCarDetail";
	}
	
	/**
	 * 查询挪车明细列表
	 */
	@RequiresPermissions("weixin:weixinMovingCarDetail:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WeixinMovingCarDetail weixinMovingCarDetail)
	{
		startPage();
        List<WeixinMovingCarDetail> list = weixinMovingCarDetailService.selectWeixinMovingCarDetailList(weixinMovingCarDetail);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出挪车明细列表
	 */
	@RequiresPermissions("weixin:weixinMovingCarDetail:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinMovingCarDetail weixinMovingCarDetail)
    {
    	List<WeixinMovingCarDetail> list = weixinMovingCarDetailService.selectWeixinMovingCarDetailList(weixinMovingCarDetail);
        ExcelUtil<WeixinMovingCarDetail> util = new ExcelUtil<WeixinMovingCarDetail>(WeixinMovingCarDetail.class);
        return util.exportExcel(list, "weixinMovingCarDetail");
    }
	
	/**
	 * 新增挪车明细
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存挪车明细
	 */
	@RequiresPermissions("weixin:weixinMovingCarDetail:add")
	@Log(title = "挪车明细", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WeixinMovingCarDetail weixinMovingCarDetail)
	{		
		return toAjax(weixinMovingCarDetailService.insertWeixinMovingCarDetail(weixinMovingCarDetail));
	}

	/**
	 * 修改挪车明细
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WeixinMovingCarDetail weixinMovingCarDetail = weixinMovingCarDetailService.selectWeixinMovingCarDetailById(id);
		mmap.put("weixinMovingCarDetail", weixinMovingCarDetail);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存挪车明细
	 */
	@RequiresPermissions("weixin:weixinMovingCarDetail:edit")
	@Log(title = "挪车明细", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WeixinMovingCarDetail weixinMovingCarDetail)
	{		
		return toAjax(weixinMovingCarDetailService.updateWeixinMovingCarDetail(weixinMovingCarDetail));
	}
	
	/**
	 * 删除挪车明细
	 */
	@RequiresPermissions("weixin:weixinMovingCarDetail:remove")
	@Log(title = "挪车明细", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(weixinMovingCarDetailService.deleteWeixinMovingCarDetailByIds(ids));
	}
	
}
