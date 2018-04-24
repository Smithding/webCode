<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>更新客户</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
	<!-- 导入jquery -->
<script type="text/javascript" src="${pageContext.request.contextPath  }/js/jquery-1.11.3.min.js"></script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/customer_update.action"
		method=post enctype="multipart/form-data">
		<input type="hidden" name="cust_id" value="<s:property value="cust_id"/>"/>
		<input type="hidden" name="cust_image" value="<s:property value="cust_image"/>"/>
		

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：客户管理 &gt; 修改客户</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						  
						    
							<TR>
								<td>客户名称：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_name" value=<s:property value="cust_name"/>>
								</td>
								<td>客户级别 ：</td>
								<td>
									<select id="custLevelId" name="custLevel.dict_id">
										<option value="">-请选择-</option>
									</select>
								</td>
							</TR>
							
							<TR>
								
								<td>信息来源 ：</td>
								<td>
									<select id="custSourceId" name="custSource.dict_id">
										<option value="">-请选择-</option>
									</select>
								</td>
								<td>所属行业 ：</td>
								<td>
									<select id="custIndustryId" name="custIndustry.dict_id">
										<option value="">-请选择-</option>
									</select>
								</td>
							</TR>
							
							<TR>
								
								
								<td>固定电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_phone" value=<s:property value="cust_phone"/>>
								</td>
								<td>移动电话 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="cust_mobile" value=<s:property value="cust_mobile"/>>
								</td>
							</TR>
							
							<TR>
								
								
								<td>资质证件 ：</td>
								<td>
								<INPUT class=textbox id=sChannel2 type="file"
														style="WIDTH: 180px ;height: 20px" maxLength=50 name="upload">
								</td>
								
							</TR>
							
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type=submit
														value=" 更新" name=sButton2>
								</td>
							</tr>
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath }/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
	
	<!--$.getJSON("${pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(result){
  			//解析result, 填充页面
  
		});  -->
	<script type="text/javascript">
		//发送Ajax请求
		//获得客户来源
		$.getJSON("${pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"002"},function(result){
			//解析result
			$(result).each(function(i,obj){
				$("#custSourceId").append("<option value='"+obj.dict_id+"'>"+obj.dict_item_name+"</option>");
			});
			// 客户来源回显数据
			$("#custSourceId option[value='${custSource.dict_id}']").prop("selected",true);
		});
		
	
		//获得客户级别
		$.getJSON("${pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"006"},function(result){
			//解析result
			$(result).each(function(i,obj){
				$("#custLevelId").append("<option value='"+obj.dict_id+"'>"+obj.dict_item_name+"</option>");
			});
			
			// 客户级别回显数据
			$("#custLevelId option[value='${custLevel.dict_id}']").prop("selected",true);
			
		});
		
		//获得客户行业
		$.getJSON("${pageContext.request.contextPath }/baseDict_findByTypeCode.action",{"dict_type_code":"001"},function(result){
			//解析result
			$(result).each(function(i,obj){
				$("#custIndustryId").append("<option value='"+obj.dict_id+"'>"+obj.dict_item_name+"</option>");
			});
			//  客户行业回显数据
			$("#custIndustryId option[value='${custIndustry.dict_id}']").prop("selected",true);
		});
		
	
	
	</script>
</BODY>
</HTML>
