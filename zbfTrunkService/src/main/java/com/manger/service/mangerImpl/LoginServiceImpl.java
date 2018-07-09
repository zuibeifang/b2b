package com.manger.service.mangerImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manger.core.entity.Page;
import com.manger.core.utils.UID;
import com.manger.entity.authorInfo.Menu;
import com.manger.entity.user.User;
import com.manger.login.dao.LoginDao;
import com.manger.menu.dao.MenuDao;

@Component
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
    @Autowired
	private MenuDao menuDao;
	/**
	 * 根据Id查询
	 */
	public User getUser() {
		User user=loginDao.getUserById(1L);
		return user;
	}
    /**
     * 更新用户信息
     * @return
     */
	public int updateUser(User user){
        
		return 0;
	}
	/**
	 * 查询用户列表
	 */
	public Page<User> getUserList(Page<User> page,Map<String,Object> filters) {
		// TODO Auto-generated method stub
		return loginDao.findByPage(page, filters);
	}
	/**
	 * 查询权限菜单
	 * filter:userID,leval
	 */
	public List<Menu> getMenus(Map<String, Object> filter) {
		//查询一级菜单
		List<Menu> list=menuDao.getMenus(filter);
		
		if(list.size()>0){
           this.getNextMenus(filter, list);
		}
		return list;
	}
	
	public List<Menu> getNextMenus(Map<String, Object> filter,List<Menu> list){
			for(Menu mu:list){
				filter.put("leval",Integer.parseInt(mu.getLeval())+1);//将要查询的菜单的等级
				filter.put("parentCode",mu.getCode());//父级菜单的编码
				
				List<Menu> menus=menuDao.getMenus(filter);
				if(menus.size()==0){
					continue;
				}	
				//查询正在遍历的菜单的子菜单
				List<Menu> menus2=this.getNextMenus(filter, menus);
				//吧查询出的子菜单存入到 当前正在遍历的菜单的listm属性中
				mu.setListm(menus2);
			}		
		return list;
	}
	
	 public int saveMenu(Map<String,Object> map){
		map.put("id",UID.next());
		map.put("version",10);
		map.put("code",UID.next());
		//检查是否存在菜单存在的话删除
		List<Menu> list=menuDao.getMenuByItems(map);
		if(list.size()>0){
			menuDao.deleteMuensById(list);
		}
		return menuDao.saveMenu(map);
	 }
    
	 public void deleteByMenuId(Long id,String roleId){
		 Map<String,Object> filter=new HashMap<String,Object>();
		 filter.put("id",id);
		 List<Menu> list=menuDao.findMenuByCode(filter);
		 List<Menu> forDel=this.getNextMenus(filter, list);
		 menuDao.deleteMuensById(list);
		 for(Menu menu:forDel){
			 this.deleteMenu(menu,roleId);
		 }
	 }
	 /**
	  * 删除级联菜单
	  * @param menu
	  * @param roleId
	  */
	 public void deleteMenu(Menu menu,String roleId){
		 if(menu.getListm()!=null&&menu.getListm().size()>0){
			 menuDao.deleteMuensById(menu.getListm());
			 Map<String,Object> roleRela=new HashMap<String,Object>();
			 roleRela.put("roleId",roleId);
			 roleRela.put("list",menu.getListm());
			 //删除菜单角色关系
			 menuDao.deleteRoleMenuRelation(roleRela);
			 for(Menu m:menu.getListm()){
				this.deleteMenu(m,roleId);
			 }
		 }		 
	 }
	 
	 public int updateMenu(Map<String,Object> filter){
		 Menu menu=menuDao.get(Long.valueOf(filter.get("id").toString()));
		 if(filter.get("menuName")!=null) menu.setMenuName(filter.get("menuName").toString());
		 if(filter.get("url").toString()!=null) menu.setUrl(filter.get("url").toString());
		 if(filter.get("imagePath").toString()!=null) menu.setImagePath(filter.get("imagePath").toString());
		 return menuDao.update(menu);
	 }
}
