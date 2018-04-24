package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.Customer;
import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;

public interface LinkManService {

	List<LinkMan> findAll();

	void save(LinkMan linkMan);

	PageBean<LinkMan> findByPage(DetachedCriteria forClass, int curPage,int curSize);

	LinkMan findById(Long lkm_id);

	void update(LinkMan linkMan);

	void delete(LinkMan linkMan);


       
}
