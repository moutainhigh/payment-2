package com.jutou.payment.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jutou.payment.enums.PayCategory;
import com.jutou.payment.enums.TradeType;

/**
 * @Title: TradeRequest.java
 * @Description: 交易请求参数
 * @author Liu.jg 
 * @date 2015年9月24日 下午8:11:23
 * @version V1.0  
 * @Company:
 * @Copyright Copyright (c) 2015
 */
public class TradeRequest implements Serializable{

	private static final long serialVersionUID = 1205731374514544952L;
	
	private String orderId;// 商户订单号
	private BigDecimal orderMoney;//订单金额:单位元，精确到分，如：99.99
	private BigDecimal payMoney; // 支付金额:单位元，精确到分，如：99.99
	private Integer productCount; // 商品数量
	private String productName;// 商品名称
	private String productCat;// 商品种类
	private String productDesc;// 商品描述
	private PayCategory payCategory; // 第三方支付对象
	private TradeType tradeType;
	private String resultUrl; // 交易结果显示链接
	private String extraCommonParam; //公用回传参数
	
	private String accountId; //第三方分成账号（支付宝：邮箱）
	
	private String host; //用户端IP
	
	private Long userId; // 用户ID
	private String userName; // 用户名
	private String openId; //第三方用户ID
	
	private String payUse;
	private Integer payConfigId;//配置id
	
	private String appId;
	private String mchId;
	private String notifyUrl;
	private String key;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getOrderMoney() {
		return orderMoney;
	}
	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
	public BigDecimal getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCat() {
		return productCat;
	}
	public void setProductCat(String productCat) {
		this.productCat = productCat;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	public String getPayUse() {
		return payUse;
	}
	public void setPayUse(String payUse) {
		this.payUse = payUse;
	}
	// 交易时间
	public String getGmtTrade() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	public PayCategory getPayCategory() {
		return payCategory;
	}
	public void setPayCategory(PayCategory payCategory) {
		this.payCategory = payCategory;
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
	}
	public String getExtraCommonParam() {
		return extraCommonParam;
	}
	public void setExtraCommonParam(String extraCommonParam) {
		this.extraCommonParam = extraCommonParam;
	}
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "{orderId:" + orderId
			  +",orderMoney:" + orderMoney
			  +",productCount:" + productCount
			  +",productName:" + productName
			  +",productDesc:" + productDesc
			  +",extraCommonParam:" + extraCommonParam
			  +",terminal:mobile}";
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public TradeType getTradeType() {
		return tradeType;
	}
	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}
	
	
	
	
}
