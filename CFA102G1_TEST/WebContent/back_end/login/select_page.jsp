<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.staff.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>員工設定</title>

<!-- <link rel="preconnect" href="https://fonts.gstatic.com"> -->

<!-- <link -->
<!-- 	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" -->
<!-- 	rel="stylesheet"> -->
<!-- <link rel="stylesheet" href="../back_CSS_JS/assets/css/bootstrap.css"> -->

<!-- <link rel="stylesheet" -->
<!-- 	href="../back_CSS_JS/assets/css/perfect-scrollbar.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="../back_CSS_JS/assets/css/bootstrap-icons.css"> -->
<!-- <link rel="stylesheet" href="../back_CSS_JS/assets/css/app.css"> -->


<!-- <link rel="shortcut icon" -->
<!-- 	href="../back_CSS_JS/assets/images/favicon.svg" type="image/x-icon"> -->

<%@ include file="../back_include_page/CSS_link.jsp"%>

</head>

<body>
	<div id="app">
		<!--*******************	
		Start Include sidebar File  
		******************* -->
		<%@ include file="../back_include_page/sidebar.jsp"%>
		<!--*******************	
		End Include sidebar File  
		******************* -->
		<div id="main">


			<div id="div1">
				<section class="section">
				<div class="row" id="table-head">
					<div class="col-12">
				
					<div class="card">
						<div class="card-header">
							<h4 class="card-title">員工資料管理</h4>
						</div>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<div>
								<c:forEach var="message" items="${errorMsgs}">
									<div style="color: red">${message}</div>
								</c:forEach>
							</div>
						</c:if>




						<div class="card-body">
							<div class="row">
								<div class="col-md-6">
									<h3>
										查詢員工 :<br>
									</h3>
									<div class="form-group">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/staff/staff.do">
											<label for="basicInput">輸入員工編號</label> <input type="text"
												class="form-control" id="basicInput" name="staff_no">
											<input type="hidden" name="action" value="getOne_For_Display">
											<input type="submit" value="送出" class="btn btn-primary">
										</FORM>
									</div>
									<br>
									<jsp:useBean id="staffSvc" scope="page"
										class="com.staff.model.StaffService" />
									<div class="form-group">
										<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/staff/staff.do">
											<label for="helpInputTop">選擇員工編號 : </label> <select size="1"
												name="staff_no"
												class="btn btn-secondary dropdown-toggle me-1">
												<c:forEach var="staffVO" items="${staffSvc.all}">
													<option value="${staffVO.staff_no}">${staffVO.staff_no}
												</c:forEach>
											</select> <input type="hidden" class="form-control" name="action"
												value="getOne_For_Display"> <input type="submit"
												value="送出" class="btn btn-primary">
										</FORM>
									</div>
									<br> <br>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/staff/staff.do">
										<div class="form-group">
											<label for="helperText">選擇員工姓名 :</label> <select size="1"
												name="staff_no"
												class="btn btn-secondary dropdown-toggle me-1">
												<c:forEach var="staffVO" items="${staffSvc.all}">
													<option value="${staffVO.staff_no}">${staffVO.staff_name}
												</c:forEach>
											</select> <input type="hidden" id="helperText" name="action"
												value="getOne_For_Display" class="form-control"> <input
												type="submit" value="送出" class="btn btn-primary">

										</div>
									</FORM>
								</div>
								<div class="col-md-6">
									<h3>
										新增員工&<br>查詢個人員工資訊 :
									</h3>

									<div class="form-group">
										<label for="disabledInput">新增員工</label> <br>
										<div>
											<a
												href="<%=request.getContextPath()%>/back_end/staff/addStaff.jsp">新增</a>
											a new Staff.
										</div>
									</div>
									<br> <br>

									<div class="form-group">
										<label for="disabledInput">員工個人資料修改</label> <br>
										<div>
											<a
												href="<%=request.getContextPath()%>/back_end/staff/update_One_Staff.jsp">修改</a>
											Staff
										</div>
									</div>
									<br> <br>
									<div class="form-group">
										<label for="disabledInput">員工個人資料查詢</label> <br>
										<div>
											<a
												href="<%=request.getContextPath()%>/back_end/staff/listOneStaffInfo.jsp">查詢</a>
											Staff
										</div>
									</div>
									<br> <br>

									<div class="form-group">
										<label for="disabledInput">全體員工資料查詢</label> <br>
										<div>
											<a
												href="<%=request.getContextPath()%>/back_end/staff/listAllStaff.jsp">List</a>
											all Staff. <br>
											<br>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
</div>
</div>
				</section>
			</div>
<!-- 			<script src="../back_CSS_JS/assets/js/perfect-scrollbar.min.js"></script> -->
<!-- 			<script src="../back_CSS_JS/assets/js/bootstrap.bundle.min.js"></script> -->
		</div>
	</div>
	
				<!--*******************	
		Start Include sidebar File  
		******************* -->
			<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
			<!--*******************	
		End Include sidebar File  
		******************* -->

<!-- 	<script src="../back_CSS_JS/assets/js/main.js"></script> -->
</body>

</html>