package com.manger.common.memcached;

import java.util.Date;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedClientManager {
	private int initConn = 10;
	private int minConn = 5;
	private int maxConn = 300;
	private int socketTimeOut = 1000;
	private int socketConnectTimeOut = 1000;
	private int sessionTime=30;//默认有效缓存30分钟
	
	private static MemCachedClient mcc = null;
	private static SockIOPool pool = null;
	
	private static MemcachedClientManager memcachedMeanger = new MemcachedClientManager();
	
	public MemcachedClientManager(){
		if(mcc==null){
			createMcc();
		}
	}
	
	public static MemcachedClientManager getInstance(){
		return memcachedMeanger;
	}
	
	
	/**
	 * 初始化memcached客户端
	 * 
	 */
	public void createMcc(){
		if(mcc==null){
			String[] servers = PropertyUtils.getValue("memcached.servers").split(",");
			pool = SockIOPool.getInstance("memcached.servers");
			pool.setServers(servers);
			pool.setInitConn(PropertyUtils.getValue("memcached.initConn")==null?initConn:Integer.valueOf(PropertyUtils.getValue("memcached.initConn")));
			pool.setMinConn(PropertyUtils.getValue("memcached.minConn")==null?minConn:Integer.valueOf(PropertyUtils.getValue("memcached.minConn")));
			pool.setMaxConn(PropertyUtils.getValue("memcached.maxConn")==null?maxConn:Integer.valueOf(PropertyUtils.getValue("memcached.maxConn")));
			pool.setMaintSleep(30);
			pool.setSocketTO(PropertyUtils.getValue("memcached.socketTimeOut")==null?socketTimeOut:Integer.valueOf(PropertyUtils.getValue("memcached.socketTimeOut")));
			pool.setSocketConnectTO(PropertyUtils.getValue("memcached.socketConnectTimeOut")==null?socketConnectTimeOut:Integer.valueOf(PropertyUtils.getValue("memcached.socketConnectTimeOut")));
			pool.setAliveCheck(true);
			pool.setHashingAlg(SockIOPool.CONSISTENT_HASH);
			pool.initialize();
			mcc = new MemCachedClient("memcached.servers", false);  
		}  
	} 
	
	/**
	 * 默认30分钟
	 * @param key
	 * @param value
	 */
    public void set(String key, Object value) {
    	
    	Date expiryDate = new Date((PropertyUtils.getValue("memcached.sessionTime")==null?sessionTime:Integer.valueOf(PropertyUtils.getValue("memcached.sessionTime")))*60*1000);
    	mcc.set(key, value,expiryDate);
	}
	/**
	 * 
	 * @param key
	 * @param expiredTime  过期时间单位是秒
	 * @param value
	 */
	public void set(String key, int expiredTime, Object value) {
		Date expiryDate = new Date(expiredTime*1000);
		mcc.set(key, value, expiryDate);
	}
    
    public Object get(String key) {
    	return mcc.get(key);
    }
    public void delete(String key) {
		Object obj = get(key);
		if(obj != null) {
			mcc.delete(key);
		}
	}

	public int getInitConn() {
		return initConn;
	}

	public void setInitConn(int initConn) {
		this.initConn = initConn;
	}

	public int getMinConn() {
		return minConn;
	}

	public void setMinConn(int minConn) {
		this.minConn = minConn;
	}

	public int getMaxConn() {
		return maxConn;
	}

	public void setMaxConn(int maxConn) {
		this.maxConn = maxConn;
	}

	public int getSocketTimeOut() {
		return socketTimeOut;
	}

	public void setSocketTimeOut(int socketTimeOut) {
		this.socketTimeOut = socketTimeOut;
	}

	public int getSocketConnectTimeOut() {
		return socketConnectTimeOut;
	}

	public void setSocketConnectTimeOut(int socketConnectTimeOut) {
		this.socketConnectTimeOut = socketConnectTimeOut;
	}
    
    
    
}
