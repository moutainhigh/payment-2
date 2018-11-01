package com.jutou.payment;

import java.util.Map;

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
 * @Title: Payment.java
 * @Description: 内部接口定义
 * @author Liu.jg  
 * @date 2014年9月19日 下午3:02:08
 * @version V1.0  
 * @Company: 
 * @Copyright Copyright (c) 2014
 */
public interface Payment {
	
	/**
	 * @Description: 生成支付表单
	 * @param payRequest
	 * @return
	 * @author Liu.jg 
	 * @date 2014年9月19日 下午8:13:40
	 */
	public PayResponse pay(PayRequest payRequest);
	
	/**
	 * @Description:验证支付回调合法性
	 * @param params
	 * @return
	 * @author Liu.jg
	 * @date 2014年9月19日 下午8:13:36
	 */
	public boolean verify(VerifyRequest request);
	
	/**
	 * @Description: 交易请求
	 * @param request 
	 * @return
	 * @author Liu.jg  
	 * @date 2014年9月19日 下午8:12:43
	 */
	public TradeResponse trade(TradeRequest request);
	
	/**
	 * @Description: 查询交易
	 * @param tradeId 支付渠道交易Id
	 * @return
	 * @author Liu.jg 
	 * @date 2014年9月22日 下午4:06:26
	 */
	public TradeQueryResponse query(TradeQueryRequest request);
	
	/**
	 * @Description: 支付异步通知处理，处理成功返回交易号
	 * @param params
	 * @author Liu.jg  
	 * @date 2015年10月8日 下午6:17:07
	 */
	public NotifyResponse notify(Map<String, String> params);
	
	/**
	  * @Description:发起退款
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月25日 上午9:52:26
	 */
	public RefundResponse Refund(RefundRequest request);
	/**
	  * @Description:退款查询
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月25日 上午9:52:58
	 */
	public RefundQueryResponse refundquery(RefundQueryRequest request);
	/**
	  * @Description:获取支付二维码
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年10月25日 上午9:53:12
	 */
	public PayResponse qrocdepay(PayRequest request);
	
	
}
