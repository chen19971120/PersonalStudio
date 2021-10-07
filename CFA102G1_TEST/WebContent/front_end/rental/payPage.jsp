<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalProductList.model.*"%>
<%@ page import="com.rentalOrder.model.*"%>
<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />
<jsp:useBean id="rplSvc" scope="page" class="com.rentalProductList.model.RentalProductListService" />
    
<% 	
	Integer ro_no = (Integer) request.getAttribute("ro_no");
	RentalOrderService roSvc = new RentalOrderService();
	RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
	pageContext.setAttribute("roVO",roVO);
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
</head>
<body>

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
		
<!-- ************************下面開始塞你自己的頁面資訊************************ -->		
<!-- ************************下面開始塞你自己的頁面資訊************************ -->		


 <!-- Start Checkout Area -->
		<section class="checkout-area ptb-50">
            <div class="container">
                <form METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do">
                    <div class="row">
                    	<div class="col-lg-2 col-md-12">
                    	</div>
                    	<div class="col-lg-2 col-md-12">
                                <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=${rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no}" alt="image"
               					style="max-width:100%" > 
                    	</div>
                        <div class="col-lg-4 col-md-12">                
                            <div class="billing-details">
                                <h3 class="title">付款</h3>

                                <div class="row">
                                	<div class="col-lg-12 col-md-6">
                                            <h6>訂單編號:&nbsp;#${roVO.ro_no}</h6>      
                                    </div>
                                    <br><br>
                                    <div class="col-lg-12 col-md-6">
                                            <h6>租賃商品:&nbsp;${rcSvc.getOneRentalClass(rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no).rc_name}</h6>      
                                    </div>                                     
                                    <br><br>  
                                    <div class="col-lg-12 col-md-6">
                                            <h6>租賃時間:&nbsp;${roVO.ro_starttime}&nbsp;&nbsp;&nbsp;~&nbsp;&nbsp;&nbsp;${roVO.ro_endtime}</h6>      
                                    </div>
                                    <br><br>
                                	<div class="col-lg-12 col-md-6">
                                		<c:choose>
                                			<c:when test="${roVO.ro_status=='續租-未付款'}">   
                                				<h6>訂單金額租賃費用:&nbsp;${roVO.ro_totalprice}</h6>                      		
	                                		</c:when>
	                                		<c:otherwise>                              			
                                            	<h6>訂單金額(押金+租賃費用):&nbsp;${roVO.ro_day*roVO.ro_price+roVO.ro_deposit}</h6>
	                                		</c:otherwise>
                                		</c:choose>       		
                                    </div>
                                    <br><br>
                                    <div class="col-lg-12 col-md-6">
                                    	信用卡號:&nbsp;&nbsp;
                                    	<input type="text" size="5" value="1234">&nbsp;-&nbsp;
                                    	<input type="text" size="5" value="4321">&nbsp;-&nbsp;
                                    	<input type="text" size="5" value="7890">&nbsp;-&nbsp;
                                    	<input type="text" size="5" value="0987">
                                    </div>
									<br><br>
                                    <div class="col-lg-12 col-md-6">
                                                                                                信用卡背面後3碼:&nbsp;&nbsp;
                                    	<input type="text" size="5" value="111">
                                    </div>
									<br><br>
									<div class="col-lg-12 col-md-6">
                                                                                                信用卡有效期限:&nbsp;&nbsp;
                                        <input type="text" size="3" value="11">月
										<input type="text" size="3" value="25">年   
                                    </div>
                                    
									<div class="col-lg-10 col-md-6">
  
                                    </div>
                                    <div class="col-lg-1 col-md-6">
                                        <div class="form-group">
                                        		<input type="submit" value="付款" class="btn btn-outline-primary">
                                        		<input type="hidden" name="ro_no" value="${roVO.ro_no}" >
                                        		<input type="hidden" name="fromfront"	value="true">
                                        		<input type="hidden" name="action"	value="payOneRO">
                                        </div>
                                    </div>                      
                                </div>
                            </div>
                        </div>                        
						<div class="col-lg-4 col-md-12">
                    	</div>
                    </div>
                </form>
            </div>
        </section>
        <!-- End Checkout Area --> 	
		
<!-- ************************上面塞你自己的頁面資訊***************************** -->	
<!-- ************************上面塞你自己的頁面資訊***************************** -->	

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
</body>
</html>