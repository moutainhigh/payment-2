package com.jutou.payment.wechatpay;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.cloudframework.core.utils.HttpUtils;
import org.cloudframework.core.utils.JacksonUtils;
import org.cloudframework.core.utils.Logs;
import org.cloudframework.core.utils.PropertyConfig;
import org.cloudframework.core.utils.RandomSecurityCode;
import org.cloudframework.core.utils.RandomSecurityCode.SecurityCodeLevel;
import org.cloudframework.core.utils.TemplateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jutou.payment.Payment;
import com.jutou.payment.enums.ResponseResult;
import com.jutou.payment.enums.Template;
import com.jutou.payment.enums.TrackStatus;
import com.jutou.payment.enums.TradeStatus;
import com.jutou.payment.enums.TradeType;
import com.jutou.payment.mapper.WechatConfigMapper;
import com.jutou.payment.model.WechatConfig;
import com.jutou.payment.request.PayRequest;
import com.jutou.payment.request.RefundQueryRequest;
import com.jutou.payment.request.RefundRequest;
import com.jutou.payment.request.TradeQueryRequest;
import com.jutou.payment.request.TradeRequest;
import com.jutou.payment.request.VerifyRequest;
import com.jutou.payment.response.NotifyResponse;
import com.jutou.payment.response.PayResponse;
import com.jutou.payment.response.RefundQueryResponse;
import com.jutou.payment.response.RefundResponse;
import com.jutou.payment.response.TradeQueryResponse;
import com.jutou.payment.response.TradeResponse;
import com.jutou.payment.wechatpay.util.HttpClientSSL;
import com.jutou.payment.wechatpay.util.MessageUtil;
import com.jutou.payment.wechatpay.util.WechatCore;
import com.jutou.payment.wechatpay.util.WechatNotify;
import com.jutou.payment.wechatpay.util.WechatUtil;
import com.jutou.payment.wechatpay.vo.OrderQuery;
import com.jutou.payment.wechatpay.vo.UnifiedOrder;
import com.jutou.payment.wechatpay.vo.WCPayRequest;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;



