package com.manger.service.userImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.manger.core.entity.Page;
import com.manger.entity.user.User;
import com.manger.user.dao.UserDao;

@Component
public class UserServiceImpl implements UserService{
    @Autowired
	private UserDao userDao;
	
	@Override
	public User getUserByName(String loginName) {
		// TODO Auto-generated method stub
		return userDao.getByUserName(loginName);
	}
    
	public void insertUser(User user){
		
		userDao.insert(user);
		
		//User user1=this.getUserByName("zhangsan");
		
		//user1.setBuMen(""+System.currentTimeMillis());
		//userDao.update(user1);

	}
	
	public void findUserPage(Page<User> page,Map<String,Object> filter){
		page.setParams(filter);
        //userDao.findPage(page, "findUserPage", filter);
		
		userDao.findPage(page,"findUserPage");
	}

	@Override
	public int saveAddUser(Map<String, Object> map) {

		return userDao.saveAddUser(map);
		
	}
}
