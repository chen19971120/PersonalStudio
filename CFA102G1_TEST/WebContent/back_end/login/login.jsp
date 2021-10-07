<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登入 - YSM3C</title>
   
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/css/app.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/css/pages/auth.css">
    		

</head>

<body>
    <div id="auth">
        
<div class="row h-100">
    <div class="col-lg-5 col-12">
        <div id="auth-left">
            <div class="auth-logo">
                <a href="login.jsp"><img src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/images/logo/logo.png" style="height:75px;width:235px;" alt="Logo"></a>
            </div>
            <h1 class="auth-title">員工登入</h1>
            
            
            <p class="auth-subtitle mb-5">為美好的一天加油</p>
            
              <div>
             <%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
				
			</c:if>
			</div>

            <form class="form" method="post" action="<%=request.getContextPath()%>/staff/staff.do">
                <div class="form-group position-relative has-icon-left mb-4">
                    <input type="text" class="form-control form-control-xl"name="staff_account" id="staff_account" placeholder="員工帳號">
        
                    <div class="form-control-icon">
                        <i class="bi bi-person"></i>
                    </div>
                </div>
                <div class="form-group position-relative has-icon-left mb-4">
                    <input type="password" class="form-control form-control-xl" name="staff_password" id="staff_password" placeholder="員工密碼">
                    
                    <div class="form-control-icon">
                        <i class="bi bi-shield-lock"></i>
                    </div>
                </div>
               
                <div class="btn-group">
                <button class="btn btn-primary btn-block btn-lg shadow-lg mt-5">登入</button>
                <input type="hidden" name="action" value="login">
                </div>
              
            </form>
            
        </div>
    </div>
    <div class="col-lg-7 d-none d-lg-block">
        <div id="auth-right">

        </div>
    </div>
</div>

    </div>
    
</body>

</html>
