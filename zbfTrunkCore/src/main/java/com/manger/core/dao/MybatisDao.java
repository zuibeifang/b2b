package com.manger.core.dao;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.util.Assert;

import com.manger.core.entity.IdEntity;
import com.manger.core.entity.Page;
import com.manger.core.utils.ReflectionUtils;
import com.manger.core.utils.UID;

public abstract class MybatisDao<T extends IdEntity> extends MybatisSessionDaoSupport implements BaseMapper<T>{
	protected Class<T> entityClass=null;

	//private static boolean allStatementsBuilt = false;
	
	private Class<?> mapperClass = null;
	
	public MybatisDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}
	
	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用MyBatisDao的构造函数.
	 * 在构造函数中定义对象类型Class.
	 * eg.
	 * SimpleHibernateDao<User> userDao = new MyBatisDao<User>(sessionFactory, User.class);
	 * @return 
	 */
	public MybatisDao(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	public T get(final Long id) {
		Assert.notNull(id, "id不能为空");
		if(logger.isDebugEnabled()){
			logger.debug("MyBatisDao.get()");
		}
		
		BaseMapper<T> mapper = getBaseMapper();		
		T obj = (T)mapper.get(id);
		return obj;
	}
	
	@SuppressWarnings("unchecked")
	private BaseMapper<T> getBaseMapper(){
		return (BaseMapper<T>)getMapper(getMapperClass());	
	}
	
	protected Class<?> getMapperClass() {
		if(mapperClass!=null){
			return mapperClass;
		}
			
		try {
			String str1=this.getClass().getName();
			String mapperCls=this.getClass().getName().replace("dao", "mapper").replace("Dao", "").trim()+"Mapper";
			mapperClass = Class.forName(mapperCls);
			return mapperClass;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e.toString());
		}
	}
	
	/**
	 * 获取Mapper。参数一表示mapper,参数二表示数据源。不传入默认连接oracle
	 * @param type
	 * @return
	 */
	protected <X> X getMapper(Class<X> type) {
		return getSqlSession().getMapper(type);
	}
	
	public int delete(final Long id) {
		Assert.notNull(id, "id不能为空");
		BaseMapper<T> mapper = getBaseMapper();	
		return mapper.delete(id);
	}
	
	/**
	 * 保存新增的对象.
	 */
	public int insert(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		if(entity.getId()==null){
			entity.setId(UID.next());
		}
		BaseMapper<T> mapper = getBaseMapper();		
		int inserted= mapper.insert(entity);
		if(inserted==0){
			logger.info("Dao insert is error.This entity message is :"+entity.toString());
		}
		return inserted;
	}
	
	/**
	 * 保存修改的对象. 返回0表示更新失败。如果这个值已经被人修改过，会抛出ConcurrentModificationException
	 */
	public int update(final T entity) throws ConcurrentModificationException, ConcurrentModificationException {
		
		BaseMapper<T> mapper = getBaseMapper();		
		int count = mapper.update(entity);
		if(logger.isDebugEnabled()){
			logger.debug("count:"+count);
		}
		if (count == 0) {
			IdEntity o = mapper.get(entity.getId());
			logger.info("Dao update is error.This entity message is :"+o.toString());
			int version = o == null ? -1 : o.getVersion();
			if (version != entity.getVersion()) {
				getSqlSession().clearCache();
				throw new ConcurrentModificationException("Stale data: version="+entity.getVersion()+", version in db="+version);
			}
			throw new RuntimeException("mapper.update return 0 for:"+ mapper.getClass().getName());
		}
		return count;
	}

    /**
     * 这个分页对应的是此分页拦截器
     * com.manger.common.mybatis.QueryInterceptor
     * 2017-06-05
     */
	public Page<T> findPage(Page<T> page, String statementName,Map<String, Object> values) {
        Assert.notNull(page, "page不能为空");
		
		List<T> result = getSqlSession().selectList(statementName, toParameterMap(values, page), page.getRowBounds());

		page.setResultList(result);
		
		return page;
	}
	/**
	 * 第二个分页拦截器,该拦截器可以封装总页数和总记录数
	 * 2017-10-4
	 * com.manger.common.mybatis.page2.ItsMeQueryInterceptor
	 * @param page
	 * @param statementName
	 * @return
	 */
	public Page<T> findPage(Page<T> page,String statementName) {
        Assert.notNull(page, "page不能为空");
		
		List<T> result = getSqlSession().selectList(statementName, page);

		page.setResultList(result);
		
		return page;
	}
	
	protected Map<String,Object> toParameterMap(Object parameter, Page<?> p) {
		Map<String,Object> map = toParameterMap(parameter);
		map.put("offset", p.getFirst() - 1);
		map.put("limit", p.getPageSize());
		return map;
	}
	
	protected Map<String,Object> toParameterMap(Object parameter) {
		if (parameter == null) {
			return new HashMap<String,Object>();
		}
		
		if (parameter instanceof Map) {
			return (Map<String,Object>) parameter;
		} else {
			try {
				return PropertyUtils.describe(parameter);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
}
