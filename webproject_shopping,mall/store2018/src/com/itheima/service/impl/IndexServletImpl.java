package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Product;
import com.itheima.dao.InDao;
import com.itheima.dao.InDao02;
import com.itheima.dao.impl.AountDao;
import com.itheima.dao.impl.AountDao02;
import com.itheima.service.InIndexServlet;

public class IndexServletImpl implements InIndexServlet {

    /**
     * 最热商品
     * @throws SQLException 
     */
	public List<Product> hotList(int hottest) throws SQLException {
		InDao02 dao = new AountDao02();
		return dao.dao_hotList(hottest);
	}

	/**
	 * 最新商品
	 * @throws SQLException 
	 */
	public List<Product> newsList(int newest) throws SQLException {
		InDao02 dao = new AountDao02();
		return dao.dao_newsList(newest);
	}
	/**
	 * 查询单个商品详情
	 * @throws SQLException 
	 */
	public List<Product> findByPid(String pid) throws SQLException {
		InDao02 dao = new AountDao02();
		return  dao.findByPid(pid);
	}

}
