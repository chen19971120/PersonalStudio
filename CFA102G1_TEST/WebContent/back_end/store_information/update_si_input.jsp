<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.store_information.model.*"%>

<%
	SiVO siVO = (SiVO) request.getAttribute("siVO"); //SiServlet.java (Controller) 存入req的siVO物件 (包括幫忙取出的siVO, 也包括輸入資料錯誤時的siVO物件)
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
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>商家資訊修改 - update_si_input.jsp</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
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
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<section class="section">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							商家資訊資料修改><a
								href="<%=request.getContextPath()%>/back_end/store_information/select_page.jsp">商家資訊管理</a>
						</h3>
					</div>
					<div class="card-body">



						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do" name="form1">

							<div class="mb-3 row">
								<label for="staticSi_no" class="col-sm-2 col-form-label">商家編號:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext"
										id="staticSi_no" value="<%=siVO.getSi_no()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputSi_address" class="col-sm-2 col-form-label">門市地址:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputSi_address"
										name="si_address" value="<%=siVO.getSi_address()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputSi_open" class="col-sm-2 col-form-label">營業時間:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputSi_open"
										name="si_open" value="<%=siVO.getSi_open()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputSi_phone" class="col-sm-2 col-form-label">聯絡電話:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputSi_phone"
										name="si_phone" value="<%=siVO.getSi_phone()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputSi_email" class="col-sm-2 col-form-label">電子郵件:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputSi_email"
										name="si_email" value="<%=siVO.getSi_email()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputSi_line" class="col-sm-2 col-form-label">LINE資訊:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputSi_line"
										name="si_line" value="<%=siVO.getSi_line()%>">
								</div>
							</div>

							<br> <input type="hidden" name="action" value="update">
							<input type="hidden" name="si_no" value="<%=siVO.getSi_no()%>">
							<input type="hidden" name="requestURL"
								value="<%=request.getParameter("requestURL")%>">
							<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
							<input type="hidden" name="whichPage"
								value="<%=request.getParameter("whichPage")%>">
							<!--只用於:istAllSi.jsp-->
							<div class="position-relative">
								<!-- 調整位置 -->
								<div class="position-absolute top-50 start-50 translate-middle">
									<!-- 調整位置 -->
									<input type="submit" class="btn btn-primary" value="送出修改">
								</div>
							</div>
						</FORM>
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