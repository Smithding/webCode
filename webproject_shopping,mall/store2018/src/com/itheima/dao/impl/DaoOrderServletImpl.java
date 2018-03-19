package com.itheima.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.dao.InDaoOrderServletImpl;
import com.itheima.utils.C3P0Utils;

public class DaoOrderServletImpl implements InDaoOrderServletImpl {

	/**
	 * `oid` varchar(32) NOT NULL,
	 `ordertime` datetime DEFAULT NULL,
	 `total` double DEFAULT NULL,
	 `state` int(11) DEFAULT NULL,
	 `address` varchar(30) DEFAULT NULL,
     `name` varchar(20) DEFAULT NULL,
	 `telephone` varchar(20) DEFAULT NULL,
	 `uid` varchar(32) DEFAULT NULL,
	  
	 * 在orders表里面中插入一条数据
	 * @throws SQLException 
	 */
	public void dao_save(Order order) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
		Object[] pame = {order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getState(),order.getAddress(),order.getName(),order.getTelephone(),order.getUser().getUid()}; 
		queryRunner.update(sql,pame);
	}

	/**
	 * itemid` varchar(32) NOT NULL,
	 `count` int(11) DEFAULT NULL,
	 `subtotal` double DEFAULT NULL,
	 `pid` varchar(32) DEFAULT NULL,
	 `oid` varchar(32) DEFAULT NULL,
	 * 在orderitem表里面中插入多条(1到n)数据
	 * @throws SQLException 
	 */
	public void dao_saveOrderItem(OrderItem or) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "insert into orderitem values(?,?,?,?,?)";
		Object[] pame = {or.getItemid(),or.getCount(),or.getSubtotal()
				,or.getProduct().getPid(),or.getOrder().getOid()}; 
		queryRunner.update(sql,pame);
	}

}
