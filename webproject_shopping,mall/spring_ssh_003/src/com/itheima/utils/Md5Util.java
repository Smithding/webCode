package com.itheima.utils;


import org.springframework.util.DigestUtils;

public class Md5Util {
	public static  String encodePwd(String password){
		for (int i = 0; i < 5; i++) {
			password = DigestUtils.md5DigestAsHex(password.getBytes());
		}
		return password;
	}
}
