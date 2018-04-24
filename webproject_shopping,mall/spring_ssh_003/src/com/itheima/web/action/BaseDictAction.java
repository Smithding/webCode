package com.itheima.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONActionRedirectResult;

import com.itheima.bean.BaseDict;
import com.itheima.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class BaseDictAction  extends ActionSupport{
	
	//获得TypeCode
	private String dict_type_code;
	
	private BaseDictService baseDictService;
	
	public String findByTypeCode() throws IOException{
		System.out.println("findByTypeCode..."+dict_type_code);
		
		//获得Response
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		//调用业务
		List<BaseDict> list =  baseDictService.findByTypeCode(dict_type_code);
		//把list转成JSON数组, 响应给前端
		String data = JSONArray.fromObject(list).toString();
		response.getWriter().print(data);
		return NONE;
	}

	
	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}
	
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
}
