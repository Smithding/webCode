package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.dao.InDao;
import com.itheima.dao.impl.AountDao;
import com.itheima.service.AdminCategoryService;
import com.itheima.service.impl.AdminCategoryImpl;
import com.itheima.utils.UUIDUtils;

/**
 * Servlet implementation class AdminCategoryServlet
 */
public class AdminCategoryServlet extends BaseServlet {
	/*
	 * 商品后台分类 展示
	 */
	public String findAll ( HttpServletRequest request, HttpServletResponse response ) {
		try {
			//调用业务
			AdminCategoryService categ = new AdminCategoryImpl();
			List<Category> list = categ.findAll();
			//存储到会话中
			request.getSession().setAttribute("list", list);
			
			return "/admin/category/list.jsp";
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	/*
	 * 后台-添加分类
	 * 
	 */
	public String addUI(  HttpServletRequest request, HttpServletResponse response ) {
		try {
			//获取输入值
			String cname = request.getParameter("cname");
		     String id = UUIDUtils.getId();
			//调用业务
			AdminCategoryService categ = new AdminCategoryImpl();
			categ.addUI(id,cname);
			//转发到查询分类里面
			response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll");
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 后台-分类商品删除
	 * @param request
	 * @param response
	 * @return
	 */
	public String deleteaddUI(HttpServletRequest request, HttpServletResponse response) {
		try {
			String cid = request.getParameter("cid");
			//调用业务
			AdminCategoryService categ = new AdminCategoryImpl();
			categ.deleteaddUI(cid);
			//转发到查询分类里面
			response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll");
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 商品全部展示
	 * @param request
	 * @param response
	 * @return
	 */
	public String findAll02 ( HttpServletRequest request, HttpServletResponse response  ) {
		
		try {
			//调用业务
			AdminCategoryService categ = new AdminCategoryImpl();
			List<Product> list = categ.findAll02();

			//存储到公共会话里面
			request.getSession().setAttribute("list", list);
			
			return "/admin/product/list.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}


