package com.manger.common.memcached;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtils {
	public static Properties prop=null;
	static {
		prop = new Properties();
		InputStream in = PropertyUtils.class.getResourceAsStream("/memcached_session.properties");
		try {
			prop.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 从application.properties取出key对应的字符串
	 * @param key
	 * @return 
	 */
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	
	/**
	 * 读取properties的配置文件
	 * @param propertyPath
	 * @return
	 */
	public Properties getProperties(String propertyPath){
		Properties properties = new Properties();
		InputStream in = PropertyUtils.class.getResourceAsStream(propertyPath);
		try {
			prop.load(in);
			in.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
