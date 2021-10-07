<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.consultation.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ConsultVO consultVO = (ConsultVO) request.getAttribute("consultVO"); //ConsultServlet.java(Controller), 存入req的consultVO物件
%>

<html>
<head>
<title>諮詢表單-listOneConsultModel.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h3 {
	color: black;
	display: block;
	margin: 5px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

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
	padding: 1px;
	text-align: center;
}
</style>
</head>
<body bgcolor='white'>

	<table class="table table-striped">
		<tr>
			<th>諮詢單編號</th>
			<th>諮詢人姓名</th>
			<th>諮詢人手機</th>
			<th>諮詢人EMAIL</th>
			<th>諮詢內容</th>
			<th>員工編號</th>
			<th>回覆狀態</th>
		</tr>
		<tr>
			<td><%=consultVO.getConsult_no()%></td>
			<td><%=consultVO.getConsultant()%></td>
			<td><%=consultVO.getConsult_phone()%></td>
			<td><%=consultVO.getConsult_email()%></td>
			<td><%=consultVO.getConsult_content()%></td>
			<td><%=consultVO.getStaff_no()%></td>
			<td><%=consultVO.getConsult_sta()%></td>
		</tr>
	</table>
</body>
</html>