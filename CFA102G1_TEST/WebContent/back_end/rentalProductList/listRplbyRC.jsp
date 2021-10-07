<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalProductList.model.*"%>
<%@ page import="com.rentalClass.model.*"%>
<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />

<% 	

	List<RentalClassVO> rcVOlist = rcSvc.getAll();
	pageContext.setAttribute("rcVOlist",rcVOlist);
	
	List<RentalProductListVO> list = (List<RentalProductListVO>) request.getAttribute("list");
	pageContext.setAttribute("list",list);
	
	Integer rcVOselect = (Integer) request.getAttribute("rcVOselect");
	pageContext.setAttribute("rcVOselect",rcVOselect);
	
%>


<html>
<head>
<title>租賃商品清單</title>
<%@ include file="../back_include_page/CSS_link.jsp"%>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<style>
  form { display: inline; }
  table {
 	background-color:white;
	width: 900px;
	margin: 5px;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
.btn-primary {
  color: #fff;
  background-color: #15407f;
  border-color: #15407f;
}
.btn-primary:hover, .btn-primary:focus, .btn-primary:active:hover{
  color: #000;
  background-color: #fff;
  border-color: #15407f;
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
	<div id="app">

		<div id="main">

					 <h3>租賃商品清單</h3>

			
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp'"  />
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品清單" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalProductList/listRpl.jsp'" />
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品圖片" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalProductImages/listRpi.jsp'" />
			<br><br>
			
			<table>
				<tr>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rentalProductList/listRpl.jsp" >
				       		<input type="submit" value="顯示全部" class="btn btn-sm btn-primary">
			     		</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do" >
					        <b>商品編號 :</b>
					        <input type="text" name="rpl_no" size="1" >
					        <input type="hidden" name="action" value="getOne_For_Display">
					        <input type="submit" value="查詢" class="btn btn-sm btn-primary">
				        </FORM>
					</td>			
					<td>
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do" >
					       <b>類別:</b>
					       <select size="1" name="rc_no">
					         <c:forEach var="rcVO" items="${rcVOlist}" > 
					          <option value="${rcVO.rc_no}" ${(rcVO.rc_no==rcVOselect)?'selected':''}>${rcVO.rc_no}.${rcVO.rc_name}
					         </c:forEach>   
					       </select>
					       <input type="hidden" name="action" value="getOneClass_For_Display">
					       <input type="submit" value="查詢" class="btn btn-sm btn-primary">					       
					     </FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do?action=addRpl" >
				 	 		<input type="submit" value="新增商品" class="btn btn-sm btn-primary">
				 	 		<input type="hidden" name="rcVOselect" value="${rcVOselect}">
			     		</FORM>
					</td>
				</tr>	
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
			
			<table class="table table-striped">
				<tr>
					<th>商品編號</th>
					<th width="200">商品類別</th>
					<th width="200">種類</th>
					<th width="250">產品序號</th>
					<th>租賃次數</th>
					<th>狀態</th>
					<th>修改狀態</th>
					<th>修改</th>
				</tr>
			<%@ include file="page1.file"%> 
				<c:forEach var="rplVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr
					<c:if test="${empty errorMsgs}">
					${(rplVO.rpl_no==param.rpl_no) ? 'bgcolor=#CCCCFF':''}
					</c:if>	
					>
						<td>${rplVO.rpl_no}</td>
						<td>${(rcSvc.getOneRentalClass(rplVO.rc_no)).rc_no}</td>
						<td>${(rcSvc.getOneRentalClass(rplVO.rc_no)).rc_item}</td>
						<td>${rplVO.rpl_serialnum}</td>
						<td>${rplVO.rpl_rentcount}</td>
						<td>${rplVO.rpl_status}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do" style="margin-bottom: 0px;">
						     <input type="submit" value=${(rplVO.rpl_status=="整備")?"待租":"整備"} 
						     ${(rplVO.rpl_status!="整備"&&rplVO.rpl_status!="待租")?"disabled":""} class="btn btn-sm btn-primary">
						     <input type="hidden" name="rpl_no"  value="${rplVO.rpl_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
						     <input type="hidden" name="action"	value="getOne_Change_Status"></FORM>
						</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rpl/rpl.do" style="margin-bottom: 0px;">
						     <input type="submit" value="修改" class="btn btn-sm btn-primary">
						     <input type="hidden" name="rpl_no"  value="${rplVO.rpl_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
						     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
						</td>
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>	
		</div>
	</div>
<!--*******************	
		Start Include sidebar File  
		******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include sidebar File  
		******************* --> 
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
</script>
</body>
</html>