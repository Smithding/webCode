package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.PageBean;
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
	Product findByPid(String pid) throws SQLException;
	
	
	/**
	 * 统计当前类别下的商品数量
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	int selectCount(String cid) throws SQLException;
	/**
	 * 分类查询当前列标下的商品
	 * @param a
	 * @param b
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	List<Product> selectLimit(int a, int b, String cid) throws SQLException;
	
	/**
	 * 根据pid查询商品所在的分类
	 * @param pid
	 * @return
	 */
	Category findCategoryByCid(String pid) throws SQLException;
}
