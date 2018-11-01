package com.jutou.payment.response;

import java.io.Serializable;

/**
 * @ClassName:  Response   
 * @Description:响应基类  
 * @author: Liu.jg 
 * @date:   2018年10月24日 上午10:43:53   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
@SuppressWarnings("serial")
public class Response implements Serializable{
	
	// 默认值为 Y,"Y"为成功,"N"为失败
	private String result = "Y";
	//错误编码
	private String errId="";    
	// 错误信息
	private String errMsg = "";
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getErrId() {
		return errId;
	}
	public void setErrId(String errId) {
		this.errId = errId;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	} 
	
	
}
