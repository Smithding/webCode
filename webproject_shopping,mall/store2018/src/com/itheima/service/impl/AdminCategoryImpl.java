package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.constant.Constant;
import com.itheima.dao.InDao;
import com.itheima.dao.impl.AountDao;
import com.itheima.service.AdminCategoryService;
import com.itheima.utils.JedisUtils;

import redis.clients.jedis.Jedis;

public class AdminCategoryImpl implements AdminCategoryService {

	/**
	 * 获取后台商品分类
	 */
	@Override
	public List<Category> findAll() throws SQLException {
		InDao dao = new AountDao();
		return dao.dao_findAll();
	}

	/**
	 * 全部商品展示
	 * @throws Exception 
	 */
	@Override
	public List<Product> findAll02() throws Exception {
		InDao dao = new AountDao();
		return  dao.hotList();
	}

	/**
	 * 添加商品分类
	 */
	@Override
	public void addUI(String cname, String id) throws Exception {
		//调用商品添加分类
		InDao dao = new AountDao();
		dao.dao_addUI(cname,id);
		
		//必须清空redis中的旧数据
	   Jedis jedis = null;
	   
	   try {
		   jedis = JedisUtils.getJedis();
		   if( jedis != null ){
			   jedis.del(Constant.STORE_CATOGRY_KEY01);
		   }
	} catch (Exception e) {
		e.printStackTrace();
	}
	   
	}

	/**
	 * 后台-根据id删除一个分类
	 */
	@Override
	public void deleteaddUI(String cid) throws Exception {
		
		//调用商品添加分类
				InDao dao = new AountDao();
				dao.dao_deleteaddUI(cid);
				
				//必须清空redis中的旧数据
			   Jedis jedis = null;
			   
			   try {
				   jedis = JedisUtils.getJedis();
				   if( jedis != null ){
					   jedis.del(Constant.STORE_CATOGRY_KEY01);
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
