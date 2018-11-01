package com.jutou.payment.enums;

/**
 * @Title: TradeStatus.java
 * @Description: 交易状态
 * @author Liu.jg  
 * @date 2015年9月16日 下午5:56:42
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
public enum TradeStatus {

	TRADE_FINISHED("TRADE_FINISHED"),
	TRADE_SUCCESS("TRADE_SUCCESS"),
	RETURN_CODE_SUCCESS("SUCCESS"),
	RETURN_CODE_FAIL("FAIL"),
	RETURN_MSG_OK("OK");
	
	private String value;

	private TradeStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
