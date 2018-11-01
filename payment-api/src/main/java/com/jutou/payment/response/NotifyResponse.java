package com.jutou.payment.response;

import java.io.Serializable;

/**
 * @Title: TradeResponse.java
 * @Description: 回调响应
 * @author Liu.jg  
 * @date 2015年9月24日 下午8:08:29
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
@SuppressWarnings("serial")
public class NotifyResponse extends Response implements Serializable{
	
	//交易ID
	private String tradeId;
	//商户自定义订单号***yyyyMMdd***
	private String orderId;

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
}
