package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.bean.User;

public interface InDao {

	/**
	 * 激活码对比方法，Dao接口方法
	 * @param code
	 * @return
	 * @throws SQLException
	 */
	User Iodao(String code) throws SQLException;
	/**
	 * 激活状态修改方法，Dao接口方法
	 * 
	 */
    void IoModifier(String code) throws SQLException;    
    
    /**
	 * 分类信息dao接口
	 * @author Administrator
	 *
	 */
	List<Category> dao_findAll () throws SQLException;
	

	/**
	 * 登录dao接口
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
	User daoRegister(String username, String password) throws Exception;

	/**
	 * 激活dao接口
	 * @param activateid
	 * @return
	 * @throws SQLException
	 */
	User daoactivate(String activateid) throws SQLException ;
	/**
	 * 注册方法，Dao接口方法
	 * @param user
	 * @throws SQLException
	 * @throws Exception
	 */
	void Iodao(User user) throws Exception;
	

	
	
	
}
