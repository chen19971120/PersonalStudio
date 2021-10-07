<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case.model.*"%>
<%@page import="java.sql.Timestamp"%>

<%
	Appraisal_CaseVO appraisalCaseVO = (Appraisal_CaseVO) request.getAttribute("appraisalCaseVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>估價案件資料新增</title>

</head>
<body>

	<table>
		<tr>
			<td><h3>估價案件資料新增</h3></td>
			<td><h4>
					<a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">回首頁</a>
				</h4></td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
		<table>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><input type="text" name="mem_no" size="45"></td>
			</tr>
			<tr>
				<td>估價商品名稱:<font color=red><b>*</b></font></td>
				<td><input type="text" name="aca_itm_id" size="45"value="<%=(appraisalCaseVO == null) ? "電腦" : appraisalCaseVO.getAca_itm_id()%>"></td>
			</tr>
			<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
			<tr>
				<td>估價類別:<font color=red><b>*</b></font></td>
				<td><select size="1" name="acl_no">
						<c:forEach var="appraisalClassVO" items="${appraisalClassSvc.all}">
							<option value="${appraisalClassVO.acl_no }"
								${(appraisalCaseVO.acl_no==appraisalClassVO.acl_no)?'selected':''}>${appraisalClassVO.acl_id }
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>估價商品內容(規格):</td>
				<td><textarea name="aca_itm_spec" rows="5"></textarea></td>
			</tr>
			<tr>
				<td>案件日期:</td>
				<td><input type="text" name="aca_date" class="aca_date"></td>
			</tr>
			<tr>
				<td>案見狀態:</td>
				<td><select size="1" name="aca_itm_mode">
					<option value="審核評估中"selected>審核評估中
					<option value="提供報價" >提供報價
					<option value="提供成交價">提供成交價
					<option value="已收取商品">已收取商品
					<option value="確認付款">確認付款
					<option value="商品退回">商品退回
					<option value="完成案件">完成案件
					<option value="取消案件">取消案件
				</select></td>
			</tr>
			<tr>
				<td>報價:</td>
				<td><input type="text" name="aca_first_p"
					value="<%=(appraisalCaseVO == null) ? "0" : appraisalCaseVO.getAca_first_p()%>"></td>
			</tr>
			<tr>
				<td>門市收貨日期:</td>
				<td><input type="text" name="aca_recpt_date" class="aca_recpt_date"></td>
			</tr>
			<tr>
				<td>成交價:</td>
				<td><input type="text" name="aca_final_p"
					value="<%=(appraisalCaseVO == null) ? " " : appraisalCaseVO.getAca_final_p()%>"></td>
			</tr>
			<tr>
				<td>出貨日期:</td>
				<td><input type="text" name="aca_shipment_date" disabled></td>
			</tr>
			<tr>
				<td>取貨日期:</td>
				<td><input type="text" name="aca_pickup_date" disabled ></td>
			</tr>
			<tr>
				<td>付款方式:</td>
				<td><select size="1" name="aca_pay">
					<option value="現金" selected>現金
					<option value="轉帳">轉帳
				</select></td>
			</tr>
			<tr>
				<td>完成日期:</td>
				<td><input type="text" name="aca_comp_date" class="aca_comp_date"></td>
			</tr>
			<tr>
				<td>運送方式:</td>
				<td><select size="1" name="aca_cod">
					<option value="自取、親送" selected>自取、親送
					<option value="超商取貨">超商取貨
					<option value="宅配">宅配
					</select></td>
			</tr>
			<tr>
				<td>配送地址:</td>
				<td><input type="text" name="aca_adrs" value="<%=(appraisalCaseVO == null) ? "桃園市中壢區復興路46號8樓之805" : appraisalCaseVO.getAca_adrs()%>"></td>
			</tr>

		</table>
		<br> 
		<input type="hidden" name="action" value="insert"> 
		<input type="submit" value="送出新增">
	</FORM>

</body>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
//案件日期
	 $('.aca_date').datetimepicker({
	  format:'Y-m-d H:i:s',
	  value:new Date(),
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('.aca_comp_date').val()?$('.aca_comp_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//門市收貨日期
	 $('.aca_recpt_date').datetimepicker({
	  format:'Y-m-d H:i:s',
	  value:new Date(),
	  onShow:function(){
	   this.setOptions({
		minDate:$('.aca_date').val()?$('.aca_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//完成日期
	 $('.aca_comp_date').datetimepicker({
	  format:'Y-m-d H:i:s',
	  onShow:function(){
	   this.setOptions({
	    minDate:$('.aca_recpt_date').val()?$('.aca_recpt_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
});
</script>
</html>