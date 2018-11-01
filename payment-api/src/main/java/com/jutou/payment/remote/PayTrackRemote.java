package com.jutou.payment.remote;

import java.util.List;

import com.jutou.payment.model.PayTrack;

/**
 * @ClassName:  PayTrackRemote   
 * @Description:支付流水
 * @author: Liu.jg 
 * @date:   2018年10月24日 上午11:28:05   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public interface PayTrackRemote {

	/**
	  * @Description:记录日志
	  * @param payTrack
	  * @author Liu.jg 
	  * @date 2018年10月24日 上午11:28:48
	 */
	public void log(PayTrack payTrack);
	
	/**
	  * @Description:查询支付日志
	  * @param payTrack
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月24日 上午11:29:09
	 */
	public List<PayTrack> search(PayTrack payTrack);
	
	/**
	  * @Description:根据订单号获取交易日志
	  * @param orderId
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月24日 上午11:29:42
	 */
	public PayTrack load(String orderId);
	
	/**
	  * @Description:设置交易超时
	  * @param hours
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月24日 上午11:30:14
	 */
	public int mergeExpired(Long hours);
}
