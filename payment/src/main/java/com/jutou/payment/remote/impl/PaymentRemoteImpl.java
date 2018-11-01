package com.jutou.payment.remote.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutou.payment.remote.PaymentRemote;
import com.jutou.payment.request.AutoBalanceRequest;
import com.jutou.payment.request.NotifyRequest;
import com.jutou.payment.request.PayRequest;
import com.jutou.payment.request.RefundRequest;
import com.jutou.payment.request.TradeQueryRequest;
import com.jutou.payment.request.TradeRequest;
import com.jutou.payment.request.VerifyRequest;
import com.jutou.payment.response.PayResponse;
import com.jutou.payment.response.RefundResponse;
import com.jutou.payment.response.TradeQueryResponse;
import com.jutou.payment.response.TradeResponse;
import com.jutou.payment.service.PaymentService;

/**
 * @ClassName:  PaymentRemoteImpl   
 * @Description:支付   
 * @author: Liu.jg 
 * @date:   2018年10月24日 下午4:18:58   
 *     
 * @Copyright: 2018 jutoukeji. All rights reserved.
 */
@Service
public class PaymentRemoteImpl implements PaymentRemote{
	
	@Autowired
	private PaymentService paymentService;

	@Override
	public PayResponse pay(PayRequest payRequest) {
		return paymentService.pay(payRequest);
	}

	@Override
	public boolean verify(VerifyRequest request) {
		return paymentService.verify(request);
	}

	@Override
	public TradeResponse trade(TradeRequest request) {
		return paymentService.trade(request);
	}

	@Override
	public TradeQueryResponse query(TradeQueryRequest request) {
		return paymentService.query(request);
	}

	@Override
	public void autoBalance(AutoBalanceRequest request) {
		paymentService.autoBalance(request);
	}

	@Override
	public String notify(NotifyRequest request) {
		return paymentService.notify(request);
	}

	@Override
	public RefundResponse Refund(RefundRequest request) {
		return paymentService.refund(request);
	}

	@Override
	public PayResponse qrocdepay(PayRequest request) {
		return paymentService.qrocdepay(request);
	}
	
	

	
}
