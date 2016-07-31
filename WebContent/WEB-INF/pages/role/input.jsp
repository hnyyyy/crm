<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>
	<head>
		<title>添加角色</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	</head>

	<body>

		<div class="page_title">
			系统角色添加
		</div>
		<div class="button_bar">
				<button class="common_button" onclick="javascript:history.go(-1);">
					返回
				</button>
				<button class="common_button" onclick="document.forms[1].submit();">
					保存
				</button>
		</div>
		
		<form action="${ctp}/role/save" method="post">
			
			
			
			<table class="query_form_table">
				<tr>
					<th>
						角色名称
					</th>
					<td>
						<input type="text" name="name" />
						<%-- <form:input path="name"/> --%>
					</td>
					<th>
						角色描述
					</th>
					<td>
						<input type="text" name="description" />
						<%-- <form:input path="description"/> --%>
					</td>
				</tr>
				<tr>
					<th>
						状态
					</th>
					<td>
						<select name="enabled">
							<option value="1">有效</option>
							<option value="0">无效</option>
						</select>
						<%-- <form:select path="enabled">
							<form:option value="1">有效</form:option>
							<form:option value="0">无效</form:option>
						</form:select> --%>
						<span class="red_star">*</span>
					</td>
					
				</tr>
			</table>
		</form>
	</body>
</html>
