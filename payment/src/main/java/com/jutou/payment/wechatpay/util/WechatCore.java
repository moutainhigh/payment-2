package com.jutou.payment.wechatpay.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.cloudframework.core.sign.MD5;
import org.cloudframework.core.utils.JacksonUtils;

/**
 * @Title: WechatCore.java
 * @Description:
 * @author  Liu.jg  
 * @date 2015年9月28日 下午12:20:19
 * @version V1.0  
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public class WechatCore {
	
	/** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /**
     * @Description: 生成签名参数
     * @param params
     * @return
     * @author  Liu.jg  
     * @date 2015年10月8日 下午3:57:13
     */
    public static String getSign(Map<String, String> params,String key){
    	//获取待签名字符串
        String stringSignTemp = createLinkString(params) + "&key=" + key;
        return MD5.MD5Encode(stringSignTemp).toUpperCase();
    }
    
    public static String getSign(Object obj,String key){
    	return getSign(bean2Map(obj),key);
    }
    
    /**
     * @Description: 转换java Bean 为 map参数
     * @param obj
     * @return
     * @author  Liu.jg  
     * @date 2015年10月8日 下午5:42:28
     */
    public static Map<String, String> bean2Map(Object obj){
    	Map<String, Object> source = JacksonUtils.beanToMap(obj);
    	Map<String, String> target = new HashMap<String, String>();
    	for(Entry<String, Object> entry:source.entrySet()){
    		if(entry.getValue() != null){
    			target.put(entry.getKey(), entry.getValue().toString());
    		}
    	}
    	return target;
    }
}
