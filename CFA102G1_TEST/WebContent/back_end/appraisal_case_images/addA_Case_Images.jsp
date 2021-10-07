<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case_images.model.*" %>

<%
	Appraisal_Case_ImagesVO appraisalCaseImagesVO = (Appraisal_Case_ImagesVO)request.getAttribute("appraisalCaseImagesVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>新增照片 - addAppraisal_Case_Images.jsp</title>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>
<body>

<table id="table-1">
	<tr><td>
		 <h3>照片新增</h3></td><td>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
	</td></tr>
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

	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do"enctype="multipart/form-data">
		<table>
			<tr>
				<td width="120px">估價案件編號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="aca_no" size="45"></td>
			</tr>
			<tr>
				<td width="120px">估價商品圖片:</td>
				<td>
					<input type="file" name="aci_img1" accept="image/*" onchange="loadFile1(event)">
 					<img id="imgDisplay1" width="120"height="120">
				</td>
			</tr>
			<tr>
				<td width="120px">估價商品圖片:</td>
				<td>
					<input type="file" name="aci_img2" accept="image/*" onchange="loadFile2(event)">
 					<img id="imgDisplay2" width="120"height="120">
				</td>
			</tr>
			<tr>
				<td width="120px">估價商品圖片:</td>
				<td>
					<input type="file" name="aci_img3" accept="image/*" onchange="loadFile3(event)">
 					<img id="imgDisplay3" width="120"height="120">
				</td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">

</FORM>
<script>
var loadFile1 = function(event) {
var output = document.getElementById('imgDisplay1');
output.src = URL.createObjectURL(event.target.files[0]);
output.onload = function() {
	URL.revokeObjectURL(output.src) // free memory
	}
};
var loadFile2 = function(event) {
var output = document.getElementById('imgDisplay2');
output.src = URL.createObjectURL(event.target.files[0]);
output.onload = function() {
	URL.revokeObjectURL(output.src) // free memory
	}
};
var loadFile3 = function(event) {
var output = document.getElementById('imgDisplay3');
output.src = URL.createObjectURL(event.target.files[0]);
output.onload = function() {
	URL.revokeObjectURL(output.src) // free memory
	}
};
</script>
</body>
</html>