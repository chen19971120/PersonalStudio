<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.sec_product_class.model.*" %>

<%
 	ProductClassService productClassSvc = new ProductClassService();
	List<ProductClassVO> list = productClassSvc.getAll();
	request.setAttribute("list", list);
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


                <section class="section">
                    <div class="card">
                        <div class="card-header">
							<h3 class="card-title">二手商品類別列表</h3>
                        </div>
                        <div class="card-body">
                            <table class="table table-striped" id="table1">
                                <thead>
                                    <tr>
                                        <th>類別編號</th>
                                        <th>類別名稱</th>
                                        <th>修改</th>
                                    </tr>
                                </thead>
                                <tbody>
                                	<c:forEach var="list" items="${list}">
	                                    <tr>
	                                        <td>${list.spc_no}</td>
	                                        <td>${list.spc_name}</td>
	                                        <td>
	                                        	<form
													action="<%=request.getContextPath()%>/secProductClass/ProductClass.do"
													method="post">
													<input type="submit" class="btn btn-outline-secondary" value="修改">
													<input type="hidden" name="spc_no" value="${list.spc_no}">
													<input type="hidden" name="action" value="getOneForUpdate">
												</form>
	                                        </td>
	                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>

                </section>

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
		
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script> --%>
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/js/bootstrap.bundle.min.js"></script> --%>
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/apexcharts/apexcharts.js"></script> --%>
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/js/pages/dashboard.js"></script> --%>
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/js/main.js"></script> --%>
<%-- 		<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/simple-datatables/simple-datatables.js"></script> --%>
		
	<script>
        // Simple Datatable
        let table1 = document.querySelector('#table1');
        let dataTable = new simpleDatatables.DataTable(table1);
    </script>
</body>
</html>