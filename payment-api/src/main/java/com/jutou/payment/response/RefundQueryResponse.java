package com.jutou.payment.response;

import java.io.Serializable;

/**
 * @ClassName:  RefundQueryResponse   
 * @Description:退款查询返回参数   
 * @author: Liu.jg 
 * @date:   2018年10月24日 下午2:03:09   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
@SuppressWarnings("serial")
public class RefundQueryResponse implements Serializable{

	
	
	 private String return_code;
	 private String return_msg;
	 private String result_code;
	 
	 private String err_code;
	 private String err_code_des;
	 
	private String appid;
	private String mch_id;

	private String nonce_str;
	private String sign;
	private String transaction_id;
	
	private String out_trade_no;
	private String out_refund_no;
	private String refund_id_0;
	private String refund_channel_0;
	private String refund_fee_0;
	private String refund_status_0;
	private String refund_account_0;
	private String refund_recv_accout_0;
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
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getRefund_id_0() {
		return refund_id_0;
	}
	public void setRefund_id_0(String refund_id_0) {
		this.refund_id_0 = refund_id_0;
	}
	public String getRefund_channel_0() {
		return refund_channel_0;
	}
	public void setRefund_channel_0(String refund_channel_0) {
		this.refund_channel_0 = refund_channel_0;
	}
	public String getRefund_fee_0() {
		return refund_fee_0;
	}
	public void setRefund_fee_0(String refund_fee_0) {
		this.refund_fee_0 = refund_fee_0;
	}
	public String getRefund_status_0() {
		return refund_status_0;
	}
	public void setRefund_status_0(String refund_status_0) {
		this.refund_status_0 = refund_status_0;
	}
	public String getRefund_account_0() {
		return refund_account_0;
	}
	public void setRefund_account_0(String refund_account_0) {
		this.refund_account_0 = refund_account_0;
	}
	public String getRefund_recv_accout_0() {
		return refund_recv_accout_0;
	}
	public void setRefund_recv_accout_0(String refund_recv_accout_0) {
		this.refund_recv_accout_0 = refund_recv_accout_0;
	}
	
	
	
	
	
	
	
}
