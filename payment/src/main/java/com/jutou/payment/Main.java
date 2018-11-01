package com.jutou.payment;

import org.cloudframework.config.PropertyConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.common.utils.ConfigUtils;

/**
 * @ClassName:  Main     
 * @author: Liu.jg 
 * @date:   2018年10月26日 上午9:19:49   
 *     
 * @Copyright: 2018 51anango. All rights reserved.
 */
public class Main {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
    public static final String SPRING_CONFIG = "dubbo.spring.config";
    
    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";

    static ClassPathXmlApplicationContext context;
    
    private static volatile boolean running = true;
    
    public static ClassPathXmlApplicationContext getContext() {
		return context;
	}

	public void start() {
        String configPath = ConfigUtils.getProperty(SPRING_CONFIG);
        if (configPath == null || configPath.length() == 0) {
            configPath = DEFAULT_SPRING_CONFIG;
        }
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"));
        context.start();
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
        	logger.error(e.getMessage(), e);
        }
    }

    public static void main(String [] args){
    	new Main().start();
    	System.out.println("Dubbo Server Started, port:" + PropertyConfigurer.getString("dubbo.protocol.port"));
    	synchronized (Main.class) {
            while (running) {
                try {
                    Main.class.wait();
                } catch (Throwable e) {
                }
            }
        }
    }
}
