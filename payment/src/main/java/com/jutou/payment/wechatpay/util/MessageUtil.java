package com.jutou.payment.wechatpay.util;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NameCoder;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
/**
 * @Title: MessageUtil.java
 * @Description: 微信消息转码
 * @author  Liu.jg  
 * @date 2015年9月28日 下午3:27:50
 * @version V1.0
 * @Company: Didihu.com.cn
 * @Copyright Copyright (c) 2015
 */
public class MessageUtil {
	
	/**
	 * @Description:解析微信发来的请求（XML）
	 * @param inputStream
	 * @return
	 * @throws Exception
	 * @author  Liu.jg    
	 * @date 2015年9月28日 下午4:16:21
	 */
	public static Map<String, String> parseXml(InputStream inputStream) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		@SuppressWarnings("unchecked")
		List<Element> elements = root.elements();
		// 遍历所有子节点
		for (Element e : elements)
			map.put(e.getName(), e.getText());
		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}

	
	
	/**
	 * @Description: java bean转xml
	 * @param obj
	 * @param clazz
	 * @return
	 * @author  Liu.jg    
	 * @date 2015年10月8日 下午6:53:27
	 */
	public static String beanToXml(Object obj, Class<?> clazz){
		xstream.processAnnotations(clazz); //使用注解
		return xstream.toXML(obj).replaceAll("__", "_");
	}
	
	
	private static HierarchicalStreamDriver driver;
	
	private static NameCoder coder;
	
	private static XStream xstream;
	
	static {
		
		coder = new XmlFriendlyNameCoder("-_", "_");
		
		driver = new DomDriver("UTF-8", coder){
			public HierarchicalStreamWriter createWriter(Writer out) {  
	            return new PrettyPrintWriter(out) {  
	                // 对所有xml节点的转换都增加CDATA标记  
	                boolean cdata = true;  
	  
	                public void startNode(String name, @SuppressWarnings("rawtypes") Class clazz) {
	                	if(Number.class.isAssignableFrom(clazz)){
	                		cdata = false;
	                	}else{
	                		cdata = true;
	                	}
	                    super.startNode(name, clazz);  
	                }  
	                
	                @Override
	                public String encodeNode(String name) {
	                	return name;
	                }
	                
	                /**
	            	 * 扩展xstream，使其支持CDATA块 
	            	 */
	                protected void writeText(QuickWriter writer, String text) {  
	                    if (cdata) {  
	                        writer.write("<![CDATA[");  
	                        writer.write(text);  
	                        writer.write("]]>");  
	                    } else {  
	                        writer.write(text);  
	                    }  
	                }  
	            };  
	        }  
		};
		xstream = new XStream(driver);
	}
	
}
