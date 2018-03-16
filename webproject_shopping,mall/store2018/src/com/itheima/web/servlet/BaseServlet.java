package com.itheima.web.servlet;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class BaseServlet extends HttpServlet {
	

	protected void service ( HttpServletRequest request, HttpServletResponse response ) { 
		
		try {

			//响应解码
			response.setContentType("text/html;charset=UTF-8");
			
			String methodStr = request.getParameter("method");
			
			Class clazz = this.getClass();
			
			Method method = clazz.getMethod(methodStr, HttpServletRequest.class, HttpServletResponse.class);
			
			String path = (String) method.invoke(this, request, response);
    
		    if( path != null) {
		    	request.getRequestDispatcher(path).forward(request, response);
		    }
		    
		} catch (Exception e) {
			System.out.println("没有收到方法返回值");
			e.printStackTrace();
		}
	}
	

}
