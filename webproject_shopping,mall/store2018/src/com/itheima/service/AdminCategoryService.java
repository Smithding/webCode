package com.itheima.service;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Product;

public interface AdminCategoryService {

	/**
	 * 后台商品分类展示
	 * @return
	 * @throws SQLException 
	 */
	List<Category> findAll() throws SQLException;

	/**
	 * 全部商品展示
	 * @return
	 * @throws Exception 
	 */
	List<Product> findAll02() throws Exception;

	/**
	 * 添加商品分类
	 * @param cname
	 * @param id 
	 * @throws Exception
	 */
	void addUI(String cname, String id)throws Exception;

	/**
	 * 后台-删除分类
	 * @param cid
	 */
	void deleteaddUI(String cid)throws Exception;

	

}
