package com.jutou.payment.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: PayCategory.java
 * @Description: 支付类型
 * @author Liu.jg 
 * @date 2015年9月24日 上午10:36:25
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public enum PayCategory {
	
	ALIPAY("ali", "alipay"), //支付宝
	WECHAT("wx", "wxpay"), //微信
	TENPAY("ten", "tenpay"), //财付通
	YEEPAY("yee", "yeepay"), //易宝
	UNIONPAY("union", "unionpay"), //银联
	CHINAPAY("china", "chinapay"), //银联电子支付
	BAIFUBAO("bfb", "bfbpay"), //百付宝
	YINSHENG("yinsheng","yingshengpay");//银盛支付
	
	private String value;
	
	private String fullValue;
	
	private static Map<String, PayCategory> map;

	private PayCategory(String value, String fullValue) {
		this.value = value;
		this.fullValue = fullValue;
		this.put();
	}

	public String getValue() {
		return value;
	}
	
	public String getFullValue(){
		return fullValue;
	}
	
	private void put(){
		if(map == null) map = new HashMap<String, PayCategory>();
		map.put(value, this);
	}
	
	public static PayCategory get(String value){
		return map.get(value);
	}
}
