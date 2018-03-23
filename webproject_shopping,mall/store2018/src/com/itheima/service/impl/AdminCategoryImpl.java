package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Order;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.constant.Constant;
import com.itheima.dao.InDao;
import com.itheima.dao.InDaoOrderServletImpl;
import com.itheima.dao.impl.AountDao;
import com.itheima.dao.impl.DaoOrderServletImpl;
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

	/**
	 * 添加所有商品
	 * @throws SQLException 
	 */
	@Override
	public void addProductServlet(Product product) throws SQLException {
		InDao dao = new AountDao();
		dao.dao_addProductServlet(product);
		
	}

	/**
	 * 后台展示所有订单
	 */
	@Override
	public PageBean<Order> findByState(int state ,int curPage) throws SQLException {
		        InDao dao = new AountDao();
		        
		        //1.一页显示的数量
				int curSize = Constant.HOUTAI_PAGE_SIZE;
				//2.订单的总数量
				int count = dao.State_sumPage(state);
				//3.总页数计算
				int sumPage = 0;
				if(count % curSize == 0) {
					sumPage = count / curSize;
				}else {
					sumPage = count / curSize + 1;
				}
				
				//4.商品的集合
				int b = curSize;
				int a = (curPage - 1)*b;
				List<Order> list = null;
				if (state >=4) {
					//5.遍历的所有订单
					list = dao.State_sum01(a,b);
				} else {
					//5.遍历的指定订单
				    list = dao.State_sum02(state,a,b);
				}
				
				
				
				/*创建分类页码对象*/
				PageBean<Order> pageBean = new PageBean<Order>();	
				//封装当前页面
				pageBean.setCurPage(curPage);
				//封装一页显示的数量
				pageBean.setCurSize(curSize);
				//封装总数量
				pageBean.setCount(count);
				//封装的总页数
				pageBean.setSumPage(sumPage);
				//封装一页上显示的集合
				pageBean.setList(list);
				
				return pageBean;
		
	}

	/**
	 * 后台-订单详情
	 * @return 
	 * @throws Exception 
	 */
	@Override
	public Order findByOid(String oid) throws Exception {
		InDaoOrderServletImpl dao = new DaoOrderServletImpl();
		return dao.dao_findByOId(oid);
	}

	/**
	 * 后台-修改发货状态
	 */
	@Override
	public void updateState(Order order) throws Exception {	
		InDaoOrderServletImpl dao = new DaoOrderServletImpl();
		dao.dao_updateOrder(order);
	}

	
}
