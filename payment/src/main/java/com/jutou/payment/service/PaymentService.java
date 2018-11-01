package com.jutou.payment.service;

import com.jutou.payment.request.AutoBalanceRequest;
import com.jutou.payment.request.NotifyRequest;
import com.jutou.payment.request.PayRequest;
import com.jutou.payment.request.RefundQueryRequest;
import com.jutou.payment.request.RefundRequest;
import com.jutou.payment.request.TradeQueryRequest;
import com.jutou.payment.request.TradeRequest;
import com.jutou.payment.request.VerifyRequest;
import com.jutou.payment.response.PayResponse;
import com.jutou.payment.response.RefundQueryResponse;
import com.jutou.payment.response.RefundResponse;
import com.jutou.payment.response.TradeQueryResponse;
import com.jutou.payment.response.TradeResponse;

/**
 * @Title: PaymentRemote.java
 * @Description: 外部接口
 * @author  Liu.jg    
 * @date 2015年9月24日 上午10:36:25
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public interface PaymentService {
	
	/**
	 * @Description: 支付
	 * @param payRequest
	 * @author  Liu.jg    
	 * @date 2014年9月16日 下午5:54:50
	 */
	public PayResponse pay(PayRequest payRequest);
	
	/**
	 * @Description: 验证支付回调合法性
	 * @param params
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年9月17日 下午3:11:26
	 */
	public boolean verify(VerifyRequest request);
	
	/**
	 * @Description: 交易请求
	 * @param request 
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年9月19日 下午8:12:43
	 */
	public TradeResponse trade(TradeRequest request);
	
	/**
	 * @Description: 获取订单交易状态
	 * @param orderId 订单ID
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年10月21日 下午7:13:39
	 */
	public TradeQueryResponse query(TradeQueryRequest request);
	
	/**
	 * @Description: 自动对账
	 * @param payCategory
	 * @author  Liu.jg    
	 * @date 2014年10月22日 上午10:08:02
	 */
	public void autoBalance(AutoBalanceRequest request);
	
	/**
	 * @Description: 支付异步通知
	 * @param requestMap
	 * @param payCategory
	 * @return
	 * @author  Liu.jg    
	 * @date 2015年10月8日 下午3:20:09
	 */
	public String notify(NotifyRequest request);
	
	/**
	  * @Description:退款申请
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年9月26日 下午2:47:33
	 */
	public RefundResponse refund(RefundRequest request);
	
	/**
	  * @Description:退款查询
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月25日 上午9:56:14
	 */
	public RefundQueryResponse refundquery(RefundQueryRequest request);
	
	/**
	  * @Description:二维码支付
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年9月28日 上午9:30:09
	 */
	public PayResponse qrocdepay(PayRequest request);
}
