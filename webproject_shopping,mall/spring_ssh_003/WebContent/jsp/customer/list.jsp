<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>客户列表</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.3.min.js"></script>
<SCRIPT language=javascript>
	//page就是查询哪一页
	function to_page(page){
		//判断有没有传当前页码
		if(page){
			$("#page").val(page);
		}
		//把id为customerForm的表单提交了
		document.customerForm.submit();
		
	}
</SCRIPT>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id="customerForm" name="customerForm"
		action="${pageContext.request.contextPath }/customer_findByPage.action"
		method=post>
		
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
								<TD class=manageHead>当前位置：客户管理 &gt; 客户列表</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						<TABLE borderColor=#cccccc cellSpacing=0 cellPadding=0
							width="100%" align=center border=0>
							<TBODY>
								<TR>
									<TD height=25>
										<TABLE cellSpacing=0 cellPadding=2 border=0>
											<TBODY>
												<TR>
			<TD>客户名称：</TD>
			<TD><INPUT class=textbox id=sChannel2
				style="WIDTH: 80px" maxLength=50 name="cust_name" value="<s:property value="cust_name"/>"></TD>
				
			<td>客户级别 ：</td>
			<td>
				<select name="custLevel.dict_id" id="custLevelId">
					<option value="">-请选择-</option>
				</select>
			</td>
			
			<td>信息来源 ：</td>
			<td>
				<select name="custSource.dict_id" id="custSourceId">
					<option value="">-请选择-</option>
				</select>
			</td>
			<td>所属行业 ：</td>
			<td>
				<select name="custIndustry.dict_id" id="custIndustryId">
					<option value="">-请选择-</option>
				</select>
			</td>
			
			<TD><INPUT class=button id=sButton2 type=submit
				value=" 筛选 " name=sButton2></TD>
		</TR>
											</TBODY>
										</TABLE>
									</TD>
								</TR>
							    
								<TR>
									<TD>
										<TABLE id=grid
											style="BORDER-TOP-WIDTH: 0px; FONT-WEIGHT: normal; BORDER-LEFT-WIDTH: 0px; BORDER-LEFT-COLOR: #cccccc; BORDER-BOTTOM-WIDTH: 0px; BORDER-BOTTOM-COLOR: #cccccc; WIDTH: 100%; BORDER-TOP-COLOR: #cccccc; FONT-STYLE: normal; BACKGROUND-COLOR: #cccccc; BORDER-RIGHT-WIDTH: 0px; TEXT-DECORATION: none; BORDER-RIGHT-COLOR: #cccccc"
											cellSpacing=1 cellPadding=2 rules=all border=0>
											<TBODY>
												<TR
													style="FONT-WEIGHT: bold; FONT-STYLE: normal; BACKGROUND-COLOR: #eeeeee; TEXT-DECORATION: none">
													<TD>客户名称</TD>
													<TD>客户级别</TD>
													<TD>客户来源</TD>
													<TD>客户行业</TD>
													<TD>电话</TD>
													<TD>手机</TD>
													<TD>操作</TD>
												</TR>
												<s:iterator value="list">
													<TR
														style="FONT-WEIGHT: normal; FONT-STYLE: normal; BACKGROUND-COLOR: white; TEXT-DECORATION: none">
														<TD><s:property value="cust_name"/></TD>
														<TD><s:property value="custLevel.dict_item_name"/></TD>
														<TD><s:property value="custSource.dict_item_name"/></TD>
														<TD><s:property value="custIndustry.dict_item_name"/></TD>
														<TD><s:property value="cust_phone"/></TD>
														<TD><s:property value="cust_mobile"/></TD>
														<TD>
														<a href="${pageContext.request.contextPath }/customer_edit.action?cust_id=<s:property value="cust_id"/>">修改</a>
														&nbsp;&nbsp;
														<a href="${pageContext.request.contextPath }/customer_delete.action?cust_id=<s:property value="cust_id"/>">删除</a>
														</TD>
													</TR>
												</s:iterator>
												
											</TBODY>
										</TABLE>
									</TD>
								</TR>
								
								<TR>
									<TD><SPAN id=pagelink>
											<DIV
												style="LINE-HEIGHT: 20px; HEIGHT: 20px; TEXT-ALIGN: right">
												
												共[<B><s:property value="count"/></B>]条记录,[<B><s:property value="sumPage"/></B>]页
												,每页显示
												<select name="curSize" onchange="to_page()">
													<option value="3" <s:if test="curSize==3">selected</s:if>>3</option>
													<option value="5" <s:if test="curSize==5">selected</s:if>>5</option>
													<option value="10" <s:if test="curSize==10">selected</s:if>>10</option>
												</select>
												
												条
												<!--上一页 :如果当前页码 > 1才显示  -->
												<s:if test="curPage > 1">
													[<A href="javascript:to_page(<s:property value="curPage-1"/>)">前一页</A>]
												</s:if>
												
												<!-- 中间页码 -->
												<s:iterator begin="1" end="sumPage" var="n">
													<!--如果n==curPage, 不是超链接 不可以点;
														如果n!=curPage, 超链接 可以点;
													  -->
													<s:if test="#n == curPage">
														<B><s:property value="#n"/></B>
													</s:if>
													<s:else>
														<B><a href="javascript:to_page(<s:property value="#n"/>)"><s:property value="#n"/></a></B>
													</s:else>
												</s:iterator>
											
												
												<!-- 下一页: 如果当前页码 < 总页码(最后一页), 才显示 -->
												<s:if test="curPage < sumPage">
													[<A href="javascript:to_page(<s:property value="curPage+1"/>)">后一页</A>] 
												</s:if>
												
												到
												<input type="text" size="3" id="page" name="curPage" />
												页
												
												<input type="button" value="Go" onclick="to_page()"/>
											</DIV>
									</SPAN></TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
					<TD width=15 background="${pageContext.request.contextPath }/images/new_023.jpg"><IMG
						src="${pageContext.request.contextPath }/images/new_023.jpg" border=0></TD>
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
<s:debug></s:debug>
</HTML>
