<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.reportProductReviews.model.*"%>
<% 	
	ReportProductReviewsService rprSvc = new ReportProductReviewsService();
	List<ReportProductReviewsVO> listAll = rprSvc.getAll();
	List<ReportProductReviewsVO> list = (List<ReportProductReviewsVO>) request.getAttribute("list");
	if(list==null)
		list = listAll;
	pageContext.setAttribute("list",list);
	
%>

<html>
<head>
<title>租賃評價檢舉</title>
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

			<h3>租賃評價檢舉</h3>
		
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃評價檢舉" onclick="location.href='<%=request.getContextPath()%>/back_end/reportProductReviews/listRpr.jsp'" />
			<input type="button" class="btn btn-sm btn-Secondary" value="租賃商品評價" onclick="location.href='<%=request.getContextPath()%>/back_end/productReviews/listPr.jsp'"/>
			<br><br>
			<table>
				<tr>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/reportProductReviews/listRpr.jsp" >
				       		<input type="submit" value="顯示全部" class="btn btn-sm btn-primary">
			     		</FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rep/rep.do" >
					       <b>編號:</b>
						   <input type="text" name="rep_no" size="1">
					       <input type="hidden" name="action" value="getOne_For_Display">
					       <input type="submit" value="查詢" class="btn btn-sm btn-primary">
					    </FORM>
					</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rep/rep.do" >
						   <b>檢舉狀態 :</b>
					        <select name="rep_status" >
					        	<option value="未處理" ${('未處理'==param.rep_status)?'selected':''}>未處理</option>
					        	<option value="通過" ${('通過'==param.rep_status)?'selected':''} >通過</option>
					            <option value="不通過" ${('不通過'==param.rep_status)?'selected':''} >不通過</option>
					        </select>
					        <input type="hidden" name="action" value="getOneRepStatus_For_Display">
					        <input type="submit" value="查詢" class="btn btn-sm btn-primary">
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
					<th>檢舉編號</th>
					<th>評價編號</th>
					<th>會員編號</th>
					<th width="300px">檢舉內容</th>
					<th width="100px">狀態</th>
					<th width="150px">處理</th>
				</tr>
			<%@ include file="page1.file"%> 
				<c:forEach var="repVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
					<tr
					<c:if test="${empty errorMsgs}">
					${(repVO.rep_no==param.rep_no) ? 'bgcolor=#CCCCFF':''}
					</c:if>
					>
						<td>${repVO.rep_no}</td>
						<td><A href="javascript:showPrDetail(${repVO.pr_no},${repVO.rep_no})">${repVO.pr_no}</A></td>
						<td>${repVO.mem_no}</td>
						<td>${repVO.report_content}</td>
						<td>${repVO.rep_status}</td>	
						<td>
							<c:if test="${repVO.rep_status=='未處理'}">
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rep/rep.do" >
						        <input type="submit" value="通過" class="btn btn-sm btn-danger">
						        <input type="hidden" name="rep_no" value="${repVO.rep_no}"> 
						        <input type="hidden" name="action" value="passReport"> 
						        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               
							</FORM>
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rep/rep.do" >
						        <input type="submit" value="不通過" class="btn btn-sm btn-success">
						        <input type="hidden" name="rep_no" value="${repVO.rep_no}">
						        <input type="hidden" name="action" value="notPassReport"> 
						        <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
							    <input type="hidden" name="whichPage"	value="<%=whichPage%>">               
							</FORM>	
							</c:if>
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
<script>
    function showPrDetail(data,data2){
    	document.open('<%=request.getContextPath()%>/back_end/rep/rep.do?rep_no='+data2+'&pr_no='+data+'&action=getOnePr_For_DisplayDetail', '' ,'height=700,width=650,left=800,top=200,resizable=yes,scrollbars=yes');
   	}   
</script>
</body>
</html>