<%@ page language="java" pageEncoding="UTF-8"%>

<%@ include file="/commons/common.jsp" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="atguigu" tagdir="/WEB-INF/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			$("#new").click(function(){
				window.location.href="${ctp}" + "/chance/create";
				return false;
			});
			$("img[id^='delete-']").click(function(){
				var label = $(this).next(":hidden").val();
				var flag = confirm("确定要删除" + label + "的信息吗?");
				if(!flag){
					return;
				}
				
				
				var id = $(this).attr("id").split("-")[1];
				alert(id);
				
				$("#hiddenForm").attr("action", "${ctp}/chance/delete/" + id + "?pageNo=${page.pageNo}");
				$("#_method").val("DELETE");
				$("#hiddenForm").submit();
				return false;
			});
		})
	</script>
</head>

<body class="main">
	<form:form action="${ctp}/chance/list" method="post">
		<div class="page_title">销售机会管理</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				查询</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">客户名称</th>
				<td class="input_content"><input type="text"
					name="search_LIKES_custName" /></td>
				<th class="input_title">概要</th>
				<td class="input_content"><input type="text"
					name="search_LIKES_title" /></td>
				<th class="input_title">联系人</th>
				<td class="input_content"><input type="text"
					name="search_LIKES_contact" /></td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />

		<c:if test="${!empty page.content}">
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>编号</th>
					<th>客户名称</th>
					<th>概要</th>
					<th>联系人</th>
					<th>联系人电话</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
				<c:forEach var="chance" items="${page.content }">
					<tr>
						<td class="list_data_number">${chance.id}</td>
						<td class="list_data_text">${chance.custName}</td>
						<td class="list_data_text">${chance.title}</td>
						<td class="list_data_text">${chance.contact}</td>
						<td class="list_data_text">${chance.contactTel}</td>
						<td class="list_data_text"><fmt:formatDate
								value="${chance.createDate }" pattern="yyyy-MM-dd" /></td>
						<td class="list_data_op"><img
							onclick="window.location.href='${ctp}/chance/dispatch/${chance.id}'"
							title="指派" src="${ctp}/static/images/bt_linkman.gif"
							class="op_button" /> <img
							onclick="window.location.href='${ctp}/chance/create/${chance.id}?pageNo=${page.pageNo}'"
							title="编辑" src="${ctp}/static/images/bt_edit.gif"
							class="op_button" />
							<img id="delete-${chance.id }" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							<input type="hidden" value="${chance.custName}"/>
					</tr>
				</c:forEach>
			</table>
		<atguigu:page page="${page}"></atguigu:page>
		</c:if>
		<c:if test="${page == null || page.totalElements == 0 }">
			没有任何数据
		</c:if>
	</form:form>

</body>
</html>

 