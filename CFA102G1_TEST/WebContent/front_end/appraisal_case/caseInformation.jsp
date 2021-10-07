<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case.model.*"%>
<%@ page import="com.appraisal_class.model.*"%>
<%@ page import="com.appraisal_case_images.model.*" %>

<%
	Appraisal_CaseVO appraisalCaseVO = (Appraisal_CaseVO) request.getAttribute("appraisalCaseVO");
%>

<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
<jsp:useBean id="appraisalCaseImages" scope="page" class="com.appraisal_case_images.model.Appraisal_Case_ImagesService" />

<!DOCTYPE html>
<html>
<head>
		<!--******************* Start Include CSS File ******************* -->
		<%@ include file="../front_include_page/CSS_link.jsp"%>
		<!--******************* End Include CSS File ******************* -->
<meta charset="UTF-8">
<title>YSM-3C 後台管理</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

</head>
<body>
<div id="app">
		<div id="main">
			<section class="section">
				<div class="row" id="basic-table">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">估價案件明細</h4>
							</div>
							
						<div class="card-content">
							<div class="card-body">
								<div class="table-responsive">
<table class="table table-lg">
	<tr>
        <td>估價案件編號</td>
        <td>${appraisalCaseVO.aca_no }</td>
    </tr>
    <tr>
        <td>估價商品名稱</td>
        <td>${appraisalCaseVO.aca_itm_id }</td>
    </tr>
    <tr>
        <td>估價類別</td>
        <td>
            <!-- 					查詢估價類別名稱 -->
            ${appraisalClassSvc.getOneA_Class(appraisalCaseVO.acl_no).acl_id }

        </td>
    </tr>
    <tr>
        <td>估價商品內容(規格)</td>
        <td>${appraisalCaseVO.aca_itm_spec }</td>
    </tr>
    <tr>
        <td>案件日期</td>
        <td><fmt:formatDate value="${appraisalCaseVO.aca_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>案見狀態</td>
        <td>${appraisalCaseVO.aca_itm_mode }</td>
    </tr>
    <tr>
        <td>報價</td>
        <td>${appraisalCaseVO.aca_first_p }</td>
    </tr>
    <tr>
        <td>門市收貨日期</td>
        <td><fmt:formatDate value="${appraisalCaseVO.aca_recpt_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>成交價</td>
        <td>${appraisalCaseVO.aca_final_p }</td>
    </tr>
    <tr>
        <td>出貨日期</td>
        <td><fmt:formatDate value="${appraisalCaseVO.aca_shipment_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>取貨日期</td>
        <td><fmt:formatDate value="${appraisalCaseVO.aca_pickup_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>付款方式</td>
        <td>${appraisalCaseVO.aca_pay }</td>
    </tr>
    <tr>
        <td>完成日期</td>
        <td><fmt:formatDate value="${appraisalCaseVO.aca_comp_date }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
        <td>運送方式</td>
        <td>${appraisalCaseVO.aca_cod }</td>
    </tr>
    <tr>
        <td>配送地址</td>
        <td>${appraisalCaseVO.aca_adrs }</td>
    </tr>
    <c:forEach var="appraisalCaseImagesVO" items="${appraisalCaseImagesVO }">
	<tr>
    	<td>估價圖片</td>
    	<td>
	    	<img width="100" height="100" src="<%=request.getContextPath()%>/front_end/appraisal_case/appraisal_case.do?aci_no=${appraisalCaseImagesVO.aci_no}&action=showIMG">
		</td>
  	</tr>
	</c:forEach>
</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</div>
</body>
</html>