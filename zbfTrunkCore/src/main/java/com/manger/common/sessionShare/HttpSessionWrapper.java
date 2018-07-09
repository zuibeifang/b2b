package com.manger.common.sessionShare;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import com.manger.common.memcached.MemcachedClientManager;


public class HttpSessionWrapper implements InvocationHandler{
   
	public MemcachedClientManager  memcachedClientManager;
	public String sessionId;
	
	public HttpSessionWrapper(MemcachedClientManager  memcachedClientManager,String sessionId){
		this.memcachedClientManager=memcachedClientManager;
		this.sessionId=sessionId;
	}
	
	
	public static HttpSession getHttpSessionInstance(HttpSession session,MemcachedClientManager  memcachedManager,String sId){
		HttpSessionWrapper httpSessionWrapper=new HttpSessionWrapper(memcachedManager,sId);
		HttpSession hs=(HttpSession)Proxy.newProxyInstance(session.getClass().getClassLoader(),session.getClass().getInterfaces(),httpSessionWrapper);
		return hs;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		   HashMap<String,Object> mapAttribute=(HashMap<String,Object>)memcachedClientManager.get(sessionId);
		   if(mapAttribute!=null&&method.getName().equals("getAttribute")){		  		   
			   return mapAttribute.get(args[0].toString());
		   }else if(mapAttribute!=null&&method.getName().equals("setAttribute")){

			   mapAttribute.put(args[0].toString(),args[1]);
			   memcachedClientManager.set(sessionId,mapAttribute);
			   
		   }else if(mapAttribute!=null&&method.getName().equals("removeAttribute")){			   
			   mapAttribute.remove(args[0]);
			   memcachedClientManager.set(sessionId,mapAttribute);
		   }else if(method.getName().equals("getId")){
			   return sessionId;
		   }		
		return null;
	}

	
	

	

}
