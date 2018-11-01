package com.jutou.payment.alipay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jutou.payment.Payment;
import com.jutou.payment.alipay.util.AlipayCore;
import com.jutou.payment.alipay.util.AlipayNotify;
import com.jutou.payment.alipay.util.AlipaySubmit;
import com.jutou.payment.alipay.util.CheckUrl;
import com.jutou.payment.enums.ResponseResult;
import com.jutou.payment.enums.TrackStatus;
import com.jutou.payment.enums.TradeStatus;
import com.jutou.payment.mapper.AliConfigMapper;
import com.jutou.payment.model.AliConfig;
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
/**
 * @Title: Alipay.java
 * @Description: 支付宝接口操作
 * @author  Liu.jg   
 * @date 2014年9月16日 下午6:09:36
 * @version V1.0  
 * @Company: Ttiao.cn
 * @Copyright Copyright (c) 2014
 */
@Component("alipayService")
public class Alipay implements Payment{
	
    @Autowired
    private AliConfigMapper aliConfigMapper;
	@Autowired
	private AlipaySubmit submit;
	@Autowired
	private AlipayNotify notify;
	
	public PayResponse pay(PayRequest payRequest){
		PayResponse response = new PayResponse();
		response.setResult(ResponseResult.SUCCESS.getValue());
		String form = buildForm(payRequest);
		response.setForm(form);
		response.setTrackId(payRequest.getTradeId());
		return response;
	}
	
	private String buildForm(PayRequest payRequest){
		AliConfig config=aliConfigMapper.get(payRequest.getPayConfigId());
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", payRequest.getServices().getService());
		sParaTemp.put("partner", config.getPartner());
		sParaTemp.put("_input_charset", config.getInputCharset());
		sParaTemp.put("payment_type", config.getPaymentType());
		sParaTemp.put("notify_url", config.getNotifyUrl());
		sParaTemp.put("return_url", config.getReturnUrl());
		if(!StringUtils.isEmpty(config.getSellerId())){
			sParaTemp.put("seller_id", config.getSellerId());
		}else{
			sParaTemp.put("seller_email", config.getSellerEmail());
		}
		sParaTemp.put("out_trade_no", payRequest.getOrderId());
		sParaTemp.put("subject", payRequest.getProductName());
		sParaTemp.put("total_fee", String.valueOf(payRequest.getOrderMoney()));
		sParaTemp.put("body", payRequest.getProductDesc());
		sParaTemp.put("anti_phishing_key", "");
		sParaTemp.put("exter_invoke_ip", "");
		sParaTemp.put("extra_common_param", payRequest.getExtraCommonParam());
		return submit.buildRequest(sParaTemp, config.getMethod(), "",config);
	}
	
	public boolean verify(VerifyRequest request){
		AliConfig config=aliConfigMapper.get(request.getPayConfigId());
		return notify.verify(request.getParams(),config);
	}
	
	@Override
	public TradeResponse trade(TradeRequest request) {
		TradeResponse response = new TradeResponse();
		if(request != null && !StringUtils.isEmpty(request.getOrderId())){
			AliConfig config=aliConfigMapper.get(request.getPayConfigId());
			response.setResult(ResponseResult.SUCCESS.getValue());
			response.setTradeId(request.getOrderId() + request.getGmtTrade());
			response.setOrderId(response.getTradeId());
			response.setApk(config.getPartnerPrivKey());
		}else{
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg("OrderId Is Null");
		}
		return response;
	}
	
	
	
	public TradeQueryResponse query(TradeQueryRequest request) {
		AliConfig config=aliConfigMapper.get(request.getPayConfigId());
		TradeQueryResponse response = new TradeQueryResponse();
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", config.getQuery());
		sParaTemp.put("partner", config.getPartner());
		sParaTemp.put("out_trade_no", request.getTradeId());
		sParaTemp.put("_input_charset", config.getInputCharset());
		Map<String, String> sPara = submit.buildRequestPara(sParaTemp,config);
		String url = config.getGateway() + AlipayCore.createLinkString(sPara);
		Map<String, String> resp = new HashMap<String, String>();
		boolean validResp = AlipayCore.verifyResponse(CheckUrl.check(url), resp);
		response.setRespMap(resp);
		if(validResp){
			response.setResult(ResponseResult.SUCCESS.getValue());
			if(TradeStatus.TRADE_SUCCESS.getValue().equalsIgnoreCase(resp.get("trade_status")) ||
					TradeStatus.TRADE_FINISHED.getValue().equalsIgnoreCase(resp.get("trade_status"))){
				response.setTrackStatus(TrackStatus.FINISHED);
			}else{
				response.setTrackStatus(TrackStatus.WAITPAY);
			}
		}else{
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg(resp.get("error"));
		}
		return response;
	}
	
	public NotifyResponse notify(Map<String, String> params) {
		NotifyResponse response =new NotifyResponse();
		// 商户订单号
		String outTradeNo = params.get("out_trade_no");
		// 支付宝订单号
		String tradeNo = params.get("trade_no");
		// 交易状态
		String tradeStatus = params.get("trade_status");
		if (TradeStatus.TRADE_FINISHED.getValue().equals(tradeStatus) || 
				TradeStatus.TRADE_SUCCESS.getValue().equals(tradeStatus)) {
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

	public RefundResponse Refund(RefundRequest request) {
		
		return null;
	}

	@Override
	public PayResponse qrocdepay(PayRequest request) {
		
		return null;
	}

	@Override
	public RefundQueryResponse refundquery(RefundQueryRequest request) {
		
		return null;
	}


}
