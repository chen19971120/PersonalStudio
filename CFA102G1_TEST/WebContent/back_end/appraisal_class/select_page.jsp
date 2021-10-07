<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>估價類別首頁</title>

</head>
<body>
	<table id="table-1">
		<tr>
			<td><h3>估價類別首頁</h3></td>
		</tr>
	</table>

	<h3>資料查詢：</h3>

	<c:if test="${not empty erroMsgs}">
		<font style="color: red">請修正以下錯誤</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<jsp:useBean id="appraisalClassSvc" scope="page"
			class="com.appraisal_class.model.Appraisal_ClassService" />

		<li><a href="<%= request.getContextPath()%>/back_end/appraisal_class/listAllA_Class.jsp">查詢所有估價類別</a></li>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_class.do">
				<b>選擇估價類別名稱：</b> <select size="1" name="acl_no">
					<c:forEach var="appraisalClassVO" items="${appraisalClassSvc.all}">
						<option value="${appraisalClassVO.acl_no}">${appraisalClassVO.acl_id}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>
	
	<h3>估價類別管理</h3>

<ul>
  <li><a href='<%= request.getContextPath()%>/back_end/appraisal_class/addA_Class.jsp'>Add</a></li>
</ul>
</body>
</html>