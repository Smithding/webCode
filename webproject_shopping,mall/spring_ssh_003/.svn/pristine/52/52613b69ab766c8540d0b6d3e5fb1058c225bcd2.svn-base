package com.itheima.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.constant.Constants;
import com.itheima.dao.SaleVisitDao;
import com.itheima.service.SaleVisitService;

@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;
	
	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public void save(SaleVisit saleVisit) {
		saleVisitDao.save(saleVisit);
	}

	@Override
	public PageBean<SaleVisit> findByPage(DetachedCriteria detachedCriteria, int curPage, int curSize) {
		
		if(curPage == 0){//解决在list页码, 选择展示数量的bug
			curPage = 1;
		}
		
		if(curSize == 0){//解决第一次(菜单选项里面的)
			curSize = Constants.CUSTOMER_CUR_SIZE;
		}
		
		//总数量
		int count = saleVisitDao.findCount(detachedCriteria);
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
		List<SaleVisit> list = saleVisitDao.findByPage(detachedCriteria,a,b);
		
		//1. 创建PageBean
		PageBean<SaleVisit> pageBean = new PageBean<SaleVisit>();
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
}
