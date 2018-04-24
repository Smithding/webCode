package com.itheima.baendao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.baendao.BaseDao;
import com.itheima.bean.Customer;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	
	public BaseDaoImpl() {
		
		//泛型的反射
		Class clazz = this.getClass();
		//调用class的方法带有泛型的父类
		Type type = clazz.getGenericSuperclass();
		
		if( type instanceof ParameterizedType ){
			ParameterizedType pType = (ParameterizedType) type;
			//获得实际类型参数
			Type[] types = pType.getActualTypeArguments();
			//获得某一个实际类型参数
			this.clazz =(Class) types[0];
		}
	}

	@Override
	public void save(T t) {
		getHibernateTemplate().save(t);

	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);

	}

	@Override
	public void delete(T t) {
		getHibernateTemplate().delete(t);

	}

	@Override
	public T findById(Serializable id) {
		return (T)this.getHibernateTemplate().get(clazz,id);
	}

	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		// 设置聚合条件
		detachedCriteria.setProjection(Projections.rowCount());

		List<Long> list = (List<Long>)this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}

		return 0;
	}
	

	@Override
	public List<T> findByPage(DetachedCriteria detachedCriteria, Integer a, Integer b) {
		// 清除聚合条件(因为查询所有的列)
		detachedCriteria.setProjection(null);

		return (List<T>)this.getHibernateTemplate().findByCriteria(detachedCriteria, a, b);
	}

	@Override
	public List<T> findAll() {
		System.out.println(clazz.getSimpleName());
		return (List<T>)this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

}
