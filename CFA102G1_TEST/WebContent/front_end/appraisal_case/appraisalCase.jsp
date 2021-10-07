<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.appraisal_case.model.*"%>
<%@page import="java.sql.Timestamp"%>
<%@ page import="com.member.model.*"%>

<%
	Appraisal_CaseVO appraisalCaseVO = (Appraisal_CaseVO) request.getAttribute("appraisalCaseVO");
%>
<!DOCTYPE html>
<html>
<head>
<head>
<!--******************* Start Include CSS File ******************* -->
<%@ include file="../front_include_page/CSS_link.jsp"%>
<!--******************* End Include CSS File ******************* -->
<title>YSM3C - 二手租賃商城</title>
<link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</head>
<body>

	<!--******************* Start Top Head Area ******************* -->
	<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--******************* End Top Head Area ******************* -->

	<!--*******************	Start Navbar Area ******************* -->
	<%@ include file="../front_include_page/navbar.jsp"%>
	<!--******************* End Navbar Area ******************* -->

	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
<section class="contact-area ptb-50">
	<div class="container">
		<div class="row">
			<div class="col-lg-5 col-md-12">
		<div class="Precautions">
			<h3>注意事項</h3>
				<ol>
				<li>本公司收購商品皆會有刪除資料、還原原廠設定動作，因此請預先為您的產品內的資料備份，以免寶貴資料遺失。</li>
				<li>實際回收價格將依最後檢測結果而定，並視機器實際狀況，依市場行情適時調整回收價格，並保留收購與否權利，買賣若已成立恕不接受取消。</li>
				<li>保證所填寫之資料均為真實且正確，且未冒用或盜用第三人之資料，若未確實填寫或填寫錯誤或冒用、盜用資訊造成無法收到相關訊息，導致無法完成回收程序，得隨時取消本服務資格，且不因此負擔任何責任。</li>
				<li>回收流程實際所需天數將因個案情形不同而受到影響，YSM3C保有個案完成回收流程所需處理天數之最終決定權，如個案未能於2個工作天內完成回收流程，YSM3C不因此負擔任何責任。</li>
				<li>本公司秉持合法經營理念，拒收來源不正當之商品，填寫買賣合約書時，需核對您的證件，請您務必備妥身份證件。</li>
				<li>凡申請參加本活動之一部或全部者，即視同接受本活動全部之注意事項及相關規定。</li>
				</ol>
		</div>
	</div>
			<div class="col-lg-7 col-md-12">
				<div class="contact-form">
					<div class="tile">
						<div class="container px-5 my-5">
							<h3>請填寫估價表單</h3>
							<h5>打<font color=red><b>*</b></font>為必填</h5>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/appraisal_case/appraisal_case.do" enctype="multipart/form-data">
            <div class="mb-3">
                <label class="form-label" for="估價商品名稱">估價商品名稱<font color=red><b>*</b></font></label> 
                <input class="form-control" id="估價商品名稱" type="text" name="aca_itm_id" size="45" placeholder="估價商品名稱" value="<%=(appraisalCaseVO == null) ? "" : appraisalCaseVO.getAca_itm_id()%>"/>
            </div>
    <jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
            <div class="mb-3">
                <label class="form-label" for="估價類別">估價類別</label>
                <select class="form-select" id="估價類別" aria-label="估價類別" name="acl_no">
                   <c:forEach var="appraisalClassVO" items="${appraisalClassSvc.all}">
                        <option value="${appraisalClassVO.acl_no }"${(appraisalCaseVO.acl_no==appraisalClassVO.acl_no)?'selected':''}>${appraisalClassVO.acl_id }
                    </c:forEach>
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label" for="估價商品內容規格">估價商品內容(規格)</label>
                <textarea class="form-control" id="估價商品內容規格"  placeholder="估價商品內容(規格)" style="height: 10rem;" name="aca_itm_spec"></textarea>
            </div>
            <div class="mb-3">
                <label for="formFile" class="form-label">上傳估價照片,估價會更準確哦~</label>
                <input class="form-control" type="file" id="formFile" name="aci_img1" accept="image/*" onchange="loadFile1(event)"><img id="imgDisplay1" width="120" height="120">
                <input class="form-control" type="file" id="formFile" name="aci_img2" accept="image/*" onchange="loadFile2(event)"><img id="imgDisplay2" width="120" height="120">
                <input class="form-control" type="file" id="formFile" name="aci_img3" accept="image/*" onchange="loadFile3(event)"><img id="imgDisplay3" width="120" height="120">
            </div>
            <div class="mb-3">
                <label class="form-label" for="付款方式">付款方式</label>
                <select class="form-select" name="aca_pay" id="付款方式" aria-label="付款方式">
					<option value="現金" ${(appraisalCaseVO.aca_pay=="現金" )?'selected':'' }>現金
                   	<option value="轉帳" ${(appraisalCaseVO.aca_pay=="轉帳" )?'selected':'' }>轉帳
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label" for="運送方式">運送方式</label> 
                <select class="form-select" name="aca_cod" id="運送方式" aria-label="運送方式">
					<option value="自取、親送" ${(appraisalCaseVO.aca_cod=="自取、親送" )?'selected':'' }>自取、親送
					<option value="宅配" ${(appraisalCaseVO.aca_cod=="宅配" )?'selected':'' }>宅配
                </select>
            </div>
            <div class="mb-3">
                <label class="form-label" for="配送地址">配送地址<font color=red><b>*</b></font></label> 
                <input class="form-control" id="配送地址" name="aca_adrs" type="text" placeholder="配送地址" value="<%=(appraisalCaseVO == null) ? "" : appraisalCaseVO.getAca_adrs()%>" />
            </div>
            <div class="mb-3">
                <label class="form-label d-block"></label>
                <div class="form-check form-check-inline">
                    <input class="form-check-input" id="同意公開注意事項" type="checkbox" onchange="document.getElementById('submitButton').disabled = !this.checked;" />
                    <label class="form-check-label" for="同意公開注意事項">同意公開注意事項</label>
                </div>
            </div>
            <div class="d-grid">
	            <input type="hidden" name="action" value="addCase">
	            <input type="submit" name="action" value="開始估價" class="btn btn-primary btn-lg" id="submitButton" disabled>
            </div>
    </FORM>
			</div>
		</div>
	</div>
</div>

</div>
</div>
</section>
	<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>

	<!--******************* Start Footer Area ******************* -->
	<%@ include file="../front_include_page/footer.jsp"%>
	<!--******************* End Footer Area ******************* -->

	<!--******************* Start Go Top Area ******************* -->
	<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--******************* End Go Top Area ******************* -->

	<!--******************* Start Include JS File ******************* -->
	<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--******************* End Include JS File ******************* -->
</body>

<script>
	var loadFile1 = function(event) {
		var output = document.getElementById('imgDisplay1');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
	var loadFile2 = function(event) {
		var output = document.getElementById('imgDisplay2');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
	var loadFile3 = function(event) {
		var output = document.getElementById('imgDisplay3');
		output.src = URL.createObjectURL(event.target.files[0]);
		output.onload = function() {
			URL.revokeObjectURL(output.src) // free memory
		}
	};
</script>
</html>