package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Product;

public interface InDao02 {

	/**
	 * 最热商品查询-dao接口
	 * @param hottest
	 * @return
	 * @throws SQLException
	 */
	List<Product> dao_hotList(int hottest) throws SQLException;
	/**
	 * 最新商品查询-dao接口
	 * @param newest
	 * @return
	 * @throws SQLException
	 */
	List<Product> dao_newsList(int newest) throws SQLException;
	/**
	 *  查询单个商品详情
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	List<Product> findByPid(String pid) throws SQLException;
}
