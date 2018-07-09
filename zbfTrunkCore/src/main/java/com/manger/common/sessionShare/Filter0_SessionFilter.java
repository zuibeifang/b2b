package com.manger.common.sessionShare;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.manger.common.memcached.MemcachedClientManager;

/**
 * 修改默认request的的拦截器
 * @author lcg
 * @date 2017-7-28
 */
public class Filter0_SessionFilter implements Filter{
	private String excludedPath;       
	private String[] excludedPathArray;
	
	//private static final String cookieDomain = "";


	private static final String cookiePath = "/";
	
	private static final MemcachedClientManager memcachedClientManager=MemcachedClientManager.getInstance();
	
	
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
 		HttpServletRequest request1=(HttpServletRequest)request;
		HttpServletResponse response1=(HttpServletResponse)response;

		Cookie[] cookies=request1.getCookies();
		Cookie sCookie = null;
		String cookName ="defindSid";
		String sid = "";
		if (cookies != null && cookies.length > 0) {
			for (int i = 0; i < cookies.length; i++) {
				sCookie = cookies[i];
				if (sCookie.getName().equals(cookName)) {
				    sid = sCookie.getValue();
				    break;
				}
			}
		}

          
		if (sid.length() == 0) {//用来向浏览器回写一个cookie，保存cookieID用来做session共享
			sid = java.util.UUID.randomUUID().toString();
			Cookie mycookies = new Cookie(cookName, sid);
			mycookies.setMaxAge(-1);//关闭浏览器失效
			mycookies.setPath(cookiePath);
			mycookies.setHttpOnly(true);
			response1.addCookie(mycookies);
		}
		//排除的url		
		String path=request1.getServletPath();		
	    for(String excluded:excludedPathArray){
	    	if(path.equals(excluded)){
	    		chain.doFilter(new HttpServletRequestWrapper(request1,memcachedClientManager,sid), response1);
	    	    return;
	    	}
	    }

		//上边验证通过memcached获取session下的数据
		HashMap<String,Object> mapAttribute=(HashMap<String,Object>)memcachedClientManager.getInstance().get(sid);
		if(mapAttribute!=null&&mapAttribute.get("user")!=null){//已登录
			memcachedClientManager.set(sid, mapAttribute);
			chain.doFilter(new HttpServletRequestWrapper(request1,memcachedClientManager,sid), response1);
		}else{
			//重定向到登录页面
			response1.sendRedirect(request1.getContextPath()+"/login/loginContro/shouye.dhtml");
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		excludedPath = filterConfig.getInitParameter("excludedPath");     
		if (StringUtils.isNotEmpty(excludedPath)) {     
			excludedPathArray = excludedPath.split(",");     
		} 
	}

	
	
	
}
