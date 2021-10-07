<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalOrder.model.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalProductList.model.*"%>
<% 	
	RentalOrderService roSvc = new RentalOrderService();
	List<RentalOrderVO> list = (List<RentalOrderVO>) request.getAttribute("list");
	if(list==null)
		list = roSvc.getAll();
	pageContext.setAttribute("list",list);
	
	String roStatusSelect = (String) request.getAttribute("roStatusSelect");
	pageContext.setAttribute("roStatusSelect",roStatusSelect);
	
	Set<String> rostatuslist = new HashSet<String>();
	for(RentalOrderVO roVO : roSvc.getAll())
		rostatuslist.add(roVO.getRo_status());
	pageContext.setAttribute("rostatuslist",rostatuslist);
%>

<html>
<head>
<title>租賃訂單資訊</title>
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
		
			<h3>租賃訂單</h3>
			<font style="color:blue;font-weight:bold">訂單規則</font>
			<ul style="color:blue;font-weight:bold">
				<li>預約訂單需當天付款，否則隔天將取消</li>
				<li>訂單每超過1天，需多收2倍租金
				<li>當第9天沒歸還商品，將直接沒收押金，結束訂單
			</ul>
			<table>
				<tr>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rentalOrder/listRo.jsp" >
				       		<input type="submit" value="顯示全部" class="btn btn-sm btn-primary">
			     		</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" >
					        <b>訂單編號 :</b>
					        <input type="text" name="ro_no" size="2">
					        <input type="hidden" name="action" value="getOne_For_Display">
					        <input type="submit" value="查詢" class="btn btn-sm btn-primary">
				        </FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" >
					        <b>訂單狀態 :</b>
					        <select name="ro_status" >
					        		<c:forEach var="rostatus" items="${rostatuslist}">
										<option value="${rostatus}" ${(rostatus==param.ro_status)?'selected':''}>${rostatus}</option>
									</c:forEach>					        
					        </select>
					        <input type="hidden" name="action" value="getOneRoStatus_For_Display">
					        <input type="submit" value="查詢" class="btn btn-sm btn-primary">
				        </FORM>
					</td>			
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp" >
				       		 <input type="submit" value="新增訂單" class="btn btn-sm btn-primary">
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
			
			<c:if test="${not empty Msgs}">
				<ul>
					<c:forEach var="message" items="${Msgs}">
						<li style="color:blue">${Msgs}</li>
					</c:forEach>
				</ul>
			</c:if>
			
			<table class="table table-striped">
				<tr>
					<th>編號</th>
					<th>會員編號</th>
					<th>商品編號</th>
					<th width="120">狀態</th>
					<th>開始時間</th>
					<th>結束時間</th>
					<th>歸還狀態</th>
					<th>實際歸還時間</th>
					<th width="120">訂單選項</th>
				</tr>
			<%@ include file="page1.file"%> 
				<c:forEach var="roVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="s">
					<tr 
					<c:if test="${empty errorMsgs}">
					${(roVO.ro_no==param.ro_no) ? 'bgcolor=#CCCCFF':''}
					</c:if>
					>
						<td><A href="javascript:showDetail(${roVO.ro_no})">${roVO.ro_no}</A></td>
						<td>${roVO.mem_no}</td>
						<td>${roVO.rpl_no}</td>
						<td><span class="ro_status">${roVO.ro_status}</span></td>
						<td>${roVO.ro_starttime}</td>
						<td>${roVO.ro_endtime}</td>
						<td>${roVO.ro_return_status}</td>
						<td>${(roVO.ro_return_date=="1970-01-01")?"":roVO.ro_return_date}</td>
						<td>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" style="margin-bottom: 0px;">
						     <input type="submit" ${(roVO.ro_status=="已付款,待出貨")?"":"disabled"} value="出貨" class="btn btn-sm btn-primary">
						     <input type="hidden" name="ro_no"  value="${roVO.ro_no}">
						     <input type="hidden" name="action"	value="deliver">
						     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						     <input type="hidden" name="whichPage"	value="<%=whichPage%>">   
						    </FORM>
						    <input type="submit" value="結束" ${(roVO.ro_status=="結束")?"disabled":""} ${(roVO.ro_status=="取消")?"disabled":"" }
						     ${(roVO.ro_status=="結束-逾期未還")?"disabled":""} 
						     onclick="showEnd(${roVO.ro_no})" class="btn btn-sm btn-primary">
					</tr>
				</c:forEach>
			</table>
			<%@ include file="page2.file"%>
			
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" >
					        <b>訂單編號 :</b>
					        <input type="text" name="ro_no">
					        <input type="submit" value="付款" class="btn btn-sm btn-primary" class="pay">
					        <input type="hidden" name="action" value="payOneRO"> 
					        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
						    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               
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
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
		
    function showDetail(data){
    	document.open('<%=request.getContextPath()%>/back_end/ro/ro.do?ro_no='+data+'&action=getOne_For_DisplayDetail', '' ,'height=500,width=520,left=600,top=200,resizable=yes,scrollbars=yes');
   	}
    function showEnd(data){
        document.open('<%=request.getContextPath()%>/back_end/ro/ro.do?ro_no='+data+'&action=getOne_For_End', '' ,'height=500,width=520,left=600,top=100,resizable=yes,scrollbars=yes');
    }
       
</script>
</body>
</html>