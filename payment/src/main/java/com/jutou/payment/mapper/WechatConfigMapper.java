package com.jutou.payment.mapper;

import org.springframework.stereotype.Repository;

import com.jutou.payment.model.WechatConfig;

@Repository
public interface WechatConfigMapper {

	
	WechatConfig get(Integer configId);
}
