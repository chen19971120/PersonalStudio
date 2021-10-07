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
       <%@ include file="../front_include_page/navbar.jsp"%>
        <!-- End Navbar Area -->

        <!-- Start Page Banner -->
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>會員登出</h2>

                </div>
            </div>
        </div>
        <!-- End Page Banner -->
				 
			 
							
					<div class="cont_login">
        <!-- Start Login Area -->
        <section class="login-area ptb-50">
        <div class="cont_forms" >
            <div class="container">
                <div class="login-form">
                    <h2>轉跳中..</h2>
	                 <div class="cont_form_login">
                    
                   
              <h1 class="auth-title">登出已成功,歡迎再登入</h1>
	 
                  
                </div>
            </div>
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



        <!-- Jquery Slim JS -->
       <%@ include file="../front_include_page/JavaScript_Include.jsp"%>
       <script language=javascript>
		setTimeout('window.location="<%=request.getContextPath()%>/front_end/login/login.jsp"', 2000)
	</script>
    </body>
    

</html>


