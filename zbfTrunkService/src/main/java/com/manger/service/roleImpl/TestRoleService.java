package com.manger.service.roleImpl;

import java.util.Map;

import com.manger.core.entity.Page;
import com.manger.entity.role.Role;

public interface TestRoleService {
	public void findRolePage(Page<Role> page, Map<String, Object> map);
	
	
	public void updateRole(Map<String, Object> map);
	
	//保存用户和角色的绑定关系
	public void saveRoleAndUser(Map<String, Object> map);
}
