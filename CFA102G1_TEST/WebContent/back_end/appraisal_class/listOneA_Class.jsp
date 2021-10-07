<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.appraisal_class.model.*"%>

<%
	Appraisal_ClassVO appraisalClassVO = (Appraisal_ClassVO) request.getAttribute("appraisalClassVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>估價類別資料</title>

<style>
table {
	width: 600px;
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
		<tr>
			<td>
				<h3>估價類別資料</h3>
				<h4>
					<a href="<%= request.getContextPath()%>/back_end/appraisal_class/select_page.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>估價類別編號</th>
			<th>估價類別名稱</th>
		</tr>
		<tr>
			<td>${appraisalClassVO.acl_no }</td>
			<td>${appraisalClassVO.acl_id }</td>
		</tr>
	</table>

</body>
</html>