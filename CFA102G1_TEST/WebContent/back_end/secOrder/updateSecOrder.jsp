<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<script
  		src="https://code.jquery.com/jquery-3.6.0.slim.min.js"
  		integrity="sha256-u7e5khyithlIdTpu22PHhENmPcRdFiHRjhAuHcs05RI="
  		crossorigin="anonymous">
	</script>
		
		
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
							<a href="<%=request.getContextPath()%>/back_end/secOrder/secOrder_select_page.jsp">管理二手訂單</a>
						</h3>
						<br>
					</div>
				</div>
			</div>


			<div class="card">
				<div class="card-content">
					<div class="card-body">
						<h4>修改訂單</h4><br>
						<h6>
							<a href="<%=request.getContextPath()%>/back_end/secOrder/listAllSecOrder.jsp">回到訂單列表</a>
						</h6>
						<!--錯誤顯示>>>>-->
						<c:if test="${not empty errorMsgs}">
							<c:forEach var="error" items="${errorMsgs}"> 
									<h4 class="card-text" style="color:red">${error}</h4>
							</c:forEach>
							<h5 class="card-text" style="color:red"></h5>
						</c:if>
							<br>
						<!--<<<<錯誤顯示-->
	
						<form method="post" class="form form-horizontal" action="<%=request.getContextPath()%>/secOrder/SecOrder.do">
							<div class="form-body">
								<div class="row">
									<div class="col-md-4">
										<label>訂單編號</label>
									</div>
									<div class="col-md-8 form-group">
										<label>${secOrderVO.so_no}</label>
									</div>

									<div class="col-md-4">
										<label>訂單狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_sta" name="so_sta">
                                            <option value = "通知自取">通知自取</option>
                                            <option value = "備貨中" >備貨中</option>
                                            <option value = "完成訂單" >完成訂單</option>
                                            <option value = "取消訂單">取消訂單</option>
                                    	</select>	
									</div>

									<div class="col-md-4">
										<label>付款狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_pay" name="so_pay_sta">
                                            <option value = "待付款">待付款</option>
                                            <option value = "已付款">已付款</option>
                                    	</select>	
									</div>
								
									<div class="col-md-4">
										<label>出貨狀態</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_ship" name="so_ship_sta">
                                            <option value = "未出貨">未出貨</option>
                                            <option value = "已出貨">已出貨</option>
                                            <option value = "待取貨">待取貨</option>
                                    	</select>	
									</div>
									
									<div class="col-md-4">
										<label>運送方式</label>
									</div>
									<div class="col-md-8 form-group">
										<select class="form-select" id="mySelect_prodel" name="so_prodel">
                                            <option value = "自取">自取</option>
                                            <option value = "宅配">宅配</option>
                                    	</select>	
									</div>
									
									<div class="col-md-4">
										<label>配送地址</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="text" class="form-control"
											name="so_deladrs" value="${secOrderVO.so_deladrs}">
									</div>
									
									<div class="col-md-4">
										<label>出貨日期</label>
									</div>
									<div class="col-md-8 form-group">
										<input type="date" class="form-control"
											name="so_shipdate" value="<fmt:formatDate pattern="yyyy-MM-dd" 
            																		  value="${secOrderVO.so_shipdate}"/>">
									</div>
									
									<div class="col-sm-12 d-flex justify-content-end">
										<button type="submit" class="btn btn-primary me-1 mb-1">修改</button>
										<button type="reset" class="btn btn-light-secondary me-1 mb-1">清除</button>
									</div>
										<input type="hidden" name="so_no" value="${secOrderVO.so_no}">
										<input type="hidden" name="so_purtime" value="${secOrderVO.so_purtime}">
										<input type="hidden" name="mem_no" value="${secOrderVO.mem_no}">
										<input type="hidden" name="ci_no" value="${secOrderVO.ci_no}">
										<input type="hidden" name="so_totalpri" value="${secOrderVO.so_totalpri}">
										<input type="hidden" name="so_paymthd" value="${secOrderVO.so_paymthd}">
										<input type="hidden" name="so_delcost" value="${secOrderVO.so_delcost}">
										<input type="hidden" name="so_discount_price" value="${secOrderVO.so_discount_price}">
										<input type="hidden" name="action" value="update">
								</div>
							</div>
						</form>
					</div>
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
		
		<script>

		$(function() {
            var temp="${secOrderVO.so_sta}"; 
            $("#mySelect_sta").val(temp);
        });
		
		$(function() {
            var temp="${secOrderVO.so_pay_sta}"; 
            $("#mySelect_pay").val(temp);
        });
		
		$(function() {
            var temp="${secOrderVO.so_ship_sta}"; 
            $("#mySelect_ship").val(temp);
        });
		
		$(function() {
            var temp="${secOrderVO.so_prodel}"; 
            $("#mySelect_prodel").val(temp);
        });
		
		</script>
</body>
</html>