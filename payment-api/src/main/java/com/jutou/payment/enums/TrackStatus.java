package com.jutou.payment.enums;

/**
 * @Title: PayCategory.java
 * @Description: 交易流水状态
 * @author Liu.jg 
 * @date 2014年9月16日 下午5:56:42
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2014
 */
public enum TrackStatus {

	/** WP:待支付；FD：已完成；SD：成功；TO：超时；NB：余额不足*/
	WAITPAY("WP"),
	FINISHED("FD"),
	SUCCESSED("SD"),
	TIMEOUT("TO"),
	NOBALANCE("NB"),
	
	/**RS:退款成功，RF:退款失败**/
	REFUND_SUCCESS("RS"),
	REFUND_FAIL("RF");
	
	private String value;

	private TrackStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
