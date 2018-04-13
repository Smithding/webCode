package com.itheima.web;



import com.itheima.bean.User;
import com.itheima.service.UserService;

import com.opensymphony.xwork2.ActionSupport;


public class UserAction extends ActionSupport {

	private UserService userService;
	  
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regist(){
		Integer uid = 1;
		System.out.println("Æô¶¯");
		userService.seva();
		User user = userService.findByUid(uid);
		System.out.println(user.toString());
		return NONE;
	}
}
