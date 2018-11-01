package com.jutou.payment.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.jutou.payment.Payment;
import com.jutou.payment.enums.PayCategory;
import com.jutou.payment.enums.ResponseResult;
import com.jutou.payment.enums.TrackStatus;
import com.jutou.payment.model.PayTrack;
import com.jutou.payment.request.AutoBalanceRequest;
import com.jutou.payment.request.NotifyRequest;
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
import com.jutou.payment.service.PayTrackService;
import com.jutou.payment.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService,ApplicationContextAware{
	private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);
	@Autowired
	private PayTrackService payTrackService;
	@Autowired
	private TaskExecutor taskExecutor;
	
	@Override
	public PayResponse pay(final PayRequest payRequest) {
		LOG.debug("payRequest {}:" + ObjectUtils.getDisplayString(payRequest));
		PayResponse response = new PayResponse();
		// 判断当前订单号是否已经支付
		PayTrack payTrack = payTrackService.load(payRequest.getOrderId());
		if(payTrack != null && (TrackStatus.FINISHED.getValue().equals(payTrack.getStatus())
				|| TrackStatus.SUCCESSED.getValue().equals(payTrack.getStatus()))){
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg("order is payed.");
			return response;
		}
		response=getPayserService(payRequest.getPayCategory()).pay(payRequest);
		if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
			final String content = response.getForm();
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 生成交易日志
					log(payRequest, content);
				}
			});
		}
		return response;
	}

	@Override
	public boolean verify(VerifyRequest request) {
		LOG.debug("VerifyRequest {}:" + ObjectUtils.getDisplayString(request.getParams()));
		return getPayserService(request.getPayCategory()).verify(request);
	}

	@Override
	public TradeResponse trade(TradeRequest request) {
		LOG.debug("TradeRequest {}:" + ObjectUtils.getDisplayString(request));
		TradeResponse response = new TradeResponse();
		response = getPayserService(request.getPayCategory()).trade(request);
		this.logPayTrack(request, response);
		return response;
	}

	@Override
	public TradeQueryResponse query(TradeQueryRequest request) {
		LOG.debug("query orderId:" + request.getTradeId());
		TradeQueryResponse response = new TradeQueryResponse();
		PayTrack payTrack = payTrackService.load(request.getTradeId());
		if(payTrack != null && !StringUtils.isEmpty(payTrack.getOrderId())){
			response = getPayserService(request.getPayCategory()).query(request);
		}else{
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg("orderId not match");
		}
		return response;
	}

	@Override
	public void autoBalance(AutoBalanceRequest request) {
		List<PayTrack> tracks = searchUnpayedTracks(request.getPayCategory());
		if(tracks != null && !tracks.isEmpty()){
			for(PayTrack track:tracks){
				TradeQueryRequest tradeRequest=new TradeQueryRequest();
				tradeRequest.setPayCategory(request.getPayCategory());
				tradeRequest.setPayConfigId(request.getPayConfigId());
				tradeRequest.setTradeId(track.getOrderId());
				TradeQueryResponse response = getPayserService(request.getPayCategory()).query(tradeRequest);
				if(ResponseResult.SUCCESS.getValue().equals(response.getResult())
						&& (TrackStatus.FINISHED.equals(response.getTrackStatus()) 
								|| TrackStatus.SUCCESSED.equals(response.getTrackStatus()))){
					// 已支付成功
					//orderService.payed(track.getOrderId());
					track.setStatus(response.getTrackStatus().getValue());
					track.setCallbackContent(response.getRespMap().toString());
					payTrackService.log(track);
				}
			}
		}
	}

	@Override
	public String notify(NotifyRequest request) {
		VerifyRequest vrequest=new VerifyRequest();
		vrequest.setParams(request.getRequestMap());
		vrequest.setPayCategory(request.getPayCategory());
		vrequest.setPayConfigId(request.getPayConfigId());
		if(verify(vrequest)){
			// 支付异步回调成功后更新订单状态为已支付
			NotifyResponse response = getPayserService(request.getPayCategory()).notify(request.getRequestMap());
			if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
				// 已支付成功
				//orderService.payed(response.getOrderId());
				log(request.getRequestMap(), request.getPayCategory());
				return ResponseResult.SUCCESS.getValue();
			}
		}
		return ResponseResult.FAIL.getValue();
	}

	@Override
	public RefundResponse refund(RefundRequest request) {
		LOG.debug("refundRequest {}:" + ObjectUtils.getDisplayString(request));
		RefundResponse response = new RefundResponse();
		// 判断当前订单号是否未支付
		final PayTrack payTrack = payTrackService.load(request.getOutTradeNo());
		if(payTrack != null && (TrackStatus.FINISHED.getValue().equals(payTrack.getStatus())
				|| TrackStatus.SUCCESSED.getValue().equals(payTrack.getStatus()))){
			response=getPayserService(request.getPayCategory()).Refund(request);
			if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
				//退款成功
				payTrack.setStatus(TrackStatus.REFUND_SUCCESS.getValue());
			}else{
				//退款失败
				payTrack.setStatus(TrackStatus.REFUND_SUCCESS.getValue());
				payTrack.setReason(response.getErrMsg());
			}
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 生成交易日志
					payTrackService.log(payTrack);
				}
			});
		}else{
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg("order is unpayed.");
		}
		return response;
	}

	@Override
	public RefundQueryResponse refundquery(RefundQueryRequest request) {
		LOG.debug("refundQueryRequest {}:" + ObjectUtils.getDisplayString(request));
		return getPayserService(request.getPayCategory()).refundquery(request);
	}
	
	@Override
	public PayResponse qrocdepay(final PayRequest payRequest) {
		LOG.debug("qrocdepay {}:" + ObjectUtils.getDisplayString(payRequest));
		PayResponse response = new PayResponse();
		// 判断当前订单号是否已经支付
		PayTrack payTrack = payTrackService.load(payRequest.getOrderId());
		if(payTrack != null && (TrackStatus.FINISHED.getValue().equals(payTrack.getStatus())
				|| TrackStatus.SUCCESSED.getValue().equals(payTrack.getStatus()))){
			response.setResult(ResponseResult.FAIL.getValue());
			response.setErrMsg("order is payed.");
			return response;
		}
		response=getPayserService(payRequest.getPayCategory()).qrocdepay(payRequest);
		if(ResponseResult.SUCCESS.getValue().equals(response.getResult())){
			final String content = response.getForm();
			taskExecutor.execute(new Runnable() {
				@Override
				public void run() {
					// 生成交易日志
					log(payRequest, content);
				}
			});
		}
		return response;
	}
	
	/**
	 * @Description: 修改交易日志
	 * @param params
	 * @param payCategory
	 * @author  Liu.jg    
	 * @date 2015年10月8日 下午3:26:11
	 */
	private void log(Map<String,String> params, PayCategory payCategory){
		try{
			String orderId = "", tradeId = "";
			switch(payCategory){
				case ALIPAY:
					orderId = params.get("out_trade_no");
					tradeId = params.get("trade_no");
					break;
				case WECHAT:
					orderId = params.get("out_trade_no");
					tradeId = params.get("transaction_id");
					break;
				default :
					break;
			}
			if(!StringUtils.isEmpty(orderId)){
				PayTrack payTrack = new PayTrack();
				payTrack.setOrderId(orderId);
				payTrack.setTradeId(tradeId);
				payTrack.setCallbackContent(ObjectUtils.getDisplayString(params));
				payTrack.setStatus(TrackStatus.FINISHED.getValue());
				payTrackService.log(payTrack);
			}
		}catch(Exception e){
			LOG.error("Pay Track Log Failed:"+e.toString(), e);
		}
	}
	
	/**
	 * @Description: 手机端获取交易号时保存支付日志
	 * @param request
	 * @param response
	 * @author  Liu.jg    
	 * @date 2014年11月4日 下午3:35:05
	 */
	private void logPayTrack(TradeRequest request, TradeResponse response){
		PayTrack payTrack = new PayTrack();
		payTrack.setChannelCode(request.getPayCategory().getValue());
		payTrack.setStatus(TrackStatus.WAITPAY.getValue());
		payTrack.setCreatedBy(request.getUserName());
		payTrack.setUpdatedBy(request.getUserName());
		payTrack.setTradeId(response.getOrderId());
		payTrack.setUrlContent(request.toString());
		payTrack.setOrderId(request.getOrderId());
		payTrack.setPayUse(request.getPayUse());
		payTrackService.log(payTrack);
	}
	
	/**
	 * @Description: 生成交易日志
	 * @param request
	 * @param response
	 * @author  Liu.jg    
	 * @date 2015年10月10日 上午11:28:42
	 */
	private void log(PayRequest request, String content){
		PayTrack payTrack = new PayTrack();
		payTrack.setChannelCode(request.getPayCategory().getValue());
		payTrack.setStatus(TrackStatus.WAITPAY.getValue());
		payTrack.setCreatedBy(request.getOpenId());
		payTrack.setUpdatedBy(request.getOpenId());
		payTrack.setPayAccount(request.getOpenId());
		payTrack.setTradeId(request.getOrderId());
		payTrack.setUrlContent(content);
		payTrack.setOrderId(request.getOrderId());
		payTrack.setPayUse(request.getPayUse());
		payTrackService.log(payTrack);
	}
	
	/**
	 * @Description: 查询待支付记录
	 * @param payCategory
	 * @return
	 * @author  Liu.jg    
	 * @date 2014年10月22日 上午11:04:39
	 */
	private List<PayTrack> searchUnpayedTracks(PayCategory payCategory){
		PayTrack payTrack = new PayTrack();
		payTrack.setChannelCode(payCategory.getValue());
		payTrack.setStatus(TrackStatus.WAITPAY.getValue());
		return payTrackService.search(payTrack);
	}
	

	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context=applicationContext;
	}

	private Payment getPayserService(PayCategory payCategory){
		if(payCategory == null) throw new IllegalArgumentException("PayCategory Is Null");
		return (Payment) context.getBean(payCategory.getValue() + "payService"); 
	}

	
	
}
