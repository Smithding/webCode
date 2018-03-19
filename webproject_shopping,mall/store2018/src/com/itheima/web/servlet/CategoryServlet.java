package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itheima.service.InService;
import com.itheima.service.impl.AountService;

/**
 * 商品分类查询
 * Servlet implementation class CategoryServlet
 */
public class CategoryServlet extends BaseServlet {

	public void fandAll(HttpServletRequest request, HttpServletResponse response) throws IOException  {
		
			try {
				// 信息分类业务调用
				InService categoryServletImp = new AountService();
				String data = categoryServletImp.findAll();
				//System.out.println("servlet接收到的数据为：" + data);

				// 将接收到的数据反应给前端
				response.getWriter().print(data);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
}
