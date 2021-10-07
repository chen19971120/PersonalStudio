<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%-- <%@ page import="com.sec_product_inform.model.*" %> --%>

<%-- <% --%>
<!-- // 	ProductInformService productInformSvc = new ProductInformService(); -->
<!-- // 	List<ProductInformVO> productInformVOs = productInformSvc.getAll(); -->
<%--  %> --%>
<jsp:useBean id="productInformSvc" scope="page" class="com.sec_product_inform.model.ProductInformService" />

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

		<div id="main">
			<div class="page-title">
				<div class="row">
					<div class="col-12 col-md-6 order-md-1 order-last">
						<h3>
							<a href="<%=request.getContextPath()%>/back_end/secOrder/secOrder_select_page.jsp">管理二手訂單</a>
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
								<h4 class="card-title">訂單詳細資訊</h4>
								<h6>
									<a href="<%=request.getContextPath()%>/back_end/secOrder/listAllSecOrder.jsp">回到訂單列表</a>
								</h6>
							</div>
							
							<div class="card-content">
								<div class="card-body">
									<!-- Table with outer spacing -->
									<div class="table-responsive">
										<table class="table table-lg">
											<thead>
												<tr>
													<th>訂單編號</th>
													<th>${secOrderVO.so_no}</th>
												</tr>
												<tr>
													<th>訂購時間</th>
													<th><fmt:formatDate value="${secOrderVO.so_purtime}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
												</tr>
												<tr>
													<th>會員編號</th>
													<th>${secOrderVO.mem_no}</th>
												</tr>											
												<tr>
													<th>訂單狀態</th>
													<th>${secOrderVO.so_sta}</th>
												</tr>
												<tr>
													<th>付款狀態</th>
													<th>${secOrderVO.so_pay_sta}</th>
												</tr>
												<tr>
													<th>出貨狀態</th>
													<th>${secOrderVO.so_ship_sta}</th>
												</tr>
												<tr>
													<th>優惠券編號</th>
													<th>${secOrderVO.ci_no}</th>
												</tr>
												
												<tr>
													<th>運送方式</th>
													<th>${secOrderVO.so_prodel}</th>
												</tr>
												<tr>
													<th>配送地址</th>
													<th>${secOrderVO.so_deladrs}</th>
												</tr>
												<tr>
													<th>付款方式</th>
													<th>${secOrderVO.so_paymthd}</th>
												</tr>
												<tr>
													<th>出貨日期</th>
													<th><fmt:formatDate value="${secOrderVO.so_shipdate}" pattern="yyyy-MM-dd HH:mm:ss"/></th>
												</tr>
												<tr>
													<th>配送費用</th>
													<th>${secOrderVO.so_delcost}</th>
												</tr>
												<tr>
													<th>訂單總價格</th>
<%-- 													<th>${secOrderVO.so_totalpri}</th> --%>
													<th><fmt:formatNumber type="number" maxFractionDigits="3" value="${secOrderVO.so_totalpri}"/></th>
													
												</tr>
												<tr>
													<th>訂單優惠價格</th>
<%-- 													<th>${secOrderVO.so_discount_price}</th> --%>
													<th><fmt:formatNumber type="number" maxFractionDigits="3" value="${secOrderVO.so_discount_price}"/></th>
												</tr>
												
											</thead>
										</table>
										<br>
										<h4>訂單明細</h4>
										<!--購物明細 -->
										<table class="table table-lg">
											<thead>
												<tr>
													<th>訂單明細編號</th>
													<th>二手商品編號</th>
													<th>購買數量</th>
													<th>明細價格</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var="secOrderListVO" items="${secOrderListVOs}">
													<tr>
														<td>${secOrderListVO.sol_no}</td>
														<td>
														<c:forEach var="productInformVO" items="${productInformSvc.all}">
															<c:if test="${secOrderListVO.spi_no==productInformVO.spi_no}">
																${productInformVO.spi_name}
															</c:if>
														</c:forEach>
														</td>
														<td>${secOrderListVO.sol_proamot}</td>
<%-- 														<td>${secOrderListVO.sol_pri}</td> --%>
														<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${secOrderListVO.sol_pri}"/></td>
													</tr>
												</c:forEach>
											</tbody>
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