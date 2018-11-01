package com.jutou.payment.request;

import java.io.Serializable;

import com.jutou.payment.enums.PayCategory;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("xml")
public class RefundQueryRequest implements Serializable{

	@XStreamAlias("appid")
	private String appId;
	@XStreamAlias("mch_id")
	private String mchId;//商户号
	@XStreamAlias("nonce_str")
	private String nonceStr;//随机字符串
	@XStreamAlias("out_refund_no")
	private String outRefundNo;//商户系统内部的退款单号
	@XStreamAlias("out_trade_no")
	private String outTradeNo;//商户订单号
	@XStreamAlias("refund_id")
	private String refundId;
	@XStreamAlias("transaction_id")
	private String transactionId;//商户订单号
	@XStreamAlias("sign")//
	private String sign;
	
	private PayCategory payCategory;
	private Integer payConfigId;
	
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
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	
	public String getOutRefundNo() {
		return outRefundNo;
	}
	public void setOutRefundNo(String outRefundNo) {
		this.outRefundNo = outRefundNo;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	public PayCategory getPayCategory() {
		return payCategory;
	}
	public void setPayCategory(PayCategory payCategory) {
		this.payCategory = payCategory;
	}
	
	
}
