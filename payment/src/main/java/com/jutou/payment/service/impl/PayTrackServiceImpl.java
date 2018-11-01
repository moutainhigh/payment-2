package com.jutou.payment.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jutou.payment.enums.TrackStatus;
import com.jutou.payment.mapper.PayTrackMapper;
import com.jutou.payment.model.PayTrack;
import com.jutou.payment.service.PayTrackService;


/**
 * @Title: PayTrackRemoteImpl.java
 * @Description:
 * @author Liu.jg  
 * @date 2015年9月24日 下午3:02:23
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
@Service
@Transactional("transactionManager")
public class PayTrackServiceImpl implements PayTrackService {
	
	@Autowired
	private PayTrackMapper payTrackMapper;
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void log(PayTrack payTrack) {
		if(payTrack != null && !StringUtils.isEmpty(payTrack.getStatus())){
			PayTrack old = payTrackMapper.load(payTrack.getOrderId());
			// 待支付
			if(TrackStatus.WAITPAY.getValue().equals(payTrack.getStatus())){
				if(old != null){
					old.setChannelCode(payTrack.getChannelCode());
					old.setStatus(payTrack.getStatus());
					old.setUrlContent(payTrack.getUrlContent());
					old.setTradeId(payTrack.getTradeId());
					payTrackMapper.merge(old);
				}else{
					payTrackMapper.add(payTrack);
				}
			}else if(TrackStatus.FINISHED.getValue().equals(payTrack.getStatus())
					|| TrackStatus.SUCCESSED.getValue().equals(payTrack.getStatus())){
				payTrackMapper.merge(payTrack);
			}
		}
	}
	
	@Override
	public List<PayTrack> search(PayTrack payTrack) {
		return payTrackMapper.search(payTrack);
	}
	
	@Override
	public PayTrack load(String orderId) {
		return payTrackMapper.load(orderId);
	}
	
	@Override
	public PayTrack loadByTradeId(String tradeId) {
		return payTrackMapper.loadByTradeId(tradeId);
	}
	
	@Override
	public int mergeExpired(Long hours) {
		return payTrackMapper.mergeExpired(hours);
	}
}
