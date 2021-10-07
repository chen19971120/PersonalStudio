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
         <%@ include file="../front_include_page/Top_head.jsp"%>
      <%@ include file="../front_include_page/CSS_link.jsp"%>

        <title>輸入驗證碼|YSM3C</title>

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
                    <h2>輸入安全驗證碼</h2>

                    
                </div>
            </div>
        </div>
        <!-- End Page Banner -->

        <!-- Start Login Area -->
        <section class="login-area ptb-50">
            <div class="container">
                <div class="login-form">
                    <div class="contact-form">
                        <h2>輸入安全驗證碼</h2>
                         <%-- 錯誤表列 --%>
                          <c:if test="${not empty errorMsgs}">
	                       <font style="color:red">請修正以下錯誤:</font>
	                        <div>
	                       <c:forEach var="message" items="${errorMsgs}">
		                    <div style="color:red">${message}</div>
		                </c:forEach>
	                   </div>
                    </c:if>
                        <p>請查看你的電子郵件信箱中是否有包含驗證碼的信件。你的驗證碼長度為 6 位數。</p>
                        <form METHOD="post" ACTION="<%=request.getContextPath()%>/maintence_case_img/maintence_case_img.do">

                            <div class="form-group">
                                <input type="text" class="form-control" name="mci_before_img" placeholder="輸入驗證碼" required data-error="請輸入驗證碼"> 
                                <div class="help-block with-errors"></div>
                            </div>

                            

                         
                      

                            <div class="col-lg-12 col-md-12">
                            <input type="hidden" name="action" value="forgetPwd_2">
                                <button type="submit" class="default-btn">
                                    繼續
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

