package com.jutou.payment.wechatpay.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.BeanUtils;
import org.cloudframework.core.sign.MD5;
import org.cloudframework.core.utils.JacksonUtils;


import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 微信工具类
 * 
 * @author LiuLin.He
 *
 */
public class WechatUtil {

	/**
	 * 生成红包签名
	 * 
	 * @param sign
	 * @param key
	 * @return
	 */
	public static String generateSignature(Object obj, String key) {
		return MD5.MD5Encode(createLinkString(bean2Map(obj)) + "&key=" + key).toUpperCase();
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if("sign".equals(key)){
				continue;
			}
			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

    /**
     * @Description: 转换java Bean 为 map参数
     * @param obj
     * @return
     * @author Alvin.zengqi  
     * @date 2015年10月8日 下午5:42:28
     */
    public static Map<String, String> bean2Map(Object obj){
    	Map<String, String> aliasMap = parseAlias(obj);
    	Map<String, Object> source = JacksonUtils.beanToMap(obj);
    	Map<String, String> target = new HashMap<String, String>();
    	for(Entry<String, Object> entry:source.entrySet()){
    		if(entry.getValue() != null){
    			target.put(aliasMap.get(entry.getKey()), entry.getValue().toString());
    		}
    	}
    	return target;
    }
    
    /**
     * @Description: 解析Java Bean 字段别名
     * @param obj
     * @return
     * @author Alvin.zengqi  
     * @date 2016年7月7日 下午6:15:49
     */
    public static Map<String, String> parseAlias(Object obj){
    	Map<String, String> aliasMap = new HashMap<String, String>();
    	Field[] fields = obj.getClass().getDeclaredFields();
    	for(Field field:fields){
    		XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
    		if(xStreamAlias != null){
    			aliasMap.put(field.getName(), xStreamAlias.value());
    		}
    	}
    	return aliasMap;
    }
	
	public static void transMap2Bean2(Map<String, String> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
