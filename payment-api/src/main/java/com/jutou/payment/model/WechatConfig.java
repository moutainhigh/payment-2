package com.jutou.payment.model;
/**
 * @ClassName:  WechatpayConfig   
 * @Description:微信支付配置   
 * @author: Liu.jg 
 * @date:   2018年10月25日 上午9:42:33   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public class WechatConfig {
	
	//应用ID，微信分配的公众账号ID（企业号corpid即为此appId） 
	private String appId;
	
	//应用密钥
	private String appSecret;
	
	//微信支付分配的商户号
	private String mchId;
	
	//商户密钥
	private String key;
	//
	private String keypath;
	
	//终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	private String deviceInfo;
	
	//商品或支付单简要描述
	private String body;
	
	//符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String feeType;
	
	//接收微信支付异步通知回调地址
	private String notifyUrl;
	
	//签名方式：签名算法，暂支持MD5
	private String signType;
	
	//取值如下：JSAPI，NATIVE，APP，WAP
	private String tradeType;
	
	//no_credit--指定不能使用信用卡支付
	private String limitType;
	
	//统一下单接口地址
	private String unifiedorder;
	
	//查询订单接口地址
	private String orderquery;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDeviceInfo() {
		return deviceInfo;
	}

	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public String getUnifiedorder() {
		return unifiedorder;
	}

	public void setUnifiedorder(String unifiedorder) {
		this.unifiedorder = unifiedorder;
	}

	public String getOrderquery() {
		return orderquery;
	}

	public void setOrderquery(String orderquery) {
		this.orderquery = orderquery;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getKeypath() {
		return keypath;
	}

	public void setKeypath(String keypath) {
		this.keypath = keypath;
	}
	
	
}
