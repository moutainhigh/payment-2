package com.jutou.payment.remote.impl;

import java.util.Map;

import org.cloudframework.config.PropertyConfigurer;
import org.cloudframework.core.utils.LocalAddressUtils;
import org.cloudframework.core.utils.RandomSecurityCode;
import org.cloudframework.core.utils.RandomSecurityCode.SecurityCodeLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutou.payment.mapper.WechatConfigMapper;
import com.jutou.payment.model.WechatConfig;
import com.jutou.payment.remote.RedPackRemote;
import com.jutou.payment.request.RedPackQueryRequest;
import com.jutou.payment.request.RedPackSendRequest;
import com.jutou.payment.response.RedPackQueryResponse;
import com.jutou.payment.response.RedPackSendResponse;
import com.jutou.payment.wechatpay.util.HttpClientSSL;
import com.jutou.payment.wechatpay.util.MessageUtil;
import com.jutou.payment.wechatpay.util.WechatUtil;

/**
 * @Title: RedPackRemoteImpl.java
 * @Description:
 * @author Liu.jg 
 * @date 2016年6月30日 下午2:09:06
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
@Service
public class RedPackRemoteImpl implements RedPackRemote {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private WechatConfigMapper wechatConfigMapper;
	
	@Override
	public RedPackSendResponse sendRedPack(RedPackSendRequest request) {
		RedPackSendResponse redPackResponse = null;
		WechatConfig config=wechatConfigMapper.get(request.getPayConfigId());
		String sendredpackUrl = PropertyConfigurer.getString("wechat.mmpaymkttransfers.sendredpack.url");
		String sendName =request.getSendName();
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String appsecret = config.getKey();
		request.setAppId(config.getAppId());
		request.setActName(sendName);
		request.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		request.setClientIp(LocalAddressUtils.getLocalHost());
		String signature = WechatUtil.generateSignature(request, appsecret);
		request.setSign(signature);
		String content = MessageUtil.beanToXml(request, RedPackSendRequest.class);
		logger.debug("Send RedPack Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, sendredpackUrl, content);
		logger.debug("Send RedPack Response:" + map);
		if(map != null){
			redPackResponse = new RedPackSendResponse();
			WechatUtil.transMap2Bean2(map, redPackResponse);
			redPackResponse.setRedPackSendRequest(request);
		}
		return redPackResponse;
	}
	
	@Override
	public RedPackQueryResponse getHBInfo(RedPackQueryRequest request) {
		RedPackQueryResponse redPackResponse = null;
		WechatConfig config=wechatConfigMapper.get(request.getPayConfigId());
		String gethbinfoUrl = PropertyConfigurer.getString("wechat.mmpaymkttransfers.gethbinfo.url");
		String keypass =config.getMchId();
		String keyPath =config.getKeypath();
		String appsecret = config.getKey();
		request.setAppId(config.getAppId());
		request.setNonceStr(new String(RandomSecurityCode.getSecurityCode(32, SecurityCodeLevel.Hard, false)));
		String signature = WechatUtil.generateSignature(request, appsecret);
		request.setSign(signature);
		String content = MessageUtil.beanToXml(request, RedPackQueryRequest.class);
		logger.debug("Query RedPack Request:" + content);
		Map<String, String> map = HttpClientSSL.post(keypass, keyPath, gethbinfoUrl, content);
		logger.debug("Query RedPack Response:" + map);
		if(map != null){
			redPackResponse = new RedPackQueryResponse();
			WechatUtil.transMap2Bean2(map, redPackResponse);
		}
		return redPackResponse;
	}
}
