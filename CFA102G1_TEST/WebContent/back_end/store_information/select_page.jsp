<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file="../back_include_page/CSS_link.jsp"%>
<!--*******************	
		End Include CSS File  
		******************* -->
<meta charset="UTF-8">
<title>YSM-3C 後台管理</title>
<link rel="icon" type="image/png"
	href="../back_CSS_JS/assets/imgaes/logo/favicon.png">
<title>YSM3C</title>
<style type="text/css">
h1 {
	text-align: center;
}
</style>
</head>
<body>
	<div id="app">
		<!--*******************	
		Start Include sidebar File  
		******************* -->
		<%@ include file="../back_include_page/sidebar.jsp"%>
		<!--*******************	
		End Include sidebar File  
		******************* -->

		<div id="main">
			<section class="section">
				<div class="card">
					<div class="card-body">
						<table id="table-1">
						</table>
						<hr>
						<h1 >STORE_INFOFMATION 商家資訊</h1>
						<hr>
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<div>
							<h3>商家資訊查詢:</h3>
							<br>
							<ul>
								<li><a
									href='<%=request.getContextPath()%>/back_end/store_information/listAllSi.jsp'>所有</a>
									商家資訊. <br> <br></li>

								<li>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do">
										<b>輸入商家編號 (如1):</b> <input type="text" name="si_no"> <input
											type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" class="btn btn-outline-secondary btn-sm"
											value="送出">
									</FORM> <br>
								</li>

								<jsp:useBean id="siSvc" scope="page"
									class="com.store_information.model.SiService" />
								<li>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do">
										<b>選擇商家編號:</b> <select size="1" name="si_no">
											<c:forEach var="siVO" items="${siSvc.all}">
												<option value="${siVO.si_no}">${siVO.si_no}
											</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" class="btn btn-outline-secondary btn-sm"
											value="送出">
									</FORM> <br>
								</li>
								<li>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/back_end/store_information/si.do">
										<b>選擇門市地址:</b> <select size="1" name="si_no">
											<c:forEach var="siVO" items="${siSvc.all}">
												<option value="${siVO.si_no}">${siVO.si_address}
											</c:forEach>
										</select> <input type="hidden" name="action" value="getOne_For_Display">
										<input type="submit" class="btn btn-outline-secondary btn-sm"
											value="送出">
									</FORM>
								</li>
							</ul>
							<h3>商家資訊管理</h3>
							<br>
							<ul>
								<li><a href='<%=request.getContextPath()%>/back_end/store_information/addSi.jsp'>新增</a> 商家資訊.</li>
								<br>
							</ul>
						</div>
					</div>
				</div>
			</section>
		</div>
		<!--*******************	
		Start Include sidebar File  
		******************* -->
		<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
		<!--*******************	
		End Include sidebar File  
		******************* -->
	</div>
</body>
</html>