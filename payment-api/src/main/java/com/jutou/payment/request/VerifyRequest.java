package com.jutou.payment.request;

import java.io.Serializable;
import java.util.Map;

import com.jutou.payment.enums.PayCategory;

@SuppressWarnings("serial")
public class VerifyRequest implements Serializable {

	private PayCategory payCategory;
	
	private Integer payConfigId;//配置id
	
	private Map<String, String> params;//需要验证的参数
	
	private String key;

	
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

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	
	
}
