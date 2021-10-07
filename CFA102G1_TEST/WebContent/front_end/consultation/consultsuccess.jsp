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
	<!-- Start Page Banner -->
	<div class="page-title-area">
		<div class="container">
			<div class="page-title-content">
				<h2>表單傳送完成</h2>


			</div>
		</div>
	</div>
	<!-- End Page Banner -->
	<!-- Start Login Area -->
	<section class="login-area ptb-50">
		<div class="container">
			<div class="login-form">
				<div class="contact-information">
				<c:forEach var="siVO" items="${list}">
					<h2>表單傳送完成</h2>
					<p>我們會盡快與您聯絡，很高興能為您服務。</p>
					<p>若未收到門市的信件、電話聯絡，麻煩顧客主動聯繫門市，請見諒!!</p>
					<p>聯絡方式如下:</p>
					<ul class="contact-list">
								<li><i class='bx bx-map'></i> 地址: <span>${siVO.si_address}</span></li>
								<li><i class='bx bx-phone-call'></i> 聯絡電話: <span>${siVO.si_phone}</span></li>
								<li><i class='bx bx-envelope'></i> 電子郵件: <span>${siVO.si_email}</span></li>
								<li><i class='bx bx-right-arrow-circle'></i> LINE資訊: <span>${siVO.si_line}</span></li>
								<li><i class='bx bx-alarm'></i>星期一~星期六: <span>${siVO.si_open}</span></li>
								<li><i class='bx bx-alarm-off'></i>星期日: <span>CLOSE</span></li>
							</ul>
				</c:forEach>






					<div align="center" style="position:relative;top:10px">
								<a href="<%=request.getContextPath()%>/front_end/index.jsp"><button
										type="submit" class="btn btn-primary btn-lg">再來去逛逛!!
									</button></a>
					</div>




				</div>
			</div>
		</div>
	</section>
	<!-- End Login Area -->

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