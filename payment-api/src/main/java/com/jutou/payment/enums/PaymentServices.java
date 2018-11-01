package com.jutou.payment.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: PaymentServices.java
 * @Description: 支付服务接口定义
 * @author Liu.jg  
 * @date 2015年9月24日 下午1:51:43
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public enum PaymentServices {
	
	// 支付宝服务接口：PC端及时到账支付
	ALIPAY_SERVICE_CREATE_DIRECT_PAY_BY_USER("create_direct_pay_by_user", "ali_pc_direct"), 
	// 支付宝服务接口：WAP版及时到账支付
	ALIPAY_SERVICE_WAP_CREATE_DIRECT_PAY_BY_USER("alipay.wap.create.direct.pay.by.user", "ali_wap_direct");
	
	private String service;
	
	private String alias;

	private static Map<String, PaymentServices> map;
	
	private PaymentServices(String service, String alias) {
		this.service = service;
		this.alias = alias;
		this.put();
	}

	public String getService() {
		return service;
	}
	
	private void put(){
		if(map == null) map = new HashMap<String, PaymentServices>();
		map.put(alias, this);
	}
	
	public static PaymentServices get(String alias){
		return map.get(alias);
	}
}
