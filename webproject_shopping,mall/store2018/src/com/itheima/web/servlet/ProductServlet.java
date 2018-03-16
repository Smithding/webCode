package com.itheima.web.servlet;

import java.awt.print.Pageable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.PageBean;
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
			request.setAttribute("p", list);
			return "/jsp/product_info.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查单个商品失败");
			return "/jsp/msg.jsp";
		}

	}
    /**
     * 分类商品的分页展示
     * @param request
     * @param response
     * @return
     * @throws SQLException 
     */
    public String findByPage(HttpServletRequest request, HttpServletResponse response) {
    	try {
			// 1. 在findByPage()方法里面
			// 2.获得请求参数(curPage, cid)
			int curPage = Integer.valueOf(request.getParameter("curPage"));
			String cid = request.getParameter("cid");
			
			// 3.调用业务,获得分页的数据PageBean
			InIndexServlet in = new IndexServletImpl();
			PageBean<Product> page = in.findByPage(curPage, cid);
			

			// 4.把PageBean存到request,转发到 /jsp/product_list.jsp
			 request.setAttribute("page",page);
			 return "/jsp/product_list.jsp";
			 
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查单个商品失败");
			return "/jsp/msg.jsp";
		}
    }
	
	
}
