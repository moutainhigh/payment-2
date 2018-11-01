package com.jutou.payment.model;


/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *版本：3.3
 *日期：2012-08-10
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
	
 *提示：如何获取安全校验码和合作身份者ID
 *1.用您的签约支付宝账号登录支付宝网站(www.alipay.com)
 *2.点击“商家服务”(https://b.alipay.com/order/myOrder.htm)
 *3.点击“查询合作者身份(PID)”、“查询安全校验码(Key)”

 *安全校验码查看时，输入支付密码后，页面呈灰色的现象，怎么办？
 *解决方法：
 *1、检查浏览器配置，不让浏览器做弹框屏蔽设置
 *2、更换浏览器或电脑，重新登录查询。
 */
public class AliConfig {
	
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
	// 合作身份者ID，以2088开头由16位纯数字组成的字符串
	private String partner;
	// 收款支付宝账号，以2088开头由16位纯数字组成的字符串
	private String sellerId;
	// 商户的私钥
	private String key;
	// 支付宝消息验证地址 
	private String notifyVerify;
	// 支付宝查询服务地址 
	private String query;
	// 支付宝提供给商户的服务接入网关URL(新)
	private String gateway;
	// 商户的邮箱地址 
	private String sellerEmail;
	// 支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
	private String paymentType;
	// 回调链接 (支付完成后跳转返回的网址URL)
	private String returnUrl;
	// 异步回调链接:通知接收URL(本地测试时，服务器返回无法测试)
	private String notifyUrl;
	// 文件加密机制类型：MD5;RSA;DSA
	private String signType;
	// 编码类型:utf-8;gbk;gb2312
	private String inputCharset;
	// 提交方式。两个值可选：post、get
	private String method;
	// 商户私钥
	private String partnerPrivKey;
	// 支付宝公钥
	private String pubKey;
	//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

	// 调试用，创建TXT日志文件夹路径
	public static String log_path = "D:\\";

	public String getPartner() {
		return partner;
	}

	public String getKey() {
		return key;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public String getSignType() {
		return signType;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public String getNotifyVerify() {
		return notifyVerify;
	}

	public String getGateway() {
		return gateway;
	}

	public String getMethod() {
		return method;
	}

	public String getPartnerPrivKey() {
		return partnerPrivKey;
	}

	public String getPubKey() {
		return pubKey;
	}

	public String getQuery() {
		return query;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setNotifyVerify(String notifyVerify) {
		this.notifyVerify = notifyVerify;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setPartnerPrivKey(String partnerPrivKey) {
		this.partnerPrivKey = partnerPrivKey;
	}

	public void setPubKey(String pubKey) {
		this.pubKey = pubKey;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
}
