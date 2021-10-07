<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		
<!-- ************************下面開始塞你自己的頁面資訊************************ -->		
<!-- ************************下面開始塞你自己的頁面資訊************************ -->		

        <!-- Start Main Slider Area 旋轉木馬-->
        <div class="main-slider-area">
            <div class="container">
                <div class="home-slides-two owl-carousel owl-theme">
                    <div class="main-slider-item-box">
                        <div class="main-slider-content">
                            <b>Big Sale Offer</b>
                            <h1>Get the Best Deals on Headphone</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            
                            <div class="slider-btn">
                                <a href="#" class="default-btn">
                                    <i class="flaticon-shopping-cart"></i>
                                    Shop Now
                                    <span></span>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="main-slider-item-box item-two">
                        <div class="main-slider-content">
                            <b>Popular in 2020</b>
                            <h1>New Arrivals CCTV Camera</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            
                            <div class="slider-btn">
                                <a href="#" class="default-btn">
                                    <i class="flaticon-shopping-cart"></i>
                                    Shop Now
                                    <span></span>
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="main-slider-item-box item-three">
                        <div class="main-slider-content">
                            <b>Big Sale Offer</b>
                            <h1>High-Quality Product Camera</h1>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
                            
                            <div class="slider-btn">
                                <a href="#" class="default-btn">
                                    <i class="flaticon-shopping-cart"></i>
                                    Shop Now
                                    <span></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Main Slider Area 旋轉木馬-->


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
                                <div class="col-lg-3 col-sm-6">
                                    <div class="single-bestsellers-products">
                                        <div class="bestsellers-products-image">
                                            <a href="products-details.html"><img src="../front_CSS_JS/assets/img/bestsellers-products/bestsellers-products-1.jpg" alt="image"></a>
                                            <div class="tag">New</div>
                                            <ul class="bestsellers-action">
                                                <li>
                                                    <a href="cart.html">
                                                        <i class="flaticon-shopping-cart"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                                </li>
                                            </ul>
                                        </div>
            
                                        <div class="bestsellers-products-content">
                                            <h3>
                                                <a href="products-details.html">Action Camera</a>
                                            </h3>
                                            <ul class="rating">
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                            </ul>
                                            <span>$150.00</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-sm-6">
                                    <div class="single-bestsellers-products">
                                        <div class="bestsellers-products-image">
                                            <a href="products-details.html"><img src="../front_CSS_JS/assets/img/bestsellers-products/bestsellers-products-2.jpg" alt="image"></a>
                                            <div class="tag">Sale</div>
                                            <ul class="bestsellers-action">
                                                <li>
                                                    <a href="cart.html">
                                                        <i class="flaticon-shopping-cart"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                                </li>
                                            </ul>
                                        </div>
            
                                        <div class="bestsellers-products-content">
                                            <h3>
                                                <a href="products-details.html">Portable Speakers</a>
                                            </h3>
                                            <ul class="rating">
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                            </ul>
                                            <span>$125.00</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-sm-6">
                                    <div class="single-bestsellers-products">
                                        <div class="bestsellers-products-image">
                                            <a href="products-details.html"><img src="../front_CSS_JS/assets/img/bestsellers-products/bestsellers-products-3.jpg" alt="image"></a>
                                            <div class="tag">New</div>
                                            <ul class="bestsellers-action">
                                                <li>
                                                    <a href="cart.html">
                                                        <i class="flaticon-shopping-cart"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                                </li>
                                            </ul>
                                        </div>
            
                                        <div class="bestsellers-products-content">
                                            <h3>
                                                <a href="products-details.html">Gaming Controller</a>
                                            </h3>
                                            <ul class="rating">
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                            </ul>
                                            <span>$100.00</span>
                                        </div>
                                    </div>
                                </div>

                                <div class="col-lg-3 col-sm-6">
                                    <div class="single-bestsellers-products">
                                        <div class="bestsellers-products-image">
                                            <a href="products-details.html"><img src="../front_CSS_JS/assets/img/bestsellers-products/bestsellers-products-4.jpg" alt="image"></a>
                                            <div class="tag">Sale</div>
                                            <ul class="bestsellers-action">
                                                <li>
                                                    <a href="cart.html">
                                                        <i class="flaticon-shopping-cart"></i>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                                </li>
                                                <li>
                                                    <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                                </li>
                                            </ul>
                                        </div>
            
                                        <div class="bestsellers-products-content">
                                            <h3>
                                                <a href="products-details.html">Wireless Mouse</a>
                                            </h3>
                                            <ul class="rating">
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                                <li><i class='bx bxs-star'></i></li>
                                            </ul>
                                            <span>$75.00</span>
                                        </div>
                                    </div>
                                </div>
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
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-arrivals-products">
                            <div class="arrivals-products-image">
                                <a href="products-details.html"><img src="../front_CSS_JS/assets/img/arrivals-products/arrivals-products-1.jpg" alt="image"></a>
                                <div class="tag">New</div>
                                <ul class="arrivals-action">
                                    <li>
                                        <a href="cart.html">
                                            <i class="flaticon-shopping-cart"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                    </li>
                                    <li>
                                        <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                    </li>
                                </ul>
                            </div>

                            <div class="arrivals-products-content">
                                <h3>
                                    <a href="products-details.html">Smart Watch</a>
                                </h3>
                                <ul class="rating">
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                </ul>
                                <span>$99.00</span>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="single-arrivals-products">
                            <div class="arrivals-products-image">
                                <a href="products-details.html"><img src="../front_CSS_JS/assets/img/arrivals-products/arrivals-products-2.jpg" alt="image"></a>
                                <div class="tag">New</div>
                                <ul class="arrivals-action">
                                    <li>
                                        <a href="cart.html">
                                            <i class="flaticon-shopping-cart"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                    </li>
                                    <li>
                                        <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                    </li>
                                </ul>
                            </div>

                            <div class="arrivals-products-content">
                                <h3>
                                    <a href="products-details.html">Digital Camera</a>
                                </h3>
                                <ul class="rating">
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                </ul>
                                <span>$125.00</span>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="single-arrivals-products">
                            <div class="arrivals-products-image">
                                <a href="products-details.html"><img src="../front_CSS_JS/assets/img/arrivals-products/arrivals-products-3.jpg" alt="image"></a>
                                <div class="tag">Sale</div>
                                <ul class="arrivals-action">
                                    <li>
                                        <a href="cart.html">
                                            <i class="flaticon-shopping-cart"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                    </li>
                                    <li>
                                        <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                    </li>
                                </ul>
                            </div>

                            <div class="arrivals-products-content">
                                <h3>
                                    <a href="products-details.html">Wireless Headphone</a>
                                </h3>
                                <ul class="rating">
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                </ul>
                                <span>$175.00</span>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-3 col-sm-6">
                        <div class="single-arrivals-products">
                            <div class="arrivals-products-image">
                                <a href="products-details.html"><img src="../front_CSS_JS/assets/img/arrivals-products/arrivals-products-4.jpg" alt="image"></a>
                                <div class="tag">New</div>
                                <ul class="arrivals-action">
                                    <li>
                                        <a href="cart.html">
                                            <i class="flaticon-shopping-cart"></i>
                                        </a>
                                    </li>
                                    <li>
                                        <a href="wishlist.html"><i class="flaticon-heart"></i></a>
                                    </li>
                                    <li>
                                        <a href="#" data-toggle="modal" data-target="#productsQuickView"><i class="flaticon-view"></i></a>
                                    </li>
                                </ul>
                            </div>

                            <div class="arrivals-products-content">
                                <h3>
                                    <a href="products-details.html">Bluetooth Speaker</a>
                                </h3>
                                <ul class="rating">
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                    <li><i class='bx bxs-star'></i></li>
                                </ul>
                                <span>$75.00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Arrivals Products Area 租賃商品-->		
		
<!-- ************************上面塞你自己的頁面資訊***************************** -->	
<!-- ************************上面塞你自己的頁面資訊***************************** -->	

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