package com.manger.role.mapper;

import java.util.Map;

import com.manger.core.dao.BaseMapper;
import com.manger.entity.role.Role;


public interface RoleMapper extends BaseMapper<Role>{
	public int insertIntoRoleMenu(Map<String, Object> map);
	public int deleteRoleMenu(Map<String, Object> map);
	
	
	//删除原来的用户和角色的绑定关系
	public int deleteRoleAndUser(Map<String, Object> map);
		
	//插入用户和角色的绑定关系
	public int insertRoleAndUser(Map<String, Object> map);
}
