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
<body onload="hiddenMode()">

<div id="app">
<!--*******************	Start Include sidebar File ******************* -->
<%@ include file="../back_include_page/sidebar.jsp"%>
<!--*******************	End Include sidebar File ******************* -->

	<div id="main">
		<h4>估價案件資料修改</h4><br>
	<section class="section">
			<div class="card">
				<div class="card-header">
					<h3 class="card-title"><a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">返回管理估價案件</a></h3>

	<h3>資料修改:</h3>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

        <div class="card-body">
	 	<FORM METHOD="post" ACTION="<%= request.getContextPath()%>/back_end/appraisal_case/appraisal_case.do">
		<table class="table table-striped" id="table1">
            <tr>
                <td>估價案件編號:<font color=red><b>*</b></font></td>
                <td><%=appraisalCaseVO.getAca_no()%></td>
            </tr>
            <tr>
                <td>會員編號:<font color=red><b>*</b></font></td>
                <td><input type="hidden" name="mem_no"value="<%=appraisalCaseVO.getMem_no()%>"><%=appraisalCaseVO.getMem_no()%></td>
            </tr>
            <tr>
                <td>估價商品名稱:</td>
                <td><input type="text" name="aca_itm_id" size="45" value="<%=appraisalCaseVO.getAca_itm_id()%>"id="finish"></td>
            </tr>
            <jsp:useBean id="appraisalClassSvc" scope="page" class="com.appraisal_class.model.Appraisal_ClassService" />
            <tr>
                <td>估價類別:</td>
                <td><select size="1" name="acl_no" id="select">
                        <c:forEach var="appraisalClassVO" items="${appraisalClassSvc.all}">
                            <option value="${appraisalClassVO.acl_no }"
                                ${(appraisalCaseVO.acl_no==appraisalClassVO.acl_no)?'selected':''}>
                                ${appraisalClassVO.acl_id }
                        </c:forEach>
                    </select></td>
            </tr>
            <tr>
                <td>估價商品內容(規格):</td>
                <td><textarea rows="5" name="aca_itm_spec" ><%= appraisalCaseVO.getAca_itm_spec() %></textarea></td>
            </tr>
            <tr>
                <td>案件日期:</td>
                <td><input type="text" name="aca_date" id="aca_date" ></td>
            </tr>
            <tr>
                <td>案見狀態:</td>
                <td><select size="1" name="aca_itm_mode" id="selectMode" onchange="showDate()">
                        <option value="審核評估中" ${(appraisalCaseVO.aca_itm_mode=="審核評估中" )?'selected':'' }>審核評估中
                        <option value="提供報價" ${(appraisalCaseVO.aca_itm_mode=="提供報價" )?'selected':'' }>提供報價
                        <option value="提供成交價" ${(appraisalCaseVO.aca_itm_mode=="提供成交價" )?'selected':'' }>提供成交價
                        <option value="已收取商品" ${(appraisalCaseVO.aca_itm_mode=="已收取商品" )?'selected':'' }>已收取商品
                        <option value="確認付款" ${(appraisalCaseVO.aca_itm_mode=="確認付款" )?'selected':'' }>確認付款
                        <option value="商品退回" ${(appraisalCaseVO.aca_itm_mode=="商品退回" )?'selected':'' }>商品退回
                        <option id="caseMode" value="完成案件" ${(appraisalCaseVO.aca_itm_mode=="完成案件" )?'selected':'' } >完成案件
                        <option id="caseMode" value="取消案件" ${(appraisalCaseVO.aca_itm_mode=="取消案件" )?'selected':'' }>取消案件
                    </select></td>
            </tr>
            <tr>
                <td>報價:</td>
                <td><input type="text" name="aca_first_p" value="<%=appraisalCaseVO.getAca_first_p()%>" >
                </td>
            </tr>
            <tr>
                <td>門市收貨日期:</td>
                <td><input type="text" name="aca_recpt_date" id="aca_recpt_date" ></td>
            </tr>
            <tr>
                <td>成交價:</td>
                <td><input type="text" name="aca_final_p" value="<%=appraisalCaseVO.getAca_final_p()%>" >
                </td>
            </tr>
            <tr id=cancelDate style="display: none;">
                <td>出貨日期:</td>
                <td><input type="text" name="aca_shipment_date" id="aca_shipment_date" ></td>
            </tr>
            <tr id=cancelDate style="display: none;">
                <td>取貨日期:</td>
                <td><input type="text" name="aca_pickup_date" id="aca_pickup_date" ></td>
            </tr>
            <tr>
                <td>付款方式:</td>
                <td><select size="1" name="aca_pay" >
                        <option value="現金" ${(appraisalCaseVO.aca_pay=="現金" )?'selected':'' }>現金
                        <option value="轉帳" ${(appraisalCaseVO.aca_pay=="轉帳" )?'selected':'' }>轉帳
                    </select></td>
            </tr>
            <tr>
                <td>完成日期:</td>
                <td><input type="text" name="aca_comp_date" id="aca_comp_date" readonly style="background-Color: darkgray;"></td>
            </tr>
            <tr>
                <td>運送方式:</td>
                <td><select size="1" name="aca_cod">
                        <option value="自取、親送" ${(appraisalCaseVO.aca_cod=="自取、親送" )?'selected':'' }>自取、親送
                        <option value="宅配" ${(appraisalCaseVO.aca_cod=="宅配" )?'selected':'' }>宅配
                    </select></td>
            </tr>
            <tr>
                <td>配送地址:</td>
                <td><input type="text" name="aca_adrs" value="<%=appraisalCaseVO.getAca_adrs()%>"></td>
            </tr>
        </table>
        <br>
	        <input type="hidden" name="action" value="update">
	        <input type="hidden" name="aca_no" value="<%=appraisalCaseVO.getAca_no()%>">
	        <input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
	        <input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>"><!--只用於:istAllEmp.jsp-->
	        <button type="submit" value="送出修改" class="btn btn-outline-secondary" id="submitFinish">送出修改</button>
	    </FORM>
	    <button id="cancelCase" class="btn btn-outline-secondary" onclick="cancelCase()">取消案件</button>
	    <button id="finishCase" class="btn btn-outline-secondary" onclick="finishCase()">完成案件</button>
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

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back_end/appraisal_case/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
   .xdsoft_datetimepicker .xdsoft_datepicker { 
            width:  300px;  width:  300px; 
   }
   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px;  height:  151px; 
   }
