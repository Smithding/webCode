package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.bean.Customer;
import com.itheima.bean.PageBean;
import com.itheima.bean.User;
import com.itheima.constant.Constants;
import com.itheima.service.CustomerService;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;


public class CustomerAction extends ActionSupport implements ModelDriven<Customer>{
	
	private Customer customer = new Customer();
	
	private CustomerService customerService;
	
	//查询哪一页
	private int curPage;
	
	//一页显示的数量
	private int curSize;
	
	private File upload;//代表的是上传文件
	private String uploadContentType;//代表文件类型
	private String uploadFileName;//代表的是文件名称
	

	/**
	 * 更新客户资料功能
	 * @return
	 * @throws IOException
	 */
	public String update() throws IOException{
        
		//2. 判断用户是否上传了文件, 判断upload是否为null
         if(upload != null){
        	 String cust_image = customer.getCust_image();
        	 if(cust_image != null){
        		 File file = new File(cust_image);
        		 if(file.exists()){
        			 file.delete();
        		 }
        	 }
        	//3. 如果用户上传了文件, 把之前的文件删除, 再上传
 			String realName = UploadUtils.getRealName(uploadFileName);
 			String uuidName = UploadUtils.getUUIDName(realName);
 			String dir = UploadUtils.getDir();
 			File fileDir = new File(Constants.UPLOAD_DIR, dir);
 			if(!fileDir.exists()){
 				fileDir.mkdirs();
 			}
 			
 			File outFile = new File(fileDir, uuidName);
 			FileUtils.copyFile(upload, outFile);
 			
 			//设置文件的路径
 			customer.setCust_image(Constants.UPLOAD_DIR+dir+"/"+uuidName);
         }
		//4. 调用业务, 修改
         customerService.update(customer);
		//5. 再查询客户列表,展示
		return "updateSuccess";	
	}
	
	/**
	 * 列表更新功能
	 * @return
	 */
	public String edit(){
		System.out.println("进入修改客户方法...");
		//1. 获得请求参数cust_id, 调用业务, 根据cust_id获得Customer对象
         customer =  customerService.findById(customer.getCust_id());
		//2.把customer对象存到值栈里面, 转发到edit页面回显数据
        ActionContext.getContext().getValueStack().push(customer);
		
		return "editSuccess";
	}
	
	
	/**
	 * 列表删除功能
	 * @return
	 */
	public String delete(){

		customer = customerService.findById(customer.getCust_id());
		
		String cust_image = customer.getCust_image();
		
		if( cust_image != null ){
			//删除图片
			File file = new File(cust_image);
			if(file.exists()){
				file.delete();
			}
		}
		
		customerService.delete(customer);
		
		return "deleteSuccess";
	}
	
	
	
	/**
	 * 分页查询客户
	 * @return
	 */
	public String findByPage(){
		 /**
		  * 使用筛查
		  */
		// 创建离线条件对象(下次课就会封装条件)
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
    
		if (!"".equals(customer.getCust_name()) && customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		// 来源
		if (customer.getCustSource() != null && !"".equals(customer.getCustSource().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("custSource.dict_id", customer.getCustSource().getDict_id()));
		}

		// 行业
		if (customer.getCustIndustry() != null && !"".equals(customer.getCustIndustry().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("custIndustry.dict_id", customer.getCustIndustry().getDict_id()));
		}

		// 等级
		if (customer.getCustLevel() != null && !"".equals(customer.getCustLevel().getDict_id())) {
			detachedCriteria.add(Restrictions.eq("custLevel.dict_id", customer.getCustLevel().getDict_id()));
		}
		
		
		//调用业务, 获得当前页的分页数据 PageBean<Customer> pageBean
		PageBean<Customer> pageBean =  customerService.findByPage(detachedCriteria,curPage,curSize);
		//把pageBean存到值栈里面, 转发list.jsp页面展示
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findByPageSuccess";
	}
	
	/**
	 * 遍历所有用户
	 * @return
	 * @throws IOException 
	 */
	public String findAll() throws IOException{
		List<Customer> list = customerService.findAll();
		JsonConfig jsonConfig = new JsonConfig();
		//忽略字段
		jsonConfig.setExcludes(new String[]{"custSource","custIndustry","custLevel","linkMans"});
		String data = JSONArray.fromObject(list, jsonConfig).toString();
		
		System.out.println("data="+data);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(data);
		return NONE;
	}
	
	
	//保存客户及图片
	public String save() throws IOException{
		if(upload != null){
			//如果上传文件,不为null
			//a. 获得文件的真实名字
			String realName = UploadUtils.getRealName(uploadFileName);
			//b获得随机的名字
			String uuidName = UploadUtils.getUUIDName(realName);
			//c. 获得两层目录
			String dir = UploadUtils.getDir();
			//d 创建两层目录
			File fileDir = new File(Constants.UPLOAD_DIR, dir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
          	//保存图片路径
			customer.setCust_image(fileDir+"/"+uuidName);
			
			File outFile = new File(fileDir, uuidName);	
			//保存图片到磁盘
			FileUtils.copyFile(upload, outFile);
		}	
		    customerService.save(customer);
		    return "saveSuccess";
	}
	
	//转发到添加客户的页面
	public String saveUI(){
		return "saveUISuccess";
	}
	
	
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public Customer getModel() {
		return customer;
	}
	
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	public void setCurSize(int curSize) {
		this.curSize = curSize;
	}
	
	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	

}
