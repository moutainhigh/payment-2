package com.jutou.payment.request;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
public class PublickeyRequest implements Serializable{

	@XStreamAlias("mch_id")
	private String mchId;
	@XStreamAlias("nonce_str")
	private String nonceStr;
	@XStreamAlias("sign")
	private String sign;
	@XStreamAlias("sign_type")
	private String signType;
	
	private Integer payConfigId;
	
	
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
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
	
}
