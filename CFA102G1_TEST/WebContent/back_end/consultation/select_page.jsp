<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Consultation</title>

<style type="text/css">
h1 {
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
					<div class="card-body">
						<table id="table-1">

						</table>
						<hr>
						<h1>CONSULTATION諮詢表單管理</h1>
						<hr>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<h3>諮詢表單資料查詢:</h3>
						<br>

						<ul>
							<li><a
								href='<%=request.getContextPath()%>/back_end/consultation/listAllConsult.jsp'>諮詢表單列表</a><br>
								<br></li>

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do">
									<b>輸入諮詢單編號 (如1):</b> <input type="text" name="consult_no">
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" class="btn btn-outline-secondary btn-sm"
										value="送出">
								</FORM> <br>
							</li>

							<jsp:useBean id="consultSvc" scope="page"
								class="com.consultation.model.ConsultService" />

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do">
									<b>選擇諮詢單編號:</b> <select size="1" name="consult_no">
										<c:forEach var="consultVO" items="${consultSvc.all}">
											<option value="${consultVO.consult_no}">${consultVO.consult_no}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" class="btn btn-outline-secondary btn-sm"
										value="送出">
								</FORM> <br>
							</li>

							<li>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do">
									<b>選擇諮詢人姓名:</b> <select size="1" name="consult_no">
										<c:forEach var="consultVO" items="${consultSvc.all}">
											<option value="${consultVO.consult_no}">${consultVO.consultant}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" class="btn btn-outline-secondary btn-sm"
										value="送出">
								</FORM> <br>
							</li>
						</ul>
						<h3>諮詢表單管理</h3>
						<hr>
						<br>
						<ul>
							<li><a
								href='<%=request.getContextPath()%>/back_end/consultation/addConsult.jsp'>新增諮詢表單</a></li>
						</ul>
					</div>
				</div>
			</section>

			<!--*******************	
		Start Include sidebar File  
		******************* -->
			<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
			<!--*******************	
		End Include sidebar File  
		******************* -->
		</div>
	</div>
</body>
</html>