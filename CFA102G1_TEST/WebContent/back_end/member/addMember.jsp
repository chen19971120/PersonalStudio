<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
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


  <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css">
  <script src="//apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
  <script src="//apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
  <link rel="stylesheet" href="jqueryui/style.css">
  <script>
  $(function() {
    $( "#datepicker" ).datepicker();
  });
  </script>
</style>												
</head>
<body>
<div id="app">
	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/sidebar.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* -->  
		<div id="main">
		<table id="table-1">
	<tr><td>
		 <h3>會員資料新增</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back_end/login/select_page_member.jsp">回首頁</a></h4>
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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1">
<table>
	<tr>
		<td>員工姓名 :</td>
		<td><input type="TEXT" name="mem_name" size="45" 
			 value="<%= (memberVO==null)? "yamamoto" : memberVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>性別 :</td>
		<td><input type="TEXT" name="mem_gender" size="45"
			 value="<%= (memberVO==null)? "女" : memberVO.getMem_gender()%>" /></td>
	</tr>
	<tr>
		<td>電話 :</td>
		<td><input type="TEXT" name="mem_phone" size="45"
			 value="<%= (memberVO==null)? "0933648215" : memberVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>信箱 :</td>
		<td><input type="TEXT" name="mem_email" size="45"
			 value="<%= (memberVO==null)? "xxxxxx@gmail.com" : memberVO.getMem_email()%>" /></td>
	</tr>
	<tr>
	    <td>地址 :</td>
		<td><input type="TEXT" name="mem_address" size="45"
			 value="<%= (memberVO==null)? "台北市大安區中正路2000號11樓" : memberVO.getMem_address()%>" /></td>
	</tr>
	<tr>
	    <td>帳號 :</td>
		<td><input type="TEXT" name="mem_account" size="45"
			 value="<%= (memberVO==null)? "xxooooxx" : memberVO.getMem_account()%>" /></td>
	</tr>
	<tr>
	    <td>密碼 :</td>
		<td><input type="TEXT" name="mem_password" size="45"
			 value="<%= (memberVO==null)? "xxooooxx" : memberVO.getMem_password()%>" /></td>
	</tr>
	<tr>
	    <td>生日 :</td>
		<td><input type="date" name="mem_birth" size="45" 
			 value="<%= (memberVO==null)? "2000/05/10" : memberVO.getMem_birth()%>" /></td>
	</tr>
	
	<tr>
	     <td>狀態 :</td>
		<td><input type="TEXT" name="mem_sta" size="45"
			 value="<%= (memberVO==null)? "100" : memberVO.getMem_sta()%>" /></td>
	</tr>
 

</table>
<br>
<div id="div1">
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</div>
</FORM>
</div>
    </div>

	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* -->

</body>
</html>