<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.consultation.model.*"%>
<%@ page import="com.store_information.model.*"%>
<%
	ConsultVO consultVO = (ConsultVO) request.getAttribute("consultVO");
%>
<%
	SiService siSvc = new SiService();
	List<SiVO> list = siSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html>
<head>
<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file="../front_include_page/CSS_link.jsp"%>
<!--*******************	
		End Include CSS File  
		******************* -->

<title>YSM3C - 二手租賃商城</title>

<link rel="icon" type="image/png"
	href="../front_CSS_JS/assets/img/favicon.png">
</head>
<body>

	<!--*******************	
		Start Top Head Area  
		******************* -->
	<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--*******************	
		End Top Head Area  
		******************* -->

	<!--*******************	
		Start Navbar Area  
		******************* -->
	<%@ include file="../front_include_page/navbar.jsp"%>
	<!--*******************	
		End Navbar Area  
		******************* -->

	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
	<!-- Start Contact Area -->
	<section class="contact-area ptb-50">
		<div class="container">
			<div class="row">
				<div class="col-lg-7 col-md-12">
					<div class="contact-form">
						<div class="tile">
							<h3>給我們留言</h3>
							<p>您的電子郵件不會被公開</p>
							<p>*為必填資料</p>
						</div>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/back_end/consultation/consult.do"
							name="form1">

							<div class="mb-3 row">
								<label for="inputConsultant" class="col-sm-2 col-form-label">諮詢人姓名*:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputConsultant"
										name="consultant">

								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_phone" class="col-sm-2 col-form-label">諮詢人手機*:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputConsult_phone"
										name="consult_phone">
								</div>
							</div>

							<div class="mb-3 row">
								<label for="inputConsult_email" class="col-sm-2 col-form-label">EMAIL*:</label>
								<div class="col-sm-10">
									<input type="TEXT" class="form-control" id="inputConsult_email"
										name="consult_email">
								</div>
							</div>


							<div class="mb-3 row">
								<label for="inputConsult_content"
									class="col-sm-2 col-form-label">諮詢內容*:</label>

								<textarea name="consult_content" id="inputConsult_content"
									cols="30" rows="5" class="form-control"></textarea>
							</div>

							<div class="mb-3 row">
								<!-- 								<label for="inputStaff_no" class="col-sm-2 col-form-label">員工編號:</label> -->
								<div class="col-sm-10">
									<input type="HIDDEN" class="form-control" id="inputStaff_no"
										name="staff_no" value="1">
								</div>
							</div>

							<div class="mb-3 row">
								<!-- 								<label for="inputConsult_sta" class="col-sm-2 col-form-label">回覆狀態:</label> -->
								<div class="col-sm-10">
									<input type="HIDDEN" class="form-control" id="inputConsult_sta"
										name="consult_sta" value="未回覆">
								</div>
							</div>
							<div align="center" style="position: relative; top: 10px">
								<input type="hidden" name="action" value="insert"> <input
									type="submit" class="default-btn" value="送出新增">
							</div>
						</FORM>
					</div>
				</div>

				<div class="col-lg-5 col-md-12">
					<div class="contact-information">
						<h3>商家資訊</h3>
						<c:forEach var="siVO" items="${list}">
							<ul class="contact-list">
								<li><i class='bx bx-map'></i> 地址: <span>${siVO.si_address}</span></li>
								<li><i class='bx bx-phone-call'></i> 聯絡電話: <span>${siVO.si_phone}</span></li>
								<li><i class='bx bx-envelope'></i> 電子郵件: <span>${siVO.si_email}</span></li>
								<li><i class='bx bx-right-arrow-circle'></i> LINE資訊: <span>${siVO.si_line}</span></li>
							</ul>

							<h4>營業時間:</h4>
							<ul class="opening-hours">
								<li>星期一: <span>${siVO.si_open}</span></li>
								<li>星期二: <span>${siVO.si_open}</span></li>
								<li>星期三: <span>${siVO.si_open}</span></li>
								<li>星期四: <span>${siVO.si_open}</span></li>
								<li>星期五: <span>${siVO.si_open}</span></li>
								<li>星期六: <span>${siVO.si_open}</span></li>
								<li>星期日: <span>CLOSE</span></li>
							</ul>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Contact Area -->

	<!-- ************************上面塞你自己的頁面資訊***************************** -->
	<!-- ************************上面塞你自己的頁面資訊***************************** -->

	<!--*******************	
		Start Footer Area  
		******************* -->
	<%@ include file="../front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->

	<!--*******************	
		Start Go Top Area  
		******************* -->
	<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->

	<!--*******************	
		Start Include JS File  
		******************* -->
	<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>