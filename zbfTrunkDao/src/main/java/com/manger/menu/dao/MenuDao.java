package com.manger.menu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.core.dao.MybatisDao;
import com.manger.core.utils.UID;
import com.manger.entity.authorInfo.Menu;
import com.manger.menu.mapper.MenuMapper;
@Component
public class MenuDao extends MybatisDao<Menu>{
	/**
	 * 查询权限菜单
	 * @return
	 */
   public List<Menu> getMenus(Map<String,Object> filter){
	   return getMapper(MenuMapper.class).getMenus(filter);
   }
   
   public int saveMenu(Map<String,Object> map){
	   return getMapper(MenuMapper.class).saveMenu(map);
   }
   
   public List<Menu> getMenuByItems(Map<String,Object> filter){
	   return getMapper(MenuMapper.class).getMenuByItems(filter);
   }
   public int deleteMuensById(List<Menu> list){
	   return getMapper(MenuMapper.class).deleteMuensById(list);
   }
   
   public int deleteRoleMenuRelation(Map<String,Object> filter){
	   return getMapper(MenuMapper.class).deleteRoleMenuRelation(filter);
   }
   public List<Menu> findMenuByCode(Map<String,Object> filter){
	   return getMapper(MenuMapper.class).findMenuByCode(filter);
   }
   
   
   //保存菜单信息
   public int saveMenuNew(Map<String,Object> map){
	   
	   Menu menu=new Menu();
	   menu.setId(UID.next());
	   menu.setCode(""+UID.next());
	   menu.setLeval(map.get("leval").toString());
	   menu.setParentCode(map.get("parentCode")==null?"0":map.get("parentCode").toString());
	   menu.setMenuName(map.get("menuName").toString());
	   menu.setImagePath("");
	   menu.setVersion(10);
	   menu.setUrl(map.get("url")==null?"":map.get("url").toString());
	   
	   
	  return this.insert(menu);
	   
	  // return getMapper(MenuMapper.class).findMenuByCode(filter);
   }
   
   /**
    * 获取所有的菜单信息
    * @param map
    * @return
    */
   public List<String> listMenuMap(Map<String,Object> map){
	   return getMapper(MenuMapper.class).listMenuMap(map);
   }
   
   public List<String> listMenuUser(Map<String,Object> map){
	   return getMapper(MenuMapper.class).listMenuUser(map);
   }
   
}
