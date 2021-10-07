<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.model.*"%>   

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java(Concroller), 存入req的empVO物件
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
	<title>YSM-3C 後台-搜尋會員管理</title>
	<link rel="icon" type="image/png" href="/back_end/back_CSS_JS/assets/imgaes/logo/favicon.png">
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
	margin-left:450px;
  }
  table, th, td {
   border: 1px solid black;
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
		 <h3>會員資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/login/select_page_member.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號 :</th>
		<th>會員姓名 :</th>
		<th>性別 :</th>
		<th>電話 :</th>
		<th>信箱 :</th>
		<th>地址 :</th>
		<th>帳號 :</th>
		<th>密碼 :</th>
		<th>生日 :</th>
		<th>狀態 :</th>
		
	</tr>
	<tr>
		<td><%=memberVO.getMem_no()%></td>
		<td><%=memberVO.getMem_name()%></td>
		<td><%=memberVO.getMem_gender()%></td>
		<td><%=memberVO.getMem_phone()%></td>
		<td><%=memberVO.getMem_email()%></td>
		<td><%=memberVO.getMem_address()%></td>
		<td><%=memberVO.getMem_account()%></td>
		<td><%=memberVO.getMem_password()%></td>
		<td><%=memberVO.getMem_birth()%></td>
		<td><%=memberVO.getMem_sta()%></td>
	    <td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no"  value="${memberVO.mem_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
	
	
	
	</tr>
	
</table>
		
		
	<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* -->

</body>
</html>