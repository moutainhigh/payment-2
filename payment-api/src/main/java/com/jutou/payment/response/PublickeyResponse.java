package com.jutou.payment.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PublickeyResponse implements Serializable{

	private String return_code;
	
	private String  return_msg;
	
	private String result_code;
	
	private String err_code;
	
	private String err_code_des;
	
	private String mch_id;
	
	private String pub_key;

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

	public String getResult_code() {
		return result_code;
	}

	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public String getErr_code() {
		return err_code;
	}

	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}

	public String getErr_code_des() {
		return err_code_des;
	}

	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}

	public String getMch_id() {
		return mch_id;
	}

	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}

	public String getPub_key() {
		return pub_key;
	}

	public void setPub_key(String pub_key) {
		this.pub_key = pub_key;
	}
	
	
	
}
