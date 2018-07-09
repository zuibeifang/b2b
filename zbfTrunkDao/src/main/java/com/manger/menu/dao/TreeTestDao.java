package com.manger.menu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.core.dao.MybatisDao;
import com.manger.entity.authorInfo.Menu;
import com.manger.menu.mapper.TreeTestMapper;
import com.manger.role.mapper.RoleMapper;
@Component
public class TreeTestDao extends MybatisDao<Menu>{

	public List<Menu> getMenus(Map<String,Object> filter){
		   return getMapper(TreeTestMapper.class).getTreeMenus(filter);
	}
	
	public List<Map<String,Object>> getMenusMySelf(Map<String,Object> filter){
		   return getMapper(TreeTestMapper.class).getMenusMySelf(filter);
	}

	/**
	 * 根据角色ID查询角色的权限列表
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getRoleListById(Map<String,Object> map){
		
		return getMapper(TreeTestMapper.class).getRoleListById(map);
		
	}
	
	
}
