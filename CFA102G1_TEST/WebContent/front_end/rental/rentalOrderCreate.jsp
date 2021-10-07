<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalOrder.model.*"%>
<%@ page import="com.member.model.*"%>
<% 	

	Integer rc_no = (Integer) session.getAttribute("rc_no");
	
	RentalClassService rcSvc = new RentalClassService();
	RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
	pageContext.setAttribute("rcVO",rcVO);
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
%>

<!DOCTYPE html>
<html>
<head>
	<!--*******************	
		Start Include CSS File  
		******************* -->
        <%@ include file="../front_include_page/CSS_link.jsp"%>
	<!--*******************	
		End Include CSS File  
		******************* -->        
        
        <title>YSM3C - 二手租賃商城</title>

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
        
        <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.css" />
               
</head>
<body>
	    <!-- Start Preloader Area 載入畫面(圈圈)-->
        <div class="preloader">
            <div class="loader">
                <div class="sbl-half-circle-spin">
                    <div></div>
                </div>
            </div>
        </div>
        <!-- End Preloader Area 載入畫面(圈圈)-->

	<!--*******************	
		Start Top Head Area  
		******************* -->
		<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--*******************	
		End Top Head Area  
		******************* -->	
		
	<!--*******************	
		Start Navbar Area  
		******************* -->		
		<%@ include file="../front_include_page/navbar.jsp"%>
	<!--*******************	
		End Navbar Area  
		******************* -->			

<!-- Start Checkout Area -->
		<section class="checkout-area ptb-50">
            <div class="container">
                <form METHOD="post" name="orderCreate" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do">
                    <div class="row">
                        <c:if test="${not empty errorMsgs}">
	                        <div class="col-lg-12 col-md-12">
	                        	<div class="user-actions">  		
									<c:forEach var="message" items="${errorMsgs}">
										<h5 style="color:red">${message}</h5>
									</c:forEach>
								</div>
	                    	</div>		
						</c:if>                              

                        <div class="col-lg-8 col-md-12">                     
                            <div class="billing-details">
                                <h3 class="title">預約資料填寫</h3>

                                <div class="row">
                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>會員編號</label>
                                            <label style="font-size:24px">${memberVO.mem_no}</label>
                                            <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃商品</label> 
                                            <label style="font-size:24px">${rcVO.rc_name}</label>
                                            <input type="hidden" name="rc_no" value="${rcVO.rc_no}"/>
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃開始時間</label>
                                            <input type="text" class="form-control" name="ro_starttime" id="start_date">
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃結束時間</label> 
                                            <input type="text" class="form-control" name="ro_endtime"   id="end_date">
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃天數</label>
                                            <label id="showday" style="font-size:24px"></label>
                                            <input type="hidden" class="form-control" name="ro_day" id="ro_day">
                                            
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃價格</label>
                                            <label style="font-size:24px">${rcVO.rc_price} 元/天 </label>
                                            <input type="hidden" name="ro_price" value="${rcVO.rc_price}" id="ro_price" />
                                        </div>
                                    </div>

                                    <div class="col-lg-3 col-md-3">
                                        <div class="form-group">
                                        	<label>配送方式</label>     
                                        </div>
                                        <input type="radio" id="selftake" name="ro_ship_method" value="自取">
                                        <label for="selftake" style="font-size:24px">自取</label>
                                        <input type="radio" id="delivery" name="ro_ship_method" value="宅配">
                                        <label for="delivery" style="font-size:24px">宅配</label>
                                    </div>


                                    <div class="col-lg-9 col-md-9">
                                        <div class="form-group">
                                            <label>配送地址</label>
                                            <input type="text" class="form-control" name="ro_ship_addrs" 
                                            id="ro_ship_addrs" >
                                        </div>
                                    </div>                                   
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-12">
                            <div class="order-details">
                                <div class="cart-totals">
                           <div class="row">                                  
                                 <div class="col-lg-5 col-md-5">
                                        <div class="form-group">
                                           <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=${rcVO.rc_no}" alt="image"
                                       		style="max-width:100%" >
                                        </div>
                                 </div>
                           </div>                        
                                    <h3>訂單總金額</h3>
            
                                    <ul>
                                        <li>訂單金額 <span id="orderprice"></span>
                                        <input type="hidden" name="ro_totalprice" id="ro_totalprice">
                                        </li>                 
                                        <li>押金 <span>${rcVO.rc_deposit} 元 </span>
                                        <input type="hidden" name="ro_deposit" value="${rcVO.rc_deposit}" id="ro_deposit" />
                                        </li>
                                        <li>應付金額 <span id="moneysum"></span></li>
                                    </ul>
            							<input type="hidden" name="front_end" value="true" />
            							<input type="hidden" name="action" value="insert"  />
            							<input type="submit" value="預約" class="default-btn"/>
                                        <span></span>                                    
                                </div>                          
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </section>
<!-- End Checkout Area -->	

	<!--*******************	
		Start Footer Area  
		******************* -->			
		<%@ include file="../front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->	
	
	<!--*******************	
		Start Go Top Area  
		******************* -->				
		<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->			

	<!--*******************	
		Start Include JS File  
		******************* -->	
		<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->	
		

<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>

$('#selftake').click(function(){
	$('#ro_ship_addrs').val("");
	$('#ro_ship_addrs').attr("disabled","disabled");
	$('#ro_ship_addrs').attr("style","background-color:#E9ECEF");
});

$('#delivery').click(function(){
	$('#ro_ship_addrs').removeAttr("disabled");
	$('#ro_ship_addrs').removeAttr("style");
});

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
$('#start_date').on('change',function(){
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
	
$('#end_date').on('change',function(){
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
		
</body>
</html>