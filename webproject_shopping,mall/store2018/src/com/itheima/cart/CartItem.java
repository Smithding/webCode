package com.itheima.cart;

import com.itheima.bean.Product;

public class CartItem {
	
	private Product product; //购买商品的基本信息
	private int count; //购买数量
	private double subtotal; //计算的价格
	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getSubtotal() {
		return  product.getShop_price()*count;
	}
	
	@Override
	public String toString() {
		return "CartItem [product=" + product + ", count=" + count + ", subtotal=" + subtotal + "]";
	}
	
	
	

}
