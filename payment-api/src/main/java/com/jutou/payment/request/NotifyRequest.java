package com.jutou.payment.request;

import java.io.Serializable;
import java.util.Map;

import com.jutou.payment.enums.PayCategory;

@SuppressWarnings("serial")
public class NotifyRequest implements Serializable{

	
	private Map<String, String> requestMap;
	PayCategory payCategory;
	private String key;
	private Integer payConfigId;
	
	public Map<String, String> getRequestMap() {
		return requestMap;
	}
	public void setRequestMap(Map<String, String> requestMap) {
		this.requestMap = requestMap;
	}
	public PayCategory getPayCategory() {
		return payCategory;
	}
	public void setPayCategory(PayCategory payCategory) {
		this.payCategory = payCategory;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Integer getPayConfigId() {
		return payConfigId;
	}
	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}
	
	
	
}
