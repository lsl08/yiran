package com.yiran.weixin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 微信挪车用户表 sys_weixin_moving_car
 * 
 * @author yiran
 * @date 2019-11-20
 */
public class WeixinMovingCar extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 编号 */
	private Integer id;
	/** 微信openId */
	private String openId;
	/** 昵称 */
	private String nickName;
	/** 车牌 */
	private String licensePlate;
	/** 手机号 */
	private String phoneNumber1;
	/** 备用手机 */
	private String phoneNumber2;
	/** 二维码路径 */
	private String qrCodeUrl;
	/** 扩展字段1 */
	private String extend1;
	/** 扩展字段2 */
	private String extend2;
	/** 扩展字段3 */
	private String extend3;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setOpenId(String openId) 
	{
		this.openId = openId;
	}

	public String getOpenId() 
	{
		return openId;
	}
	public void setNickName(String nickName) 
	{
		this.nickName = nickName;
	}

	public String getNickName() 
	{
		return nickName;
	}
	public void setLicensePlate(String licensePlate) 
	{
		this.licensePlate = licensePlate;
	}

	public String getLicensePlate() 
	{
		return licensePlate;
	}
	public void setPhoneNumber1(String phoneNumber1) 
	{
		this.phoneNumber1 = phoneNumber1;
	}

	public String getPhoneNumber1() 
	{
		return phoneNumber1;
	}
	public void setPhoneNumber2(String phoneNumber2) 
	{
		this.phoneNumber2 = phoneNumber2;
	}

	public String getPhoneNumber2() 
	{
		return phoneNumber2;
	}
	public void setQrCodeUrl(String qrCodeUrl) 
	{
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getQrCodeUrl() 
	{
		return qrCodeUrl;
	}
	public void setExtend1(String extend1) 
	{
		this.extend1 = extend1;
	}

	public String getExtend1() 
	{
		return extend1;
	}
	public void setExtend2(String extend2) 
	{
		this.extend2 = extend2;
	}

	public String getExtend2() 
	{
		return extend2;
	}
	public void setExtend3(String extend3) 
	{
		this.extend3 = extend3;
	}

	public String getExtend3() 
	{
		return extend3;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("openId", getOpenId())
            .append("nickName", getNickName())
            .append("licensePlate", getLicensePlate())
            .append("phoneNumber1", getPhoneNumber1())
            .append("phoneNumber2", getPhoneNumber2())
            .append("qrCodeUrl", getQrCodeUrl())
            .append("extend1", getExtend1())
            .append("extend2", getExtend2())
            .append("extend3", getExtend3())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
