<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.appraisal_class.model.*"%>

<%
	Appraisal_ClassService appraisalClassSvc = new Appraisal_ClassService();
	List<Appraisal_ClassVO> list = appraisalClassSvc.getAll();
	pageContext.setAttribute("list", list);
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
				<h3>所有估價類別資料</h3>

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
					<h3 class="card-title"><a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">返回管理估價案件</a></h3>
				<div class="card-body">
	<table class="table table-striped" id="table1">
		<thead>
		<tr>
			<th>估價類別編號</th>
			<th>估價類別名稱</th>
			<th>修改</th>
			<th>查詢單一類別估價單</th>
		</tr>
		</thead>
		<tbody>
		<%@ include file="pages/page1.file" %>
		<c:forEach var="appraisalClassVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

			<tr ${(appraisalClassVO.acl_no==param.acl_no) ? 'bgcolor=#69acd8':'' }>
				<td>${appraisalClassVO.acl_no }</td>
				<td>${appraisalClassVO.acl_id }</td>
				<td>
					<FORM METHOD="post"ACTION="<%= request.getContextPath()%>/back_end/appraisal_class/appraisal_class.do"style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-secondary" value="修改"> 
						<input type="hidden"name="acl_no" value="${appraisalClassVO.acl_no}">
						<input type="hidden" name="whichPage"	value="<%=whichPage%>">
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"ACTION="<%= request.getContextPath()%>/back_end/appraisal_class/appraisal_class.do"style="margin-bottom: 0px;">
						<input type="submit" class="btn btn-outline-secondary" value="送出查詢">
						<input type="hidden"name="acl_no" value="${appraisalClassVO.acl_no}">
						<input type="hidden" name="whichPage"	value="<%=whichPage%>">  
						<input type="hidden" name="action" value="listCase_ByClass_B">
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
	<!--*******************Start Include JS File******************* -->
	<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************End Include JS File******************* -->
	
	<%if (request.getAttribute("listCase_ByClass")!=null){ %>
		<jsp:include page="listCase_ByClass.jsp"></jsp:include>
	<%} %>
</body>
</html>