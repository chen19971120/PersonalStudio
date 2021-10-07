<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ page import="com.sec_product_class.model.*" %>	
<%@ page import="com.sec_product_images.model.*"   %>
<%@ page import="java.util.*" %>

<%-- <%  --%>
<!-- // 	ProductClassService productClassSvc = new ProductClassService(); -->
<!-- // 	List<ProductClassVO> classList = productClassSvc.getAll(); -->
<!-- // 	request.setAttribute("classList", classList); -->
<%-- %> --%>

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
						<h3>
							<a
								href="<%=request.getContextPath()%>/back_end/secProductInfo/select_page.jsp">管理二手商品</a>
						</h3>
					</div>
				</div>
			</div>

			<!-- Basic Tables start -->
			<section class="section">
				<div class="row" id="basic-table">
					<div class="col-12">
						<div class="card">
							<div class="card-header">
								<h4 class="card-title">二手商品類別</h4>
							</div>
							<div class="card-content">
								<div class="card-body">
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												<tr>
													<th>類別編號</th>
													<th>${productClassVO.spc_no}</th>
												</tr>
												<tr>
													<th>類別名稱</th>
													<th>${productClassVO.spc_name}</th>
												</tr>
											</thead>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Basic Tables end -->


		</div>
	</div>

	<!--*******************	
		Start Include JS File  
		******************* -->
	<%@ include file="../back_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>