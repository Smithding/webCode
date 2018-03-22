package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.bean.User;
import com.itheima.dao.InDao;
import com.itheima.utils.C3P0Utils;

public class AountDao implements InDao {

	/**
	 * 注册业务操作
	 * @throws SQLException 
	 */
	public void Iodao(User user) throws Exception  {
	     QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
         String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
         Object[] pame ={user.getUid(),user.getUsername(),user.getPassword()
        		 ,user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday()
        		 ,user.getSex(),user.getState(),user.getCode()};
         
         queryRunner.update(sql,pame);
	}
	
	/**
	 * 激活码查询业务操作
	 * @throws SQLException 
	 */
	public User Iodao(String code) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from user where code = ? ";
        User user = queryRunner.query(sql, new BeanHandler<User>(User.class),code);
		return user;
		
	}

	/**
	 * 激活码修改为激活状态
	 */
	public void IoModifier(String code) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update user set state = ? where code = ? ";
        queryRunner.update(sql,1,code);
		
	}
	
	public List<Category> dao_findAll() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from category";
		List<Category> list = queryRunner.query(sql, new BeanListHandler<>(Category.class));
		return list;
	}
	
	/**
	 * 全部商品sql查询
	 * @throws SQLException 
	 */
	public List<Product> hotList() throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product ";
		List<Product> query = queryRunner.query(sql, new BeanListHandler<>(Product.class));
		return query;
	}
	
	/**
	 * 登录操作查询
	 */
	public User daoRegister(String username, String password) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where username = ? and password=? ";
		Object[] pame = { username,password };
		User user = queryRunner.query(sql, new BeanHandler<>(User.class), pame);
		return user;
		
	}

	/**
	 * 查询激活码操作
	 */
	public User daoactivate(String activateid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from user where state = ? and uid = ?  ";
		User user = queryRunner.query(sql, new BeanHandler<>(User.class),1,activateid);
		return user;
	}

	/**
	 * 添加分类
	 */
	@Override
	public void dao_addUI(String cname, String id) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into category values(?,?)  ";
	    queryRunner.update(sql, cname,id);
	}

	/**
	 * 根据id删除分类
	 */
	@Override
	public void dao_deleteaddUI(String cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "delete from category where cid = ?";
		queryRunner.update(sql,cid);
	}

	

}
