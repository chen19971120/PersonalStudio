<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ page import=" java.text.DecimalFormat"%>
<%@ page import="java.util.*" %>

<%Vector<ProductInformVO> productInformList = (Vector<ProductInformVO>) session.getAttribute("shoppingCart_sec"); %>
<%Map<Integer, Integer> Quamap = (Map<Integer, Integer>) session.getAttribute("Quamap"); %>

<!-- 前台_購物車頁面 -->
<!DOCTYPE html>
<html>
<head>
	<!--*******************	
		Start Include CSS File  
		******************* -->
        <%@ include file="../front_include_page/CSS_link.jsp"%>
	<!--*******************	
		End Include CSS File  
		******************* -->        
        
        <title>YSM3C - 二手租賃商城</title>

        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/favicon.png">
</head>
<body>
	    <!-- Start Preloader Area 載入畫面(圈圈)-->
        <div class="preloader">
            <div class="loader">
                <div class="sbl-half-circle-spin">
                    <div></div>
                </div>
            </div>
        </div>
        <!-- End Preloader Area 載入畫面(圈圈)-->

	<!--*******************	
		Start Top Head Area  
		******************* -->
		<%@ include file="../front_include_page/Top_head.jsp"%>
	<!--*******************	
		End Top Head Area  
		******************* -->	

	<!--*******************	
		Start Navbar Area  
		******************* -->		
		<%@ include file="../front_include_page/navbar.jsp"%>
	<!--*******************	
		End Navbar Area  
		******************* -->
		<br>
		<h6>
			<a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?action=showAllProduct">&nbsp&nbsp &lt&lt 回到商品列表</a>
		</h6>

        <!-- Start Cart Area -->
		<section class="cart-area ptb-50">
            <div class="container">
                <div class="row">
                
                    <div class="col-lg-8 col-md-12">
                        <form>
                            <div class="cart-table table-responsive">
                      
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th scope="col">商品照</th>
                                            <th scope="col">商品名稱</th>
                                            <th scope="col">單價</th>
                                            <th scope="col">購買數量</th>
                                            <th scope="col">小計</th>
                                        </tr>
                                    </thead>
        
                                    <tbody>
                                    
                                    <%if (productInformList != null && (productInformList.size() > 0)) {%>
                                    	<%
                                    	for(int index=0 ; index < productInformList.size() ; index++){
                                    		ProductInformVO order = productInformList.get(index);
                                    	%>
                                        <tr class="top-class">
                                            <td class="product-thumbnail">
                                                <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=<%=order.getSpi_no() %>&index=<%=index %>&action=cart&cart_action=delete" class="remove">
                                                	<i class='bx bx-x'></i>
                                                </a>
													<!--點選商品圖片會回到商品頁面 -->
                                                <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=<%=order.getSpi_no() %>&action=showProductDetail">
                                                    <img src="<%=request.getContextPath()%>/secProductImg/ProductImg.do?spi_no=<%=order.getSpi_no()%>&action=showShopImage" alt="item">
                                                </a>
                                            </td>
        
                                            <td class="product-name">
                                                <a href="products-details.html"><%=order.getSpi_name() %></a>
                                            </td>
        
                                            <td class="product-price">
                                                <span class="unit-amount">$<%=new DecimalFormat(",###").format(order.getSpi_pri()) %></span>
                                            </td>
        
                                            <td class="product-quantity">
                                                <div class="input-counter">
<!--                                                     <span class="minus-btn"><i class='bx bx-minus'></i></span> -->
                                                    <input type="text" value="<%=Quamap.get(order.getSpi_no())%>">
<!--                                                     <span class="plus-btn"><i class='bx bx-plus'></i></span> -->
                                                </div>
                                                
                                            </td>
        
                                            <td class="product-subtotal">
                                                <span class="subtotal-amount">$<%=new DecimalFormat(",###").format(order.getSpi_pri()*Quamap.get(order.getSpi_no())) %></span>
                                            </td>
                                        </tr>
										<%} %>
									<%}else{ %>
										<h4>您的購物車中沒有商品</h4>
									<%} %>
                                    </tbody>
                                </table>
                            </div>
        
                            <div class="cart-buttons">
                                <div class="row align-items-center">
                                    <div class="col-lg-7 col-sm-7 col-md-7">
                                        <div class="shopping-coupon-code">
<!--                                             <input type="text" class="form-control" placeholder="輸入優惠券" name="coupon-code" id="coupon-code"> -->
<!--                                             <button type="submit">Apply Coupon</button> -->
                                        </div>
                                    </div>
        
                                    <div class="col-lg-5 col-sm-5 col-md-5 text-right">
                                        <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?&action=cart&cart_action=deleteAll" class="default-btn">
                                           	移除所有商品
                                            <span></span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="col-lg-4 col-md-12">
                        <div class="cart-totals">
                            <h3>Cart Totals</h3>
    						        <!--Start 錯誤訊息顯示 -->                         		
                                    <c:if test="${not empty errorMsgs}">
                                    	<ul>
											<c:forEach var="error" items="${errorMsgs}"> 
												<li><h5 style="color:red">${error}</h5></li>
											</c:forEach>
										</ul>
									<h5 class="card-text" style="color:red"></h5>
									</c:if>      
                                    <!--End 錯誤訊息顯示 -->
                            <ul>
                                <li>小計 <span>$<%=(productInformList != null && (productInformList.size() > 0))? new DecimalFormat(",###").format(Quamap.get(999)) : 0%></span></li>
                            </ul>
                            
                            <form method="post" action="<%=request.getContextPath()%>/secOrder/SecOrder.do"> 
	                            <input type="text" class="form-control" placeholder="輸入優惠券" name="ci_code" id="coupon-code" maxlength="6">
	                            <br>
	                            <input type="hidden" name="action" value="checkCart">
	                            <button type="submit" class="default-btn">結帳 <span></span></button>
                            </form>
                            
                        </div>
                    </div>
                    
                    
                </div>
            </div>
        </section>
        <!-- End Cart Area -->


	<!--*******************	
		Start Footer Area  
		******************* -->			
		<%@ include file="../front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->	
	
	<!--*******************	
		Start Go Top Area  
		******************* -->				
		<%@ include file="../front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->			

	<!--*******************	
		Start Include JS File  
		******************* -->	
		<%@ include file="../front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->	
</body>
</html>