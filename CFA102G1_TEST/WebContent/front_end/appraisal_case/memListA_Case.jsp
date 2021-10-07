<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appraisal_case.model.*"%>
<%@ page import="com.member.model.*" %>
<%@page import="java.util.stream.Collectors"%>
<%
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

	Appraisal_CaseService appraisalCaseSvc = new Appraisal_CaseService();
	List<Appraisal_CaseVO> list = appraisalCaseSvc.getAll().
				stream()
			   .filter(m -> m.getMem_no().equals(memberVO.getMem_no()))
			   .collect(Collectors.toList());
	pageContext.setAttribute("list", list);
%>

<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />

<!DOCTYPE html>
<html>
<head>
<!--*******************	Start Include CSS File ******************* -->
<%@ include file="../front_include_page/CSS_link.jsp"%>
<!--*******************	End Include CSS File ******************* -->
<meta charset="UTF-8">
<title>YSM3C - 二手租賃商城</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

</head>
<body>

	<!--******************* Start Top Head Area ******************* -->
	<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--******************* End Top Head Area ******************* -->

	<!--*******************	Start Navbar Area ******************* -->
	<%@ include file="../front_include_page/member_head.jsp"%>
	<!--******************* End Navbar Area ******************* -->

	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
<section class="checkout-area ptb-50">
<div class="container">
<div class="row">
<div class="col-lg-12 col-md-12">
	<div id="main">
		<h4>估價案件資料</h4><br>

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
				<div class="card-body">
					<table class="table table-striped" id="table1">
						<thead>
		<tr>
			<th>估價案件編號</th>
			<th>估價商品名稱</th>
			<th>估價類別</th>
			<th>案件日期</th>
			<th>案見狀態</th>
			<th>報價</th>
			<th>付款方式</th>
			<th>成交價</th>
			<th>完成日期</th>
			<th>運送方式</th>
			<th>配送地址</th>
			<th>查看詳情</th>
		</tr>
		</thead>
		<tbody>
		<%@ include file="pages/page1.file" %>
		<c:forEach var="appraisalCaseVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${appraisalCaseVO.aca_no }</td>
				<td>${appraisalCaseVO.aca_itm_id }</td>
				<td>
					<!--查詢估價類別名稱 -->
					${appraisalClassSvc.getOneA_Class(appraisalCaseVO.acl_no).acl_id }
				</td>
				<td><fmt:formatDate value="${appraisalCaseVO.aca_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${appraisalCaseVO.aca_itm_mode }</td>
				<td>${appraisalCaseVO.aca_first_p }</td>
				<td>${appraisalCaseVO.aca_pay }</td>
				<td>${appraisalCaseVO.aca_final_p }</td>
				<td><fmt:formatDate value="${appraisalCaseVO.aca_comp_date }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${appraisalCaseVO.aca_cod }</td>
				<td>${appraisalCaseVO.aca_adrs }</td>
				<td>
					<FORM METHOD="post"	ACTION="<%= request.getContextPath()%>/front_end/appraisal_case/appraisal_case.do">
						<input type="hidden"name="aca_no" value="${appraisalCaseVO.aca_no}">
						<input type="hidden" name="action" value="caseInformation">
						<input type="button" value="查看詳情"class="btn btn-outline-secondary" onclick="presses(${appraisalCaseVO.aca_no})">
					</FORM>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%@ include file="pages/page2.file" %>
</div>
</div>
</div>
</section>
</div>
</div>
</div></div></section>
	<!--******************* Start Footer Area ******************* -->
	<%@ include file="../front_include_page/footer.jsp"%>
	<!--******************* End Footer Area ******************* -->

	<!--******************* Start Go Top Area ******************* -->
	<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--******************* End Go Top Area ******************* -->

	<!--******************* Start Include JS File ******************* -->
	<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--******************* End Include JS File ******************* -->

	<script>
		function presses(data){
			window.open("<%= request.getContextPath()%>/front_end/appraisal_case/appraisal_case.do?aca_no=" + data + "&action=caseInformation","","height=750,width=500,left=65,top=157,resizable=yes,scrollbars=yes");
		}
	</script>

</body>
</html>