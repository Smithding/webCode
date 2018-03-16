package com.itheima.bean;

import java.util.List;

/**
 * 分页javaBean
 * @author Administrator
 * @param <T>
 *
 */
public class PageBean<T> {

	private List<T>  list;//商品的集合 List  list
  	private int curPage;  //当前页码   int curPage;
  	private int sumPage;   //总页码     int sumPage;
  	private int count;    //总数量     int count;
  	private int curSize; //一页显示的数量  int curSize;
  	
	
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getSumPage() {
		return sumPage;
	}
	public void setSumPage(int sumPage) {
		this.sumPage = sumPage;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getCurSize() {
		return curSize;
	}
	public void setCurSize(int curSize) {
		this.curSize = curSize;
	}
	
	@Override
	public String toString() {
		return "PageBean [list=" + list + ", curPage=" + curPage + ", sumPage=" + sumPage + ", count=" + count
				+ ", curSize=" + curSize + "]";
	}
	
	}
	
	
  	
  	

