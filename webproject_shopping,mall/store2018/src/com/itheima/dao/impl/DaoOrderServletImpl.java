package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.Product;
import com.itheima.dao.InDaoOrderServletImpl;
import com.itheima.utils.C3P0Utils;

public  class DaoOrderServletImpl implements InDaoOrderServletImpl {

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

	/**
	 * 查询订单的总数量
	 * @throws SQLException 
	 */
	@Override
	public int dao_sumPage(String uid) throws SQLException  {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select count(*) from orders where uid = ?";  
		Long n = (Long) queryRunner.query(sql, new ScalarHandler(),uid);
		return n.intValue();
	}

	/**
	 * 查询所有商品订单
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 */
	@Override
	public List<Order> dao_selectList(String uid, int a, int b) throws Exception {
		QueryRunner queryRunner = new  QueryRunner(C3P0Utils.getDataSource());
 		String sql = "select * from orders where uid = ? limit ?,?";
 		//把每个订单项查找出来
 		List<Order> list = queryRunner.query(sql, new BeanListHandler<>(Order.class),uid,a,b);
 		//遍历list ，封装每个订单项
 		for(Order order : list) {
 			sql = "select * from orderitem oi , product p where oi.pid = p.pid and oi.oid = ?";
 			      
 			//查询商品到一个订单
 			List<Map<String, Object>> maplist = queryRunner.query(sql, new MapListHandler(),order.getOid());
 			//一个map对应一个OrderItem对象（并且里面包含了product）
 			for(Map<String,Object> map : maplist) {
 				Product product = new Product();
 				BeanUtils.populate(product, map);
 				
 				OrderItem orderItem = new OrderItem();
 				BeanUtils.populate(orderItem, map);
 				
 				orderItem.setProduct(product);
 				order.getItems().add(orderItem);
 				
 			}
 		}
 		
		return list;
	}

	/**
	 * 订单详情支付页面
	 */
	@Override
	public Order dao_findByOId(String oid) throws Exception {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "select * from orders where oid = ?";
	    Order order = queryRunner.query(sql, new BeanHandler<>(Order.class),oid);
	    
	    sql = "select * from orderitem io ,product p where io.pid = p.pid and oid = ? ";
	   List<Map<String, Object>> query = queryRunner.query(sql,new MapListHandler(),order.getOid());
	   
	   for (Map<String, Object> map : query) {
		  //将orderItem对象和product对象添加进去
		  OrderItem orderItem = new OrderItem();
		  Product product = new Product();
		  BeanUtils.populate(product, map);
		  BeanUtils.populate(orderItem, map);
		  
		  orderItem.setProduct(product);
		  
		  order.getItems().add(orderItem);
	}
	    
	    
		return order;
	}

	/**
	 * 第三方支付功能
	 * @throws SQLException 
	 */
	@Override
	public void dao_updateOrder(Order order) throws SQLException {
		QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
		String sql = "update orders set  ordertime = ?, state = ?, address = ?,name = ?,telephone = ? where oid = ?";
		Object[] params = {order.getOrdertime(), order.getState(), order.getAddress(),order.getName(),
				order.getTelephone(),order.getOid()};
		queryRunner.update(sql,params);
		
	}

	  

}
