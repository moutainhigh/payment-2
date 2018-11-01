package com.jutou.payment.request;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@SuppressWarnings("serial")
@XStreamAlias("xml")
public class PayBankRequest implements Serializable{

	@XStreamAlias("mch_id")
	private String mchId;//微信支付分配的商户号
	@XStreamAlias("partner_trade_no")
	private String partnerTradeNo;//商户订单号
	@XStreamAlias("nonce_str")
	private String nonceStr;//随机字符串，不长于32位
	@XStreamAlias("sign")
	private String sign;//通过MD5签名算法计算得出的签名值
	@XStreamAlias("enc_bank_no")
	private String encBankNo;//收款方银行卡号
	@XStreamAlias("enc_true_name")
	private String encTrueName;//收款方用户名
	@XStreamAlias("bank_code")
	private String bankCode;//银行卡所在开户行编号
	@XStreamAlias("amount")
	private String amount;//付款金额：RMB分
	@XStreamAlias("desc")
	private String desc;//企业付款到银行卡付款说明,即订单备注
	
	
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
	public String getEncBankNo() {
		return encBankNo;
	}
	public void setEncBankNo(String encBankNo) {
		this.encBankNo = encBankNo;
	}
	public String getEncTrueName() {
		return encTrueName;
	}
	public void setEncTrueName(String encTrueName) {
		this.encTrueName = encTrueName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
}
