package com.jutou.payment.service;

import java.util.List;

import com.jutou.payment.model.PayTrack;

/**
 * @Title: PayTrackRemote.java
 * @Description: 支付流水
 * @author  Liu.jg    
 * @date 2015年9月22日 下午8:26:13
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public interface PayTrackService {
	
	/**
	 * @Description: 记录日志
	 * @param payTrack
	 * @author  Liu.jg    
	 * @date 2014年9月20日 下午6:09:07
	 */
	public void log(PayTrack payTrack);
	
	/**
	 * @Description: 查询支付日志
	 * @param payTrack
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年9月22日 下午5:25:52
	 */
	public List<PayTrack> search(PayTrack payTrack);
	
	/**
	 * @Description: 根据订单号获取交易日志
	 * @param orderId
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年10月21日 下午7:18:48
	 */
	public PayTrack load(String orderId);
	
	/**
	 * @Description:根据交易号获取交易日志
	 * @param tradeId
	 * @return
	 * @author  Liu.jg    
	 * @date 2016年1月23日 下午4:11:36
	 */
	public PayTrack loadByTradeId(String tradeId);
	
	/**
	 * @Description: 设置交易超时
	 * @param hours 超时小时数
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年10月28日 下午5:56:43
	 */
	public int mergeExpired(Long hours);
}
