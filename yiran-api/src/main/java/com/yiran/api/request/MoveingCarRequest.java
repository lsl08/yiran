package com.yiran.api.request;
/**
 * 扫码挪车请求参数
 * @author pandaa
 *
 */
public class MoveingCarRequest {
	/**
	 * 微信openId
	 */
	private String openId;
	/**
	 * 车牌
	 */
	private String licensePlate;
	/**
	 * 手机号
	 */
	private String phoneNumber;
	/**
	 * 验证码
	 */
	private String verificationCode;
	
	/**
	 * 短信发送流水号
	 */
	private String msgOrderNo;
	
	public String getMsgOrderNo() {
		return msgOrderNo;
	}
	public void setMsgOrderNo(String msgOrderNo) {
		this.msgOrderNo = msgOrderNo;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getVerificationCode() {
		return verificationCode;
	}
	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}
	@Override
	public String toString() {
		return "MoveingCarRequest [openId=" + openId + ", licensePlate=" + licensePlate + ", phoneNumber=" + phoneNumber
				+ ", verificationCode=" + verificationCode + ", msgOrderNo=" + msgOrderNo + "]";
	}
	
	
	
	

}
