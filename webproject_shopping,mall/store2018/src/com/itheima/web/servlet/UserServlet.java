package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.service.InService;
import com.itheima.service.impl.AountService;
import com.itheima.utils.UUIDUtils;

public class UserServlet extends BaseServlet {

	
      /**
       * 注册
       * @param request
       * @param response
       * @return
       * @throws ServletException
       * @throws IOException
       */
	public  String register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 1. 获得请求参数
			Map<String, String[]> map = request.getParameterMap();
			User user = new User();
			BeanUtils.populate(user, map);
			
			// 2. 手动添加参数
			user.setUid(UUIDUtils.getId());
			user.setState(Constant.STATE_MA0);
			user.setCode(UUIDUtils.getCode());
			
			// 2. 调用业务
			AountService aountService = new AountService();
			aountService.Inservlet(user);
			// 3. 分发转向(跳转页面) 转发 > 重定向
			request.setAttribute("msg", "注册成功，请查收您的邮箱激活");
			return "/jsp/msg.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			// 4 .查询失败转发
			request.setAttribute("msg", "注册会员失败");
			return "/jsp/msg.jsp";
		}

	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
 	public  String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1. 获得请求参数
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		InService serviceRegister = new AountService();
		
		// 2. 调用登录业务
		User user = serviceRegister.setRegister(username, password);
		/*String activateid = user.getUid();
		// 3. 调用业务
		User tmp = serviceRegister.setactivate(activateid);*/
		
			
			// 3. 判断是否登录成功
			if (user != null) {
				//判断是否激活成功
				if( user.getState() == Constant.STATE_MA1 ) {
				   // 用户是否勾选
				   String with = request.getParameter("with");
				   String aount = request.getParameter("aount");
				// 判断是否勾选自动登录
				if( with != null  ) {
					//使用自动登录
					String str = username+"v"+password;
					Cookie cookie = new Cookie(str,str);
					cookie.setMaxAge(60*60*24*7);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				} else {
					Cookie cookie = new Cookie("with","");
					cookie.setMaxAge(0);
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				
				// 记住用户名，判断是否勾选
				if( aount != null  ) {
					Cookie cookiezz = new Cookie("aount",aount);
					cookiezz.setMaxAge(60*60*24*7);
					cookiezz.setPath(request.getContextPath());
					response.addCookie(cookiezz);
				} else {
					Cookie cookiezz = new Cookie("aount","");
					cookiezz.setMaxAge(0);
					cookiezz.setPath(request.getContextPath());
					response.addCookie(cookiezz);
				}
	
				request.getSession().setAttribute("user", user);
				response.sendRedirect(request.getContextPath()+"/index.jsp");
				return null;

			} else {
				// 5. 分发转向
				request.setAttribute("msg", "账号没有激活");
				return "/jsp/login.jsp";	
			}
			
		} else {
			// 4. 分发转向
			request.getSession().setAttribute("msg", "账号与密码不匹配");
			return "/jsp/login.jsp";
		}
		
		
		
		

		
	}

 	/**
 	 * 激活
 	 * @param request
 	 * @param response
 	 * @return
 	 * @throws SQLException
 	 */
 	public  String activate(HttpServletRequest request, HttpServletResponse response) throws SQLException{
		
		try {
			// 1. 获得请求参数
			String code = request.getParameter("code");
			// 2.调用业务激活码是否正确业务
			InService serviceActivate = new AountService();
			User user = serviceActivate.serviceActivate(code);
			
			if (user != null) {
				// 3.调用激活状态修改业务
				serviceActivate.activateModifier(code);
				request.setAttribute("msg", "激活成功，请登录账号");
                return "/jsp/msg.jsp"; 
			} else {

				request.setAttribute("msg", "激活失败");
				return "/jsp/msg.jsp";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 4. 分发转向
		return "/jsp/msg.jsp";
	}
 	/**
 	 * 注销登录
 	 * @param request
 	 * @param response
 	 * @throws IOException 
 	 */
 	public String logout( HttpServletRequest request, HttpServletResponse response ) throws IOException{
 		//获取session数据
 		HttpSession session = request.getSession(); 
 		//注销存储用户
 		if( session != null) {
 			session.removeAttribute("user");
 		}
 		  response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
 		 return null;
 	}
 	
	
}
