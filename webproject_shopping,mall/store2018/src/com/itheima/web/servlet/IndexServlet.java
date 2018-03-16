package com.itheima.web.servlet;


import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Product;
import com.itheima.constant.Constant;
import com.itheima.service.InIndexServlet;
import com.itheima.service.impl.IndexServletImpl;

/**
 * 查询最热商品和最新商品
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends BaseServlet {
	
	public String index(HttpServletRequest request, HttpServletResponse response) {
		try {
			//1.调用业务,获得热门商品(List hotList)和最新商品(List newsList)
			int hottest = Constant.PRODUCT_MA1; //最热商品
			int newest = Constant.PRODUCT_MA0 ;//最新商品
			
			InIndexServlet index = new IndexServletImpl();
			List<Product> hotList = index.hotList(hottest);
			List<Product> newsList = index.newsList(newest);
	
			//2.把数据存到request里面,转发到网站首页
			request.setAttribute("hotList",hotList);
			request.setAttribute("newsList",newsList);

			return "/jsp/index.jsp";
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询最热商品失败");
			return "/jsp/msg.jsp";
			
		} 
		
	}

}
