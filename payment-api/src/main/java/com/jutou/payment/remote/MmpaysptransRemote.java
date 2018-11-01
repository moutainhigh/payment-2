package com.jutou.payment.remote;

import com.jutou.payment.request.PayBankRequest;
import com.jutou.payment.request.PublickeyRequest;
import com.jutou.payment.request.QueryBankRequest;
import com.jutou.payment.response.PayBankResponse;
import com.jutou.payment.response.PublickeyResponse;
import com.jutou.payment.response.QueryBankResponse;

/**
 * @ClassName:  MmpaysptransRemote   
 * @Description:企业付款到银行卡   
 * @author: Liu.jg 
 * @date:   2018年5月17日 上午10:07:15   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public interface MmpaysptransRemote {

	/**
	  * @Description:付款到银行卡
	  * @param payBankRequest
	  * @return
	  * @author Liu.jg 
	  * @date 2018年5月17日 上午10:07:42
	 */
	PayBankResponse payBank(PayBankRequest payBankRequest);
	
	/**
	  * @Description:查询付款
	  * @param queryBankRequest
	  * @return
	  * @author Liu.jg 
	  * @date 2018年5月17日 上午10:08:03
	 */
	QueryBankResponse queryBank(QueryBankRequest queryBankRequest);
	
	
	/**
	  * @Description:获取公钥
	  * @param appId
	  * @param publickeyRequest
	  * @return
	  * @author Liu.jg 
	  * @date 2018年5月21日 下午3:54:15
	 */
	PublickeyResponse getPublickey(PublickeyRequest publickeyRequest);
	
}
