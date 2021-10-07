<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.coupon_list.model.*"%>

<%
Coupon_ListVO couponListVO = (Coupon_ListVO) request.getAttribute("couponListVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�u�f����Ӹ��</title>

<style>
table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>

	<table id="table-1">
		<tr><td><h3>�u�f����Ӹ��</h3>
				<h4><a href="<%= request.getContextPath()%>/back_end/coupon_list/select_page.jsp">�^����</a></h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>�u�f��s��</th>
			<th>�|���s��</th>
			<th>���A</th>
		</tr>
		<tr>
				<td>${couponListVO.ci_no }</td>
				<td>${couponListVO.ci_name }</td>
				<td>${couponListVO.ci_code }</td>
				<td><fmt:formatDate value="${couponListVO.ci_start_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td><fmt:formatDate value="${couponListVO.ci_end_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${couponListVO.discount }</td>
				<td>${couponListVO.ci_content}</td>
		</tr>
	</table>

</body>
</html>