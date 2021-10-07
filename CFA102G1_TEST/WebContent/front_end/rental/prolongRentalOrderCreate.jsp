<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalOrder.model.*"%>
<%@ page import="com.rentalProductList.model.*"%>
<% 	

	Integer ro_no = new Integer(request.getParameter("ro_no"));
	RentalOrderService roSvc = new RentalOrderService();
	RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
	
	RentalProductListService rplSvc = new RentalProductListService();
	RentalProductListVO rplVO = rplSvc.getOneRentalProductList(roVO.getRpl_no());
	
	RentalClassService rcSvc = new RentalClassService();
	RentalClassVO rcVO = rcSvc.getOneRentalClass(rplVO.getRc_no());
	
	pageContext.setAttribute("roVO",roVO);
	pageContext.setAttribute("rcVO",rcVO);
	

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
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do">
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
                                <h3 class="title">續租申請資料填寫</h3>

                                <div class="row">
 
                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>商品編號</label> 
                                            <label style="font-size:24px">${roVO.rpl_no}</label>
                                            <input type="hidden" name="rpl_no" value="${roVO.rpl_no}">
                                            <input type="hidden" name="mem_no" value="${roVO.mem_no}">
                                            <input type="hidden" name="ro_no" value="${roVO.ro_no}">
                                        </div>
                                    </div>
                                    
                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>租賃商品</label> 
                                            <label style="font-size:24px">${rcVO.rc_name}</label>
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>原預定結束時間</label> 
                                            <label style="font-size:24px">${roVO.ro_endtime}</label>
                                        	<input type="hidden" value="${roVO.ro_endtime}" 
                                        	id="end_date" name="ro_starttime">
                                        </div>
                                    </div>
                                    
                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>續租日期</label>
                                            <input type="text" class="form-control" 
                                            name="ro_endtime" id="ro_oncerentendtime">
                                        </div>
                                    </div>

                                    <div class="col-lg-6 col-md-6">
                                        <div class="form-group">
                                            <label>續租天數</label>
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
                                        <input type="hidden" name="ro_deposit" value="${roVO.ro_deposit}">
                                        </li>                                                        
                                        <li>應付金額 <span id="moneysum"></span></li>
                                    </ul>
            							<input type="hidden" name="front_end" value="true" />
            							<input type="hidden" name="action" value="insertProlongOrder"  />
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
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});

$.datetimepicker.setLocale('zh'); // kr ko ja en

$(function(){	
	 $('#ro_oncerentendtime').datetimepicker({
	  format:'Y-m-d',
	  timepicker:false,
	  minDate: (new Date($('#end_date').val())).Format()
	 });	 
	
});

Date.prototype.Format = function() {
	var mm = this.getMonth() + 1; // getMonth() is zero-based
	var dd = this.getDate()+1;

	return [this.getFullYear(),
	         (mm>9 ? '' : '0') + mm,
	         (dd>9 ? '' : '0') + dd
	       ].join('-');
	};

// 當選定租賃日期,計算租賃天數	
$('#ro_oncerentendtime').on('change',function(){
	if($('#ro_oncerentendtime').val()==""){
		var start = new Date($('#end_date').val());
		var end = new Date($('#ro_oncerentendtime').val());
		var Days = parseInt(Math.abs(end - start)/(1000*60*60*24));
		document.getElementById("showday").innerHTML = "";
		$('#ro_day').val(0);
		var price = $('#ro_price').val() * Days;
		document.getElementById("orderprice").innerHTML = "";
		$('#ro_totalprice').val(0);
		document.getElementById("moneysum").innerHTML = "";
		
	}
	else{
		var start = new Date($('#end_date').val());
		var end = new Date($('#ro_oncerentendtime').val());
		var Days = parseInt(Math.abs(end - start)/(1000*60*60*24));
		document.getElementById("showday").innerHTML = Days + " 天";
		$('#ro_day').val(Days)
		var price = $('#ro_price').val() * Days
		document.getElementById("orderprice").innerHTML = price + " 元";
		$('#ro_totalprice').val(price);
		document.getElementById("moneysum").innerHTML = price + " 元";	
	}
	
});

</script>		
		
</body>
</html>