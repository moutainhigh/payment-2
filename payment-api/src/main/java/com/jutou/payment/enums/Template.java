package com.jutou.payment.enums;

/**
 * <P>Description： 模板名称定义</P>
 *
 * @author Liu.jg
 * @version 1.0
 */
public enum Template {
	
	GET_BRAND_WC_PAY_REQUEST("getBrandWCPayRequest.flt"),
	GET_QRCODE_URL_PAY("getQrcodeUrlPay.flt");
	private String value;
	
	private Template(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
