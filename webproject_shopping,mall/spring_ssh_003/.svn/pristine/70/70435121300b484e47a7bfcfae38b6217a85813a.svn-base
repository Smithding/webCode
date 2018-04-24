package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.Customer;
import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.constant.Constants;
import com.itheima.dao.LinkManDao;
import com.itheima.service.LinkManService;

@Transactional
public class LinkManServiceImpl implements LinkManService {

	private LinkManDao linkManDao;
	
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	
	// 如果想搜索Customer，应该使用CustomerService，而不是在LinkManService里面去查询Customer
	
	@Override
	public List<LinkMan> findAll() {
		
		return linkManDao.findAll();
	}

	@Override
	public void save(LinkMan linkMan) {
		 linkManDao.save(linkMan);
		
	}

	@Override
	public PageBean<LinkMan> findByPage(DetachedCriteria forClass, int curPage,int curSize) {
		
		if(curPage == 0){//解决在list页码, 选择展示数量的bug
			curPage = 1;
		}
		
		if(curSize == 0){//解决第一次(菜单选项里面的)
			curSize = Constants.CUSTOMER_CUR_SIZE;
		}
		
		//总数量
		int count = linkManDao.findCount(forClass);
		//一页显示的数量
		//int curSize = Constants.CUSTOMER_CUR_SIZE;
		//总页码
		int sumPage = 0;
		if(count % curSize == 0){
			sumPage = count / curSize;
		}else{
			sumPage = count / curSize +1;
		}
		
		//当前页里面客户的List集合
		int b = curSize;
		int a = (curPage-1)*b;
		List<LinkMan> list = linkManDao.findByPage(forClass,a,b);
		
		//1. 创建PageBean
		PageBean<LinkMan> pageBean = new PageBean<LinkMan>();
		//1.1 封装当前页码
		pageBean.setCurPage(curPage);
		//1.2封装一页显示的数量
		pageBean.setCurSize(curSize);
		//1.3封装总数量
		pageBean.setCount(count);
		//1.4 总页码
		pageBean.setSumPage(sumPage);
		//1.5 封装List
		pageBean.setList(list);
		
		return pageBean;
	}


	@Override
	public LinkMan findById(Long lkm_id) {
		
		 return linkManDao.findById(lkm_id);
	}



	@Override
	public void update(LinkMan linkMan) {
	
	    linkManDao.update(linkMan);
	}



	@Override
	public void delete(LinkMan linkMan) {
		
		 linkManDao.delete(linkMan);
	}


	

}
