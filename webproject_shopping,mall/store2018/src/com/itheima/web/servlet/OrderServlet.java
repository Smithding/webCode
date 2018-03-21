package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.Order;
import com.itheima.bean.OrderItem;
import com.itheima.bean.PageBean;
import com.itheima.bean.User;
import com.itheima.cart.Cart;
import com.itheima.cart.CartItem;
import com.itheima.constant.Constant;
import com.itheima.service.InOrderServlet;
import com.itheima.service.InfindByPage;
import com.itheima.service.impl.OrderServletImpl;
import com.itheima.service.impl.ServiceFindByPageImpl;
import com.itheima.utils.PaymentUtil;
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
			
			Cart cart = (Cart) request.getSession().getAttribute("cart");
			
			//创建属于用户的一个订单实体
			Order order = new Order();
			order.setOid(UUIDUtils.getId());
			order.setOrdertime(new Date());
			order.setTotal(cart.getTotal());
			order.setState(Constant.NO_PAYMENT);
			order.setUser(user);
			//创建订单集合，所有单个商品的信息，存储到这个集合里面
			List<OrderItem> items = new ArrayList<OrderItem>();
			//创建一条商品信息
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
			Cart cart2 = (Cart) request.getSession().getAttribute("cart");
			cart2.clearCart();
			response.sendRedirect(request.getContextPath()+"/jsp/order_info.jsp");
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "创建订单失败");
			return "/jsp/msg.jsp";
		}
	}

	/**
	 * 创建我的订单实体功能
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public String findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, Exception {
    try {
		//1. 获得session里面的user对象,获得uid
			User user = (User) request.getSession().getAttribute("user");
			if( user == null ) {
				response.sendRedirect(request.getContextPath()+"/jsp/login.jsp");
			}
		   	//获得请求参数(curPage)
			int curPage = Integer.valueOf(request.getParameter("curPage"));
		   	//调用业务,获得分页数据 PageBean<Order> pageBean  
			InfindByPage page = new ServiceFindByPageImpl();
			PageBean<Order> pageBean = page.findByPage(curPage,user.getUid());

		   	//把pageBean  存到request里面, 转发到 /jsp/order_list.jsp
		    request.getSession().setAttribute("bean", pageBean); 
			return "/jsp/order_list.jsp";
			
			
	} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("msg", "查询我的订单失败");
		return "/jsp/msg.jsp";
	}
  }
	/**
	 * 订单详情支付页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public String findByOId(HttpServletRequest request, HttpServletResponse response) {
		try {
			//获取oid
			String oid = request.getParameter("oid");
			//调用业务
			InfindByPage page = new ServiceFindByPageImpl();
			Order order = page.findByOId(oid);
			
			//3. 把order存到request里面, 转发到 order_info.jsp页面展示
			request.getSession().setAttribute("order", order);
			return "/jsp/order_info.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "跳转支付页面失败");
			return "/jsp/msg.jsp";
		}
	}
	/**
	 * 第三方支付功能
	 * @param request
	 * @param respone
	 * @return
	 * @throws Exception
	 */
	public String pay(HttpServletRequest request,HttpServletResponse respone) throws Exception{
		try {
			//接受参数
			String address=request.getParameter("address");
			String name=request.getParameter("name");
			String telephone=request.getParameter("telephone");
			String oid=request.getParameter("oid");
			
			System.out.println(address+""+name+""+telephone+""+oid);
			//通过id获取order
			InfindByPage page = new ServiceFindByPageImpl();
			Order order = page.findByOId(oid);
			
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			
			//更新order
			
			page.updateOrder(order);
			

			// 组织发送支付公司需要哪些数据
			String pd_FrpId = request.getParameter("pd_FrpId");
			String p0_Cmd = "Buy";
			String p1_MerId = ResourceBundle.getBundle("merchantInfo").getString("p1_MerId");
			String p2_Order = oid;
			String p3_Amt = "0.01";
			String p4_Cur = "CNY";
			String p5_Pid = "";
			String p6_Pcat = "";
			String p7_Pdesc = "";
			// 支付成功回调地址 ---- 第三方支付公司会访问、用户访问
			// 第三方支付可以访问网址
			String p8_Url = ResourceBundle.getBundle("merchantInfo").getString("responseURL");
			String p9_SAF = "";
			String pa_MP = "";
			String pr_NeedResponse = "1";
			// 加密hmac 需要密钥
			String keyValue = ResourceBundle.getBundle("merchantInfo").getString("keyValue");
			String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
					p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
					pd_FrpId, pr_NeedResponse, keyValue);

			
			//发送给第三方
			StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
			sb.append("p0_Cmd=").append(p0_Cmd).append("&");
			sb.append("p1_MerId=").append(p1_MerId).append("&");
			sb.append("p2_Order=").append(p2_Order).append("&");
			sb.append("p3_Amt=").append(p3_Amt).append("&");
			sb.append("p4_Cur=").append(p4_Cur).append("&");
			sb.append("p5_Pid=").append(p5_Pid).append("&");
			sb.append("p6_Pcat=").append(p6_Pcat).append("&");
			sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
			sb.append("p8_Url=").append(p8_Url).append("&");
			sb.append("p9_SAF=").append(p9_SAF).append("&");
			sb.append("pa_MP=").append(pa_MP).append("&");
			sb.append("pd_FrpId=").append(pd_FrpId).append("&");
			sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
			sb.append("hmac=").append(hmac);
			
			respone.sendRedirect(sb.toString());
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 第三方支付接收功能
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String callback(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String p1_MerId = request.getParameter("p1_MerId");
		String r0_Cmd = request.getParameter("r0_Cmd");
		String r1_Code = request.getParameter("r1_Code");
		String r2_TrxId = request.getParameter("r2_TrxId");
		String r3_Amt = request.getParameter("r3_Amt");
		String r4_Cur = request.getParameter("r4_Cur");
		String r5_Pid = request.getParameter("r5_Pid");
		String r6_Order = request.getParameter("r6_Order");
		String r7_Uid = request.getParameter("r7_Uid");
		String r8_MP = request.getParameter("r8_MP");
		String r9_BType = request.getParameter("r9_BType");
		String rb_BankId = request.getParameter("rb_BankId");
		String ro_BankOrderId = request.getParameter("ro_BankOrderId");
		String rp_PayDate = request.getParameter("rp_PayDate");
		String rq_CardNo = request.getParameter("rq_CardNo");
		String ru_Trxtime = request.getParameter("ru_Trxtime");
		// 身份校验 --- 判断是不是支付公司通知你
		String hmac = request.getParameter("hmac");
		String keyValue = ResourceBundle.getBundle("merchantInfo").getString(
				"keyValue");

		// 自己对上面数据进行加密 --- 比较支付公司发过来hamc
		boolean isValid = PaymentUtil.verifyCallback(hmac, p1_MerId, r0_Cmd,
				r1_Code, r2_TrxId, r3_Amt, r4_Cur, r5_Pid, r6_Order, r7_Uid,
				r8_MP, r9_BType, keyValue);
		if (isValid) {
			// 响应数据有效
			if (r9_BType.equals("1")) {
				// 浏览器重定向
				System.out.println("111");
				request.setAttribute("msg", "您的订单号为:"+r6_Order+",金额为:"+r3_Amt+"已经支付成功,等待发货~~");
				
			} else if (r9_BType.equals("2")) {
				// 服务器点对点 --- 支付公司通知你
				System.out.println("付款成功！222");
				// 修改订单状态 为已付款
				// 回复支付公司
				response.getWriter().print("success");
			}
			
			//修改订单状态
			InfindByPage orderItem = new ServiceFindByPageImpl();
			Order order = orderItem.findByOId(r6_Order);
			order.setState(Constant.YES_PAYMENT);
			orderItem.updateOrder(order);
			

			
			orderItem.updateOrder(order);
			
		} else {
			// 数据无效
			System.out.println("数据被篡改！");
		}
		
		
		return "/jsp/msg.jsp";
		
	}
}
