package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Order;
import com.itheima.bean.PageBean;

public interface InfindByPage {

	/**
	 * 我的订单分页查询
	 * @param curPage
	 * @param uid
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	PageBean<Order> findByPage(int curPage, String uid) throws SQLException, Exception;

	/**
	 * 订单详情支付页面
	 * @param oid
	 * @return 
	 * @throws Exception
	 */
	Order findByOId(String oid) throws Exception;

	/**
	 * 第三方支付功能
	 * @param order
	 * @throws SQLException 
	 */
	void updateOrder(Order order) throws SQLException;




	
}
