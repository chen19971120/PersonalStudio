<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.store_information.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%-- 0403版本+有小視窗版本 --%>
<%
	SiService siSvc = new SiService();
	List<SiVO> list = siSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="siDAO" scope="page"
	class="com.store_information.model.SiDAO" />

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
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table {
	width: 1265px;
	margin-top: 5px;
	margin-bottom: 5px;
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
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div id="main">
			<section class="section">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							商家資訊列表><a
								href="<%=request.getContextPath()%>//back_end/store_information/select_page.jsp">商家資訊管理</a>
						</h3>
<!-- 						<div class="position-relative"> -->
<!-- 							<div class="position-absolute top-0 end-0"> -->
<!-- 								<h3> -->
<%-- 									<a href='<%=request.getContextPath()%>/back_end/store_information/addSi.jsp'><button type="button" --%>
<!-- 											class="btn btn-primary ">新增商家資訊</button></a> -->
<!-- 								</h3> -->
<!-- 							</div> -->
<!-- 						</div> -->
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
									<th>修改</th>
									<th>刪除</th>
								</tr>
							</thead>
							<tbody>
								<%@ include file="../back_include_page/page1.file"%>
								<c:forEach var="siVO" items="${list}" begin="<%=pageIndex%>"
									end="<%=pageIndex+rowsPerPage-1%>">
									<tr ${(siVO.si_no==param.si_no) ? 'bgcolor=#cfe9d3':''}>
										<!--將修改的那一筆加入對比色而已-->
										<td><A
											href="si.do?si_no=${siVO.si_no}&action=getOne_From06">${siVO.si_no}</a></td>
										<td>${siVO.si_address}</td>
										<td>${siVO.si_open}</td>
										<td>${siVO.si_phone}</td>
										<td>${siVO.si_email}</td>
										<td>${siVO.si_line}</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="修改"> <input
													type="hidden" name="si_no" value="${siVO.si_no}"> <input
													type="hidden" name="requestURL"
													value="<%=request.getServletPath()%>">
												<!--送出本網頁的路徑給Controller-->
												<input type="hidden" name="whichPage" value="<%=whichPage%>">
												<!--送出當前是第幾頁給Controller-->
												<input type="hidden" name="action" value="getOne_For_Update">
											</FORM>
										</td>
										<td>
											<FORM METHOD="post"
												ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do"
												style="margin-bottom: 0px;">
												<input type="submit" value="刪除"> <input
													type="hidden" name="si_no" value="${siVO.si_no}"> <input
													type="hidden" name="action" value="delete">
											</FORM>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@ include file="../back_include_page/page2.file"%>
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
							<jsp:include page="listOneSiModel.jsp" />
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