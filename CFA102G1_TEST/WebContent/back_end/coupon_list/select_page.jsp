<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>優惠券明細首頁</title>
</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>優惠券明細首頁</h3></td>
		</tr>
	</table>

	<h3>資料查詢：</h3>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<ul>

		<li><a href="<%= request.getContextPath()%>/back_end/coupon_list/listAllC_List.jsp">查詢所有優惠券資訊</a></li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/coupon_list/coupon_list.do">
				<b>輸入優惠券明細編號：</b>
				<input type="text" name="ci_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>	
<!-- 		<li> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/coupon_list/coupon_list.do"> --%>
<!-- 				<b>查詢會員持有的優惠券：</b> -->
<!-- 				<input type="text" name="mem_no"> -->
<!-- 				<input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</li>			 -->
<%-- 		<jsp:useBean id="coupon_listSvc" scope="page" class="com.coupon_list.model.Coupon_ListService" /> --%>
<!-- 		<li> -->
<%-- 			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/coupon_list/coupon_list.do"> --%>
<!-- 				<b>查詢會員優惠卷的使用狀況：</b> <select size="1" name="ci_no"> -->
<%-- 					<c:forEach var="coupon_listVO" items="${coupon_listSvc.all}"> --%>
<%-- 						<option value="${coupon_listVO.mem_no}">${coupon_listVO.cl_status} --%>
<%-- 					</c:forEach> --%>
<!-- 				</select> <input type="hidden" name="action" value="getOne_For_Display"> -->
<!-- 				<input type="submit" value="送出"> -->
<!-- 			</FORM> -->
<!-- 		</li> -->
	</ul>
	
	<h3>優惠券明細管理</h3>

<ul>
  <li><a href='<%= request.getContextPath()%>/back_end/coupon_list/addC_List.jsp'>Add</a></li>
</ul>

</body>
</html>