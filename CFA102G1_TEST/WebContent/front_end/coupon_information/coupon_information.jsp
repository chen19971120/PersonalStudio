<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coupon_information.model.*"%>
<%@page import="java.util.stream.Collectors"%>
<%@ page import="java.util.*"%>

<%
	Coupon_InformationService couponInformaionSvc = new Coupon_InformationService();
	List<Coupon_InformationVO> list = couponInformaionSvc.getAll().stream().filter(i -> i.getCi_no()!= 1).collect(Collectors.toList());
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!--*******************	 Start Include CSS File ******************* -->
<%@ include file="../front_include_page/CSS_link.jsp"%>
<!--******************* End Include CSS File ******************* -->
<title>YSM3C - 二手租賃商城</title>
<link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">

</head>
<body>

	<!--******************* Start Top Head Area ******************* -->
	<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--******************* End Top Head Area ******************* -->

	<!--*******************	Start Navbar Area ******************* -->
	<%@ include file="../front_include_page/navbar.jsp"%>
	<!--******************* End Navbar Area ******************* -->

<div class="page-title-area">
	<div class="container">
		<div class="page-title-content">
			<h2>所有優惠卷</h2>
<section class="login-area ptb-50">
	<div class="card">
		<div class="card-header">
				<div class="card-body">
				<table class="table table-striped" id="table1">
				<thead>
				<tr>
					<th>優惠券名稱</th>
					<th>編碼</th>
					<th>開始時間</th>
					<th>結束時間</th>
					<th>促銷折扣</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach var="couponInformaionVO" items="${list}">
		
					<tr>
						<td>${couponInformaionVO.ci_name }</td>
						<td>${couponInformaionVO.ci_code }</td>
						<td><fmt:formatDate value="${couponInformaionVO.ci_start_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td><fmt:formatDate value="${couponInformaionVO.ci_end_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						<td>${couponInformaionVO.discount }</td>
						</tr>
						</c:forEach>
						</tbody>
				</table>
			</div>
		</div>
		</div>
</section>
		</div>
		</div>
	</div>
	<!-- End Page Banner -->
	<!-- Start Login Area -->

	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
	<!--******************* Start Footer Area ******************* -->
	<%@ include file="../front_include_page/footer.jsp"%>
	<!--******************* End Footer Area ******************* -->
	<!--******************* Start Go Top Area ******************* -->
	<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--******************* End Go Top Area ******************* -->
	<!--******************* Start Include JS File ******************* -->
	<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--******************* End Include JS File ******************* -->
</body>
</html>