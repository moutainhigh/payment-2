package com.jutou.payment.wechatpay.util;

import java.util.Map;

/**
 * @Title: WechatNotify.java
 * @Description:
 * @author Alvin.zengqi  
 * @date 2015年10月8日 下午3:38:53
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public class WechatNotify {
	
	public static boolean verify(Map<String, String> params,String key) {
		String sign = "";
	    if(params.get("sign") != null) {sign = params.get("sign");}
	    return getSignVeryfy(params, sign,key);
	}
	
	/**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign,String key) {
    	//过滤空值、sign与sign_type参数
    	Map<String, String> sParaNew = WechatCore.paraFilter(Params);
        //获得签名验证结果
        boolean isSign = false;
        isSign = sign.equals(WechatCore.getSign(sParaNew,key));
        return isSign;
    }
}
