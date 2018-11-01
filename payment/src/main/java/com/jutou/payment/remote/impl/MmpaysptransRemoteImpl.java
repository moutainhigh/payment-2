package com.jutou.payment.remote.impl;

import java.util.Map;

import org.cloudframework.config.PropertyConfigurer;
import org.cloudframework.core.utils.RandomSecurityCode;
import org.cloudframework.core.utils.RandomSecurityCode.SecurityCodeLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutou.payment.mapper.WechatConfigMapper;
import com.jutou.payment.model.WechatConfig;
import com.jutou.payment.remote.MmpaysptransRemote;
import com.jutou.payment.request.PayBankRequest;
import com.jutou.payment.request.PublickeyRequest;
import com.jutou.payment.request.QueryBankRequest;
import com.jutou.payment.response.PayBankResponse;
import com.jutou.payment.response.PublickeyResponse;
import com.jutou.payment.response.QueryBankResponse;
import com.jutou.payment.wechatpay.util.HttpClientSSL;
import com.jutou.payment.wechatpay.util.MessageUtil;
import com.jutou.payment.wechatpay.util.WechatUtil;

@Service
public class MmpaysptransRemoteImpl implements MmpaysptransRemote{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatConfigMapper wechatConfigMapper;
	
	@Override
	public PayBankResponse payBank(PayBankRequest payBankRequest) {
		PayBankResponse response=null;
		WechatConfig config=wechatConfigMapper.get(payBankRequest.getPayConfigId());
		String paybankUrl = PropertyConfigurer.getString("wechat.mmpaysptrans.paybank.url");
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String appsecret = config.getKey();
		payBankRequest.setMchId(config.getMchId());
		payBankRequest.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		String signature = WechatUtil.generateSignature(payBankRequest, appsecret);
		payBankRequest.setSign(signature);
		String content=MessageUtil.beanToXml(payBankRequest, PayBankRequest.class);
		logger.debug("payBank Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, paybankUrl, content);
		logger.debug("Send RedPack Response:" + map);
		if(map != null){
			response=new PayBankResponse();
			WechatUtil.transMap2Bean2(map, response);
		}
		return response;
	}

	
	@Override
	public QueryBankResponse queryBank(QueryBankRequest queryBankRequest) {
		QueryBankResponse response=null;
		WechatConfig config=wechatConfigMapper.get(queryBankRequest.getPayConfigId());
		String querybankUrl = PropertyConfigurer.getString("wechat.mmpaysptrans.querybank.url");
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String appsecret = config.getKey();
		queryBankRequest.setMchId(config.getMchId());
		queryBankRequest.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		String signature = WechatUtil.generateSignature(queryBankRequest, appsecret);
		queryBankRequest.setSign(signature);
		String content=MessageUtil.beanToXml(queryBankRequest, QueryBankRequest.class);
		logger.debug("queryBank Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, querybankUrl, content);
		logger.debug("queryBank Response:" + map);
		if(map != null){
			response=new QueryBankResponse();
			WechatUtil.transMap2Bean2(map, response);
		}
		return response;
	}


	@Override
	public PublickeyResponse getPublickey(PublickeyRequest publickeyRequest) {
		PublickeyResponse response=null;
		WechatConfig config=wechatConfigMapper.get(publickeyRequest.getPayConfigId());
		String getpublickeyurl =PropertyConfigurer.getString("wechat.risk.getpublickey.url");
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String appsecret = config.getKey();
		publickeyRequest.setMchId(config.getMchId());
		publickeyRequest.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		publickeyRequest.setSignType("MD5");
		String signature = WechatUtil.generateSignature(publickeyRequest, appsecret);
		publickeyRequest.setSign(signature);
		String content=MessageUtil.beanToXml(publickeyRequest, PublickeyRequest.class);
		logger.debug("getPublickey Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, getpublickeyurl, content);
		logger.debug("getPublickey Response:" + map);
		if(map != null){
			response=new PublickeyResponse();
			WechatUtil.transMap2Bean2(map, response);
		}
		return response;
	}
}
