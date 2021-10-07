<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.consultation.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	ConsultVO consultVO = (ConsultVO) request.getAttribute("consultVO"); //ConsultServlet.java(Controller), 存入req的consultVO物件
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
							諮詢表單資料><a
								href="<%=request.getContextPath()%>/back_end/consultation/select_page.jsp">諮詢表單管理</a>
						</h3>
					</div>
					<div class="card-body">
						<table class="table table-striped" id="table1">
							<thead>
								<tr>
									<th>諮詢單編號</th>
									<th>諮詢人姓名</th>
									<th>諮詢人手機</th>
									<th>諮詢人EMAIL</th>
									<th>諮詢內容</th>
									<th>員工編號</th>
									<th>回覆狀態</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><%=consultVO.getConsult_no()%></td>
									<td><%=consultVO.getConsultant()%></td>
									<td><%=consultVO.getConsult_phone()%></td>
									<td><%=consultVO.getConsult_email()%></td>
									<td><%=consultVO.getConsult_content()%></td>
									<td><%=consultVO.getStaff_no()%></td>
									<td><%=consultVO.getConsult_sta()%></td>
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