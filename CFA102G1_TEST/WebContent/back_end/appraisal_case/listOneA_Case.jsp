<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case.model.*"%>

<%
	Appraisal_CaseVO appraisalCaseVO = (Appraisal_CaseVO) request.getAttribute("appraisalCaseVO");
%>
<!DOCTYPE html>
<html>
<head>
<!--*******************	Start Include CSS File ******************* -->
<%@ include file="../back_include_page/CSS_link.jsp"%>
<!--*******************	End Include CSS File ******************* -->
<meta charset="UTF-8">
<title>YSM-3C 後台管理</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

</head>
<body onload="hiddenUpdate()">
<div id="app">
<!--*******************	Start Include sidebar File ******************* -->
<%@ include file="../back_include_page/sidebar.jsp"%>
<!--*******************	End Include sidebar File ******************* -->
	<div id="main">
		<h4>估價案件資料</h4><br>
		<section class="section">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title"><a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">返回管理估價案件</a></h3>
					<div class="card-body">
						<table class="table table-striped" id="table1">
							<tr>
								<td>估價案件編號:<font color=red><b>*</b></font></td>
								<td><%=appraisalCaseVO.getAca_no()%></td>
							</tr>
							<tr>
								<td>會員編號:<font color=red><b>*</b></font></td>
								<td><input type="hidden" name="mem_no" value="<%=appraisalCaseVO.getMem_no()%>"><%=appraisalCaseVO.getMem_no()%></td>
							</tr>
							<tr>
								<td>估價商品名稱:</td>
								<td><input type="text" name="aca_itm_id" size="45" value="<%=appraisalCaseVO.getAca_itm_id()%>" disabled></td>
							</tr>
							<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
							<tr>
								<td>估價類別:</td>
								<td><select size="1" name="acl_no" disabled>
										<c:forEach var="appraisalClassVO" items="${appraisalClassSvc.all}">
											<option value="${appraisalClassVO.acl_no }" ${(appraisalCaseVO.acl_no==appraisalClassVO.acl_no)?'selected':''}>${appraisalClassVO.acl_id }
										</c:forEach>
								</select></td>
							</tr>
							<tr>
								<td>估價商品內容(規格):</td>
								<td><textarea rows="5" name="aca_itm_spec" disabled><%=appraisalCaseVO.getAca_itm_spec()%></textarea></td>
							</tr>
							<tr>
								<td>案件日期:</td>
								<td><input type="text" name="aca_date" id="aca_date" value="<%=appraisalCaseVO.getAca_date()%>" disabled></td>
							</tr>
							<tr>
								<td>案見狀態:</td>
								<td><select size="1" name="aca_itm_mode" id="selectMode" disabled>
										<option value="審核評估中" ${(appraisalCaseVO.aca_itm_mode=="審核評估中")?'selected':'' }>審核評估中
										<option value="提供報價" ${(appraisalCaseVO.aca_itm_mode=="提供報價")?'selected':'' }>提供報價
										<option value="提供成交價" ${(appraisalCaseVO.aca_itm_mode=="提供成交價")?'selected':'' }>提供成交價
										<option value="已收取商品" ${(appraisalCaseVO.aca_itm_mode=="已收取商品")?'selected':'' }>已收取商品
										<option value="確認付款" ${(appraisalCaseVO.aca_itm_mode=="確認付款")?'selected':'' }>確認付款
										<option value="商品退回" ${(appraisalCaseVO.aca_itm_mode=="商品退回")?'selected':'' }>商品退回
										<option value="完成案件" ${(appraisalCaseVO.aca_itm_mode=="完成案件")?'selected':'' }>完成案件
										<option value="取消案件" ${(appraisalCaseVO.aca_itm_mode=="取消案件")?'selected':'' }>取消案件
								</select></td>
							</tr>
							<tr>
								<td>報價:</td>
								<td><input type="text" name="aca_first_p" value="<%=appraisalCaseVO.getAca_first_p()%>" disabled></td>
							</tr>
							<tr>
								<td>門市收貨日期:</td>
								<td><input type="text" name="aca_recpt_date" id="aca_recpt_date" value="<%=(appraisalCaseVO.getAca_recpt_date() == null) ? "" : appraisalCaseVO.getAca_recpt_date()%>" disabled></td>
							</tr>
							<tr>
								<td>成交價:</td>
								<td><input type="text" name="aca_final_p" value="<%=appraisalCaseVO.getAca_final_p()%>" disabled></td>
							</tr>
							<tr>
								<td>出貨日期:</td>
								<td><input type="text" name="aca_shipment_date" id="aca_shipment_date" value="<%=(appraisalCaseVO.getAca_shipment_date() == null) ? "" : appraisalCaseVO.getAca_shipment_date()%>" disabled></td>
							</tr>
							<tr>
								<td>取貨日期:</td>
								<td><input type="text" name="aca_pickup_date" id="aca_ickup_date" value="<%=(appraisalCaseVO.getAca_pickup_date() == null) ? "" : appraisalCaseVO.getAca_pickup_date()%>" disabled></td>
							</tr>
							<tr>
								<td>付款方式:</td>
								<td><select size="1" name="aca_pay" disabled>
										<option value="現金" ${(appraisalCaseVO.aca_pay=="現金")?'selected':'' }>現金
										<option value="信用卡" ${(appraisalCaseVO.aca_pay=="信用卡")?'selected':'' }>信用卡
										<option value="轉帳" ${(appraisalCaseVO.aca_pay=="轉帳")?'selected':'' }>轉帳
								</select></td>
							</tr>
							<tr>
								<td>完成日期:</td>
								<td><input type="text" name="aca_comp_date" id="aca_comp_date" value="<%=(appraisalCaseVO.getAca_comp_date() == null) ? "" : appraisalCaseVO.getAca_comp_date()%>" disabled></td>
							</tr>
							<tr>
								<td>運送方式:</td>
								<td><select size="1" name="aca_cod" disabled>
										<option value="自取、親送" ${(appraisalCaseVO.aca_cod=="自取、親送")?'selected':'' }>自取、親送
										<option value="超商取貨" ${(appraisalCaseVO.aca_cod=="超商取貨")?'selected':'' }>超商取貨
										<option value="宅配" ${(appraisalCaseVO.aca_cod=="宅配")?'selected':'' }>宅配
								</select></td>
							</tr>
							<tr>
								<td>配送地址:</td>
								<td><input type="text" name="aca_adrs" value="<%=appraisalCaseVO.getAca_adrs()%>" disabled></td>
							</tr>
							<tr>
								<td>查看圖片</td>
								<td>
									<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do">
										<input type="hidden"name="aca_no" value="${appraisalCaseVO.aca_no}">
										<input type="hidden" name="action" value="imagesInformation">
										<input type="button" value="查看圖片" class="btn btn-outline-secondary"onclick="pressesImg(${appraisalCaseVO.aca_no})">
									</FORM>
								</td>						
							</tr>
						</table>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
							<button type="submit" value="修改" class="btn btn-outline-secondary" id="update">修改</button>
							<input type="hidden" name="aca_no" value="${appraisalCaseVO.aca_no}"> 
							<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
							<input type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>

	<!--*******************Start Include JS File******************* -->
	<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************End Include JS File******************* -->

</body>

<script>
	function hiddenUpdate() {
		var selectMode = document.getElementById("selectMode");
		var update = document.getElementById("update");
		var txt = "案件已完成無法修改";
		if (selectMode[6].selected == true) {
			update.innerText = txt;
			update.disabled = true;
		}
	}
	function pressesImg(data){
		window.open("<%= request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do?aca_no=" + data + "&action=imagesInformation","","height=600,width=900,left=65,top=157,resizable=yes,scrollbars=yes");
	}
</script>
</html>