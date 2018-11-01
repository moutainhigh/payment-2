package com.jutou.payment.wechatpay.util;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jutou.payment.request.RedPackSendRequest;

/**
 * @ClassName:  HttpClientSSL   
 * @Description:带 证书请求  
 * @author: Liu.jg 
 * @date:   2018年10月25日 上午9:45:25   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public class HttpClientSSL {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientSSL.class);

	public static Map<String, String> post(String keyPass, String keyPath, String url,  String content) {
		Map<String, String> result = null;
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			File file = new File(keyPath);
			if(!file.exists()){
				file = new File(HttpClientSSL.class.getResource(keyPath).getPath());
			}
			if(!file.exists()){
				return null;
			}
			FileInputStream instream = new FileInputStream(file);
			try {
				keyStore.load(instream, keyPass.toCharArray());
			} finally {
				instream.close();
			}
			SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, keyPass.toCharArray()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
					SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
			try {
				HttpPost post = new HttpPost(url);
				post.setEntity(new StringEntity(content, Consts.UTF_8));
				CloseableHttpResponse response = httpclient.execute(post);
				try {
					HttpEntity entity = response.getEntity();
					if (entity != null) {
						result = MessageUtil.parseXml(entity.getContent());
						logger.debug(result.toString());
					}
					EntityUtils.consume(entity);
				} finally {
					response.close();
				}
			} finally {
				httpclient.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}

	public final static void main(String[] args) throws Exception {
		String keyPath = HttpClientSSL.class.getResource("/META-INF/apiclient/10037109/apiclient_cert.p12").getPath();
		Map<String, String> map = post("10037109", keyPath, "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack", MessageUtil.beanToXml(new RedPackSendRequest(), RedPackSendRequest.class));
		System.out.println(map);
	}
}
