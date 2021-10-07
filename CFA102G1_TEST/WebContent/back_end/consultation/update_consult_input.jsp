<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.consultation.model.*"%>

<%
	ConsultVO consultVO = (ConsultVO) request.getAttribute("consultVO"); //ConsultServlet.java (Controller) 存入req的consultVO物件 (包括幫忙取出的consultVO, 也包括輸入資料錯誤時的consultVO物件)
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
							諮詢表單資料修改><a
								href="<%=request.getContextPath()%>/back_end/consultation/select_page.jsp">諮詢表單管理</a>
						</h3>
					</div>
					<div class="card-body">
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do" name="form1">
							<div class="mb-3 row">
								<label for="staticConsult_no" class="col-sm-2 col-form-label">諮詢單編號:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext"
										id="staticConsult_no" value="<%=consultVO.getConsult_no()%>">
								</div>
							</div>
							<div class="mb-3 row">
								<label for="inputConsultant" class="col-sm-2 col-form-label">諮詢人姓名:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext" id="inputConsultant"
										name="consultant" value="<%=consultVO.getConsultant()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_phone" class="col-sm-2 col-form-label">諮詢人手機:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext" id="inputConsult_phone"
										name="consult_phone" value="<%=consultVO.getConsult_phone()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_email" class="col-sm-2 col-form-label">諮詢人EMAIL:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext" id="inputConsult_email"
										name="consult_email" value="<%=consultVO.getConsult_email()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_content"
									class="col-sm-2 col-form-label">諮詢內容:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext"
										id="inputConsult_content" name="consult_content"
										value="<%=consultVO.getConsult_content()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputStaff_no" class="col-sm-2 col-form-label">員工編號:</label>
								<div class="col-sm-10">
									<input type="TEXT" readonly class="form-control-plaintext" id="inputStaff_no"
										name="staff_no" value="<%=consultVO.getStaff_no()%>">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_sta" class="col-sm-2 col-form-label">回覆狀態:</label>
								<div class="col-sm-10">
									<select name="consult_sta" class="form-control"
										id="inputConsult_sta">
										<option selected></option>
										<option>已回覆</option>
										<option>未回覆</option>
									</select>
								</div>
							</div>

							<br> <input type="hidden" name="action" value="update">
							<input type="hidden" name="consult_no"
								value="<%=consultVO.getConsult_no()%>"> <input
								type="hidden" name="requestURL"
								value="<%=request.getParameter("requestURL")%>">
							<!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
							<input type="hidden" name="whichPage"
								value="<%=request.getParameter("whichPage")%>">
							<!--只用於:istAllEmp.jsp-->
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