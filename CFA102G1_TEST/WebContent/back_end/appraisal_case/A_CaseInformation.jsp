<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case.model.*"%>
<%@ page import="com.appraisal_class.model.*"%>

<%
	Appraisal_CaseVO appraisalCaseVO = (Appraisal_CaseVO) request.getAttribute("appraisalCaseVO");
%>

<jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />

<!DOCTYPE html>
<html>
<head>
<!--*******************	Start Include CSS File ******************* -->
<%@ include file="../back_include_page/CSS_link.jsp"%>
<!--*******************	End Include CSS File ******************* -->
<meta charset="UTF-8">
<title>估價案件資料</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">

</head>
<body>
<section class="section">
	<div class="card">
		<div class="card-body">
	<table class="table table-striped" id="table1">
    <tr>
        <td>估價案件編號</td>
        <td>${appraisalCaseVO.aca_no }</td>
    </tr>
    <tr>
        <td>會員編號</td>
        <td>${appraisalCaseVO.mem_no }</td>
    </tr>
    <tr>
        <td>估價商品名稱</td>
        <td>${appraisalCaseVO.aca_itm_id }</td>
    </tr>
    <tr>
        <td>估價類別</td>
        <td>
            <!--查詢估價類別名稱 -->
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
    
</table>
</div>
</div>
</section>
<!--*******************Start Include JS File******************* -->
<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
<!--*******************End Include JS File******************* -->
</body>
</html>