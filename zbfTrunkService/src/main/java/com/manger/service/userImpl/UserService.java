package com.manger.service.userImpl;

import java.util.Map;

import com.manger.core.entity.Page;
import com.manger.entity.user.User;

public interface UserService {
   public User getUserByName(String loginName);
   
   public void insertUser(User user);
   
   public void findUserPage(Page<User> page, Map<String, Object> filter);
   
   
   public int saveAddUser(Map<String, Object> map);
}
