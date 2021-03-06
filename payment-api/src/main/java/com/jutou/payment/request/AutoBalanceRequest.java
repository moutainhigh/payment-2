package com.jutou.payment.request;

import java.io.Serializable;

import com.jutou.payment.enums.PayCategory;

@SuppressWarnings("serial")
public class AutoBalanceRequest implements Serializable{

	
	private PayCategory payCategory;
	
	private Integer payConfigId;

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
