package com.jutou.payment.request;

import java.io.Serializable;

import com.jutou.payment.enums.PayCategory;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("xml")
public class RefundRequest implements Serializable{

	@XStreamAlias("appid")
	private String appId;
	@XStreamAlias("mch_id")
	private String mchId;//商户号
	@XStreamAlias("nonce_str")
	private String nonceStr;//随机字符串
	@XStreamAlias("op_user_id")
	private String opUserId;//操作员,默认为商户号
	@XStreamAlias("out_refund_no")
	private String outRefundNo;//商户系统内部的退款单号
	@XStreamAlias("out_trade_no")
	private String outTradeNo;//商户订单号
	@XStreamAlias("refund_fee")
	private String refundFee;//退款总金额，订单总金额，单位为分
	@XStreamAlias("total_fee")
	private String totalFee;//订单总金额，单位为分，只能为整数
	@XStreamAlias("transaction_id")
	private String transactionId;//商户订单号
	@XStreamAlias("sign")//
	private String sign;
	@XStreamAlias("refund_account")
	private String refundAccount;
	
	
	private PayCategory payCategory;
	
	private Integer payConfigId;//配置id
	
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
	public String getOpUserId() {
		return opUserId;
	}
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
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
	public String getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	public String getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
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
	public String getRefundAccount() {
		return refundAccount;
	}
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}
	public PayCategory getPayCategory() {
		return payCategory;
	}
	public void setPayCategory(PayCategory payCategory) {
		this.payCategory = payCategory;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
}
