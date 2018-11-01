package com.jutou.payment.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jutou.payment.enums.PayCategory;
import com.jutou.payment.enums.PaymentServices;


/**
 * @Title: PayRequest.java
 * @Description: 支付请求信息
 * @author Liu.jg 
 * @date 2015年9月24日 下午5:52:13
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public class PayRequest implements Serializable{
	
	private static final long serialVersionUID = -5257847320868159604L;
	
	
	private String orderId;// 商户订单号
	private String tradeId;// 交易流水号（第三方支付机构生成）
	private BigDecimal orderMoney;//订单金额:单位元，精确到分，如：99.99
	private BigDecimal payMoney; // 支付金额:单位元，精确到分，如：99.99
	private BigDecimal couponAmount; //抵用券金额：单位元
	private Integer productCount; // 商品数量
	private String productName;// 商品名称
	private String productCat;// 商品种类
	private String productDesc;// 商品描述
	private PayCategory payCategory; // 第三方支付对象
	private PaymentServices services; //支付服务定义
	private String resultUrl; // 交易结果显示链接
	private String extraCommonParam; //公用回传参数
	
	private String accountId; //第三方分成账号（支付宝：邮箱）
	private Long cpId; //机构ID
	private String grade; //机构等级，银钻：LV01；金钻：LV02；
	private String openId; //第三方用户ID
	
	private String payUse;
	private Integer payConfigId;//支付配置id
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	// 交易时间
	public String getGmtTrade() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}
	public String getResultUrl() {
		return resultUrl;
	}
	public void setResultUrl(String resultUrl) {
		this.resultUrl = resultUrl;
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
	public Long getCpId() {
		return cpId;
	}
	public void setCpId(Long cpId) {
		this.cpId = cpId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	public PayCategory getPayCategory() {
		return payCategory;
	}
	public void setPayCategory(PayCategory payCategory) {
		this.payCategory = payCategory;
	}
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
	public PaymentServices getServices() {
		return services;
	}
	public void setServices(PaymentServices services) {
		this.services = services;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getPayUse() {
		return payUse;
	}
	public void setPayUse(String payUse) {
		this.payUse = payUse;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
}
