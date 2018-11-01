package com.jutou.payment.enums;
/**
 * @Title: PayCategory.java
 * @Description: 加密方式
 * @author Liu.jg  
 * @date 2014年9月16日 下午5:56:42
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2014
 */
public enum SignType {
	
	MD5("MD5"), 
	RSA("RSA"), 
	DSA("DSA");
	
	private String value;

	private SignType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
