package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.constant.Constant;
import com.itheima.dao.InDao;
import com.itheima.dao.InDao02;
import com.itheima.dao.impl.AountDao;
import com.itheima.dao.impl.AountDao02;
import com.itheima.service.InIndexServlet;

public class IndexServletImpl implements InIndexServlet {

    /**
     * 最热商品
     * @throws SQLException 
     */
	public List<Product> hotList(int hottest) throws SQLException {
		InDao02 dao = new AountDao02();
		return dao.dao_hotList(hottest);
	}

	/**
	 * 最新商品
	 * @throws SQLException 
	 */
	public List<Product> newsList(int newest) throws SQLException {
		InDao02 dao = new AountDao02();
		return dao.dao_newsList(newest);
	}
	/**
	 * 查询单个商品详情
	 * @throws SQLException 
	 */
	public List<Product> findByPid(String pid) throws SQLException {
		InDao02 dao = new AountDao02();
		return  dao.findByPid(pid);
	}

	/**
	 * 分类商品的分页展示
	 * @throws SQLException 
	 */
	public PageBean<Product> findByPage(int curPage, String cid) throws SQLException {
		        InDao02 dao = new AountDao02();
		       
				//一页显示的数量
				int curSize = Constant.PRODUCT_PAGE_SIZE;
				//商品的总数量(查询数据库)
				int count = dao.selectCount(cid);
				//总页数
				int sumPage = 0;
				if(count % curSize == 0){
					sumPage = count/curSize;
				}else{
					sumPage = count/curSize + 1;
				}
				//一页上面显示商品的集合(查询数据库)  ... limit a ,b;
				int b = curSize;
				int a = (curPage - 1)*b;
				
				List<Product> list = dao.selectLimit(a,b,cid);
				
				//1, 创建PageBean
				PageBean<Product> pageBean = new PageBean<Product>();
				
				//2.封装pageBean
				//2.1 封装当前页码
				pageBean.setCurPage(curPage);
				//2.2 封装一页显示的数量
				pageBean.setCurSize(curSize);
				//2.3 封装总数量(查询数据库)
				pageBean.setCount(count);
				//2.4 封装总页码(算的)
				pageBean.setSumPage(sumPage);
				//2.5封装一页上面显示商品的集合(查询数据库)
				pageBean.setList(list);
				
				return pageBean;
		
	}

}
