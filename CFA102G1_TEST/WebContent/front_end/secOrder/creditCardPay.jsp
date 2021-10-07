<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ page import="java.util.*" %>

<!-- 前台_信用卡付款頁面 -->
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
		
		<style>
		.card{
			display:inline; 
 			text-align:center;/*信用卡卡號文字置中 */
		}
		
		</style>
        
        <title>YSM3C - 二手租賃商城</title>

        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/favicon.png">
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

		<!-- Start Page Banner -->
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>付款頁面</h2>
                </div>
            </div>
        </div>
        <!-- End Page Banner -->

        <!-- Start Login Area -->
        <section class="login-area ptb-50">
            <div class="container">
                <div class="login-form">
                    <h2>信用卡資訊</h2>
                    
                    <!--錯誤顯示>>>>-->
					<c:if test="${not empty errorMsgs}">
						<br>
								<c:forEach var="error" items="${errorMsgs}"> 
							<h1 class="card-text" style="color:red">${error}</h1>
								</c:forEach>
						<br>
					</c:if>	
					<!--<<<<錯誤顯示-->
					
					<c:if test="${empty errorMsgs}">
                    <form method="post" action="<%=request.getContextPath()%>/secOrder/SecOrder.do">
                        
                        <div class="form-group">
                        	<h4>訂單成立</h4>
                        	<h5>訂單編號 : ${secOrderVO.so_no}</h5>
                        	<h3 style="color:red">請在24小時內付款!!</h3>     	
                        </div>                 
                    	<hr>
                        <div class="form-group">
                        	<h5>卡號</h5>
                              	<input type="text" class="card" maxlength="4"  size="15" >
  								<input type="text" class="card" maxlength="4"  size="15" >
  								<input type="text" class="card" maxlength="4"  size="15" >
  								<input type="text" class="card" maxlength="4"  size="15" >
                        </div>
                        <hr>
                        <div class="form-group">
                        	<h5>三碼檢查碼</h5>
                              	<input type="password" class="card" maxlength="3"  size="15" >
                        </div>
						<hr>
                        <div class="form-group">
                        	<h5>到期日</h5>
                        	<input class="inputCard" name="expiry" id="expiry" type="month" required/>
                        </div>
                        <hr>
                        <div class="form-group">
                        	<h4>金額 : $<fmt:formatNumber type="number" maxFractionDigits="3" value="${secOrderVO.so_discount_price}"/></h4>
                        </div>
						<hr>
                        <button type="submit">確認</button>
                        <input type="hidden" name="action" value="confirmPay">
                        <input type="hidden" name="so_no" value="${secOrderVO.so_no}">
                    </form>
                    </c:if>
                </div>
            </div>
        </section>
        <!-- End Login Area -->


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
		
		
		<script>
		$("input.card").on("keydown", function(e){
		    //console.log(e.which);
		    if((e.which >= 48 && e.which <= 57) || e.which == 8){
		      
		      //console.log(e.target.value.length);
		      if(e.target.value.length == 0 && e.which == 8){
		        $(this).prev().focus();
		      }
		    }else{
		      e.preventDefault();
		    }
		});

		$("input.card").on("keyup", function(e){
		  
		  // \D 代表非數字字元，g 代表全域尋找
		  //let str = e.target.value;
		  //console.log(e.target.value);
		  let str = (e.target.value).replace(/\D/g, "");
		  
		  $(this).val(str);
		  
		  
		  //console.log(str.length);
		  if(str.length == 4){
		    $(this).next().focus();
		  }
		});
		</script>
</body>
</html>