package com.jutou.payment.request;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("xml")
public class QueryBankRequest implements Serializable{

	@XStreamAlias("mch_id")
	private String mchId;
	@XStreamAlias("partner_trade_no")
	private String partnerTradeNo;
	@XStreamAlias("nonce_str")
	private String nonceStr;
	@XStreamAlias("sign")
	private String 	sign;
	
	
	private Integer payConfigId;
	
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getPartnerTradeNo() {
		return partnerTradeNo;
	}
	public void setPartnerTradeNo(String partnerTradeNo) {
		this.partnerTradeNo = partnerTradeNo;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
	
}
