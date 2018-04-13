package com.itheima.service.impl;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.service.UserService;

@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void seva() {
		System.out.println("������UserServiceImpl��save()����...");
		User user = new User();
		user.setUname("����");
		user.setUage(22);
	    userDao.sava(user);
	}
	
	public User findByUid(Integer uid){
		 return userDao.findByUid(uid);
	}

}
