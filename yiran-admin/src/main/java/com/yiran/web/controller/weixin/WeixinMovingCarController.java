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
import com.yiran.weixin.domain.WeixinMovingCar;
import com.yiran.weixin.service.IWeixinMovingCarService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;
import com.yiran.framework.util.ShiroUtils;
/**
 * 微信挪车用户 信息操作处理
 * 
 * @author yiran
 * @date 2019-11-20
 */
@Controller
@RequestMapping("/weixin/weixinMovingCar")
public class WeixinMovingCarController extends BaseController
{
    private String prefix = "weixin/weixinMovingCar";
	
	@Autowired
	private IWeixinMovingCarService weixinMovingCarService;
	
	@RequiresPermissions("weixin:weixinMovingCar:view")
	@GetMapping()
	public String weixinMovingCar()
	{
	    return prefix + "/weixinMovingCar";
	}
	
	/**
	 * 查询微信挪车用户列表
	 */
	@RequiresPermissions("weixin:weixinMovingCar:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WeixinMovingCar weixinMovingCar)
	{
		startPage();
        List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(weixinMovingCar);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出微信挪车用户列表
	 */
	@RequiresPermissions("weixin:weixinMovingCar:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WeixinMovingCar weixinMovingCar)
    {
    	List<WeixinMovingCar> list = weixinMovingCarService.selectWeixinMovingCarList(weixinMovingCar);
        ExcelUtil<WeixinMovingCar> util = new ExcelUtil<WeixinMovingCar>(WeixinMovingCar.class);
        return util.exportExcel(list, "weixinMovingCar");
    }
	
	/**
	 * 新增微信挪车用户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存微信挪车用户
	 */
	@RequiresPermissions("weixin:weixinMovingCar:add")
	@Log(title = "微信挪车用户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WeixinMovingCar weixinMovingCar)
	{		
		
		return toAjax(weixinMovingCarService.insertWeixinMovingCar(weixinMovingCar));
	}

	/**
	 * 修改微信挪车用户
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		WeixinMovingCar weixinMovingCar = weixinMovingCarService.selectWeixinMovingCarById(id);
		mmap.put("weixinMovingCar", weixinMovingCar);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存微信挪车用户
	 */
	@RequiresPermissions("weixin:weixinMovingCar:edit")
	@Log(title = "微信挪车用户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WeixinMovingCar weixinMovingCar)
	{		
		return toAjax(weixinMovingCarService.updateWeixinMovingCar(weixinMovingCar));
	}
	
	/**
	 * 删除微信挪车用户
	 */
	@RequiresPermissions("weixin:weixinMovingCar:remove")
	@Log(title = "微信挪车用户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(weixinMovingCarService.deleteWeixinMovingCarByIds(ids));
	}
	
}
