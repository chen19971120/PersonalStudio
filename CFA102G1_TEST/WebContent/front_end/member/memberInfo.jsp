<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
  MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<jsp:useBean id="memberSvc" scope="page" class="com.member.model.MemberService" />
<!doctype html>
    <html lang="zxx">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS --> 
     <%@ include file="../front_include_page/Top_head.jsp"%>
     <%@ include file="../front_include_page/CSS_link.jsp"%>

        <title>會員資料修改|YSM3C</title>

        <style>
  table#table-1 {
	background-color: grey;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
  #div2{
  margin-left:220px;
  }
</style>

<style>
  table {
	width: 1300px;
	
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	margin-left:450px;
  }
  table, th, td {
    border: 1px solid black;
  }
  #div1 {
   margin-left:1670px;
}
#div11{
 margin-left:600px;
 }
  
</style>
  
  <link rel="icon" type="image/png"
	href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/favicon.png">
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
                    <h2>會員資料</h2>


                    
                </div>
            </div>
        </div>
        <!-- End Page Banner -->

        <!-- Start Register Area -->
        
                        
        
  

      
                          <div id="div11">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>
	

<section class="register-area ptb-50">
            <div class="container">
                <div class="register-form">
                    <div class="contact-form">
                        <h2>個人資料</h2>

                        <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" name="form1">
       
		
                            <p>姓名：</p>
                            <div class="form-group">
                                <input type="text" class="form-control"  name="mem_name" value="<%=memberVO.getMem_name()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                          
                                     <p>性別：</p>
                            <div class="form-group">
                                <input type="text" class="form-control"  name="mem_gender" value="<%=memberVO.getMem_gender()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            
 
                                                                               
                            <p>出生年月日：</p>
                            <div class="form-group">
                                <input type="date" class="form-control" name="mem_birth"  value="<%=memberVO.getMem_birth()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            
                            <p>聯絡電話：</p>
                            <div class="form-group">
                                <input type="text" name="mem_phone" class="form-control" value="<%=memberVO.getMem_phone()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            <p>信箱：</p>
                            <div class="form-group">
                                <input type="text" name="mem_email" class="form-control" value="<%=memberVO.getMem_email()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            <p>地址：</p>
                            <div class="form-group">
                                <input type="text" class="form-control" name="mem_address" value="<%=memberVO.getMem_address()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            <p>帳號：</p>
                            <div class="form-group">
                                <input type="text" class="form-control" name="mem_account" value="<%=memberVO.getMem_account()%>" readonly="readonly"/> 
                                <div class="help-block with-errors"></div>
                            </div>
                            <p>密碼：</p>
                            <div class="form-group">
                                <input type="password" class="form-control" name="mem_password" value="<%=memberVO.getMem_password()%>" readonly="readonly" /> 
                                <div class="help-block with-errors"></div>
                            </div>
                             <p>會員狀態：</p>
                            <div class="form-group">
                                  <input type="text" class="form-control" name="mem_sta" value="<%=memberVO.getMem_sta()%>" readonly="readonly"/>
                                <div class="help-block with-errors"></div>
                            </div>
                            <div class="row align-items-center">
                                <div class="col-lg-6 col-md-6 col-sm-6">
                                    <div class="form-check" id ="div2">
                                        <a href="<%=request.getContextPath()%>/front_end/login/update_member.jsp"  class="btn btn-primary">
                                        修改</a>
                                      
                                    </div>
                                </div>
                            </div>                                                          
                            
                           

                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Register Area -->
<br>

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

