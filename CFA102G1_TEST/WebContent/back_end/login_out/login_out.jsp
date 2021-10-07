<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - YSM3C</title>
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/css/bootstrap.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end//back_CSS_JS/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end//back_CSS_JS/assets/css/app.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/back_end//back_CSS_JS/assets/css/pages/auth.css">
    
</head>

<title>登出 |YSM3C</title>
<%
session.invalidate();
%>



</head>
<body>
  <div id="auth">
        
<div class="row h-100">
    <div class="col-lg-5 col-12">
        <div id="auth-left">
            <div class="auth-logo">
                <a href="index.html"><img src="../back_CSS_JS/assets/images/logo/logo.png" alt="Logo"></a>
            </div>
            <h1 class="auth-title">登出已成功,歡迎再登入</h1>
          
           

            
                   

  
    <div class="col-lg-7 d-none d-lg-block">
        <div id="auth-right">

        </div>
    </div>
</div>
</div>
    </div>
    </div>
	<script language=javascript>
		setTimeout('window.location="<%=request.getContextPath()%>/back_end/login/login.jsp"', 2000)
	</script>

</body>
</html>