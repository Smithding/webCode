package com.itheima.baendao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.Customer;

public interface BaseDao<T> {

	 void save(T t);
	
	 void update(T t);
	
	 void delete(T t);
	
	 T findById(Serializable id);
	
	/**
	 * 统计客户的数量
	 * @param detachedCriteria 离线条件对象
	 * @return
	 */
	 Integer findCount(DetachedCriteria detachedCriteria);

	/**
	 * 分页查询List
	 * @param detachedCriteria
	 * @param a
	 * @param b
	 * @return
	 */
	 List<T> findByPage(DetachedCriteria detachedCriteria, Integer a, Integer b);
	
	 List<T> findAll();
	
}
