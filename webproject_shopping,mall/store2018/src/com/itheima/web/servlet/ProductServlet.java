package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Product;
import com.itheima.dao.impl.AountDao;
import com.itheima.service.InIndexServlet;
import com.itheima.service.InService;
import com.itheima.service.impl.AountService;
import com.itheima.service.impl.IndexServletImpl;

/**
 * 查询单个商品详情
 * Servlet implementation class ProductServlet
 */
public class ProductServlet extends BaseServlet {
	
    public String findByPid( HttpServletRequest request, HttpServletResponse response ) {
		try {
			// 1. 获得请求参数(pid)
			String pid = request.getParameter("pid");
			System.out.println(pid);
			// 2. 调用业务,根据pid获得当前商品的信息(Product)
			InIndexServlet in = new IndexServletImpl();
			List<Product> list = in.findByPid(pid);
			System.out.println(list.toString());
			// 3. 把Product存到request, 转发 "/jsp/product_info.jsp"
			request.setAttribute("list", list);
			return "/jsp/product_info.jsp";
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "查单个商品失败");
			return "/jsp/msg.jsp";
		}

	}
	
	
}
