<%@page import="com.appraisal_case.model.Appraisal_CaseVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Appraisal_CaseVO appraisalCaseVO = new Appraisal_CaseVO();
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
<body>
	<div id="app">
		<!--*******************	Start Include sidebar File ******************* -->
		<%@ include file="../back_include_page/sidebar.jsp"%>
		<!--*******************	End Include sidebar File ******************* -->
		<div id="main">
            <div class="page-title">
                <div class="row">
                    <div class="col-12 col-md-6 order-md-1 order-last">
                        <h3>估價案件首頁</h3><br>
                    </div>
                </div>
            </div>
	<h4>資料查詢：</h4>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

	<ul>

		<li><a href="<%= request.getContextPath() %>/back_end/appraisal_case/listAllA_Case.jsp">查詢所有估價案件</a></li>
		
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
				<b>輸入估價案件編號：</b>
				<input type="text" name="aca_no">
				<input type="submit" class="btn btn-outline-secondary" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>
		<jsp:useBean id="appraisalCaseSvc" scope="page" class="com.appraisal_case.model.Appraisal_CaseService" />
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
				<b>選擇估價案件編號：</b> <select size="1" name="aca_no">
					<c:forEach var="appraisalCaseVO" items="${appraisalCaseSvc.all}">
						<option value="${appraisalCaseVO.aca_no}">${appraisalCaseVO.aca_no}
					</c:forEach>
				</select> 
				<input type="submit" class="btn btn-outline-secondary" value="送出">
				<input type="hidden" name="action" value="getOne_For_Display">
			</FORM>
		</li>

	<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
		<li>
			<FORM METHOD="post"ACTION="<%= request.getContextPath()%>/back_end/appraisal_class/appraisal_class.do"style="margin-bottom: 0px;">
				<b><font color=orange>選擇類別:</font></b>
				<select size="1" name="acl_no">
				<c:forEach var="appraisalClassVo" items="${appraisalClassSvc.all }">
					<option value="${appraisalClassVo.acl_no }">${appraisalClassVo.acl_id }
				</c:forEach>
				</select>
				<input type="submit" class="btn btn-outline-secondary" value="查詢">
				<input type="hidden"name="action" value="listCaseByClass_A">
			</FORM>
		</li>
	</ul>
	<ul>
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
					<b>輸入會員編號:</b>
					<input type="text" name="mem_no"><br>
				<b>選擇估價案件狀態:</b> 
				<select size="1" name="aca_itm_mode">
						<option value=" "selected>
						<option value="審核評估中">審核評估中
						<option value="提供報價" >提供報價
						<option value="提供成交價">提供成交價
						<option value="已收取商品">已收取商品
						<option value="確認付款">確認付款
						<option value="商品退回">商品退回
						<option value="完成案件">完成案件
						<option value="取消案件">取消案件
				</select><br>
				
				<b>選擇付款方式:</b>
				<select size="1" name="aca_pay">
					<option value=" "selected>
					<option value="現金">現金
					<option value="轉帳">轉帳
				</select><br>
				
				<input type="submit" class="btn btn-outline-secondary" value="查詢">
				<input type="hidden" name="action" value="listA_Case_ByCompositeQuery">
			</FORM>
		</li>
	</ul>	

	<h3>類別查詢</h3>
	<ul>
	<li><a href="<%= request.getContextPath()%>/back_end/appraisal_class/listAllA_Class.jsp">估價類別</a></li>
	</ul>
	</div>
			
	<!--*******************	Start Include sidebar File  ******************* -->
        <%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	End Include sidebar File ******************* -->
	</div>
	
</body>
</html>