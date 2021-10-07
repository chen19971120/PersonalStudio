<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.store_information.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	SiVO siVO = (SiVO) request.getAttribute("siVO"); //SiServlet.java(Controller), 存入req的siVO物件
%>

<!DOCTYPE html>
<html>
<head>

<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file="../back_include_page/CSS_link.jsp"%>
<!--*******************	
		End Include CSS File  
		******************* -->
<meta charset="UTF-8">
<title>YSM-3C 後台管理</title>
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<title>商家資訊 - listOneSi.jsp</title>

<style>
table#table-1 {
	border: 2px solid black;
	text-align: center;
}
</style>
<style>
table {
	width: 1265px;
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
<body bgcolor='white'>
	<div id="app">
		<!--*******************	
		Start Include sidebar File  
		******************* -->
		<%@ include file="../back_include_page/sidebar.jsp"%>
		<!--*******************	
		End Include sidebar File  
		******************* -->

		<div id="main">
			<section class="section">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							商家資訊><a
								href="<%=request.getContextPath()%>//back_end/store_information/select_page.jsp">商家資訊管理</a>
						</h3>
					</div>
					<div class="card-body">
						<table class="table table-striped" id="table1">
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
					</div>
				</div>
			</section>
		</div>
		<!--*******************	
		Start Include sidebar File  
		******************* -->
		<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
		<!--*******************	
		End Include sidebar File  
		******************* -->
	</div>
</body>
</html>