package com.itheima.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.bean.User;
import com.itheima.constant.Constant;
import com.itheima.dao.InDao;
import com.itheima.dao.impl.AountDao;
import com.itheima.service.InService;
import com.itheima.utils.JedisUtils;
import com.itheima.utils.JsonUtil;
import com.itheima.utils.MailUtils;

import redis.clients.jedis.Jedis;

public class AountService implements InService {
    
	public void Inservlet(User user) throws Exception{
	    //调用注册业务-实现接口
		InDao dao = new AountDao();
		dao.Iodao(user);
		//激活邮件
	    MailUtils.sendMail(user.getEmail(),"尊敬的"+user.getUsername()+"用户:欢迎使用黑马商城，请点击下列链接，进行账号激活！<a href='http://localhost:8090/store2018/demo?method=activate&code="+user.getCode()+"'>点击激活</a>");
		}
	
	/**
	 * 调用激活业务-实现接口方法
	 * @return 
	 * @throws SQLException 
	 */
	public User serviceActivate(String code) throws SQLException {
		InDao dao = new AountDao();
		return dao.Iodao(code);
	}

	/**
	 * 调用激活状态修改业务-实现接口方法
	 * @param code 
	 * @throws SQLException 
	 */
	public void activateModifier(String code) throws SQLException {
		// TODO Auto-generated method stub
		InDao daoModifier = new AountDao();
		daoModifier.IoModifier(code);
	}
	
	
	
	/**
	 * 分类信息展示业务调用
	 * @author Administrator
	 *
	 */
	public String findAll() throws SQLException {
		String data = null;
		Jedis jedis = null;
		try {
			//获得redis链接对象
			jedis = JedisUtils.getJedis();
			//从redis中取出数据
			data = getFromRedis(jedis);

			if (data != null) {
				//System.out.println("从 redis中获取数据......");
			} else {
				// 服务器开了，但是没有数据，从mysql中获取，然后存在redis中
				//System.out.println("服务器开了，从mysql里面获得数据...");
				data = getFromMySql();
				saveToRedis(jedis, data);
			}

		} catch (Exception e) {
			// redis 服务器没有打开，会抛出异常
			//System.out.println("redis服务器没开，从mysql里面获得数据......");
			data = getFromMySql();

			// 再次尝试存到redis中
			saveToRedis(jedis, data);
			e.printStackTrace();
		} finally {
			JedisUtils.closeJedis(jedis);

		}
		return data;
	}
	
	

	//把数据保存到redis中
	private void saveToRedis(Jedis jedis, String data) {
		if ( jedis != null ) {
			jedis.set(Constant.STORE_CATOGRY_KEY01, data);
		}
		
	}

	//Redis数据
	private String getFromRedis(Jedis jedis) {
		if( jedis != null ) {
			return jedis.get(Constant.STORE_CATOGRY_KEY01);
		}
		return null;
	}

	//mysql数据
	private String getFromMySql() throws SQLException {
		String list2json = JsonUtil.list2json(new AdminCategoryImpl().findAll());
		return list2json;
	}

	

	/**
	 * 实现登录操作
	 */
	public User setRegister(String username, String password) throws Exception {
		InDao dao = new AountDao();
		return  dao.daoRegister(username,password);
	}

	public User setactivate(String activateid) throws Exception {
		
		
		return null;
	}

	

	
}
