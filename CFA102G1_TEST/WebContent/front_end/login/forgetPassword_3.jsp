<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
    <html lang="zxx">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS --> 
         <%@ include file="../front_include_page/Top_head.jsp"%>
      <%@ include file="../front_include_page/CSS_link.jsp"%>

        <title>更改完畢 |YSM3C</title>

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
    </head>

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

      

       
        <!-- End Middle Header Area -->

        <!-- Start Navbar Area -->
      <%@ include file="../front_include_page/member_head.jsp"%>
        <!-- End Navbar Area -->

        <!-- Start Page Banner -->
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>密碼已變更</h2>

                    
                </div>
            </div>
        </div>
        <!-- End Page Banner -->

        <!-- Start Login Area -->
        <section class="login-area ptb-50">
            <div class="container">
                <div class="login-form">
                    <div class="contact-form">
                        <h2>密碼已變更</h2>
                        <p>請重新登入。</p>
                        <form id="contactForm">

                            

                            

                            

                            <div class="col-lg-12 col-md-12">
                                <button type="submit" class="btn_login">
                             <a href="login.jsp" class="nav-link">繼續</a>
                                    <span></span>
                                </button>
                                <div class="clearfix"></div>
                           
                            </div>

                            
                        </form>


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
    </body>
    </html>
