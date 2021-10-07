<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="com.appraisal_case_images.model.*" %>
<%@ page import="com.appraisal_case.model.*" %>

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
<title>估價案件圖片</title>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>


</head>
<body onload="hiddenUpdate()">
<section class="section">
	<div class="card">
		<div class="card-body">
<c:if test="${not empty errorMsgs }">
	<font style="color:red">請修正以下錯誤：</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<table class="table table-striped" id="table1">

	<c:forEach var="appraisalCaseImagesVO" items="${appraisalCaseImagesVO}">
		<tr>
			<th>圖片編號:${appraisalCaseImagesVO.aci_no}</th>
			<th><select size="1" name="aca_itm_mode" id="selectMode" hidden>
					<option value="審核評估中" ${(appraisalCaseVO.aca_itm_mode=="審核評估中")?'selected':'' }>審核評估中
					<option value="提供報價" ${(appraisalCaseVO.aca_itm_mode=="提供報價")?'selected':'' }>提供報價
					<option value="提供成交價" ${(appraisalCaseVO.aca_itm_mode=="提供成交價")?'selected':'' }>提供成交價
					<option value="已收取商品" ${(appraisalCaseVO.aca_itm_mode=="已收取商品")?'selected':'' }>已收取商品
					<option value="確認付款" ${(appraisalCaseVO.aca_itm_mode=="確認付款")?'selected':'' }>確認付款
					<option value="商品退回" ${(appraisalCaseVO.aca_itm_mode=="商品退回")?'selected':'' }>商品退回
					<option value="完成案件" ${(appraisalCaseVO.aca_itm_mode=="完成案件")?'selected':'' }>完成案件
					<option value="取消案件" ${(appraisalCaseVO.aca_itm_mode=="取消案件")?'selected':'' }>取消案件
				</select></th>
			<th>估價圖片</th>
			<th><img width="120" height="120" src="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do?aci_no=${appraisalCaseImagesVO.aci_no}&action=showIMG"></th>
			<th>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do" style="margin-bottom: 0px;">
					<input type="submit" class="btn btn-outline-secondary" value="刪除">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					<input type="hidden" name="aci_no" value="${appraisalCaseImagesVO.aci_no}">
					<input type="hidden" name="aca_no" value="${appraisalCaseImagesVO.aca_no}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</th>
		</tr>
	</c:forEach>
		<tr>
			<td colspan="5">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/appraisal_case_images/appraisal_case_images.do" style="margin-bottom: 0px;" enctype="multipart/form-data">
   				<div class="mb-3">
                <label for="formFile" class="form-label">新增估價圖片</label>
                <input class="form-control" type="file" name="aci_img" accept="image/*" id="progressbarTWInput" multiple>
   				<div id="preview_progressbarTW_imgs"></div>
              	</div>
				<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
				<input type="hidden" name="aca_no" value="${aca_no}">
				<input type="hidden" name="action" value="insert"> 
				<input type="submit" class="btn btn-outline-secondary" value="新增圖片">
			</FORM>
			</td>
		</tr>
</table>
</div>
</div>
</section>
<!--*******************Start Include JS File******************* -->
<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
<!--*******************End Include JS File******************* -->
</body>
<script>
$("#progressbarTWInput").change(function(){
  $("#preview_progressbarTW_imgs").html(""); // 清除預覽
  readURL(this);
});

function readURL(input){
  if (input.files && input.files.length >= 0) {
    for(var i = 0; i < input.files.length; i ++){
      var reader = new FileReader();
      reader.onload = function (e) {
        var img = $("<img width='120' height='120'>").attr('src', e.target.result);
        $("#preview_progressbarTW_imgs").append(img);
      }
      reader.readAsDataURL(input.files[i]);
    }
  }
}
</script>
<script>
function hiddenUpdate() {
	var selectMode = document.getElementById("selectMode");
	var input = document.getElementsByTagName("input");
	for(var i = 0; i < input.length; i++){
		if (selectMode[6].selected == true) {
			input[i].disabled = true;
		}
	}
}
</script>
</html>