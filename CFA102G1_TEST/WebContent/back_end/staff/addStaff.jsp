<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.staff.model.*"%>

<%
  StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
%>
<!DOCTYPE html>
<html>
<head>
	<!--*******************	
		Start Include CSS File  
		******************* -->
        <%@ include file="../back_include_page/CSS_link.jsp"%>
	<!--*******************	
		End Include CSS File  
		******************* -->  
	<meta charset="UTF-8">
	<title>YSM-3C 後台-資料新增管理</title>
	<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
		 
<style>
  table#table-1 {
	background-color: grey;
    border: 2px solid black;
    text-align: center;
  
  }
  table#table-1 h3 {
    
    margin-bottom: 1px;
  }
  
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>	
<style>
  table {
	width: 600px;
	margin-top: 1px;
	margin-bottom: 1px;
	margin-left:600px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
    margin-left:600px;
  }
  th, td {
    padding: 1px;
    margin-left:600px;
  }
  #div1 {
   margin-left:850px;
}
#div11{
 margin-left:600px;
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
		<table id="table-1">
	<tr><td>
		 <h3>員工資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/login/select_page.jsp">回首頁</a></h4>
	</td></tr>
</table>


<div id="div11">
<%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/staff/staff.do" name="form1">
<table>
	<tr>
		<td>員工姓名 :</td>
		<td><input type="TEXT" name="staff_name" size="45" 
			 value="<%= (staffVO==null)? "yamamoto" : staffVO.getStaff_name()%>" /></td>
	</tr>
	<tr>
		<td>性別 :</td>
		<td><select name="staff_gender" > 
                     <option  value="男"> 男 </option>
                     <option value="女"> 女 </option>
                   </select></td>
	</tr>
	<tr>
		<td>電話 :</td>
		<td><input type="TEXT" name="staff_phone" size="45"
			 value="<%= (staffVO==null)? "0933648215" : staffVO.getStaff_phone()%>" /></td>
	</tr>
	<tr>
		<td>信箱 :</td>
		<td><input type="TEXT" name="staff_email" size="45"
			 value="<%= (staffVO==null)? "xxxxxx@gmail.com" : staffVO.getStaff_email()%>" /></td>
	</tr>
	<tr>
	    <td>地址 :</td>
		<td><input type="TEXT" name="staff_address" size="45"
			 value="<%= (staffVO==null)? "台北市大安區中正路2000號11樓" : staffVO.getStaff_address()%>" /></td>
	</tr>
	<tr>
	    <td>帳號 :</td>
		<td><input type="TEXT" name="staff_account" size="45"
			 value="<%= (staffVO==null)? "xxooooxx" : staffVO.getStaff_account()%>" /></td>
	</tr>
	<tr>
	    <td>密碼 :</td>
		<td><input type="TEXT" name="staff_password" size="45"
			 value="<%= (staffVO==null)? "xxooooxx" : staffVO.getStaff_password()%>" /></td>
	</tr>
	
	<tr>
	     <td>狀態 :</td>
		<td><select name="staff_sta" >
                     
                     <option  value="正常"> 正常 </option>
                     <option value="停權"> 停權</option>
                   </select></td>
	</tr>


</table>
<br>
<div id="div1">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</div>
</FORM>

	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* -->

</body>
</html>