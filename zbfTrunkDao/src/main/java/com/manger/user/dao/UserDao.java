package com.manger.user.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.core.dao.MybatisDao;
import com.manger.core.entity.Page;
import com.manger.entity.user.User;
import com.manger.user.mapper.UserMapper;

@Component
public class UserDao extends MybatisDao<User>{
    public User getByUserName(String loginName){
    	return getMapper(UserMapper.class).getByUserName(loginName);
    }
    
    
    public int saveAddUser(Map<String, Object> map){
    	return getMapper(UserMapper.class).saveAddUser(map);
    }
}
