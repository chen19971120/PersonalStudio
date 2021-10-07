<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalProductList.model.*"%>

<%
	RentalProductListVO rplVO = (RentalProductListVO) request.getAttribute("rplVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改租賃商品</title>
<%@ include file="../back_include_page/CSS_link.jsp"%>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<style>
  form { display: inline; }
  table {
 	background-color:white;
	width: 900px;
	margin: 5px;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
.btn-primary {
  color: #fff;
  background-color: #15407f;
  border-color: #15407f;
}
.btn-primary:hover, .btn-primary:focus, .btn-primary:active:hover{
  color: #000;
  background-color: #fff;
  border-color: #15407f;
}
</style>
</head>
<body>
	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/sidebar.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* --> 
	<div id="app">
		<div id="main">
					 <h3>修改租賃商品</h3>
					 <h6><a href="<%=request.getContextPath()%>/back_end/rentalProductList/listRpl.jsp">回首頁</a></h6>
			
			<h5>資料修改:</h5>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do" name="form1">
			<table class="table table-striped">
			<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />
				<tr>
					<td>商品編號:<font color=red><b>*</b></font></td>
					<td><%=rplVO.getRpl_no()%></td>
				</tr>
				<tr>
					<td>租賃商品類別:</td>
					<td><select size="1" name="rc_no">
						<c:forEach var="rcVO" items="${rcSvc.all}">
							<option value="${rcVO.rc_no}" ${(rplVO.rc_no==rcVO.rc_no)?'selected':'' } >${rcVO.rc_no}.${rcVO.rc_name}
						</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>產品序號:</td>
					<td><input type="TEXT" name="rpl_serialnum" size="30"	value="<%=rplVO.getRpl_serialnum()%>" /></td>
				</tr>
				<tr>
					<td>備註:</td>
					<td><input type="TEXT" name="rpl_note" size="30"	value="<%=rplVO.getRpl_note()%>" /></td>
				</tr>
				<tr>
					<td>租賃次數:</td>
					<td>
					<%=rplVO.getRpl_rentcount()%>
					<input type="hidden" name="rpl_rentcount" value="<%=rplVO.getRpl_rentcount()%>" />
					</td>
				</tr>
				<tr>
					<td>新增日期:</td>
					<td>
					<%=rplVO.getRpl_jointtime()%>
					<input type="hidden" name="rpl_jointtime" value="<%=rplVO.getRpl_jointtime()%>" />
					</td>
				</tr>
				<tr>
					<td>租賃商品狀態:</td>
					<td>
						<select size="1" name="rpl_status">
						<option value="整備" ${(rplVO.rpl_status=="整備")?'selected':'' } >整備
						<option value="待租" ${(rplVO.rpl_status=="待租")?'selected':'' } >待租
						<option value="遺失" ${(rplVO.rpl_status=="遺失")?'selected':'' } >遺失
						<option value="損毀" ${(rplVO.rpl_status=="損毀")?'selected':'' } >損毀
						<option value="逾期未還" ${(rplVO.rpl_status=="逾期未還")?'selected':'' } >逾期未還
						</select>
					</td>
				</tr>
			
			</table>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="rpl_no" value="<%=rplVO.getRpl_no()%>">
			<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
			<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">
			<input type="submit" value="送出" class="btn btn-sm btn-primary">
			</FORM>		
		</div>
	</div>
<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* --> 
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
</script>
</body>
</html>