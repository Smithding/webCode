package com.itheima.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.User;
import com.itheima.constant.Constants;
import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import com.itheima.utils.Md5Util;

@Transactional
public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public void regist(User user) {
		//设置用户状态可用1
		user.setUser_state(Constants.USER_IN_JOB);
		//对密码进行加密
		String user_password = user.getUser_password();
		user_password = Md5Util.encodePwd(user_password);
		//再设置user里面
		user.setUser_password(user_password);
		
		userDao.save(user);
	}


	@Override
	public User login(User user) {
		//进行加密: 因为注册的时候存的是加密数据
		user.setUser_password(Md5Util.encodePwd(user.getUser_password()));
		return userDao.login(user);
	}


	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

}
