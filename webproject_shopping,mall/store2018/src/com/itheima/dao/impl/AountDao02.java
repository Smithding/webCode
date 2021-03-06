package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.bean.Category;
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
	public Product findByPid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from product where pid = ?";
		Product query = queryRunner.query(sql, new BeanHandler<Product>(Product.class),pid);
		return query;
	}

	/**
	 * 统计当前类别下订单数量
	 * @throws SQLException 
	 */
	public int selectCount(String cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long query =  (Long) queryRunner.query(sql, new ScalarHandler(),cid);
	    return query.intValue();
	}

	/**
	 * 分类查询当前列标下的全部订单
	 * @throws SQLException 
	 */
	public List<Product> selectLimit(int a, int b, String cid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql ="select * from product where cid = ? limit ? ,?";
		List<Product> query = queryRunner.query(sql, new BeanListHandler<Product>(Product.class),cid,a,b);
		return query;
	}

	/**
	 * 根据pid查询商品所在分类
	 * @throws SQLException 
	 */
	public Category findCategoryByCid(String pid) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "SELECT * FROM category WHERE cid = (SELECT cid FROM product WHERE pid = ?)";
		return queryRunner.query(sql, new BeanHandler<Category>(Category.class),pid);
	}

	
}
