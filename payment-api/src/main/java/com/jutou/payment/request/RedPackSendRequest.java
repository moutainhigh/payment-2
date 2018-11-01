package com.jutou.payment.request;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @ClassName:  RedPackSendRequest   
 * @Description:
 * @author: Liu.jg 
 * @date:   2018年3月9日 下午2:26:10   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
@SuppressWarnings("serial")
@XStreamAlias("xml")
public class RedPackSendRequest implements Serializable {

	@XStreamAlias("mch_billno")
	private String mchBillno;// 商户订单号

	@XStreamAlias("mch_id")
	private String mchId; // 商户号

	// 接受红包的用户用户在wxappid下的openid
	@XStreamAlias("re_openid")
	private String openId;

	@XStreamAlias("send_name")
	private String sendName;// 红包发送者名称

	@XStreamAlias("total_amount")
	private int totalAmount;// 红包金额，单位：分

	@XStreamAlias("total_num")
	private int totalNum = 1;// 红包发送总人数

	@XStreamAlias("wishing")
	private String wishing;// 红包祝福语

	@XStreamAlias("act_name")
	private String actName;// 活动名称

	@XStreamAlias("remark")
	private String remark;// 备注

	@XStreamAlias("wxappid")
	private String appId;// 公众账号appid

	@XStreamAlias("nonce_str")
	private String nonceStr;// 随机字符串

	@XStreamAlias("sign")
	private String sign;// 签名

	@XStreamAlias("client_ip")
	private String clientIp;// 调用接口的机器Ip地址

	private Integer payConfigId;
	
	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getSendName() {
		return sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public String getWishing() {
		return wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMchBillno() {
		return mchBillno;
	}

	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	
	public Integer getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}

	public String toSignOfSendRedPack() {
		StringBuilder sb = new StringBuilder("");

		if (null != getActName() && !"".equals(getActName())) {
			sb.append("&act_name=" + getActName());
		}
		if (null != getClientIp() && !"".equals(getClientIp())) {
			sb.append("&client_ip=" + getClientIp());
		}
		if (null != getMchBillno() && !"".equals(getMchBillno())) {

			sb.append("&mch_billno=" + getMchBillno());
		}
		if (null != getMchId() && !"".equals(getMchId())) {
			sb.append("&mch_id=" + getMchId());

		}
		if (null != getNonceStr() && !"".equals(getNonceStr())) {

			sb.append("&nonce_str=" + getNonceStr());
		}
		if (null != getOpenId() && !"".equals(getOpenId())) {

			sb.append("&re_openid=" + getOpenId());
		}
		if (null != getRemark() && !"".equals(getRemark())) {

			sb.append("&remark=" + getRemark());
		}
		if (null != getSendName() && !"".equals(getSendName())) {

			sb.append("&send_name=" + getSendName());
		}
		sb.append("&total_amount=" + getTotalAmount());
		sb.append("&total_num=" + getTotalNum());
		if (null != getWishing() && !"".equals(getWishing())) {

			sb.append("&wishing=" + getWishing());
		}
		sb.append("&wxappid=" + getAppId());
		return sb.toString().substring(1);
	}
}
