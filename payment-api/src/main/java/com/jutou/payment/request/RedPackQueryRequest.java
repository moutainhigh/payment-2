package com.jutou.payment.request;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @ClassName:  RedPackQueryRequest   
 * @Description:   
 * @author: Liu.jg 
 * @date:   2018年3月9日 下午2:25:57   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
@SuppressWarnings("serial")
@XStreamAlias("xml")
public class RedPackQueryRequest implements Serializable {

	@XStreamAlias("mch_billno")
	private String mchBillno;// 商户订单号

	@XStreamAlias("mch_id")
	private String mchId; // 商户号

	@XStreamAlias("bill_type")
	private String billType = "MCHT";// MCHT:通过商户订单号获取红包信息

	@XStreamAlias("appid")
	private String appId;// 公众账号appid

	@XStreamAlias("nonce_str")
	private String nonceStr;// 随机字符串

	@XStreamAlias("sign")
	private String sign;// 签名

	private Integer payConfigId;
	
	public String getMchBillno() {
		return mchBillno;
	}

	public void setMchBillno(String mchBillno) {
		this.mchBillno = mchBillno;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
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

	
	public Integer getPayConfigId() {
		return payConfigId;
	}

	public void setPayConfigId(Integer payConfigId) {
		this.payConfigId = payConfigId;
	}

	public String toSignOfQueryRedPack() {
		StringBuilder sb = new StringBuilder("");

		if (null != getBillType() && !"".equals(getBillType())) {
			sb.append("&bill_type=" + getBillType());
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
		sb.append("&appid=" + getAppId());
		return sb.toString().substring(1);
	}
}
