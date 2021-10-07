<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ page import="com.sec_product_class.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


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
			<div class="page-heading">
				<div class="page-title">
					<div class="row">
						<div class="col-12 col-md-6 order-md-1 order-last">
							<h3>
								<a href="<%=request.getContextPath()%>/back_end/secProductInfo/select_page.jsp">管理二手商品</a>
							</h3>
							<br> <br>
						</div>
					</div>
				</div>

				<!-- Table head options start -->
				<section class="section">
					<div class="card">
						<div class="card-header">
							<h3 class="card-title">二手商品列表</h3>
						</div>

						<div class="card-body">
							<table class="table table-striped" id="table1">
								<thead>
									<tr>
										<th>商品編號</th>
										<th>商品名稱</th>
										<th>商品類別編號</th>
										<th>商品價格</th>
										<th>商品狀態</th>
										<th>詳細</th>
										<th>圖片</th>
										<th>修改</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="productInfoVO" items="${productInfoVOs}" varStatus="s">
										<tr>
											<td>${productInfoVO.spi_no}</td>
											<td>${productInfoVO.spi_name}</td>
											<td>${productInfoVO.spc_no} <c:forEach
													var="prodcutClassVO" items="${pdoductClassSvc.all}">
													<c:if
														test="${productInfoVO.spc_no == prodcutClassVO.spc_no}"> 
															${prodcutClassVO.spc_name}
														</c:if>
												</c:forEach>
											</td>
											<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${productInfoVO.spi_pri}"/></td>
											<td>${productInfoVO.spi_sta}</td>
											<td>
												<A HREF="javascript:presses${s.count}()" class="btn btn-outline-secondary">查看詳情</a>
											</td>
											<td>										
												<A HREF="javascript:presses_img${s.count}()" class="btn btn-outline-secondary">查看圖片</a>
											</td>
											<td>
												<form
													action="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do"
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="spi_no" value="${productInfoVO.spi_no}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
											</td>
										</tr>
										<script>
         									function presses${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=${productInfoVO.spi_no}&action=getOneForDiplay", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
         									function presses_img${s.count}(){
        	 								document.open("<%=request.getContextPath()%>/secProductImg/ProductImg.do?spi_no=${productInfoVO.spi_no}&action=showImages", "" ,"height=550,width=850,left=65,top=100,resizable=yes,scrollbars=yes");
         									}
        								</script>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</section>
				<!-- Table head options end -->
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
		
	<script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
</body>
</html>