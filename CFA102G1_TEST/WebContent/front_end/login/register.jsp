<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>

<%
  MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<!doctype html>
    <html lang="zxx">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS --> 
         <%@ include file="../front_include_page/Top_head.jsp"%>
        <%@ include file="../front_include_page/CSS_link.jsp"%>
        <title>會員註冊|YSM3C</title>

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
        <script>
function reloadImage(){
	//使用GET方式，把當下的時間轉為UNIX時間作為參數，所以參數不會重覆到
	document.getElementById('identity').src = 'identityservlet?ts=' 
			+ new Date().getTime();
}
</script>
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

        <!-- Start Register Area -->
        <section class="register-area ptb-50">
            <div class="container">
                <div class="register-form">
                    <div class="contact-form">
                        <h2>註冊</h2>

            <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1">
 <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
				
			</c:if>
                            <div class="form-group">
                                <input type="text" name="mem_name" class="form-control" placeholder="姓名" required data-error="請輸入姓名"
                                value="<%= (memberVO==null)? "" : memberVO.getMem_name()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>
                            <p>性別：</p>

                            <input type="radio" name="mem_gender" class="btn-check" name="mem_gender" id="option1" value="男" autocomplete="off" checked
                            value="<%= (memberVO==null)? "" : memberVO.getMem_gender()%>">
                            <label class="btn btn-light" for="option1">男</label>

                            <input type="radio" name="mem_gender" class="btn-check" name="mem_gender" id="option2" value="女" autocomplete="off"
                            value="<%= (memberVO==null)? "" : memberVO.getMem_gender()%>">
                            <label class="btn btn-light" for="option2">女</label>
                            
                                                                               
                            
                            <div class="form-group">
                                <input type="date" name="mem_birth" class="form-control" placeholder="生日 :  西元年/月份/日期" required data-error="請輸入生日"
                                 value="<%= (memberVO==null)? "" : memberVO.getMem_birth()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>
                            
                            
                            <div class="form-group">
                                <input type="text" name="mem_phone" class="form-control" placeholder="電話" required data-error="請輸入電話"
                                 value="<%= (memberVO==null)? "" : memberVO.getMem_phone()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <input type="text" name="mem_email" class="form-control" placeholder="電子郵件" required data-error="請輸入電子郵件"
                                 value="<%= (memberVO==null)? "" : memberVO.getMem_email()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <input type="text" name="mem_address" class="form-control" placeholder="地址" required data-error="請輸入地址"
                                 value="<%= (memberVO==null)? "" : memberVO.getMem_address()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <input type="text" name="mem_account" class="form-control" placeholder="帳戶" required data-error="請輸入帳戶"
                                value="<%= (memberVO==null)? "" : memberVO.getMem_account()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>

                            <div class="form-group">
                                <input type="text" name="mem_password" class="form-control" placeholder="密碼" required data-error="請輸入密碼"
                                 value="<%= (memberVO==null)? "" : memberVO.getMem_password()%>"> 
                                <div class="help-block with-errors"></div>
                            </div>
                                                                          
             
                            <div class="form-group">
                            <button type="submit">現在註冊</button>
                            <input type="hidden" name="action" value="register">
                            </div>
                        </form>

                        <div class="important-text">
                            <p>已經有帳戶? <a href="login.jsp">現在登入!</a></p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Register Area -->


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

