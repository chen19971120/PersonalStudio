<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon_information.model.*"%>

<%
	Coupon_InformationVO couponInformationVO = (Coupon_InformationVO) request.getAttribute("couponInformationVO");
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
		<h3>優惠券資訊資料新增</h3>
<section class="section">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title"><a href="<%=request.getContextPath()%>/back_end/coupon_information/select_page.jsp">回首頁</a></h3>
		
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<div class="card-body">
			<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back_end/coupon_information/coupon_information.do">
				<table class="table table-striped" id="table1">
					<tr>
						<td>優惠券名稱:<font color=red><b>*</b></font></td>
						<td><input type="text" name="ci_name" size="45" value="<%=(couponInformationVO == null) ? "" : couponInformationVO.getCi_name()%>"></td>
					</tr>
					<tr>
						<td>編碼:<font color=red><b>*</b></font></td>
						<td><input type="text" name="ci_code" size="45" value="<%=(couponInformationVO == null) ? "" : couponInformationVO.getCi_code()%>"></td>
					</tr>
					<tr>
						<td>優惠券開始時間:<font color=red><b>*</b></font></td>
						<td><input type="text" name="ci_start_time" id="start_dateTime"></td>
					</tr>
					<tr>
						<td>優惠券結束時間:<font color=red><b>*</b></font></td>
						<td><input type="text" name="ci_end_time" id="end_dateTime"></td>
					</tr>
					<tr>
						<td>優惠券促銷折扣:<font color=red><b>*</b></font></td>
						<td><input type="text" name="discount"size="45" value="<%=(couponInformationVO == null) ? "" : couponInformationVO.getDiscount()%>"></td>
					</tr>
					<tr>
						<td>優惠券內容:<font color=red><b>*</b></font></td>
						<td><textarea rows="5" name="ci_content"><%=(couponInformationVO == null) ? "" : couponInformationVO.getCi_content()%></textarea></td>
					</tr>
				</table>
				<br> 
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" class="btn btn-secondary" value="送出新增">
			</FORM>
					</div>
					</div>
					</div>
					</section>
			</div> <!--id="main"-->
	</div> <!--id="app"-->
	
		<!--*******************
		Start Include JS File  
		******************* -->
		<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
		<!--*******************	
		End Include JS File  
		******************* -->

</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/coupon_information/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back_end/coupon_information/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back_end/coupon_information/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
	 $('#start_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_dateTime').val()?$('#end_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
	 
	 $('#end_dateTime').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('#start_dateTime').val()?$('#start_dateTime').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
});
</script>
</html>