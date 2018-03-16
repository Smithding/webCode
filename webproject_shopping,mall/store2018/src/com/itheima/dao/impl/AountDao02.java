package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.bean.Product;
import com.itheima.dao.InDao02;
import com.itheima.utils.C3P0Utils;

public class AountDao02 implements InDao02 {



	/**
	 * 最热商品查询
	 * @throws SQLException 
	 */
	public List<Product> dao_hotList(int hottest) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where is_hot = ?";
		List<Product> query = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),hottest);
		return query;
	}
	
	/**
	 * 最新商品查询
	 * @throws SQLException 
	 */
	public List<Product> dao_newsList(int newest) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pflag = ?";
		List<Product> query = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),newest);
		return query;
	
	}
	/**
	 * 查询单个商品详情
	 * @throws SQLException 
	 */
	public List<Product> findByPid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pid = ?";
		List<Product> query = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),pid);
		return query;
	}

}
