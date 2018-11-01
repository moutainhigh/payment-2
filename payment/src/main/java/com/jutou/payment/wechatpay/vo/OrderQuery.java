package com.jutou.payment.wechatpay.vo;

import java.io.Serializable;

/**
 * @Title: OrderQuery.java
 * @Description: 订单查询请求参数
 * @author  Liu.jg  
 * @date 2015年10月8日 下午7:04:12
 * @version V1.0
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
@SuppressWarnings("serial")
public class OrderQuery implements Serializable{
	// 公众账号ID:微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;
	// 商户号:微信支付分配的商户号
	private String mch_id;
	// 随机字符串:不长于32位
	private String nonce_str;
	// 签名:
	private String sign;
	// 商户订单号:商户系统内部的订单号,32个字符内、可包含字母,当没提供transaction_id时需要传这个
	private String out_trade_no;
	// 微信订单号:优先使用 
	private String transaction_id;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
}
