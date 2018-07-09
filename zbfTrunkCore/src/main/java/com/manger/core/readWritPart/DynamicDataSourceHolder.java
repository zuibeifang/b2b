package com.manger.core.readWritPart;
/**
 * 设置当前线程的变量的工具类，用于设置对应的数据源名称
 * @author zyl
 * @date 2017-8-2
 */
public class DynamicDataSourceHolder{
	
	  private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
	    /** 
	     * @Description: 设置数据源类型 
	     * @param dataSourceType  数据库类型 
	     * @return void 
	     * @throws 
	     */  
	    public static void putDataSource(String dataSourceType) {  
	        contextHolder.set(dataSourceType);  
	    }  
	      
	    /** 
	     * @Description: 获取数据源类型 
	     * @param  
	     * @return String 
	     * @throws 
	     */  
	    public static String getDataSouce() {  
	        return contextHolder.get();  
	    }  
	      
	    /** 
	     * @Description: 清除数据源类型 
	     * @param  
	     * @return void 
	     * @throws 
	     */  
	    public static void clearDataSource() {  
	        contextHolder.remove();  
	    }  
}
