<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.appraisal_case_images.model.*"%>

<%
	Appraisal_Case_ImagesVO appraisalCaseImagesVO = (Appraisal_Case_ImagesVO) request.getAttribute("appraisalCaseImagesVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>照片資料</title>

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
		 <h3>照片資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/appraisal_case_images/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>
<table>
	<tr>
		<th>估價案件圖片編號</th>
		<th>估價案件編號</th>
		<th>估價圖片</th>
	</tr>
	<tr>
		<td>${appraisalCaseImagesVO.aci_no }</td>
		<td>${appraisalCaseImagesVO.aca_no }</td>
		<td><img width="120" height="120" src="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do?aci_no=${appraisalCaseImagesVO.aci_no}&action=showIMG"></td>
	</tr>
</table>
</body>
</html>