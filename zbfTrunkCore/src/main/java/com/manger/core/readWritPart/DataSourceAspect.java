package com.manger.core.readWritPart;

import java.util.List;

import org.aspectj.lang.JoinPoint;

public class DataSourceAspect {
    /**
     * 数据源名称
     */
	private String readDataSourceName;
	/**
	 * 需要用读库的方法名称
	 */
	private List<String> methodReadNames;

	

	public String getReadDataSourceName() {
		return readDataSourceName;
	}


	public void setReadDataSourceName(String readDataSourceName) {
		this.readDataSourceName = readDataSourceName;
	}


	public List<String> getMethodReadNames() {
		return methodReadNames;
	}


	public void setMethodReadNames(List<String> methodReadNames) {
		this.methodReadNames = methodReadNames;
	}

    
	public void before(JoinPoint jp){
        try{
            String methodName = jp.getSignature().getName();
            for(String method:methodReadNames){
            	if(methodName.startsWith(method)){
            		DynamicDataSourceHolder.putDataSource(readDataSourceName);
            	}
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	public void after(){
		DynamicDataSourceHolder.clearDataSource();
	}
}