package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Order;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;

public interface AdminCategoryService {

	/**
	 * 后台商品分类展示
	 * @return
	 * @throws SQLException 
	 */
	List<Category> findAll() throws SQLException;

	/**
	 * 全部商品展示
	 * @return
	 * @throws Exception 
	 */
	List<Product> findAll02() throws Exception;

	/**
	 * 添加商品分类
	 * @param cname
	 * @param id 
	 * @throws Exception
	 */
	void addUI(String cname, String id)throws Exception;

	/**
	 * 后台-删除分类
	 * @param cid
	 */
	void deleteaddUI(String cid)throws Exception;

	/**
	 * 添加商品
	 * @param product
	 * @throws SQLException 
	 */
	void addProductServlet(Product product) throws SQLException;

	/**
	 * 查询所有订单
	 * @param state 
	 * @return
	 * @throws SQLException
	 */
	PageBean<Order> findByState(int state, int curPage) throws SQLException;

	/**
	 * 后台-订单详情
	 * @param oid 
	 * @return 
	 * @throws Exception 
	 */
	Order findByOid(String oid)throws Exception;

	/**
	 * 后台-修改发货状态
	 * @param oid
	 * @return
	 * @throws Exception 
	 */
	void updateState(Order order) throws Exception;

	

}
