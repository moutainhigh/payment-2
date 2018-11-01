package com.jutou.payment.response;

import java.io.Serializable;

@SuppressWarnings("serial")
public class QueryBankResponse implements Serializable{

	private String return_code;
	private String return_msg;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String mch_id;
	private String partner_trade_no;
	private String payment_no;
	private String bank_no_md5;
	private String true_name_md5;
	private String amount;
	private String status;
	private String cmms_amt;
	private String create_time;
	private String pay_succ_time;
	private String reason;
	
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
	public String getPartner_trade_no() {
		return partner_trade_no;
	}
	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}
	public String getPayment_no() {
		return payment_no;
	}
	public void setPayment_no(String payment_no) {
		this.payment_no = payment_no;
	}
	public String getBank_no_md5() {
		return bank_no_md5;
	}
	public void setBank_no_md5(String bank_no_md5) {
		this.bank_no_md5 = bank_no_md5;
	}
	public String getTrue_name_md5() {
		return true_name_md5;
	}
	public void setTrue_name_md5(String true_name_md5) {
		this.true_name_md5 = true_name_md5;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCmms_amt() {
		return cmms_amt;
	}
	public void setCmms_amt(String cmms_amt) {
		this.cmms_amt = cmms_amt;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getPay_succ_time() {
		return pay_succ_time;
	}
	public void setPay_succ_time(String pay_succ_time) {
		this.pay_succ_time = pay_succ_time;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
