package com.manger.login.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manger.core.dao.BaseMapper;
import com.manger.entity.user.User;

@Repository
public interface LoginMapper extends BaseMapper<User>{
    public List<User> getUserList();
}
