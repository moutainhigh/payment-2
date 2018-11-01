package com.jutou.payment.alipay.util;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.util.StringUtils;
/**
 * @Title: CheckUrl.java
 * @Description:
 * @author Alvin.zengqi
 * @date 2014年9月16日 下午6:30:49
 * @version V1.0
 * @Company: Ttiao.cn
 * @Copyright Copyright (c) 2014
 */
public class CheckUrl {
	
	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String check(String urlvalue) {
		StringBuffer inputLine = new StringBuffer("");
		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//			inputLine = in.readLine().toString();
			String line = "";
			while(!StringUtils.isEmpty(line = in.readLine())){
				inputLine.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputLine.toString();
	}
}
