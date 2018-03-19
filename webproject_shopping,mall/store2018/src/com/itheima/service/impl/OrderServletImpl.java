package com.itheima.service.impl;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.dao.InDaoOrderServletImpl;
import com.itheima.dao.impl.DaoOrderServletImpl;
import com.itheima.service.InOrderServlet;
import com.itheima.utils.ConnectionManager;

public class OrderServletImpl implements InOrderServlet {

	/**
	 * 在orders表里面中插入一条数据,在orderitem表里面中插入多条(1到n)数据
	 * @throws SQLException 
	 */
	public void save(Order order)  {
		Connection connection = null;
		try {
		    connection = ConnectionManager.getConnectionByLocalThread();
			connection.setAutoCommit(false);
			
			InDaoOrderServletImpl dao = new DaoOrderServletImpl();
			dao.dao_save(order);
			
			List<OrderItem> items = order.getItems();
			for (OrderItem orderItem : items) {
				dao.dao_saveOrderItem(orderItem);
			}
			
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		
	}

	

}
