package com.jutou.payment.request;

import java.io.Serializable;

import com.jutou.payment.enums.PayCategory;

@SuppressWarnings("serial")
public class TradeQueryRequest implements Serializable{

	private PayCategory payCategory;
	
	private String tradeId;
	
	private Integer payConfigId;
	
	
	public String getTradeId() {
		return tradeId;
	}
	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
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
