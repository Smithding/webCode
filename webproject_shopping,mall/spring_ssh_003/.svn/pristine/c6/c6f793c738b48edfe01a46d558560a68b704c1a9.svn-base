package com.itheima.web.action;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.bean.PageBean;
import com.itheima.bean.SaleVisit;
import com.itheima.service.SaleVisitService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit>{ 

	private SaleVisitService saleVisitService;
	
	private SaleVisit saleVisit = new SaleVisit();
	
	private int curPage;
	
	private int curSize;
	
	/**
	 * 分页显示客户列表
	 * @return
	 */
    public String findByPage(){
    	//设置离线查询条件
    	DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SaleVisit.class);
    	
        PageBean<SaleVisit>	pageBean = saleVisitService.findByPage(detachedCriteria,curPage,curSize);
    	
        ActionContext.getContext().getValueStack().push(pageBean);
        
    	return "findByPageSuccess"; 
    }
	
	/**
	 * 拜访记录保存
	 * @return
	 */
	public String save(){
		
		saleVisitService.save(saleVisit);
		
		return "saveSuccess";
	}	
	/**
	 * 页面跳转
	 * @return
	 */
	public String saveUI(){
		return "saveUISuccess";
	}
	
	public void setSaleVisitService(SaleVisitService saleVisitService) {
		this.saleVisitService = saleVisitService;
	}

	@Override
	public SaleVisit getModel() {
		// TODO Auto-generated method stub
		return saleVisit;
	}

	
}
