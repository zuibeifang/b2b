package com.manger.user.mapper;

import java.util.Map;

import com.manger.core.dao.BaseMapper;
import com.manger.entity.user.User;

public interface UserMapper  extends BaseMapper<User>{
      	
   public User getByUserName(String loginName);
   
   
   public int saveAddUser(Map<String, Object> map);
}
