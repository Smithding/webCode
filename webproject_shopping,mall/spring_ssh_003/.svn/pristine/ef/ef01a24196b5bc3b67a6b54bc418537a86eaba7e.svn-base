package com.itheima.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户(公司)的实体  m
 * @author yp
 *
 */
public class Customer {

	
	private Long cust_id;
	private String cust_name;
/*	private String cust_source;
	private String cust_industry;
	private String cust_level;*/
	private String cust_phone;
	private String cust_mobile;
	
	//表达一方
	private BaseDict custSource;//客户来源
	private BaseDict custIndustry;//客户行业
	private BaseDict custLevel;//客户级别
	
	
	private String cust_image; //文件资质
	
	// 放的是联系人的集合:
    private Set<LinkMan> linkMans = new HashSet<LinkMan>();
	
	
	
	public Set<LinkMan> getLinkMans() {
		return linkMans;
	}
	public void setLinkMans(Set<LinkMan> linkMans) {
		this.linkMans = linkMans;
	}
	public String getCust_image() {
		return cust_image;
	}
	public void setCust_image(String cust_image) {
		this.cust_image = cust_image;
	}
	public BaseDict getCustSource() {
		return custSource;
	}
	public void setCustSource(BaseDict custSource) {
		this.custSource = custSource;
	}
	public BaseDict getCustIndustry() {
		return custIndustry;
	}
	public void setCustIndustry(BaseDict custIndustry) {
		this.custIndustry = custIndustry;
	}
	public BaseDict getCustLevel() {
		return custLevel;
	}
	public void setCustLevel(BaseDict custLevel) {
		this.custLevel = custLevel;
	}
	public Long getCust_id() {
		return cust_id;
	}
	public void setCust_id(Long cust_id) {
		this.cust_id = cust_id;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mobile() {
		return cust_mobile;
	}
	public void setCust_mobile(String cust_mobile) {
		this.cust_mobile = cust_mobile;
	}
	@Override
	public String toString() {
		return "Customer [cust_id=" + cust_id + ", cust_name=" + cust_name + ", cust_phone=" + cust_phone
				+ ", cust_mobile=" + cust_mobile + ", custSource=" + custSource + ", custIndustry=" + custIndustry
				+ ", custLevel=" + custLevel + ", cust_image=" + cust_image + "]";
	}
	
	

}
