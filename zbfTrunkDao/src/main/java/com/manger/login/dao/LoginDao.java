package com.manger.login.dao;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.manger.core.dao.MybatisDao;
import com.manger.core.entity.Page;
import com.manger.entity.user.User;
import com.manger.login.mapper.LoginMapper;

@Component
public class LoginDao extends MybatisDao<User>{

    public User getUserById(long id){
    	
    	return getMapper(LoginMapper.class).get(id);
    	
    }

    public Page<User> findByPage(Page<User> page,Map<String,Object> filters){
    	return findPage(page,"findByPage", filters);
    }
}
