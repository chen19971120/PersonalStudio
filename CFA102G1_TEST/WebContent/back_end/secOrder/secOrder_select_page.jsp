<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
                        <h3>管理二手商品訂單</h3>
                        <br>
                    </div>
                </div>
            </div>			
		
			<!-- Table head options start 二手商品-->
			<section class="section">
				<div class="row" id="table-head">
					<div class="col-12">
						<div class="card">
							<div class="card-content">
								<!-- table head dark -->
								<div class="table-responsive">
									<table class="table mb-0">
										<thead class="thead-dark">
																					
											<tr>
												<th>
													<h4>查詢訂單</h4>
													
													<!--錯誤訊息顯示 -->
													<c:if test="${not empty errorMsgs}">
														<c:forEach var="message" items="${errorMsgs}">
															<h5 style="color:red">${message}</h5>
														</c:forEach>
													</c:if>
													
													<!--錯誤訊息顯示 -->
													
													<!-- 複合查詢>>>> -->
													<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/secOrder/SecOrder.do" >
												        <b>輸入訂單編號 : </b>
												        <input type="text" name="so_no">
    												<br>
    												<br>
												        <b>輸入會員編號 : </b>
												        <input type="text" name="mem_no">
    												<br>
    												<br>
												        <b>訂單狀態 : </b>
												        <select  id="basicSelect" name="so_sta">
                                            				<option value = ""></option>
                                            				<option value = "通知自取">通知自取</option>
                                            				<option value = "備貨中">備貨中</option>
                                            				<option value = "完成訂單">完成訂單</option>
                                            				<option value = "取消訂單">取消訂單</option>
                                    					</select>
    												<br>
    												<br>
												        <b>付款狀態 : </b>
												        <select  id="basicSelect" name="so_pay_sta">
                                            				<option value = ""></option>
                                            				<option value = "待付款">待付款</option>
                                            				<option value = "已付款">已付款</option>
                                    					</select>  												
    												<br>
    												<br>
												        <b>出貨狀態 : </b>
												        <select  id="basicSelect" name="so_ship_sta">
                                            				<option value = ""></option>
                                            				<option value = "未出貨">未出貨</option>
                                            				<option value = "已出貨">已出貨</option>
                                            				<option value = "待取貨">待取貨</option>
                                    					</select>
                                    					
                                    					<br>
                                    					<br>
                                    					<input type="hidden" name="action" value="compoundQuery">  
												        <input type="submit" class="btn btn-secondary" value="送出查詢">
    												</FORM>
													<!-- <<<<複合查詢  -->
												</th>
											</tr>
																							
										</thead>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</section>
			<!-- Table head options end 二手商品-->
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