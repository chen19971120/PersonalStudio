<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sec_product_class.model.*" %>	
<%@ page import="com.sec_product_inform.model.*"  %>

<% 
	ProductInformVO productInformVO = (ProductInformVO) request.getAttribute("productInformVO");
%>
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
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3><a href="<%=request.getContextPath()%>/back_end/secProductInfo/select_page.jsp">管理二手商品</a></h3>
						<br>
					</div>
				</div>
			</div>


			<div class="card">
				<div class="card-content">
					<div class="card-body">
						<h4>修改二手商品</h4><br>
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h4 class="card-text" style="color:red">${error}</h4>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
	
						<form method="post" class="form form-horizontal" enctype="multipart/form-data" action="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>商品名稱</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="spi_name" value="<%=(productInformVO==null)?"":productInformVO.getSpi_name()  %>">
									</div>
																		
									<jsp:useBean id="classSvc" scope="page" class="com.sec_product_class.model.ProductClassService"/>
									<div class="col-md-4">
										<label>商品類別</label>
									</div>
									<div class="col-md-8 form-group">									
										<select class="form-select" id="basicSelect" name="spc_no">
											<c:forEach var="ClassVO" items="${classSvc.all}">
                                            	<option value = "${ClassVO.spc_no}">${ClassVO.spc_name}</option>
                                           </c:forEach>
                                    	</select>
									</div>
									<div class="col-md-4">
										<label>商品內容</label>
									</div>
									<div class="col-md-8 form-group">
										<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="spi_content"><%= (productInformVO==null)?"":productInformVO.getSpi_content()%></textarea>
									</div>

									<div class="col-md-4">
										<label>商品價格</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="spi_pri" value="<%=(productInformVO==null)?" ":productInformVO.getSpi_pri()%>">
									</div>

									<div class="col-md-4">
										<label>庫存</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="spi_stock" value="<%=(productInformVO==null)?" ":productInformVO.getSpi_stock()%>">
									</div>
								
									<div class="col-md-4">
										<label>商品狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="basicSelect" name="spi_sta">
                                            <option value = "上架">上架</option>
                                            <option value = "下架">下架</option>
                                    	</select>	
									</div>
									
<!-- 									<div class="col-md-4"> -->
<!-- 										<label>商品照片</label> -->
<!-- 									</div> -->
									
<!-- 									<div class="col-md-8 form-group"> -->
<!-- 										<input class="form-control" type="file" id="formFile" multiple name="imageFile"> -->
<!-- 									</div> -->
									
									<div class="col-sm-12 d-flex justify-content-end">
										<button type="submit" class="btn btn-primary me-1 mb-1">修改</button>
										<button type="reset" class="btn btn-light-secondary me-1 mb-1">清除</button>
									</div>
										<input type="hidden" name="spi_no" value="<%=productInformVO.getSpi_no()%>">
										<input type="hidden" name="action" value="update">
										
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>



		<!--*******************	
		Start Include JS File  
		******************* -->
		<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
		<!--*******************	
		End Include JS File  
		******************* -->
	</div>
</body>
</html>