package com.itheima.web.filter;

import org.aopalliance.intercept.Invocation;
import org.apache.struts2.ServletActionContext;

import com.itheima.bean.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class PrivilegeInterceptor  extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("exitUser");
		
		if( user == null ){
			System.out.println("登录成功！");
			return "toLoginPage";
		}
		
		invocation.invoke();
		return null;
	}

}
