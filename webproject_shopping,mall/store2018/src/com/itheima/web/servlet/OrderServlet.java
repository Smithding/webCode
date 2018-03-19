package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.User;
import com.itheima.cart.Cart;
import com.itheima.cart.CartItem;
import com.itheima.constant.Constant;
import com.itheima.service.InOrderServlet;
import com.itheima.service.impl.OrderServletImpl;
import com.itheima.utils.UUIDUtils;
import com.mysql.jdbc.Connection;

/**
 * Servlet implementation class OrderServlet
 */
public class OrderServlet extends BaseServlet {

	public String save (HttpServletRequest request, HttpServletResponse response ) throws SQLException {
		try {
			//是否登录，没有登录不能创建订单
			User user = (User) request.getSession().getAttribute("user");
			if( user == null){
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				cart.clearCart();
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
				
			}
			
			System.out.println("接收导数据");
		     
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			//创建属于用户的订单功能
			Order order = new Order();
			order.setOid(UUIDUtils.getId());
			order.setOrdertime(new Date());
			order.setTotal(cart.getTotal());
			order.setState(Constant.NO_PAYMENT);
			order.setUser(user);
			
			List<OrderItem> items = new ArrayList<OrderItem>();
			Collection<CartItem> vlaues = cart.getvlaues();
			for (CartItem e : vlaues) {
				OrderItem orderItem = new OrderItem();
				orderItem.setCount(e.getCount());
				orderItem.setSubtotal(e.getSubtotal());
				orderItem.setItemid(UUIDUtils.getId());
				orderItem.setProduct(e.getProduct());
				orderItem.setOrder(order);
				
				items.add(orderItem);
			}
			
			order.setItems(items);
			
			//调用业务
			InOrderServlet Impl = new OrderServletImpl();
			Impl.save(order);
			
			request.getSession().setAttribute("order", order );
			response.sendRedirect(request.getContextPath()+"/jsp/order_info.jsp");
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "创建订单失败");
			return "/jsp/msg.jsp";
		}
	}
/*	*//**
	 * 没有用户登录，点击购物车跳转到登录页
	 * @param request
	 * @param response
	 * @return
	 *//*
	public String delete(HttpServletRequest request, HttpServletResponse response){
		try {
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			cart.clearCart();
			// 3.重定向到/jsp/cart.jsp页面从域里面拿出Cart展示
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}*/
}
