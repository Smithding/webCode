package com.itheima.web.filter;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.bean.User;
import com.itheima.service.InService;
import com.itheima.service.impl.AountService;
import com.itheima.utils.CookUtils;



/**
 * Servlet Filter implementation class Filterdemo
 */
public class Filterdemo implements Filter {

 
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		
		
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse)resp;
		
		User user = (User) request.getSession().getAttribute("user");
		
		if( user == null){
			Cookie[] cookies = request.getCookies();
			Cookie kies = CookUtils.getCookieByName(cookies, "with");
			
			if (kies != null) {
				try {
					// 提取aout值
					String value = kies.getValue();
					String username = value.split("v")[0];
					String password = value.split("v")[1];

					System.out.println("通过拦截器自动登录......");

					// 调取业务实现自动登录
					InService serviceRegister = new AountService();
					User tmp = serviceRegister.setRegister(username, password);
					// 是否成功登录
					if (tmp != null) {
						request.getSession().setAttribute("user", tmp);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		chain.doFilter(request, response);
	}

	
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
