package com.jutou.payment.enums;
/**
 * @Title: ResponseCode.java
 * @Description: 支付响应状态码
 * @author Liu.jg 
 * @date 2015年9月24日 上午10:49:48
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public class ResponseCode {
	
	//百付宝
	public enum BaiFuBaoResponseCode {
		/** 1：交易成功; 2:待支付*/
		SUCCESS("1"),
		WAITING("2");
		private String value;
		private BaiFuBaoResponseCode(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	//银联电子
	public enum ChinaPayResponseCode {
		/** 1001：交易成功*/
		SUCCESS("1001");
		private String value;
		private ChinaPayResponseCode(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
	
	//银联网上
	public enum UnionPayResponseCode {

		/** 00:交易成功结束, 01:处理中, 03:交易处理失败*/
		SUCCESS("00"),
		PROCESSING("01"),
		FAIL("03");
		private String value;
		private UnionPayResponseCode(String value) {
			this.value = value;
		}
		public String getValue() {
			return value;
		}
	}
}
