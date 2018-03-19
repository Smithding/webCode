package com.itheima.service;

import java.sql.SQLException;

import com.itheima.bean.Order;

public interface InOrderServlet {

	/**
	 * 在orders表里面中插入一条数据,在orderitem表里面中插入多条(1到n)数据
	 * @param order
	 * @throws SQLException 
	 */
	void save(Order order) throws SQLException;

}
