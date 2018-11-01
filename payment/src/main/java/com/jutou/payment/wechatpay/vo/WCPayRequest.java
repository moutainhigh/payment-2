package com.jutou.payment.wechatpay.vo;

import java.io.Serializable;

/**
 * @Title: WCPayRequest.java
 * @Description: 网页支付请求参数
 * @author  Liu.jg   
 * @date 2015年10月8日 下午7:49:08
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
@SuppressWarnings("serial")
public class WCPayRequest implements Serializable{
	
	private String appId;
	// 当前的时间戳
	private String timeStamp;
	// 随机字符串，不长于32位
	private String nonceStr;
	
	// 订单详情扩展字符串:统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
	private String packageStr;
	// 签名算法，暂支持MD5
	private String signType;
	
	private String paySign;
	
	// 商户订单号
	private String orderId;

	private String payUse;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPaySign() {
		return paySign;
	}

	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}

	public String getPackage() {
		return packageStr;
	}

	public void setPackage(String packageStr) {
		this.packageStr = packageStr;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayUse() {
		return payUse;
	}

	public void setPayUse(String payUse) {
		this.payUse = payUse;
	}
	
	
}
