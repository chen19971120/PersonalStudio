<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.appraisal_case.model.*"%>

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
			<h2>估價案件新增完成</h2>
		</div>
	</div>
</div>
	<!-- End Page Banner -->
	<!-- Start Login Area -->
<section class="login-area ptb-50">
	<div class="container">
		<div class="login-form">
			<div class="contact-information">
				<div align="center" style="position:relative;top:10px">
					<div class="text-center mb-3">
						<div class="fw-bolder">您新增了一筆估價案件~</div>
							<p>到會員中心查看新增的估價案件</p>
							<a href="<%=request.getContextPath()%>/front_end/appraisal_case/memListA_Case.jsp">
							<button type="submit" class="btn btn-primary btn-lg ">查看估價案件</button></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

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