<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.store_information.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	SiVO siVO = (SiVO) request.getAttribute("siVO"); //SiServlet.java(Controller), 存入req的siVO物件
%>

<html>
<head>
<title>商家資訊-listOneSiModel.jsp</title>
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
		<thead>
			<tr>
				<th>商家編號</th>
				<th>門市地址</th>
				<th>營業時間</th>
				<th>聯絡電話</th>
				<th>電子郵件</th>
				<th>LINE資訊</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><%=siVO.getSi_no()%></td>
				<td><%=siVO.getSi_address()%></td>
				<td><%=siVO.getSi_open()%></td>
				<td><%=siVO.getSi_phone()%></td>
				<td><%=siVO.getSi_email()%></td>
				<td><%=siVO.getSi_line()%></td>
			</tr>
		</tbody>
	</table>
</body>
</html>