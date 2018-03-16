package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;

/**
 * 最热商品和最新商品查询
 * @author Administrator
 *
 */
public interface InIndexServlet {
	/**
	 * 热门商品
	 * @param hottest 
	 * @return
	 * @throws SQLException 
	 */
	List<Product> hotList(int hottest) throws SQLException;
	/**
	 * 最新商品
	 * @param newest 
	 * @return
	 * @throws SQLException 
	 */
	List<Product> newsList(int newest) throws SQLException; 
	/**
	 * 查询单个商品详情
	 * @param pid 
	 * @return 
	 * @throws SQLException 
	 */
	List<Product> findByPid(String pid) throws SQLException;
	/**
	 * 分类商品的分页展示
	 * @param curPage
	 * @param cid
	 * @return
	 * @throws SQLException 
	 */
	PageBean<Product> findByPage(int curPage, String cid) throws SQLException;
	

}
