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

    <body>
        
        <!-- Start Preloader Area -->
        <div class="preloader">
            <div class="loader">
                <div class="sbl-half-circle-spin">
                    <div></div>
                </div>
            </div>
        </div>
       
       <%@ include file="../front_include_page/navbar.jsp"%>
    
        <div class="page-title-area">
            <div class="container">
                <div class="page-title-content">
                    <h2>會員登入</h2>

                    <ul>
                        <li><a href="<%=request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
                        <li>登入</li>
                    </ul>
                </div>
            </div>
        </div>

				 
			 <div class="cotn_principal">
				<div class="cont_centrar">
				 			<div class="success">
								<%-- 成功表列 --%>
								<c:if test="${not empty success}">
										<c:forEach var="message" items="${success}">
											<h5><span style="color: green">${message}</span></h5><br>
										</c:forEach>
								</c:if>
						   </div>
	   
	   
	  						
							
					<div class="cont_login">
        <!-- Start Login Area -->
        <section class="login-area ptb-50">
        <div class="cont_forms" >
            <div class="container">
                <div class="login-form">
                    <h2>登入</h2>
	                 <div class="cont_form_login">
	                 
	                 <div class="errorMegs">
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<h5><font style="color: red">請修正以下錯誤:</font></h5>
										<c:forEach var="message" items="${errorMsgs}">
										<h5><span style="color: red">${message}</span></h5><br>
										</c:forEach>
								</c:if>
						   </div>
                    <form METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do">



                        <div class="form-group">
                            <input type="text" class="form-control" name="mem_account" id="mem_account"  placeholder="帳號" >
                         
                       </div>
                       <div class="form-group">
                            <input type="password" class="form-control" name="mem_password" id="mem_password" placeholder="密碼" >
                            
                        </div>

                        <div class="row align-items-center">
                            <div class="col-lg-6 col-md-6 col-sm-6">
                                <div class="form-check">
                                    <input type="checkbox" class="form-check-input" name="passcookies">
                                    <label class="form-check-label" for="passcookies">記住我!</label>
                                </div>
                            </div>

                            <div class="col-lg-6 col-md-6 col-sm-6 lost-your-password">
                                <a href="<%=request.getContextPath()%>/front_end/login/forgetPassword_1.jsp"  class="lost-your-password">忘記密碼?</a>
                                
                            </div>
                        </div>
                        <input type="hidden" name="action" value="login">
                        <button type="submit"  class="btn_login" >會員登入</button>
                      
                    </form>
	 
                    <div class="important-text">
                        <p>還不是會員嗎? <a href="<%=request.getContextPath()%>/front_end/login/register.jsp">馬上註冊!</a></p>
                    </div>
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
		<script>
			const mem_account = document.querySelector('#mem_account');
			const mem_password = document.querySelector('#mem_password');
			const { cookie } = document;
			if (cookie) {
				for (let item of cookie.split('; ')) {
					const property = item.split('=');
					const name = property[0];
					const value = property[1];
					if (name === 'mem_account') {
						mem_account.value = value;
					} else if (name === 'mem_password') {
						mem_password.value = value;
					}
				}
			}
		</script>
    </body>
    

</html>

