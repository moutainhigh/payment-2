package com.jutou.payment.response;

import java.io.Serializable;

import com.jutou.payment.request.RedPackSendRequest;


/**
 * @ClassName:  RedPackSendResponse   
 * @Description: 
 * @author: Liu.jg 
 * @date:   2018年10月24日 下午2:02:32   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
@SuppressWarnings("serial")
public class RedPackSendResponse implements Serializable {
	
	/**红包发送返回参数**/
	private String return_code;
	private String return_msg;
	private String result_code;
	private String err_code;
	private String err_code_des;
	private String mch_billno;
	private String mch_id;
	private String wxappid;
	private String re_openid;
	private String total_amount;
	private String send_listid;
	private String send_time;
	
	//保存请求参数，便于下次发送
	private RedPackSendRequest redPackSendRequest;
	
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
	public String getMch_billno() {
		return mch_billno;
	}
	public void setMch_billno(String mch_billno) {
		this.mch_billno = mch_billno;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getWxappid() {
		return wxappid;
	}
	public void setWxappid(String wxappid) {
		this.wxappid = wxappid;
	}
	public String getRe_openid() {
		return re_openid;
	}
	public void setRe_openid(String re_openid) {
		this.re_openid = re_openid;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getSend_listid() {
		return send_listid;
	}
	public void setSend_listid(String send_listid) {
		this.send_listid = send_listid;
	}
	public String getSend_time() {
		return send_time;
	}
	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}
	public RedPackSendRequest getRedPackSendRequest() {
		return redPackSendRequest;
	}
	public void setRedPackSendRequest(RedPackSendRequest redPackSendRequest) {
		this.redPackSendRequest = redPackSendRequest;
	}
}
