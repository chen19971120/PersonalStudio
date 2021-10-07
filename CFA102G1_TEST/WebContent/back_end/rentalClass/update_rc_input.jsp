<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalClass.model.*"%>

<%
	RentalClassVO rcVO = (RentalClassVO) request.getAttribute("rcVO"); 
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>租賃類別修改</title>
<%@ include file="../back_include_page/CSS_link.jsp"%>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<style>
  form { display: inline; }
  table {
  	background-color: white;
	width: 900px;
	margin: 5px;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
  button#control{
  	margin: 5px;
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

					 <h3>租賃商品修改</h3>
					 <h6><a href="<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp">回首頁</a></h6>
			<p>
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
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" name="form1">
			<table  class="table table-striped">
				<tr>
					<td>類別編號:<font color=red><b>*</b></font></td>
					<td><%=rcVO.getRc_no()%></td>
				</tr>
				<tr>
					<td>商品類別名稱:</td>
					<td><input type="TEXT" name="rc_name" size="30" value="<%=rcVO.getRc_name()%>" /></td>
				</tr>
				<tr>
					<td>種類:</td>
					<td><input type="TEXT" name="rc_item" size="30"	value="<%=rcVO.getRc_item()%>" /></td>
				</tr>
				<tr>
					<td>詳細資訊:</td>
					<td><input type="TEXT" name="rc_detail" size="30"	value="<%=rcVO.getRc_detail()%>" /></td>
				</tr>
				<tr>
					<td>押金:</td>
					<td><input type="TEXT" name="rc_deposit" size="30" value="<%=rcVO.getRc_deposit()%>" /></td>
				</tr>
				<tr>
					<td>價格/天:</td>
					<td><input type="TEXT" name="rc_price" size="30" value="<%=rcVO.getRc_price()%>" /></td>
				</tr>
				<tr>
					<td>評價總人數:</td>
					<td>
						<%=rcVO.getRc_total_count()%>
						<input type="hidden" name="rc_total_count" value="<%=rcVO.getRc_total_count()%>" />
					</td>
				</tr>
				<tr>
					<td>評價總分:</td>
					<td>
						<%=rcVO.getRc_total_score()%>
						<input type="hidden" name="rc_total_score" value="<%=rcVO.getRc_total_score()%>" />
					</td>
				</tr>
				<tr>
					<td>庫存:</td>
					<td>
						<%=rcVO.getRc_storage()%>
						<input type="hidden" name="rc_storage" value="<%=rcVO.getRc_storage()%>" />
					</td>
				</tr>
				<tr>
					<td>類別狀態:</td>
					<td><select size="1" name="rc_status">
						<option value="上架" ${(rcVO.rc_status=="上架")?'selected':'' } >上架
						<option value="下架" ${(rcVO.rc_status=="下架")?'selected':'' } >下架
					</select></td>
				</tr>
			
			</table>
			<br>
			<input type="hidden" name="action" value="update">
			<input type="hidden" name="rc_no" value="<%=rcVO.getRc_no()%>">
			<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
			<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">
			<input type="submit" value="修改" class="btn btn-sm btn-primary">
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