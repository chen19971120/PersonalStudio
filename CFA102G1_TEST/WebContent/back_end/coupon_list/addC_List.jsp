<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon_list.model.*"%>

<%
	Coupon_ListVO couponListVO = (Coupon_ListVO) request.getAttribute("couponListVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>優惠券明細資料新增</title>

</head>
<body>

	<table>
		<tr>
			<td><h3>優惠券明細資料新增</h3></td>
			<td><h4>
					<a href="<%=request.getContextPath()%>/back_end/coupon_lsit/select_page.jsp">回首頁</a>
				</h4></td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%= request.getContextPath()%>/back_end/coupon_lsit/coupon_lsit.do">
		<table>
			<tr>
				<td>優惠券編號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="ci_no" size="45"></td>
			</tr>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="mem_no" size="45"></td>
			</tr>
			<tr>
				<td>狀態:<font color=red><b>*</b></font></td>
				<td><select size="1" name="cl_status">
					<option value="未使用" selected >未使用
					<option value="已使用" >已使用
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>
</html>