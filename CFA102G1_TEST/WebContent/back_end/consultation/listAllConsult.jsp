<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.consultation.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
                                                     <%-- 0403版本+有小視窗版本 --%>
<%
	ConsultService consultSvc = new ConsultService();
	List<ConsultVO> list = consultSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="consultDAO" scope="page"
	class="com.consultation.model.ConsultDAO" />

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
<!-- 處理 Bootstrap CSS 衝突 加入下面這行,然後將HTML包裝在帶有類bootstrap-iso的div中 <div class="bootstrap-iso"> 本頁145行 -->
<link rel="stylesheet"
	href="https://formden.com/static/assets/demos/bootstrap-iso/bootstrap-iso/bootstrap-iso.css" />
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<meta name="viewport" content="width=device-width, initial-scale=1">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


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

<style>
.myTable {
	width: 100%;
}

.myTable * {
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
							諮詢表單列表><a
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
									<th>修改</th>
<!-- 									<th>刪除</th> -->
								</tr>
							</thead>
							<tbody>
								<c:forEach var="consultVO" items="${consultDAO.all}" varStatus="s">

									<tr
										${(consultVO.consult_no==param.consult_no) ? 'bgcolor=#cfe9d3':''}>
										<!--將修改的那一筆加入對比色而已-->
										<td><A
											href="consult.do?consult_no=${consultVO.consult_no}&action=getOne_From06">${consultVO.consult_no}</a></td>
										<td>${consultVO.consultant}</td>
										<td>${consultVO.consult_phone}</td>
										<td>${consultVO.consult_email}</td>
										<td>${consultVO.consult_content}</td>
										<td>${consultVO.staff_no}</td>
										<td>${consultVO.consult_sta}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="consult_no"
													value="${consultVO.consult_no}"> 
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
<!-- 										<td> -->
<!-- 											<FORM METHOD="post" -->
<%-- 												ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do" --%>
<!-- 												style="margin-bottom: 0px;"> -->
<!-- 												<input type="submit" value="刪除"> <input -->
<%-- 													type="hidden" name="consult_no" value="${consultVO.consult_no}"> <input --%>
<!-- 													type="hidden" name="action" value="delete"> -->
<!-- 											</FORM> -->
<!-- 										</td> -->
									</tr>
								</c:forEach>
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
	<div class="bootstrap-iso">
		<c:if test="${openModal!=null}">

			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLongTitle">諮詢表單資料</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<!-- =========================================以下為原listOneEmp.jsp的內容========================================== -->
							<jsp:include page="listOneConsultModel.jsp" />
							<!-- =========================================以上為原listOneEmp.jsp的內容========================================== -->
						</div>

						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">取消</button>
						</div>
					</div>
				</div>
			</div>

			<script>
				$("#basicModal").modal({
					show : true
				});
			</script>
		</c:if>
	</div>
</body>
</html>