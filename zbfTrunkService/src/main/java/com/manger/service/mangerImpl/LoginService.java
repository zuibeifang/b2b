package com.manger.service.mangerImpl;

import java.util.List;
import java.util.Map;

import com.manger.core.entity.Page;
import com.manger.entity.authorInfo.Menu;
import com.manger.entity.user.User;

public interface LoginService {
	public Page<User> getUserList(Page<User> page, Map<String, Object> filters);
    public User getUser();
    
    public List<Menu> getMenus(Map<String, Object> filter);
    
    public int saveMenu(Map<String, Object> map);
    
    public void deleteByMenuId(Long id, String roleId);
    
    public int updateMenu(Map<String, Object> filter);
}
