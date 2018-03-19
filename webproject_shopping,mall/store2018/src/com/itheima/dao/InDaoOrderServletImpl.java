package com.itheima.dao;

import java.sql.SQLException;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;

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

}
