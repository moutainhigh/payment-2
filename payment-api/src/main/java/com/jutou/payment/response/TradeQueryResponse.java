package com.jutou.payment.response;

import java.io.Serializable;
import java.util.Map;

import com.jutou.payment.enums.TrackStatus;


/**
 * @Title: TradeQueryResponse.java
 * @Description: 交易查询响应
 * @author Liu.jg  
 * @date 2015年9月24日 上午9:45:32
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2015
 */
@SuppressWarnings("serial")
public class TradeQueryResponse extends Response implements Serializable{
	
	/**
	 * 交易查询响应Map 
	 */
	private Map<String, String> respMap;
	/**
	 * 交易流水状态
	 */
	private TrackStatus trackStatus;

	public Map<String, String> getRespMap() {
		return respMap;
	}

	public void setRespMap(Map<String, String> respMap) {
		this.respMap = respMap;
	}

	public TrackStatus getTrackStatus() {
		return trackStatus;
	}

	public void setTrackStatus(TrackStatus trackStatus) {
		this.trackStatus = trackStatus;
	}
	
	
}