/**
 * @Title: WechatPay.java
 * @Description: 微信支付
 * @author  Liu.jg    
 * @date 2015年9月24日 下午3:14:24
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
@Service("wxpayService")
public class WechatPay implements Payment {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TemplateUtil templateUtil;
	@Autowired
	private WechatConfigMapper wechatConfigMapper;
	
	@Override
	public PayResponse pay(PayRequest payRequest) {
		return buildRequestJs(payRequest);
	}
	
	
	private PayResponse buildRequestJs(PayRequest payRequest){
		WechatConfig  config=wechatConfigMapper.get(payRequest.getPayConfigId());
		PayResponse payResponse = new PayResponse();
		TradeRequest request = new TradeRequest();
		request.setOrderId(payRequest.getOrderId());
		request.setOrderMoney(payRequest.getOrderMoney());
		request.setProductName(payRequest.getProductName());
		request.setOpenId(payRequest.getOpenId());
		request.setAppId(config.getAppId());
		request.setMchId(config.getMchId());
		request.setNotifyUrl(config.getNotifyUrl());
		request.setKey(config.getKey());
		TradeResponse response = trade(request);
		BeanUtils.copyProperties(response, payResponse);
		if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
			WCPayRequest wcPayRequest = new WCPayRequest();
			wcPayRequest.setAppId(config.getAppId());
			wcPayRequest.setSignType("MD5");
			wcPayRequest.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
			wcPayRequest.setTimeStamp(String.valueOf(new Date().getTime()));
			wcPayRequest.setPackage("prepay_id=" + response.getTradeId());
			wcPayRequest.setPaySign(WechatCore.getSign(wcPayRequest,config.getKey()));
			wcPayRequest.setOrderId(payRequest.getOrderId());
			wcPayRequest.setPayUse(payRequest.getPayUse());
			payResponse.setForm(templateUtil.getTemplateIntoString(Template.GET_BRAND_WC_PAY_REQUEST.getValue(), JacksonUtils.jsonToMap(JacksonUtils.beanToJson(wcPayRequest))));
		}
		return payResponse;
	}
	
	@Override
	public TradeQueryResponse query(TradeQueryRequest request) {
		TradeQueryResponse response = new TradeQueryResponse();
		try {
			WechatConfig  config=wechatConfigMapper.get(request.getPayConfigId());
			String xml = HttpUtils.post("https://api.mch.weixin.qq.com/pay/orderquery", buildOrderQueryRequestXml(request.getTradeId(),config));
			Logs.getLogger().info("Wechat Order Query Response:" + xml);
			Map<String, String> responseMap = MessageUtil.parseXml(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			response.setRespMap(responseMap);
			VerifyRequest vrequest =new VerifyRequest();
			vrequest.setParams(responseMap);
			vrequest.setKey(config.getKey());
			vrequest.setPayConfigId(request.getPayConfigId());
			if(verify(vrequest)){
				response.setResult(ResponseResult.SUCCESS.getValue());
				if(responseMap.get("return_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue()) && 
						responseMap.get("result_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue()) && 
							responseMap.get("trade_state").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue())){
					response.setTrackStatus(TrackStatus.FINISHED);
				}else{
					response.setTrackStatus(TrackStatus.WAITPAY);
					response.setErrMsg(responseMap.get("trade_state_desc"));
				}
			}
		} catch (Exception e) {
			Logs.getLogger().error(e.getMessage(), e);
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg(e.getMessage());
		}
		return response;
	}
	
	@Override
	public TradeResponse trade(TradeRequest request) {
		TradeResponse response = new TradeResponse();
		try {
			if(StringUtils.isEmpty(request.getKey())){
				WechatConfig  config=wechatConfigMapper.get(request.getPayConfigId());
				request.setKey(config.getKey());
				request.setAppId(config.getAppId());
				request.setMchId(config.getMchId());
				request.setNotifyUrl(config.getNotifyUrl());
			}
			String paramsXml=buildUnifiedOrderRequestXml(request);
			Logs.info("统一下单xml:"+paramsXml);
			String xml = HttpUtils.post("https://api.mch.weixin.qq.com/pay/unifiedorder", paramsXml);
			Logs.getLogger().info("Wechat Pay Trade Response:" + xml);
			Map<String, String> responseMap = MessageUtil.parseXml(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			if(responseMap.get("return_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue()) && 
					responseMap.get("result_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue())){
				response.setResult(ResponseResult.SUCCESS.getValue());
				response.setOrderId(request.getOrderId());
				response.setTradeId(responseMap.get("prepay_id"));
				if(TradeType.NATIVE.getValue().equals(request.getTradeType().getValue())){
					response.setCodeUrl(responseMap.get("code_url"));
				}
			}else if(responseMap.get("return_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue()) && 
					responseMap.get("return_msg").equals(TradeStatus.RETURN_MSG_OK.getValue()) &&
					responseMap.get("result_code").equals(TradeStatus.RETURN_CODE_FAIL.getValue())){
				response.setResult(ResponseResult.FAIL.getValue());
				response.setErrId(responseMap.get("err_code"));
				response.setErrMsg(responseMap.get("err_code_des"));
			}else{
				response.setResult(ResponseResult.FAIL.getValue());
				response.setErrMsg(responseMap.get("return_msg"));
			}
		} catch (Exception e) {
			Logs.getLogger().error(e.getMessage(), e);
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg(e.getMessage());
		}
		return response;
	}
	
	/**
	 * @Description: 构建统一下单请求xml
	 * @param request
	 * @return
	 * @author  Liu.jg    
	 * @date 2015年10月8日 下午5:55:09
	 */
	
	private String buildUnifiedOrderRequestXml(TradeRequest request){
		UnifiedOrder order = new UnifiedOrder();
		order.setAppid(request.getAppId());
		order.setMch_id(request.getMchId());
		order.setNonce_str(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		order.setBody(request.getProductName());
		order.setOut_trade_no(request.getOrderId());
		order.setTotal_fee((int)request.getOrderMoney().multiply(new BigDecimal(100)).doubleValue());
		order.setSpbill_create_ip(request.getHost());
		order.setNotify_url(request.getNotifyUrl());
		order.setTrade_type(request.getTradeType().getValue());
		order.setLimit_pay("no_credit");
		order.setOpenid(request.getOpenId());
		order.setSign(WechatCore.getSign(order,request.getKey()));
		//解决XStream对出现双下划线的bug
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		xstream.alias("xml", UnifiedOrder.class);
		return xstream.toXML(order);
	}
	
	/**
	 * @Description: 构建订单查询请求xml
	 * @param tradeId
	 * @return
	 * @author  Liu.jg    
	 * @date 2015年10月8日 下午7:07:34
	 */
	private String buildOrderQueryRequestXml(String tradeId,WechatConfig config){
		OrderQuery order = new OrderQuery();
		order.setAppid(config.getAppId());
		order.setMch_id(config.getMchId());
		order.setNonce_str(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		order.setOut_trade_no(tradeId);
		order.setSign(WechatCore.getSign(order,config.getKey()));
		//解决XStream对出现双下划线的bug
		XStream xstream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
		xstream.alias("xml", OrderQuery.class);
		return xstream.toXML(order);
	}
	@Override
	public boolean verify(VerifyRequest request) {
		if(StringUtils.isEmpty(request.getKey())){
			WechatConfig  config=wechatConfigMapper.get(request.getPayConfigId());
			request.setKey(config.getKey());
		}
		return WechatNotify.verify(request.getParams(),request.getKey());
	}
	@Override
	public NotifyResponse notify(Map<String, String> params) {
		NotifyResponse response = new NotifyResponse();
		if(params.get("return_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue()) && 
				params.get("result_code").equals(TradeStatus.RETURN_CODE_SUCCESS.getValue())){
			// 商户订单号
			String outTradeNo = params.get("out_trade_no");
			// 微信订单号
			String tradeNo = params.get("transaction_id");
			if (!StringUtils.isEmpty(outTradeNo)) {
				response.setOrderId(outTradeNo);
				response.setTradeId(tradeNo);
				response.setResult(ResponseResult.SUCCESS.getValue());
			}
		}else{
			response.setResult(ResponseResult.FAIL.getValue());
		}
		return response;
	}


	@Override
	public RefundResponse Refund(RefundRequest request) {
		RefundResponse refundResponse = null;
		String refundUrl = PropertyConfig.getString("wechat.mmpaymkttransfers.refund.url",
				"https://api.mch.weixin.qq.com/secapi/pay/refund");
		WechatConfig  config=wechatConfigMapper.get(request.getPayConfigId());
		String appId =config.getAppId();	
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String apiKey =config.getKey();
		request.setMchId(config.getMchId());
		request.setOpUserId(config.getMchId());
		request.setAppId(appId);
		request.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		String signature = WechatUtil.generateSignature(request, apiKey);
		request.setSign(signature);
		String content = MessageUtil.beanToXml(request, RefundRequest.class);
		logger.info("send refund Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, refundUrl, content);
		logger.info("send refund Response:" + map);
		if(map != null){
			refundResponse = new RefundResponse();
			WechatUtil.transMap2Bean2(map, refundResponse);
		}
		return refundResponse;
	}

	@Override
	public RefundQueryResponse refundquery(RefundQueryRequest request) {
		RefundQueryResponse refundQueryResponse = null;
		String refundqueryUrl = PropertyConfig.getString("wechat.mmpaymkttransfers.refundquery.url",
				"https://api.mch.weixin.qq.com/pay/refundquery");
		WechatConfig  config=wechatConfigMapper.get(request.getPayConfigId());
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String apiKey =config.getKey();
		request.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		String signature = WechatUtil.generateSignature(request, apiKey);
		request.setSign(signature);
		String content = MessageUtil.beanToXml(request, RefundQueryRequest.class);
		logger.info("query refundquery Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, refundqueryUrl, content);
		logger.info("query refundquery Response:" + map);
		if(map != null){
			refundQueryResponse = new RefundQueryResponse();
			WechatUtil.transMap2Bean2(map, refundQueryResponse);
		}
		return refundQueryResponse;
	}
	
	@Override
	public PayResponse qrocdepay(PayRequest payRequest) {
		WechatConfig  config=wechatConfigMapper.get(payRequest.getPayConfigId());
		PayResponse payResponse = new PayResponse();
		TradeRequest request = new TradeRequest();
		request.setOrderId(payRequest.getOrderId());
		request.setOrderMoney(payRequest.getOrderMoney());
		request.setProductName(payRequest.getProductName());
		request.setOpenId(payRequest.getOpenId());
		request.setAppId(config.getAppId());
		request.setMchId(config.getMchId());
		request.setNotifyUrl(config.getNotifyUrl());
		request.setKey(config.getKey());
		request.setTradeType(TradeType.NATIVE);
		TradeResponse response = trade(request);
		BeanUtils.copyProperties(response, payResponse);
		if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
			payResponse.setResult(ResponseResult.SUCCESS.getValue());
			payResponse.setQrcodeUrl(response.getCodeUrl());
		}else{
			payResponse.setResult(ResponseResult.FAIL.getValue());
			payResponse.setErrMsg(response.getErrMsg());
		}
		return payResponse;
	}


	
	
}
