package com.yiran.api.request;

import java.io.Serializable;
/**
 * 通知车主请求
 * @author pandaa
 *
 */
public class NotifyOwnerRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7329070848746274666L;
	/**
	 * openId
	 */
	private String openId;
	/**
	 * 通知方式
	 */
	private String notifyType;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNotifyType() {
		return notifyType;
	}
	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}
	@Override
	public String toString() {
		return "NotifyOwnerRequest [openId=" + openId + ", notifyType=" + notifyType + "]";
	}

	
}
