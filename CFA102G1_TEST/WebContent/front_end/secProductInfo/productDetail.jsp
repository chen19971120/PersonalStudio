<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
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
        <!-- Start Products Details Area -->
        <section class="products-details-area ptb-50">
            <div class="container">
                <div class="products-details-desc">
       
                    <div class="row align-items-center">

                        <!-- Start 商品照片 -->
                        <div class="col-lg-6 col-md-6">
                            <div class="main-products-image">
                                <!-- Start 商品左側小圖 -->
                                <div class="slider slider-nav">
                                	<c:forEach var="imglist" items="${afterFilterImages}">
                                    	<div><img src="<%=request.getContextPath()%>/secProductImg/ProductImg.do?spim_no=${imglist.spim_no}&action=ExportImages" alt="image"></div>
                                	</c:forEach>
                                </div>
                                <!-- End 商品左側小圖 -->

                                <!-- Start 商品大圖 -->
                                <div class="slider slider-for">
									<c:forEach var="imglist" items="${afterFilterImages}">
                                    	<div><img src="<%=request.getContextPath()%>/secProductImg/ProductImg.do?spim_no=${imglist.spim_no}&action=ExportImages" alt="image"
                                    	style="width:600px;height:500px;"></div>
                                	</c:forEach>
                                </div>
                                <!-- Start 商品大圖 -->
                            </div>
                        </div>
                        <!-- End 商品照片 -->

                        <div class="col-lg-6 col-md-6">
                            <div class="product-content content-two">
                                <h3>${productInformVO.spi_name}</h3>

                                <div class="price">                                    
                                    <span class="new-price">$<fmt:formatNumber type="number" maxFractionDigits="3" value="${productInformVO.spi_pri}"/></span>
                                    
                                </div>

                                <ul class="products-info">
                                    <li><span>庫存:</span> <a href="#">${productInformVO.spi_stock}</a></li>
                                    <li><span>商品編號:</span> <a href="#">${productInformVO.spi_no}</a></li>
                                </ul>
                                
								<form METHOD="POST" ACTION="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do">
	                                <div >
	                                    <span>購買數量:</span>
	
	                                    <div class="input-counter">
											<select name="quantity">
												<c:forEach var="i" begin="1" end="${productInformVO.spi_stock}">
													<option value="${i}">${i}</option>
												</c:forEach>
											</select>
											<br>
	                                    </div>
	                                </div>
	
	                                <div class="product-add-to-cart">
	                                    <button type="submit" class="default-btn">
	                                        <i class="flaticon-shopping-cart"></i>
	                                        	加入購物車
	                                        <span></span>
	                                    </button>
	                                </div>
	                                <input type="hidden" name="spi_no" value="${productInformVO.spi_no}">
	                                <input type="hidden" name="action" value="cart">
	                                <input type="hidden" name="cart_action" value="add">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="products-details-tabs">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item"><a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab" aria-controls="description">Description</a></li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="description" role="tabpanel">
                            <h2>商品簡介</h2>
								<!--商簡介內容透過JS新增 -->
                             <div id="prodContent"></div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Products Details Area -->


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
		
	<script>
		//將商品介紹內文，拆解為文字陣列
		let arr = `${productInformVO.spi_content}`.split(">>");  //方法一
// 		let arr = $("#prodContent").text().split(">>");	//方法二
		let str = "";
		
		//將陣列加上換行
		for(let i = 0; i < arr.length; i++) {
			if(arr[i] != ""){
				str += (">>" + arr[i] + "<br>");
			}
		}
		$("#prodContent").html(str);
	</script>
</body>
</html>