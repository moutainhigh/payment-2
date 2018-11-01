package com.jutou.payment.enums;

public enum TradeType {

	JSAPI("JSAPI"),//公众号支付
	NATIVE("NATIVE"),//扫码支付
	APP("APP");//APP支付
	
	private String value;

	private TradeType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
