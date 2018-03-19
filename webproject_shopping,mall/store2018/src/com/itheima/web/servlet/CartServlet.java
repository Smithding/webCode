package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Product;
import com.itheima.cart.Cart;
import com.itheima.cart.CartItem;
import com.itheima.dao.InDao02;
import com.itheima.dao.impl.AountDao02;

/**
 * Servlet implementation class CartServlet
 */
public class CartServlet extends BaseServlet {

	public String  addToCart( HttpServletRequest request, HttpServletResponse response ) {
		try {
			// 1.获得请求参数(商品的pid, 购买数量), 
			String pid = request.getParameter("pid");
			int count = Integer.valueOf(request.getParameter("count"));
			
			//(单个商品的业务)调用业务查询商品数据
			InDao02 dao = new AountDao02();
			Product product = dao.findByPid(pid);
			//封装成CartItem对象
			CartItem cartItem = new CartItem();
			cartItem.setCount(count);
			cartItem.setProduct(product);
			// 2.从session里面获得购物车对象, 调用addToCart方法
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			if(cart == null) {
				cart = new Cart();
			}
			 cart.addToCart(cartItem);
			//存储carrt值		
			request.getSession().setAttribute("cart", cart);
			// 3.重定向到/jsp/cart.jsp页面从域里面拿出Cart展示
            response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "创建订单失败");
			return "/jsp/msg.jsp";
		} 
		
	}
	/**
	 * 删除某个商品订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String removeFromCart (HttpServletRequest request, HttpServletResponse response ){
		try {
			String pid = request.getParameter("pid");
			System.out.println(pid);
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.removeFromCart(pid);
			// 3.重定向到/jsp/cart.jsp页面从域里面拿出Cart展示
			response.sendRedirect(request.getContextPath()+"/jsp/cart.jsp");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			request.setAttribute("msg", "删除订单失败");
			return "/jsp/msg.jsp";
		}
		
	}
	
	/**
	 * 清空所有订单
	 * @param request
	 * @param response
	 * @return
	 */
	public String clearCart(HttpServletRequest request, HttpServletResponse response){
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.clearCart();
			// 3.重定向到/jsp/cart.jsp页面从域里面拿出Cart展示
			response.sendRedirect(request.getContextPath() + "/jsp/cart.jsp");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			request.setAttribute("msg", "清空订单失败");
			return "/jsp/msg.jsp";
		}
		
		
	}
	
	

}
