<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<% 	

	RentalClassService rcSvc = new RentalClassService(); 
	List<RentalClassVO> listAll = rcSvc.getAll();
	List<RentalClassVO> list = (List<RentalClassVO>) request.getAttribute("list");
	if(list==null)
		list = listAll;
	
	pageContext.setAttribute("list",list);
	pageContext.setAttribute("listAll",listAll);
	
	List<String> rc_itemList = rcSvc.getAllRc_Item();
	pageContext.setAttribute("rc_itemList",rc_itemList);
	
%>

<html>
<head>
<title>租賃商品類別</title>
<%@ include file="../back_include_page/CSS_link.jsp"%>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<style>
  form { display: inline; }
  table {
 	background-color: white;
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

			<h3>租賃商品</h3>
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp'"  />
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品清單" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalProductList/listRpl.jsp'" />
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品圖片" onclick="location.href='<%=request.getContextPath()%>/back_end/rentalProductImages/listRpi.jsp'" />
			<br><br>
			<table>
				<tr>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp" >
				       		<input type="submit" value="顯示全部" class="btn btn-sm btn-primary">
			     		</FORM>
					</td>
					<td>
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" >
					       <b>編號:</b>
						   <input type="text" name="rc_no" size="1" >
					       <input type="hidden" name="action" value="getOne_For_Display">
					       <input type="submit" value="查詢" class="btn btn-sm btn-primary">
					     </FORM>
					</td>
					<td>
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" >
					       <b>編號:</b>
					       <select size="1" name="rc_no">
					         <c:forEach var="rcVOAll" items="${listAll}" > 
					          <option value="${rcVOAll.rc_no}" ${rcVOAll.rc_no==list.get(0).rc_no?'selected':''}  >${rcVOAll.rc_no}.${rcVOAll.rc_name}
					         </c:forEach>   
					       </select>
					       <input type="hidden" name="action" value="getOne_For_Display">					  
					       <input type="submit" value="查詢" class="btn btn-sm btn-primary">
					     </FORM>
					</td>		
					<td>
					     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" >
					       <b>種類:</b>
					       <select size="1" name="rc_item" >
					         <c:forEach var="rc_itemList" items="${rc_itemList}" >
			<!-- 		         list.get(0)取一個用來產生selected -->
					          <option value="${rc_itemList}" ${rc_itemList==param.rc_item?'selected':''} >${rc_itemList}
					         </c:forEach>   
					       </select>
					       <input type="hidden" name="action" value="getOneRc_item_For_Display">			
					       <input type="submit" value="查詢" class="btn btn-sm btn-primary">
					     </FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rentalClass/addRc.jsp" >
				       		<input type="submit" value="新增" class="btn btn-sm btn-primary">
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
					<th>下單</th>
					<th>編號</th>
					<th width="250px">名稱</th>
					<th>種類</th>
					<th>圖片</th>
					<th>押金</th>
					<th>價格/天</th>
					<th>庫存</th>
					<th>狀態</th>
					<th>上下架</th>	
					<th>修改</th>
				</tr>
			<%@ include file="page1.file"%> 
				<c:forEach var="rcVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr
					<c:if test="${empty errorMsgs}">
					${(rcVO.rc_no==param.rc_no) ? 'bgcolor=#CCCCFF':''}
					</c:if>
					>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" style="margin-bottom: 0px;">
						     <input type="submit" value="下單" class="btn btn-sm btn-primary">
						     <input type="hidden" name="rc_no"  value="${rcVO.rc_no}">
						     <input type="hidden" name="action"  value="addRo">
						  </FORM>
						</td>
						<td>${rcVO.rc_no}</td>
						<td><A href=
						"<%=request.getContextPath()%>/back_end/rpl/rpl.do?action=getOneClass_For_Display&rc_no=${rcVO.rc_no}">
						${rcVO.rc_name}</A></td>
						<td>${rcVO.rc_item}</td>
						<td><A href=
						"<%=request.getContextPath()%>/back_end/rpi/rpi.do?action=getOneClass_For_Display&rc_no=${rcVO.rc_no}">
						查看</A></td>
						<td>${rcVO.rc_deposit}</td>
						<td>${rcVO.rc_price}</td>
						<td>${rcVO.rc_storage}</td>
						<td>${rcVO.rc_status}</td>
						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" style="margin-bottom: 0px;">
						     <input type="submit" value=${rcVO.rc_status=="上架"?"下架":"上架"} class="btn btn-sm btn-primary">
						     <input type="hidden" name="rc_no"  value="${rcVO.rc_no}">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
						     <input type="hidden" name="action"	value="getOne_Change_Status"></FORM>
						</td>

						<td>
						  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rc/rc.do" style="margin-bottom: 0px;">
						     <input type="submit" value="修改" class="btn btn-sm btn-primary">
						     <input type="hidden" name="rc_no"  value="${rcVO.rc_no}">   
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