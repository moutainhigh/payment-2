package com.jutou.payment.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jutou.payment.model.PayTrack;

@Repository
public interface PayTrackMapper {
	
	PayTrack load(String orderId);
	
	PayTrack loadByTradeId(String tradeId);
	
	int add(PayTrack payTrack);
	
	int merge(PayTrack payTrack);
	
	List<PayTrack> search(PayTrack payTrack);
	
	/**
	 * @Description: 设置交易超时
	 * @param hours 超时小时数
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年10月28日 下午5:56:43
	 */
	int mergeExpired(Long hours);
}