package com.itheima.dao;

import com.itheima.bean.User;

public interface UserDao {

	void sava(User user);

	User findByUid(Integer uid);
	
}