</style>

<script>
$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function(){
//案件日期
	 $('#aca_date').datetimepicker({
		format:'Y-m-d H:i:s',
		value:'<%=appraisalCaseVO.getAca_date()%>',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#aca_pickup_date').val()?$('#aca_pickup_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//門市收貨日期
	 $('#aca_recpt_date').datetimepicker({
		  format:'Y-m-d H:i:s',
		  value:'<%=(appraisalCaseVO.getAca_recpt_date()==null)?"":appraisalCaseVO.getAca_recpt_date()%>',
	  onShow:function(){
	   this.setOptions({
		minDate:$('#aca_date').val()?$('#aca_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//出貨日期
	 $('#aca_shipment_date').datetimepicker({
		  format:'Y-m-d H:i:s',
		  value:'<%=(appraisalCaseVO.getAca_shipment_date()==null)?"":appraisalCaseVO.getAca_shipment_date()%>',
	  onShow:function(){
	   this.setOptions({
		minDate:$('#aca_recpt_date').val()?$('#aca_recpt_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//取貨日期
	 $('#aca_pickup_date').datetimepicker({
		  format:'Y-m-d H:i:s',
		  value:'<%=(appraisalCaseVO.getAca_pickup_date()==null)?"":appraisalCaseVO.getAca_pickup_date()%>',
	  onShow:function(){
	   this.setOptions({
		minDate:$('#aca_shipment_date').val()?$('#aca_shipment_date').val():false
	   })
	  },
	  timepicker:true,
	  step: 1
	 });
//完成日期
	 $('#aca_comp_date').datetimepicker({
		  format:'Y-m-d H:i:s',
		  value:'<%=(appraisalCaseVO.getAca_comp_date()==null)?"":appraisalCaseVO.getAca_comp_date()%>'
	 });
});

</script>

<script>
   var selectMode = document.getElementById("selectMode");
   var input = document.getElementsByTagName("input");
   var textarea = document.getElementsByTagName("textarea");
   var option = document.getElementsByTagName("option");
   var submitFinish = document.getElementById("submitFinish");
   
   function cancelCase() {//按下取消案件時
        if (confirm("確定取消估價案件嗎?")) {
           //隱藏取消按鈕
           document.getElementById("cancelCase").style.display = 'none';
           var cancelDate = document.querySelectorAll("#cancelDate");
           for (var i = 0; i < cancelDate.length; i++) {
                cancelDate[i].style.display = 'table-row';
           }
           //select選項都變隱藏 限Chrome瀏覽器使用
           for(var i = 0; i<option.length;i++){
           	option[i].style.display = 'none';
           }
               	selectMode[7].selected = true;
                alert("估價案件已取消~");
            } else {
                alert("估價案件尚未取消");
            }
        }

	function finishCase() {//按下完成案件時
        if (confirm("確定完成估價案件嗎")) {
            document.getElementById("cancelCase").style.display = 'none';
            selectMode[6].selected = true;
                
            document.getElementById("finishCase").style.display = 'none';
            //送出修改更改為案件完成確認送出    
            submitFinish.innerText = "案件完成確認送出";

            //估價內容不能修改
            textarea[0].readOnly=true;
            textarea[0].style.backgroundColor = "darkgray";
            //估價input欄位不能修改
    		for(var i = 0; i < input.length; i++){
	    		input[i].readOnly = true;
	    		input[i].style.backgroundColor = "darkgray";
    		}
            //select選項都變隱藏 限Chrome瀏覽器使用
            for(var i = 0; i<option.length;i++){
            	option[i].style.display = 'none';
            }
            //完成日期自動填入
            var d = new Date();
            d = new Date(d.getTime() - 3000000);
            var date_format_str = d.getFullYear().toString() + "-" + ((d.getMonth() + 1).toString().length == 2 ? (d.getMonth() + 1).toString() : "0" + (d.getMonth() + 1).toString()) + "-" + (d.getDate().toString().length == 2 ? d.getDate().toString() : "0" + d.getDate().toString()) + " " + (d.getHours().toString().length == 2 ? d.getHours().toString() : "0" + d.getHours().toString()) + ":" + ((parseInt(d.getMinutes() / 5) * 5).toString().length == 2 ? (parseInt(d.getMinutes() / 5) * 5).toString() : "0" + (parseInt(d.getMinutes() / 5) * 5).toString()) + ":00";

            var compDate = document.getElementById("aca_comp_date").value = date_format_str;
            alert("估價案件已完成~");
		} else {
            alert("估價案件尚未完成~");
        }
   }
        
     function showDate(){
        //選到商品退回顯示出貨日期
        if(selectMode[5].selected == true){
			document.getElementById("cancelDate").style.display = 'table-row';		
        }
     }
    function hiddenMode(){ //載入頁面
    //下拉式選單完成案件取消案件
    	var caseMode = document.querySelectorAll("#caseMode");
    	for(var i = 0; i < caseMode.length;i++){
    		caseMode[i].style.display = 'none';
    	}
    	if(selectMode[7].selected == true){
    	//取消案件為預設
    	document.getElementById("cancelCase").style.display = 'none';
    	var cancelDate = document.querySelectorAll("#cancelDate");
        	for (var i = 0; i < cancelDate.length; i++) {
            	cancelDate[i].style.display = 'table-row';
        	}
            //select選項都變隱藏 限Chrome瀏覽器使用
            for(var i = 0; i<option.length;i++){
            	option[i].style.display = 'none';
            }
    	}else if(selectMode[5].selected == true){
    		var cancelDate = document.querySelectorAll("#cancelDate");
            for (var i = 0; i < cancelDate.length; i++) {
            	cancelDate[i].style.display = 'table-row';
            }
    	}else if(selectMode[6].selected == true){
    		document.getElementById("cancelCase").style.display = 'none';
    		document.getElementById("finishCase").style.display = 'none';
    		var txt = "案件已完成無法修改";
    		submitFinish.innerText = txt;
    		submitFinish.disabled = true;
    	}	
    }
    </script>
</html>