package com.manger.service.roleImpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manger.core.entity.Page;
import com.manger.entity.role.Role;
import com.manger.menu.dao.TreeTestDao;
import com.manger.role.dao.RoleDao;

@Component
public class TestRoleServiceImpl implements TestRoleService{
    @Autowired
	private RoleDao roleDao;
    @Autowired
    private TreeTestDao treeTestDao;
    
	public void findRolePage(Page<Role> page,Map<String,Object> map){
		page.setParams(map);
		roleDao.findPage(page,"findRolePage");
	}

	@Override
	public void updateRole(Map<String, Object> map) {
		
		String ids=map.get("ids").toString();
			
		String[] idssss=ids.split(",");
		Map<String,Object> mapp=null;
		
		roleDao.deleteRoleMenu(map);
		
		for(String id:idssss){
			
			mapp=new HashMap<String,Object>();
			mapp.put("id",System.currentTimeMillis());
			mapp.put("roleId",map.get("roleId"));
			mapp.put("menuId",id);
			
			roleDao.insertIntoRoleMenu(mapp);
			
		}
		
	}
	
	
	//保存用户和角色的绑定关系
	public void saveRoleAndUser(Map<String,Object> map){
		//先删除
		roleDao.deleteRoleAndUser(map);
		//再添加
		roleDao.insertRoleAndUser(map);
	}
}
