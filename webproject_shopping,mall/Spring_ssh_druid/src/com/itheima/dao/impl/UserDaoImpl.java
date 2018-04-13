package com.itheima.dao.impl;



import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	

	@Override
	public void sava(User user) {
		System.out.println("������UserDaoImpl��save()����...");
		getHibernateTemplate().save(user);
			
	}

	public User findByUid(Integer uid) {
		
		return getHibernateTemplate().get(User.class, uid);
		
	}

}
