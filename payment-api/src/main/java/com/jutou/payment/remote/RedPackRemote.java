package com.jutou.payment.remote;

import com.jutou.payment.request.RedPackQueryRequest;
import com.jutou.payment.request.RedPackSendRequest;
import com.jutou.payment.response.RedPackQueryResponse;
import com.jutou.payment.response.RedPackSendResponse;



/**
 * 
 * @ClassName:  RedPackRemote   
 * @Description:发送红包
 * @author: Liu.jg 
 * @date:   2018年5月17日 上午10:06:27   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public interface RedPackRemote {
	
	/**
	  * @Description:发送红包
	  * @param appId
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年5月17日 上午10:06:44
	 */
	public RedPackSendResponse sendRedPack(RedPackSendRequest request);
	
	/**
	  * @Description:查询红包
	  * @param appId
	  * @param request
	  * @return
	  * @author Liu.jg 
	  * @date 2018年5月17日 上午10:06:54
	 */
	public RedPackQueryResponse getHBInfo(RedPackQueryRequest request);
	
	
}
