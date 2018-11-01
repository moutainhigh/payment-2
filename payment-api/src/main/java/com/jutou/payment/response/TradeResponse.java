package com.jutou.payment.response;

import java.io.Serializable;

/**
 * @Title: TradeResponse.java
 * @Description: 交易响应
 * @author Liu.jg  
 * @date 2015年9月24日 下午8:08:29
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public class TradeResponse extends Response implements Serializable{
	
	private static final long serialVersionUID = -3926915635578628645L;
	
	//交易ID
	private String tradeId;
	// alipay private key
	private String apk;
	//商户自定义订单号***yyyyMMdd***
	private String orderId;

	private String codeUrl;
	
	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getApk() {
		return apk;
	}

	public void setApk(String apk) {
		this.apk = apk;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCodeUrl() {
		return codeUrl;
	}

	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}
	
	
}
