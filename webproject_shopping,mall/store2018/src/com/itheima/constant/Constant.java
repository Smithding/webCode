package com.itheima.constant;

public class Constant {
	/**
	 * 激活码常量：1 激活成功 ，0 激活失败
	 */
	public static final int STATE_MA0 = 0;
	public static final int STATE_MA1 = 1;
	
	/**
	 * redis 优化常量
	 */
	public static final String STORE_CATOGRY_KEY01 = "store_catogry_key01";
	public static final String STORE_CATOGRY_KEY02 = "store_catogry_key02";
	
	/**
	 * Product 商品状态常量
	 */
	public static final int PRODUCT_MA0 = 0;
	public static final int PRODUCT_MA1 = 1;
	
	/**
	 * 一页显示的数量
	 */
	public static final int PRODUCT_PAGE_SIZE = 12;
	/**
	 * 订单状态 0:未付款	1:已付款	2:已发货	3.已完成
	 */
	public static final int  NO_PAYMENT= 0;
	public static final int  YES_PAYMENT= 1;
	public static final int  YES_DELIVER_CARGO= 2;
	public static final int  YES_COMPLETE= 3;
	/**
	 * 订单分页，一页显示的数量
	 */
	public static final int ORDER_PAGE_SIZE = 2;
	
}
