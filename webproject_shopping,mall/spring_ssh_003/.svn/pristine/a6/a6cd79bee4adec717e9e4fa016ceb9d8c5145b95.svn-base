package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.itheima.bean.BaseDict;
import com.itheima.bean.Customer;
import com.itheima.bean.LinkMan;
import com.itheima.bean.User;
import com.itheima.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	
	//定义User
	private User user = new User();
	
	//用户的业务
	private UserService userService;

	//登录
	public String login(){
		//获得请求参数(模型驱动)
		//调用业务, 根据登录名和登录密码 获得用户对象
		User exitUser =   userService.login(user);
		//判断用户对象, 给用户提示
		if(exitUser != null){
			//登录成功 保存登录状态 重定向到首页
			ServletActionContext.getRequest().getSession().setAttribute("exitUser", exitUser);
			return "loginSuccess";
		}else{
			//登录失败
			ActionContext.getContext().getValueStack().set("msg", "用户异常,请联系管理员...");
			return "loginError";
		}
		
	}
	
	/**
	 * 遍历所有用户
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<User> list = userService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		//忽略字段
		jsonConfig.setExcludes(new String[]{"custSource","custIndustry","custLevel","linkMans"});
		String data = JSONArray.fromObject(list, jsonConfig).toString();
		
		System.out.println("data="+data);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		return NONE;
	}
	
	
	//注册
	public String regist(){
		userService.regist(user);
		return "registSuccess";
	}
	
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

}
