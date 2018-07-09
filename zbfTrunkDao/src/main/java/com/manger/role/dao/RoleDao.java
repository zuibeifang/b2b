package com.manger.role.dao;


import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.core.dao.MybatisDao;
import com.manger.entity.role.Role;
import com.manger.role.mapper.RoleMapper;

@Component
public class RoleDao extends MybatisDao<Role>{

	public int insertIntoRoleMenu(Map<String,Object> map){
		return getMapper(RoleMapper.class).insertIntoRoleMenu(map);
	}
	public int deleteRoleMenu(Map<String,Object> map){
		return getMapper(RoleMapper.class).deleteRoleMenu(map);
	}
	
	//删除原来的用户和角色的绑定关系
	public int deleteRoleAndUser(Map<String,Object> map){
		return getMapper(RoleMapper.class).deleteRoleAndUser(map);
	};
	
	//插入用户和角色的绑定关系
	public int insertRoleAndUser(Map<String,Object> map){
		return getMapper(RoleMapper.class).insertRoleAndUser(map);
	};
}
