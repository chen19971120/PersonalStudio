<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="zxx">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS --> 
        <%@ include file="../front_include_page/CSS_link.jsp"%>
         <link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/register_success.css">
		
		<title>會員登入|YSM3C</title>

        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front_end//front_CSS_JS/assets/img/favicon.png">
    </head>
<style>


</style>
    <body>
        
        <!-- Start Preloader Area -->
        <div class="preloader">
            <div class="loader">
                <div class="sbl-half-circle-spin">
                    <div></div>
                </div>
            </div>
        </div>
        <!-- End Preloader Area -->

     

             

       
     

        <!-- Start Navbar Area -->
       <%@ include file="../front_include_page/member_head.jsp"%>
        <!-- End Navbar Area -->

        <!-- Start Page Banner -->
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>會員註冊</h2>

                   
                </div>
            </div>
        </div>
        <!-- End Page Banner -->
				 
			 <div class="cotn_principal">
				<div class="cont_centrar">
				 			
							
					<div class="cont_login">
        <!-- Start Login Area -->
        <section class="login-area ptb-50">
       
   <div class="background"></div>
<div class="container">
	<div class="row">
		<div class="modalbox success col-sm-8 col-md-6 col-lg-5 center animate">
			<div class="icon">
				<span class="glyphicon glyphicon-ok"></span>
			</div>
			<!--/.icon-->
			<h1>註冊成功!</h1>
			<p>點擊下方按鈕立即轉跳
				<br>登入會員</p>
			<button type="button" class="redo btn"><a href="../front_end/login/login.jsp" class="nav-link">Ok</a></button>
			<span class="change">-- Click to see member state --</span>
		</div>
		<!--/.success-->
	</div>
	
</div>
<!--/.container-->

        
          
        </section>
        </div>
<!--/.container-->
      
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


        <!-- Jquery Slim JS -->
       <%@ include file="../front_include_page/JavaScript_Include.jsp"%>
   
    </body>
    

</html>