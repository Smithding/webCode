package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.itheima.bean.Product;
import com.itheima.bean.User;

public interface InService {

	/**
	 * 激活-接口方法
	 * @param code
	 * @throws SQLException 
	 */
	User serviceActivate(String code) throws SQLException;
	
	/**
	 * 激活状态修改-接口方法
	 * @param code 
	 * @throws SQLException
	 */
	void activateModifier(String code) throws SQLException;
     
    /**
     * 分类信息展示业务-接口方法
     * @author Administrator
     *
     */
   String findAll () throws SQLException;
  
   /**
    * 注册-接口方法
    * @param user
    * @throws MessagingException 
    * @throws AddressException 
    * @throws Exception 
    */
	void Inservlet(User user) throws AddressException, MessagingException, Exception;
	
	/**
	 * 登录-接口方法
	 * @param username
	 * @param password
	 * @return
	 * @throws Exception
	 */
    User setRegister(String username,String password) throws Exception;
	
    /**
     * 激活码查询-接口方法
     * @param activateid
     * @return
     * @throws Exception
     */
	User setactivate(String activateid) throws Exception;

	
	   
}
