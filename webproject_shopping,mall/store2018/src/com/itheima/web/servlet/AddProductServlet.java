package com.itheima.web.servlet;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.service.AdminCategoryService;
import com.itheima.service.impl.AdminCategoryImpl;
import com.itheima.utils.UUIDUtils;
import com.itheima.utils.UploadUtils;


/**
 * Servlet implementation class AddProductServlet
 */
public class AddProductServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//0创建Product
			Product product = new Product();
		
			Map<String, Object> map = new HashMap<String, Object>();//把表单里面的name属性值当做key, 把value当做值
			
			//1. 创建磁盘文件项工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//2. 创建文件上传核心对象
			ServletFileUpload upload = new ServletFileUpload(factory);
			//3. 解析request, 获得文件项集合
			List<FileItem> list = upload.parseRequest(request);
			//4. 遍历list
			for (FileItem fileItem : list) {//不是File,需要那value封装到Product里面; 是File,需要把文件的路径封装到Product, 保存文件到服务器
				if(fileItem.isFormField()){
					//不是File,需要那value封装到Product里面
					map.put(fileItem.getFieldName(), fileItem.getString("utf-8"));
				}else{
					//是File,需要把文件的路径封装到Product, 保存文件到服务器
					//***********需要把文件的路径封装到Product******************(products/5/5/CC15BA1CC6F049238FA124CB30EFCEDB.jpg)
					//a 获得文件名(有可能带盘符) G:/a.jpg
					String name = fileItem.getName();
					//b.去掉盘符的文件名  a.jpg
					String realName = UploadUtils.getRealName(name);
					//c,得到UUID文件名  CC15BA1CC6F049238FA124CB30EFCEDB.jpg
					String uuidName = UploadUtils.getUUIDName(realName);
					//d.获得两层目录 /5/5
					String dir = UploadUtils.getDir();
					//e 把文件的路径保存到map
					map.put(fileItem.getFieldName(),"products"+dir+"/"+uuidName);
					
					//***********保存文件到服务器******************
					//a.获得文件内容(流)
					InputStream is = fileItem.getInputStream();
					//b. 动态获得\products的绝对路径(tomcat里面的)  E:\worksoft\tomcat\apache-tomcat-7.0.52_31\webapps\store31\products
					/*String realPath = "f:/redes";*/
					String realPath = getServletContext().getRealPath("/products");
					//c.创建两层文件夹
					File dirPath = new File(realPath, dir);
					if(!dirPath.exists()){
						dirPath.mkdirs();
					}
					//d.创建输出流
					OutputStream os =  new FileOutputStream(new File(dirPath, uuidName));
					
					IOUtils.copy(is, os);
					IOUtils.closeQuietly(is);
					IOUtils.closeQuietly(os);
					fileItem.delete();
					
				}
			}
			
			//5. 封装product
			BeanUtils.populate(product, map);
			//5.1 手动封装(pid,pdate,category)
			product.setPid(UUIDUtils.getId());
			product.setPdate(new Date());
			Category category = new Category();
			category.setCid((String)map.get("cid"));
			product.setCategory(category);
			
			//调用业务
			AdminCategoryService categ = new AdminCategoryImpl();
			categ.addProductServlet(product);
			
			//重定向到所有商品页面，在查询所有
			response.sendRedirect(request.getContextPath()+"/adminCategoryServlet?method=findAll02");
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
