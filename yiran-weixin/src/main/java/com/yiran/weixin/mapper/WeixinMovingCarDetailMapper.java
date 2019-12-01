package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinMovingCarDetail;
import java.util.List;	

/**
 * 挪车明细 数据层
 * 
 * @author yiran
 * @date 2019-11-20
 */
public interface WeixinMovingCarDetailMapper 
{
	/**
     * 查询挪车明细信息
     * 
     * @param id 挪车明细ID
     * @return 挪车明细信息
     */
	public WeixinMovingCarDetail selectWeixinMovingCarDetailById(Integer id);
	
	/**
     * 查询挪车明细列表
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 挪车明细集合
     */
	public List<WeixinMovingCarDetail> selectWeixinMovingCarDetailList(WeixinMovingCarDetail weixinMovingCarDetail);
	
	/**
     * 新增挪车明细
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 结果
     */
	public int insertWeixinMovingCarDetail(WeixinMovingCarDetail weixinMovingCarDetail);
	
	/**
     * 修改挪车明细
     * 
     * @param weixinMovingCarDetail 挪车明细信息
     * @return 结果
     */
	public int updateWeixinMovingCarDetail(WeixinMovingCarDetail weixinMovingCarDetail);
	
	/**
     * 删除挪车明细
     * 
     * @param id 挪车明细ID
     * @return 结果
     */
	public int deleteWeixinMovingCarDetailById(Integer id);
	
	/**
     * 批量删除挪车明细
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinMovingCarDetailByIds(String[] ids);
	
}