package com.yiran.weixin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.weixin.mapper.WeixinMovingCarDetailMapper;
import com.yiran.weixin.domain.WeixinMovingCarDetail;
import com.yiran.weixin.service.IWeixinMovingCarDetailService;
import com.yiran.common.support.Convert;

/**
 * 挪车明细 服务层实现
 * 
 * @author yiran
 * @date 2019-11-20
 */
@Service
public class WeixinMovingCarDetailServiceImpl implements IWeixinMovingCarDetailService 
{
	@Autowired
	private WeixinMovingCarDetailMapper weixinMovingCarDetailMapper;

	/**
     * 查询挪车明细信息
     * 
     * @param id 挪车明细ID
     * @return 挪车明细信息
     */
    @Override
	public WeixinMovingCarDetail selectWeixinMovingCarDetailById(Integer id)
	{
	    return weixinMovingCarDetailMapper.selectWeixinMovingCarDetailById(id);
	}
	
	/**
     * 查询挪车明细列表
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 挪车明细集合
     */
	@Override
	public List<WeixinMovingCarDetail> selectWeixinMovingCarDetailList(WeixinMovingCarDetail weixinMovingCarDetail)
	{
	    return weixinMovingCarDetailMapper.selectWeixinMovingCarDetailList(weixinMovingCarDetail);
	}
	
    /**
     * 新增挪车明细
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 结果
     */
	@Override
	public int insertWeixinMovingCarDetail(WeixinMovingCarDetail weixinMovingCarDetail)
	{
	    return weixinMovingCarDetailMapper.insertWeixinMovingCarDetail(weixinMovingCarDetail);
	}
	
	/**
     * 修改挪车明细
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 结果
     */
	@Override
	public int updateWeixinMovingCarDetail(WeixinMovingCarDetail weixinMovingCarDetail)
	{
	    return weixinMovingCarDetailMapper.updateWeixinMovingCarDetail(weixinMovingCarDetail);
	}

	/**
     * 删除挪车明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeixinMovingCarDetailByIds(String ids)
	{
		return weixinMovingCarDetailMapper.deleteWeixinMovingCarDetailByIds(Convert.toStrArray(ids));
	}
	
}
