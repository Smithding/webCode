package com.itheima.web.action;

import java.util.List;

import org.apache.struts2.views.velocity.components.DateDirective;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.format.datetime.standard.DateTimeContextHolder;

import com.itheima.bean.Customer;
import com.itheima.bean.LinkMan;
import com.itheima.bean.PageBean;
import com.itheima.service.CustomerService;
import com.itheima.service.LinkManService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

	private LinkManService  linkManService;
	
	// 再注入一个CustomerService，专门处理Customer相关的业务
	private CustomerService customerService;
	
	private LinkMan linkMan = new LinkMan();
	
	private int curPage;
	
	private int curSize;
	
	
	/**
	 * 根据id删除联系人
	 * @return
	 */
	public String delete(){

		//2. 调用业务, 删除联系人
		linkManService.delete(linkMan);
		//3. 再分页查询展示
		return "deleteSuccess";
	}
	
	/**
	 * 更新联系人
	 * @return
	 */
	public String update(){
		//2. 调用业务, 更新联系人
		linkManService.update(linkMan);
		//3. 再重新分页查询展示
		System.out.println("接收");
		return "updateSuccess";
	}
	
	
	/**
	 * 修改回显示联系人
	 * @return
	 */
	public String findById(){
		
		
		// 1. 调用业务. 根据联系人id获得当前联系人对象linkMan; 把所有的客户查询出来(List)
		LinkMan link = linkManService.findById(linkMan.getLkm_id());
		// 2.把所有客户遍历出来
		// 使用CustomerService去查询有关Customer的对象
        List<Customer> list = customerService.findAll();
	     //把客户数据List<Customer> list存到值栈, 转发到添加联系人页面
		ActionContext.getContext().getValueStack().set("list", list);

		// 3. 把linkMan对象和客户List存到值栈里面 转发edit页面回显数据
		ActionContext.getContext().getValueStack().push(link);
       
	   return "editSuccess";
	}
	
	/**
	 * 列表分类
	 * @return
	 */
	public String findByPage(){
		//创建离线查询条件
		DetachedCriteria forClass = DetachedCriteria.forClass(LinkMan.class);
		
		if(!"".equals(linkMan.getLkm_name()) && linkMan.getLkm_name() != null){
			forClass.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
			
		}
		
		if(!"".equals(linkMan.getLkm_gender()) && linkMan.getLkm_gender() != null){
			forClass.add(Restrictions.eq("lkm_gender", linkMan.getLkm_gender()));
		}
		
		/*if(!"".equals(linkMan.getCustomer().getCust_id()) && linkMan.getCustomer().getCust_id() != null){
			forClass.add(Restrictions.eq("lkm_cust_id", linkMan.getCustomer().getCust_id()));
		}*/
	
		//2. 调用业务, 获得分页数据 PageBean<LinkMan> pageBean
        PageBean<LinkMan> pageBean = linkManService.findByPage(forClass,curPage,curSize);
		//3. 把pageBean存到值栈里面, 转发list.jsp页面展示
        ActionContext.getContext().getValueStack().push(pageBean);
        
        
		return "findByPageSuccess";
	}
	
	/**
	 * 保存联系人
	 * @return
	 */
	public String save(){
		
		linkManService.save(linkMan);
		
		return "saveSuccess";
	}
	
	
	//新增联系人客户选择
	public String saveUI(){
		
		//调用客户的业务,查询所有的客户
		List<Customer> list = customerService.findAll();
		//把客户数据List<Customer> list存到值栈, 转发到添加联系人页面
       ActionContext.getContext().getValueStack().set("list", list);
		
		return "saveUISuccess";
	}
	
	public void setLinkManService(LinkManService linkManService) {
		this.linkManService = linkManService;
	}


	@Override
	public LinkMan getModel() {
		// TODO Auto-generated method stub
		return linkMan;
	}
	

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public void setCurSize(int curSize) {
		this.curSize = curSize;
	}
    
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}


	
}
