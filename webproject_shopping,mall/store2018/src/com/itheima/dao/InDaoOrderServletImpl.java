package com.itheima.dao;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;

public interface InDaoOrderServletImpl {

	/**
	 * 在orders表里面中插入一条数据
	 * @param order
	 * @throws SQLException 
	 */

	void dao_save(Order order) throws SQLException;

	/**
	 * 在orderitem表里面中插入多条(1到n)数据
	 * @param orderItem
	 */
	void dao_saveOrderItem(OrderItem orderItem) throws SQLException;

	/**
	 * 查询订单的总数量
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	int dao_sumPage(String uid) throws SQLException;

	/**
	 * 查询所有订单数量
	 * @param b 
	 * @param a 
	 * @param uid 
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 * 
	 */
	List<Order> dao_selectList(String uid, int a, int b) throws Exception;

	/**
	 * 订单详情支付页面
	 * @param oid
	 * @return
	 * @throws Exception
	 */
	Order dao_findByOId(String oid)throws Exception;


   
}
