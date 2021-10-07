<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.productReviews.model.*"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ page import=" java.text.DecimalFormat"%>
<%@ page import=" java.util.stream.Collectors"%>

<%

	RentalClassService rcSvc = new RentalClassService(); 
	List<RentalClassVO> list = rcSvc.getRcRentHotList(8);
	pageContext.setAttribute("list",list);
			
	ProductReviewsService prSvc = new ProductReviewsService();
	
// 	二手商品顯示>>>>
	ProductInformService productInformSvc = new ProductInformService();
	List<ProductInformVO> productInformVOs = productInformSvc.getAll()
															 .stream()
															 .filter(i -> i.getSpi_stock().intValue() > 0)
															 .filter(i -> i.getSpi_sta().equals("上架"))
															 .collect(Collectors.toList());
	
	
//  <<<<二手商品顯示
%>

<!DOCTYPE html>
<html>
<style>
.main-slider-item {
	background: -webkit-gradient(linear, left top, left bottom, from(#99b9da),
		color-stop(#aac4e0), color-stop(#bbcfe6), color-stop(#ccdbec),
		to(#dce6f2));
	background: linear-gradient(to bottom, #99b9da, #aac4e0, #bbcfe6, #ccdbec, #dce6f2);
	height: 730px;
}

.main-slider-item-box {
	background-image: url(../../assets/img/main-slider/slider-bg-1.jpg);
	background-position: center center;
	background-size: cover;
	background-repeat: no-repeat;
	padding-top: 82px;
	padding-bottom: 125px;
	padding-left: 50px;
	padding-right: 50px;
}

.main-slider-item-box.item-two {
	background-image: url(../../assets/img/main-slider/slider-bg-2.jpg);
}

.main-slider-item-box.item-three {
	background-image: url(../../assets/img/main-slider/slider-bg-3.jpg);
}
</style>
<head>
<!--*******************	
		Start Include CSS File  
		******************* -->
<%@ include file="front_include_page/CSS_link.jsp"%>
<!--*******************	
		End Include CSS File  
		******************* -->

<title>YSM3C - 二手租賃商城</title>

<link rel="icon" type="image/png"
	href="front_CSS_JS/assets/img/favicon.png">
</head>
<body>

	<!--*******************	
		Start Top Head Area  
		******************* -->
	<%@ include file="front_include_page/Top_head.jsp"%>
	<!--*******************	
		End Top Head Area  
		******************* -->

	<!--*******************	
		Start Navbar Area  
		******************* -->
	<%@ include file="front_include_page/navbar.jsp"%>
	<!--*******************	
		End Navbar Area  
		******************* -->

	<!-- ************************下面開始塞你自己的頁面資訊************************ -->
	<!-- ************************下面開始塞你自己的頁面資訊************************ -->

	<!-- Start Main Slider Area 旋轉木馬-->
	<div class="main-slider-area">
		<div class="container">
			<div class="home-slides-two owl-carousel owl-theme">
				<div class="main-slider-item-box">
					<div class="main-slider-content">
						<h2>YSM3C</h2>
						<h1>二手電腦專賣店</h1>
						<p>桌上型電腦/筆記型電腦/電腦零件</p>
						<div class="slider-btn">
							<a
								href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?action=showAllProduct&spc_no=0"
								class="default-btn"> <i class="flaticon-shopping-cart"></i>
								來去逛逛!! <span></span>
							</a>
						</div>
					</div>
				</div>
				<div class="main-slider-item-box item-two">
					<div class="main-slider-content">
						<h2>3C租賃服務</h2>
						<h1>專業●創新●國際化</h1>
						<br>
						<div class="slider-btn">
							<a href="<%=request.getContextPath()%>/front_end/rental/rentalProductList.jsp" class="default-btn"> <i
								class="flaticon-shopping-cart"></i> 馬上租賃!! <span></span>
							</a>
						</div>
					</div>
				</div>
				<div class="main-slider-item-box item-three">
					<div class="main-slider-content">
						<h2>24 小時免費快速估價</h2>
						<h1>高價●快速●安全</h1>
						<br>
						<div class="slider-btn">
							<a href="<%=request.getContextPath()%>/front_end/appraisal_case/appraisalCase.jsp" class="default-btn"> <i
								class="flaticon-shopping-cart"></i> 馬上估價!! <span></span>
							</a>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Slider Area 旋轉木馬-->
	<br>
	<br>
	<!-- Start Bestsellers Area 二手商品-->
	<section class="bestsellers-area pb-20">
		<div class="container">
			<div class="section-title">
				<h2>二手商品</h2>
			</div>
			<div class="tab bestsellers-list-tab">
				<div class="tab_content">
					<div class="tabs_item">
						<div class="row">
							<!-- Start 顯示單個商品 -->
							<%
								for (int i = 0; i < 8; i++) {
									ProductInformVO productInformVO = productInformVOs.get(i);
							%>
							<div class="col-lg-3 col-sm-6">
								<div class="single-bestsellers-products">
									<div class="bestsellers-products-image">
										<a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=<%=productInformVO.getSpi_no() %>&action=showProductDetail">
											<img src="<%=request.getContextPath()%>/secProductImg/ProductImg.do?spi_no=<%=productInformVO.getSpi_no() %>&action=showShopImage" alt="image" 
											style="width:250px;height:250px;">
                                		</a>
									</div>

									<div class="bestsellers-products-content">
										<h3>
											<a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=<%=productInformVO.getSpi_no() %>&action=showProductDetail">
												<%=productInformVO.getSpi_name() %>
											</a>
										</h3>
										<span>$<%=new DecimalFormat(",###").format(productInformVO.getSpi_pri())%></span>
									</div>
								</div>
							</div>
							<%
								}
							%>
							<!-- Start 顯示單個商品 -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Bestsellers Area 二手商品-->


	<!-- Start Arrivals Products Area 租賃商品-->
	<section class="arrivals-products-area pb-20">
		<div class="container">
			<div class="section-title">
				<h2>租賃商品</h2>
			</div>

			<div class="row">
				<%if(list.size()==0) {%>
				目前無租賃商品
				<%} else { %>
				<%for(RentalClassVO rcVO:list){%>

				<div class="col-lg-3 col-sm-6">
					<div class="single-arrivals-products">
						<div class="arrivals-products-image">
							<a
								href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rcVO.getRc_no()%>">
								<img
								src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=<%=rcVO.getRc_no()%>"
								alt="image" width="450px" height="450px">
							</a>
							<div class="tag">HOT</div>
							<ul class="arrivals-action">
								<li><a
									href="<%=request.getContextPath()%>/back_end/ro/ro.do?action=showRoCreatePage&rc_no=<%=rcVO.getRc_no()%>">
										<i class="flaticon-shopping-cart"></i>
								</a></li>
							</ul>
						</div>

						<div class="arrivals-products-content">
							<h3>
								<a
									href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rcVO.getRc_no()%>">
									<%=rcVO.getRc_name()%></a>
							</h3>
							<span>NT$<%=rcVO.getRc_price()%>/天
							</span><br> <span>租賃次數: <%=rcVO.getRc_total_count()%> 次
							</span>
							<%if(rcVO.getRc_total_score()!=0){ %>
							<%Integer pr_num = prSvc.getListbyRc(rcVO.getRc_no()).size();%>
							<ul class="rating">
								<%for(int i=1;i<=Math.round(rcVO.getRc_total_score()/pr_num);i++){ %>
								<li><i class='bx bxs-star'></i></li>
								<%}%>
							</ul>
							<%}%>
						</div>
					</div>
				</div>
				<%}%>
				<%}%>
			</div>
		</div>
	</section>
	<!-- End Arrivals Products Area 租賃商品-->

	<!-- ************************上面塞你自己的頁面資訊***************************** -->
	<!-- ************************上面塞你自己的頁面資訊***************************** -->

	<!--*******************	
		Start Footer Area  
		******************* -->
	<%@ include file="front_include_page/footer.jsp"%>
	<!--*******************	
		End Footer Area  
		******************* -->

	<!--*******************	
		Start Go Top Area  
		******************* -->
	<%@ include file="front_include_page/startGoTop.jsp"%>
	<!--*******************	
		End Go Top Area  
		******************* -->

	<!--*******************	
		Start Include JS File  
		******************* -->
	<%@ include file="front_include_page/JavaScript_Include.jsp"%>
	<!--*******************	
		End Include JS File  
		******************* -->
</body>
</html>