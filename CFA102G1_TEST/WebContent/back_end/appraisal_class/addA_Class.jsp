<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_class.model.*"%>

<%
	Appraisal_ClassVO appraisalClassVO = (Appraisal_ClassVO) request.getAttribute("appraisalClassVO");
%>
<!DOCTYPE html>
<html>
<head>
<!--*******************	Start Include CSS File ******************* -->
<%@ include file="../back_include_page/CSS_link.jsp"%>
<!--*******************	End Include CSS File ******************* -->
<meta charset="UTF-8">
<title>YSM-3C 後台管理</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

</head>
<body>

<div id="app">
<!--*******************	Start Include sidebar File ******************* -->
<%@ include file="../back_include_page/sidebar.jsp"%>
<!--*******************	End Include sidebar File ******************* -->
	<div id="main">
	<h3>估價類別資料新增</h3>
<section class="section">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title"><a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">返回管理估價案件</a></h3>

	<h4>資料新增:</h4>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
        <div class="card-body">
	<FORM METHOD="post" ACTION="<%= request.getContextPath() %>/back_end/appraisal_class/appraisal_class.do">
		<table class="table table-striped" id="table1">
			<tr>
				<td>估價類別名稱：</td>
				<td><input type="text" name="acl_id" size="45"
					value="<%= (appraisalClassVO == null) ? "電腦" : appraisalClassVO.getAcl_id()%>"></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
	     <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"><!--只用於:istAllEmp.jsp-->
		<input type="submit" value="送出新增" class="btn btn-outline-secondary">
	</FORM>
	</div>
    			</div>
    		</div>
	    </section>
    </div>
</div>
    
	<!--*******************Start Include JS File******************* -->
	<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************End Include JS File******************* -->
    

</body>
</html>