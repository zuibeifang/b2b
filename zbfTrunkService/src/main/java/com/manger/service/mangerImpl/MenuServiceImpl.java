package com.manger.service.mangerImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manger.core.utils.UID;
import com.manger.entity.authorInfo.Menu;
import com.manger.menu.dao.MenuDao;


/**
 * 菜单管理
 * @author lcg
 *
 */
@Component
public class MenuServiceImpl {
    @Autowired
	private MenuDao menuDao;
	
	public void saveMenu(Map<String,Object> map){
		Menu menu=new Menu();
		   menu.setId(UID.next());
		   menu.setCode(""+UID.next());
		   menu.setLeval(map.get("leval").toString());
		   menu.setParentCode(map.get("parentCode")==null?"0":map.get("parentCode").toString());
		   menu.setMenuName(map.get("menuName").toString());
		   menu.setImagePath("");
		   menu.setVersion(10);
		   menu.setUrl(map.get("url")==null?"":map.get("url").toString());
		
		menuDao.insert(menu);
	}
}
