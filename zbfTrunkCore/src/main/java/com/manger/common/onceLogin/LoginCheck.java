package com.manger.common.onceLogin;

import java.util.HashMap;
import java.util.Map;

import com.manger.common.memcached.MemcachedClientManager;

/**
 * 这个类用来检测登录
 * @author lcg
 * @date 2017-7-28
 */
public class LoginCheck {
	
	private static MemcachedClientManager memcachedClientManager=new MemcachedClientManager(); 
		
	
	/**
	 * 判断是否还可以登陆
	 * @param userId 用户Id
	 * @param loginTimes 设置允许登录的次数
	 */
	@SuppressWarnings("unchecked")
	public static void isLogin(String userId,int loginTimes,String sessionid){
		HashMap<String,Object> loginMap=(HashMap<String,Object>)memcachedClientManager.get("userId_"+userId);
		if(loginMap!=null){
			Object sid=loginMap.get("sessionid");
			if(sid!=null&&!sessionid.equals(sid.toString())){//证明不是同一浏览器登陆			
				//不是同一浏览器登录时，取出上一次登录的sessionid，然后去除缓存中对应的sessionid信息
				memcachedClientManager.delete(sid.toString());
			}
		}	
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("userId",userId);
		map.put("loginTimes",1);
		map.put("sessionid",sessionid);
		memcachedClientManager.set("userId_"+userId,map);

	}
}
