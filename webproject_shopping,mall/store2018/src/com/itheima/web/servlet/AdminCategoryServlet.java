package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Category;
import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.constant.Constant;
import com.itheima.dao.InDao;
import com.itheima.dao.impl.AountDao;
import com.itheima.service.AdminCategoryService;
import com.itheima.service.impl.AdminCategoryImpl;
import com.itheima.utils.JsonUtil;
import com.itheima.utils.UUIDUtils;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

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
			request.getSession().setAttribute("listAll", list);
			
			return "/admin/product/list.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 后台-查询全部订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByState (HttpServletRequest request, HttpServletResponse response ){
		try {
			
			int state = Integer.valueOf( request.getParameter("state")); 
			int curPage = Integer.valueOf( request.getParameter("curPage")); 
			//1.调用业务,根据状态获得订单List<Order> list
			AdminCategoryService cate = new AdminCategoryImpl();
			PageBean<Order> pageBean = cate.findByState(state,curPage);
			//2.把list存到域里面
			request.getSession().setAttribute("pageBean", pageBean);
			//3.转发到 /admin//order/list.jsp

			return "/admin/order/list.jsp";
			
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询全部点单失败");
			return "/jsp/msg.jsp";
		}
		
	}
	/**
	 * 后台-点击点单详情
	 * @param request
	 * @param response
	 * @return
	 */
	public String findByOid (HttpServletRequest request, HttpServletResponse response){
		         
		try {
			// 获取参数
			String oid = request.getParameter("oid");
			// 调用业务
			AdminCategoryService cate = new AdminCategoryImpl();
			Order findByOid = cate.findByOid(oid);
			// 拿出Order中的list<Product>集合
			List<OrderItem> list = findByOid.getItems();
			// 装换为jsno
			String list2json = JsonUtil.list2json(list);
			response.getWriter().print(list2json);

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public String updateState (HttpServletRequest request, HttpServletResponse response){
		          
		try {
			// 获取参数
			String oid = request.getParameter("oid");
		    System.out.println(oid);
			
			// 调用业务,获得orders对象
			AdminCategoryService cate = new AdminCategoryImpl();
			Order order = cate.findByOid(oid);
			
			order.setState(Constant.YES_DELIVER_CARGO);
			
			cate.updateState(order);
			
			response.sendRedirect(request.getServletPath()+"${pageContext.request.contextPath}/adminCategoryServlet?method=findByState&state=2&curPage=1");
			return null;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
}


