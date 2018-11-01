package com.jutou.payment.alipay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @Title: AlipayParser.java
 * @Description: Alipay XML返回值解析
 * @author Alvin.zengqi  
 * @date 2014年10月21日 下午4:57:33
 * @version V1.0  
 * @Company: Ttiao.cn
 * @Copyright Copyright (c) 2014
 */
public class AlipayParser {
	
    private static AlipayParser parser;
    
    public static final String SUCCESS = "T";
    public static final String FAIL = "F";
    
    private AlipayParser() {
    }
 
    public static AlipayParser getInstance() {
        if (parser == null) {
            parser = new AlipayParser();
        }
        return parser;
    }
 
    /**
     * @Description: 获取所有节点
     * @return
     * @throws XPathExpressionException
     * @author Alvin.zengqi  
     * @date 2014年10月21日 下午6:13:11
     */
    private List<Node> getNodeList(NodeList nodeList)
            throws XPathExpressionException {
    	List<Node> nodes = new ArrayList<Node>();
    	if(nodeList.getLength() > 0){
    		if(nodeList.getLength() == 1 
    				&& nodeList.item(0).getNodeType() == Node.TEXT_NODE )return nodes;
    		for(int i=0; i<nodeList.getLength(); i++){
    			nodes.add(nodeList.item(i));
    			nodes.addAll(getNodeList(nodeList.item(i).getChildNodes()));
    		}
    	}
    	return nodes;
    }
 
    /**
     * @Description: 初始化
     * @param xml 字符串
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     * @author Alvin.zengqi  
     * @date 2014年10月21日 下午5:16:05
     */
    public Document init(String xml) throws ParserConfigurationException, SAXException,
            IOException {
        DocumentBuilder dbd = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder();
        return dbd.parse(new ByteArrayInputStream(xml.getBytes()));
    }
 
    /**
     * @Description: xml字符串解析成map
     * @param xml
     * @return
     * @author Alvin.zengqi  
     * @date 2014年10月21日 下午6:38:01
     */
    public Map<String, String> parse(String xml) {
    	Map<String, String> xmlMap = new HashMap<String, String>();
        try {
        	Document doc = init(xml);
        	if(doc != null && doc.hasChildNodes()){
	            List<Node> nodes = getNodeList(doc.getChildNodes());
	            for (Node node:nodes) {
	                xmlMap.put(node.getNodeName(), node.getTextContent());
	            }
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xmlMap;
    }
}
