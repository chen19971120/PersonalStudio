<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.coupon_list.model.*"%>

<%
Coupon_ListVO couponListVO = (Coupon_ListVO) request.getAttribute("couponListVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�u�f����Ӹ�ƭק�</title>

</head>
<body>

	<table>
		<tr><td><h3>�u�f����Ӹ�ƭק�</h3>
				<h4><a href="<%=request.getContextPath()%>/back_end/coupon_list/select_page.jsp">�^����</a></h4></td>
		</tr>
	</table>

	<h3>��ƭק�:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%= request.getContextPath()%>/back_end/coupon_list/coupon_list.do">
		<table>
			<tr>
				<td>�u�f��s��:<font color=red><b>*</b></font></td>
				<td><%=couponListVO.getCi_no() %></td>
			</tr>
			<tr>
				<td>�|���s��:<font color=red><b>*</b></font></td>
				<td><%=couponListVO.getCi_no()%></td>
			</tr>
			<tr>
				<td>���A:<font color=red><b>*</b></font></td>
				<td><select size="1" name="cl_status">
					<option value="�w�ϥ�" selected>�w�ϥ�
					<option value="���ϥ�" >���ϥ�
				</select></td>
			</tr>

		</table>
		<br> <input type="hidden" name="action" value="update"> <input
			type="hidden" name="ci_no" value="<%=couponListVO.getCi_no()%>">
		<input type="submit" value="�e�X�ק�">
	</FORM>
</body>
</html>