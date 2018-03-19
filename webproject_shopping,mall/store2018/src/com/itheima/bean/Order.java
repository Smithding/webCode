package com.itheima.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 * @author Administrator
 *
 */
public class Order {
   
	/**
	 * id   

	下单时间

	订单金额

	订单状态

	收货人地址

	收货人名字

	收货人电话

	user对象

	订单项列表(List<OrderItem> list)

	 */
	private String oid; //id
	private Date ordertime; //下单时间
	private Double total; //订单金额
	
	private Integer state;//订单状态 0:未付款	1:已付款	2:已发货	3.已完成
	private String address; //收货人地址
	private String name; //收货人名字
	
	private String telephone; //收货人电话
	
	//表示当前订单属于那个用户
	private User user; 
	 
	//表示当前订单包含的订单项
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	

	public String getOid() {
		return oid;
	}



	public void setOid(String oid) {
		this.oid = oid;
	}



	public Date getOrdertime() {
		return ordertime;
	}



	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}



	public Double getTotal() {
		return total;
	}



	public void setTotal(Double total) {
		this.total = total;
	}



	public Integer getState() {
		return state;
	}



	public void setState(Integer state) {
		this.state = state;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getTelephone() {
		return telephone;
	}



	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public List<OrderItem> getItems() {
		return items;
	}



	public void setItems(List<OrderItem> items) {
		this.items = items;
	}



	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", name=" + name + ", telephone=" + telephone + ", user=" + user + ", items=" + items + "]";
	}
	
	
}
