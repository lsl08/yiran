package com.yiran.weixin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.weixin.mapper.WeixinMovingCarMapper;
import com.yiran.weixin.domain.WeixinMovingCar;
import com.yiran.weixin.service.IWeixinMovingCarService;
import com.yiran.common.support.Convert;

/**
 * 微信挪车用户 服务层实现
 * 
 * @author yiran
 * @date 2019-11-20
 */
@Service
public class WeixinMovingCarServiceImpl implements IWeixinMovingCarService 
{
	@Autowired
	private WeixinMovingCarMapper weixinMovingCarMapper;

	/**
     * 查询微信挪车用户信息
     * 
     * @param id 微信挪车用户ID
     * @return 微信挪车用户信息
     */
    @Override
	public WeixinMovingCar selectWeixinMovingCarById(Integer id)
	{
	    return weixinMovingCarMapper.selectWeixinMovingCarById(id);
	}
	
	/**
     * 查询微信挪车用户列表
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 微信挪车用户集合
     */
	@Override
	public List<WeixinMovingCar> selectWeixinMovingCarList(WeixinMovingCar weixinMovingCar)
	{
	    return weixinMovingCarMapper.selectWeixinMovingCarList(weixinMovingCar);
	}
	
    /**
     * 新增微信挪车用户
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 结果
     */
	@Override
	public int insertWeixinMovingCar(WeixinMovingCar weixinMovingCar)
	{
	    return weixinMovingCarMapper.insertWeixinMovingCar(weixinMovingCar);
	}
	
	/**
     * 修改微信挪车用户
     * 
     * @param weixinMovingCar 微信挪车用户信息
     * @return 结果
     */
	@Override
	public int updateWeixinMovingCar(WeixinMovingCar weixinMovingCar)
	{
	    return weixinMovingCarMapper.updateWeixinMovingCar(weixinMovingCar);
	}

	/**
     * 删除微信挪车用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeixinMovingCarByIds(String ids)
	{
		return weixinMovingCarMapper.deleteWeixinMovingCarByIds(Convert.toStrArray(ids));
	}
	
}
