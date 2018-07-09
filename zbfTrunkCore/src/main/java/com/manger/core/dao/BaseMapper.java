package com.manger.core.dao;

import java.util.Map;

import com.manger.core.entity.IdEntity;
import com.manger.core.entity.Page;

public interface BaseMapper<T extends IdEntity> {
	/**
	 * return 0 if fail
	 */
	int insert(T entity);
	
	/*int singleBatchInsert(List<T> entitys);*/
	
	/*int delete(T entity);*/
	
	int delete(Long id);

	/*int batchDelete(List<Long> ids);*/

	int update(T entity);

	/*int batchSaveOrUpdate(List<T> entities);*/
	
	T get(Long id);
	
	Page<T> findPage(Page<T> page, String statementName, Map<String, Object> values);
	
	Page<T> findPage(Page<T> page, String statementName);
}
