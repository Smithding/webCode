package com.itheima.bean;

import java.util.Date;

/**
 * 商品实体JavaBean
 * @author Administrator
 *
 */
public class Product {

	private String pid;  //id
	private String pname; //名称
  	private Double market_price; // 市场价格
	
	private Double shop_price; // 内部价格
	private String pimage; // 图片地址
	private Date pdate; //修改日期
	
	private Integer is_hot;  //是否热门  1:热门    0:不热门
	private String pdesc; //商品介绍
	private Integer pflag;	//是否下架    1:下架	0:未下架
	
	private Category category; 

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(Double market_price) {
		this.market_price = market_price;
	}

	public Double getShop_price() {
		return shop_price;
	}

	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public Date getPdate() {
		return pdate;
	}

	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public Integer getPflag() {
		return pflag;
	}

	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", pname=" + pname + ", market_price=" + market_price + ", shop_price="
				+ shop_price + ", pimage=" + pimage + ", pdate=" + pdate + ", is_hot=" + is_hot + ", pdesc=" + pdesc
				+ ", pflag=" + pflag + ", category=" + category + "]";
	}
	
	
	
}
