package com.jutou.payment.wechatpay.vo;

import java.io.Serializable;

/**
 * @Title: UnifiedOrder.java
 * @Description: 统一下单请求参数
 * @author  Liu.jg    
 * @date 2015年10月8日 下午4:04:55
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
@SuppressWarnings("serial")
public class UnifiedOrder implements Serializable{
	
	//公众账号ID:微信分配的公众账号ID（企业号corpid即为此appId）
	private String appid;
	
	//商户号:微信支付分配的商户号
	private String mch_id;
	
	//随机字符串:不长于32位
	private String nonce_str;
	
	//签名:
	private String sign;
	
	//商品描述:商品或支付单简要描述
	private String body;
	
	//商户订单号:商户系统内部的订单号,32个字符内、可包含字母
	private String out_trade_no;
	
	//总金额:订单总金额，只能为整数，单位为【分】
	private int total_fee;
	
	//终端IP:APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP
	private String spbill_create_ip;
	
	//通知地址:接收微信支付异步通知回调地址
	private String notify_url;
	
	//交易类型:取值如下：JSAPI，NATIVE，APP，WAP
	private String trade_type;

	//指定支付方式:no_credit--指定不能使用信用卡支付
	private String limit_pay;
	
	//用户标识:trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识
	private String openid;

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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}

	public String getLimit_pay() {
		return limit_pay;
	}

	public void setLimit_pay(String limit_pay) {
		this.limit_pay = limit_pay;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
}
