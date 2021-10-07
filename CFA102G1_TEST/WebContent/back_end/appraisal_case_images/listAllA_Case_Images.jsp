<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.appraisal_case_images.model.*" %>

<%
	Appraisal_Case_ImagesService appraisalCaseImagesSvc = new Appraisal_Case_ImagesService();
	List<Appraisal_Case_ImagesVO> list = appraisalCaseImagesSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>所有估價照片 - A_Case_Images.jsp</title>

<style>
  table {
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body>

<table id="table-1">
    <tr><td>
    	<h3>所有照片資料</h3>
        <h4><a href="<%=request.getContextPath()%>/back_end/appraisal_case_images/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table>
	<tr>
		<th>估價案件圖片編號</th>
		<th>估價案件編號</th>
		<th>估價圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="pages/page1.file" %>
	<c:forEach var="appraisalCaseImagesVO" items="${list}"  begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr ${(appraisalCaseImagesVO.aci_no==param.aci_no)?'bgcolor=#CCCCFF':''  }>
			<td>${appraisalCaseImagesVO.aci_no}</td>
			<td>${appraisalCaseImagesVO.aca_no}</td>
			<td><img width="120" height="120" src="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do?aci_no=${appraisalCaseImagesVO.aci_no}&action=showIMG"></td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do" style="margin-bottom: 0px;">
					<input type="submit" value="修改">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					<input type="hidden" name="aci_no" value="${appraisalCaseImagesVO.aci_no}">
					<input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do" style="margin-bottom: 0px;">
					<input type="submit" value="刪除">
					<input type="hidden" name="aci_no" value="${appraisalCaseImagesVO.aci_no}">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>
</body>
</html>