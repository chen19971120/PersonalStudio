<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO"); //MemberServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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
	<title>YSM-3C 後台-修改會員資料管理</title>
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
	margin-left:450px;
  }
  table, th, td {
    border: 1px solid black;
  }
  #div1 {
   margin-left:1670px;
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
		 <h3>會員資料修改</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/login/select_page_member.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>
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
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memberVO.getMem_no()%></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="mem_name" size="45" value="<%=memberVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>性別:</td>
		<td><select name="mem_gender" >
                     <option value="${memberVO.mem_gender}"> ${memberVO.mem_gender} </option>
                      
                     <option  value="男"> 男 </option>
                     <option value="女"> 女 </option>
                   </select></td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="TEXT" name="mem_phone" size="45"	value="<%=memberVO.getMem_phone()%>" /></td>
	</tr>
	<tr>
		<td>信箱:</td>
		<td><input type="TEXT" name="mem_email" size="45"	value="<%=memberVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>地址:</td>
		<td><input type="TEXT" name="mem_address" size="45" value="<%=memberVO.getMem_address()%>" /></td>
	</tr>
<tr>
		<td>帳號:</td>
		<td><input type="TEXT" name="mem_account" size="45"	value="<%=memberVO.getMem_account()%>" /></td>
	</tr>
	<tr>
		<td>密碼:</td>
		<td><input type="TEXT" name="mem_password" size="45"	value="<%=memberVO.getMem_password()%>" /></td>
	</tr>
    <tr>
		<td>生日:</td>
		<td><input type="date" name="mem_birth" size="45"	value="<%=memberVO.getMem_birth()%>" /></td>
	</tr>
	<tr>
		<td>狀態:</td>
		<td><select name="mem_sta" >
                     <option value="${memberVO.mem_sta}"> ${memberVO.mem_sta} </option>
                      
                     <option  value="正常"> 正常 </option>
                     <option value="停權"> 停權 </option>
                   </select></td>
	</tr>
	
</table>
<br>
<div id="div1">
<input type="hidden" name="action" value="back_update">
<input type="hidden" name="mem_no" value="<%=memberVO.getMem_no()%>">
<input type="submit" value="送出修改">
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