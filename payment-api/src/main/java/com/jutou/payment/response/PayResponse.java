package com.jutou.payment.response;

import java.io.Serializable;

/**
 * @ClassName:  PayResponse   
 * @Description:支付响应   
 * @author: Liu.jg 
 * @date:   2018年10月24日 上午10:44:25   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public class PayResponse extends Response implements Serializable{

	private static final long serialVersionUID = 2212017051341241351L;
	
	// 支付表单html
	private String form;
	// 商户自定义订单交易ID
	private String trackId;
	//支付二维码
	private String qrcodeUrl;
	
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getTrackId() {
		return trackId;
	}
	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	
	
}
