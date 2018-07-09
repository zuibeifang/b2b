package com.manger.common.sessionShare;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.manger.common.memcached.MemcachedClientManager;

public class HttpServletRequestWrapper extends javax.servlet.http.HttpServletRequestWrapper{
    
	private MemcachedClientManager memcachedClientManager;
	
	private String sessionId;
	
	public HttpServletRequestWrapper(HttpServletRequest request,MemcachedClientManager memcachedClientManager,String sessionId) {
		
		super(request);	
		this.memcachedClientManager=memcachedClientManager;
		this.sessionId=sessionId;

	}
	
	/**
	 * 获取Session
	 */
	public HttpSession getSession() {
		
		HashMap<String,Object> sessionMap=(HashMap<String,Object>)memcachedClientManager.get(sessionId);
		if(sessionMap==null){
			HashMap<String,Object> session=new HashMap<String,Object>();
			memcachedClientManager.set(sessionId,session);			
		}
		
		return HttpSessionWrapper.getHttpSessionInstance(super.getSession(),memcachedClientManager,sessionId);
		
	}
	
	 @Override
	public String getParameter(String name) {
	    // 返回值之前 先进行过滤
	    return XssShieldUtil.stripXss(super.getParameter(XssShieldUtil.stripXss(name)));
	}
	 
	 @Override
	 public String[] getParameterValues(String name) {
	        // 返回值之前 先进行过滤
	        String[] values = super.getParameterValues(XssShieldUtil.stripXss(name));
	        if(values != null){
	            for (int i = 0; i < values.length; i++) {
	                values[i] = XssShieldUtil.stripXss(values[i]);
	            }
	        }
	        return values;
	 } 
	 
	 @Override
	 public Map<String, String[]> getParameterMap() {
		// TODO Auto-generated method stub
		 Map<String,String[]> pm=super.getParameterMap();
		 
		return super.getParameterMap();
	 }
}
