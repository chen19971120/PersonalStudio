<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalOrder.model.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="java.util.*"%>

<%
	Integer rc_no = new Integer(request.getParameter("rc_no"));

	RentalClassService rcSvc = new RentalClassService();
	RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
	pageContext.setAttribute("rcVO",rcVO);

	RentalOrderVO roVO = (RentalOrderVO) request.getAttribute("roVO");
%>
<html>
<head>
<title>新增租賃訂單</title>
<%@ include file="../back_include_page/CSS_link.jsp"%>
<link rel="icon" type="image/png" href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<style>
  form { display: inline; }
  table {
	width: 900px;
	margin: 5px;
	background-color: white;
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

			<h3>新增租賃訂單</h3>
			<h6><a href="<%=request.getContextPath()%>/back_end/rentalOrder/listRo.jsp">回首頁</a></h6>

			
			<p>
			
			<h5>資料新增:</h5>
			
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<font style="color:red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>	
				
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" name="form1">
			<table class="table table-striped">	
				<tr>
					<th>會員編號:</th>
					<td><input type="TEXT" name="mem_no" size="5" 
					value="<%= (roVO==null)? "1" : roVO.getMem_no()%>"/></td>
					<th>租賃商品類別:</th>
					<td>${rcVO.rc_no}.${rcVO.rc_name}
					   <input type="hidden" name="rc_no" value="${rcVO.rc_no}"/>		
					</td>
				</tr>
				<tr>
					<th>運送方式:</th>
					<td colspan="3">
						<input type="radio" id="selftake" name="ro_ship_method" value="自取" >
						<label for="selftake">自取</label>
						<input type="radio" id="delivery" name="ro_ship_method" value="宅配"  checked>
						<label for="delivery">宅配</label>
					</td>
				</tr>
				<tr>
					<th>配送地址:</th>
					<td colspan="3"><input type="TEXT" name="ro_ship_addrs" size="65" 
					value="<%= (roVO==null)? "320桃園市中壢區復興路46號9樓" : roVO.getRo_ship_addrs()%>"/></td>
				</tr>
				<tr>
					<th>租賃開始時間:</th>
					<td><input name="ro_starttime" id="start_date" type="text" class="renttime1" ></td>
					<th>租賃結束時間:</th>
					<td><input name="ro_endtime"   id="end_date"   type="text" class="renttime2"></td>
				</tr>
				<tr>
					<th>租賃天數:</th>
					<td>
						<span id="showday"></span>
						<input type="hidden" name="ro_day" id="ro_day" >
					</td>
					<th>租賃價格:</th>
					<td>${rcVO.rc_price} 元/天
					<input type="hidden" name="ro_price" value="${rcVO.rc_price}" id="ro_price" />
					</td>
			
				</tr>
				<tr>
					<th>訂單金額:</th>
					<td><span id="orderprice"></span>
					<input type="hidden" name="ro_totalprice" id="ro_totalprice" >
					</td>
					<th>押金:</th>
					<td>${rcVO.rc_deposit} 元
					<input type="hidden" name="ro_deposit" value="${rcVO.rc_deposit}" id="ro_deposit" />
					</td>
				</tr>	
				<tr>
					<th>應付總金額:</th>
					<td colspan="3">
					<span id="moneysum"></span>
					</td>
				</tr>				
			</table>			
			<br>
			<input type="hidden" name="action" value="insert">
			<input type="submit" value="送出" class="btn btn-sm btn-primary">
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
</body>

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.full.js"></script>

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
	 $('#start_date').datetimepicker({
	  format:'Y-m-d',
	  onShow:function(){
	   this.setOptions({
	    maxDate:$('#end_date').val()?$('#end_date').val():false
	   })
	  },
	  timepicker:false
	 });
	 
  	 var today = new Date();
     var somedate1 = new Date(today.getFullYear(),today.getMonth(),today.getDate()+3);	 
     var somedate2 = new Date(today.getFullYear(),today.getMonth(),today.getDate()+7);
     $('#start_date').datetimepicker({
         beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate1.getYear() || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
             ||
        		date.getYear() >  somedate2.getYear() || 
        		(date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        		(date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
       	  
       	  ) {
                  return [false, ""]
             }
             return [true, ""];
     }});    
      
     var somedate3 = new Date(today.getFullYear(),today.getMonth(),today.getDate()+9);
	 $('#end_date').datetimepicker({
	  format:'Y-m-d',
	  timepicker:false,
	  beforeShowDay: function(date) {
       	  if (  date.getYear() <  somedate3.getYear() || 
		           (date.getYear() == somedate3.getYear() && date.getMonth() <  somedate3.getMonth()) || 
		           (date.getYear() == somedate3.getYear() && date.getMonth() == somedate3.getMonth() && date.getDate() < somedate3.getDate())
             ) {
                  return [false, ""]
             }
             return [true, ""];
     }
	  
	 });
});

$('#start_date').on('change',function(){
	var somedate3 = new Date($('#start_date').val().substring(0,4),parseInt($('#start_date').val().substring(5,7))-1,parseInt($('#start_date').val().substring(8,10))+6);
	$('#end_date').datetimepicker({
		  format:'Y-m-d',
		  timepicker:false,
		  beforeShowDay: function(date) {
	       	  if (  date.getYear() <  somedate3.getYear() || 
			           (date.getYear() == somedate3.getYear() && date.getMonth() <  somedate3.getMonth()) || 
			           (date.getYear() == somedate3.getYear() && date.getMonth() == somedate3.getMonth() && date.getDate() < somedate3.getDate())
	             ) {
	                  return [false, ""]
	             }
	             return [true, ""];
	     }
		  
		 });
});

// 當選定租賃日期,計算租賃天數
$('.renttime1').on('change',function(){
	if($('#start_date').val()&&$('#end_date').val()){
		var start = new Date($('#start_date').val());
		var end = new Date($('#end_date').val());
		var Days = parseInt(1+Math.abs(end - start)/(1000*60*60*24));
		var str = Days
		document.getElementById("showday").innerHTML = Days + " 天";
		$('#ro_day').val(Days)
		var price = $('#ro_price').val() * Days
		document.getElementById("orderprice").innerHTML = price + " 元";
		$('#ro_totalprice').val(price);
		document.getElementById("moneysum").innerHTML = $('#ro_price').val() * Days + parseInt($('#ro_deposit').val()) + " 元";
	}
	
});
	
$('.renttime2').on('change',function(){
	if($('#start_date').val()&&$('#end_date').val()){
		var start = new Date($('#start_date').val());
		var end = new Date($('#end_date').val());
		var Days = parseInt(1+Math.abs(end - start)/(1000*60*60*24));
		document.getElementById("showday").innerHTML = Days + " 天";
		$('#ro_day').val(Days)
		var price = $('#ro_price').val() * Days
		document.getElementById("orderprice").innerHTML = price + " 元";
		$('#ro_totalprice').val(price);
		document.getElementById("moneysum").innerHTML = $('#ro_price').val() * Days + parseInt($('#ro_deposit').val()) + " 元";
	}
});

	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});

</script>
</html>