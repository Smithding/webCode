package com.itheima.service.impl;


import java.awt.PageAttributes;
import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Order;
import com.itheima.bean.PageBean;
import com.itheima.constant.Constant;
import com.itheima.dao.InDaoOrderServletImpl;
import com.itheima.dao.impl.DaoOrderServletImpl;
import com.itheima.service.InfindByPage;

public class ServiceFindByPageImpl implements InfindByPage {

	/**
	 * 商品订单的分页查询
	 * @throws Exception 
	 */
	@Override
	public PageBean<Order> findByPage(int curPage, String uid) throws Exception {
		InDaoOrderServletImpl dao = new DaoOrderServletImpl();
		//1.一页显示的数量
		int curSize = Constant.ORDER_PAGE_SIZE;
		//2.订单的总数量
		int count = dao.dao_sumPage(uid);
		//3.总页数计算
		int sumPage = 0;
		if(count % curSize == 0) {
			sumPage = count / curSize;
		}else {
			sumPage = count / curSize + 1;
		}
		
		//4.商品的集合
		int b = curSize;
		int a = (curPage - 1)*b;
		
		//5.遍历的所有订单
		List<Order> list = dao.dao_selectList(uid,a,b);
		
		
		/*创建分类页码对象*/
		PageBean<Order> pageBean = new PageBean<Order>();	
		//封装当前页面
		pageBean.setCurPage(curPage);
		//封装一页显示的数量
		pageBean.setCurSize(curSize);
		//封装总数量
		pageBean.setCount(count);
		//封装的总页数
		pageBean.setSumPage(sumPage);
		//封装一页上显示的集合
		pageBean.setList(list);
		
		return pageBean;
	}

	/**
	 * 订单详情支付页面
	 * @return 
	 */
	@Override
	public Order findByOId(String oid) throws Exception {
		InDaoOrderServletImpl dao = new DaoOrderServletImpl();
		return dao.dao_findByOId(oid);
	}

	

}
