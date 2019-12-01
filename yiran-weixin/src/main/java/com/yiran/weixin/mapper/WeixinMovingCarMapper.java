package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinMovingCar;
import java.util.List;	

/**
 * 微信挪车用户 数据层
 * 
 * @author yiran
 * @date 2019-11-20
 */
public interface WeixinMovingCarMapper 
{
	/**
     * 查询微信挪车用户信息
     * 
     * @param id 微信挪车用户ID
     * @return 微信挪车用户信息
     */
	public WeixinMovingCar selectWeixinMovingCarById(Integer id);
	
	/**
     * 查询微信挪车用户列表
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 微信挪车用户集合
     */
	public List<WeixinMovingCar> selectWeixinMovingCarList(WeixinMovingCar weixinMovingCar);
	
	/**
     * 新增微信挪车用户
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 结果
     */
	public int insertWeixinMovingCar(WeixinMovingCar weixinMovingCar);
	
	/**
     * 修改微信挪车用户
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 结果
     */
	public int updateWeixinMovingCar(WeixinMovingCar weixinMovingCar);
	
	/**
     * 删除微信挪车用户
     * 
     * @param id 微信挪车用户ID
     * @return 结果
     */
	public int deleteWeixinMovingCarById(Integer id);
	
	/**
     * 批量删除微信挪车用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinMovingCarByIds(String[] ids);
	
}