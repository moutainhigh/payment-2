package com.jutou.payment.enums;

/**
 * @Title: PayCategory.java
 * @Description: 响应结果状态
 * @author Liu.jg  
 * @date 2015年9月24日 下午5:56:42
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public enum ResponseResult {

	FAIL("failed"),
	SUCCESS("success");
	
	private String value;

	private ResponseResult(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
