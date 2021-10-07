<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>

<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
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
	<title>YSM-3C 後台-會員管理</title>
	<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
		<style>

  table#table-1 {
	background-color: grey;
    border: 2px solid black;
    text-align: center;
    
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
	width: 1300px;
	
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
/* 	margin-left:450px; */
  }
  table, th, td {
    border: 1px solid black;
  }
 
 
</style>	
<style> 
.divcss5-right{ display: flex;
    justify-content: center; 
    align-items: center; } 
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
		 <h3>所有會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/login/select_page_member.jsp">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
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
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>性別</th>
		<th>電話</th>
		<th>信箱</th>
		<th>地址</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>生日</th>
		<th>狀態</th>
		<th>修改</th>
	</tr>
	
<%@ include file="page1.file" %> 
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		
		<tr>
		
			<td>${memberVO.mem_no}</td>
			<td>${memberVO.mem_name}</td>
			<td>${memberVO.mem_gender}</td>
			<td>${memberVO.mem_phone}</td>
			<td>${memberVO.mem_email}</td>
			<td>${memberVO.mem_address}</td>
			<td>${memberVO.mem_account}</td>
			<td>${memberVO.mem_password}</td>
			<td>${memberVO.mem_birth}</td>
			<td>${memberVO.mem_sta}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
		</tr>
</c:forEach>

</table>
<%@ include file="page2.file" %>
		
	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* -->
		</div>
	</div>
</body>
</html>