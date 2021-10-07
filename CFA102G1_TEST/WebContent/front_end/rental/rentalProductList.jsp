<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.productReviews.model.*"%>
<%

	RentalClassService rcSvc = new RentalClassService(); 
	List<RentalClassVO> TopRclist = rcSvc.getRcRentHotList(3);
	
	List<RentalClassVO> listAll = rcSvc.getAll();
	List<RentalClassVO> list = (List<RentalClassVO>) request.getAttribute("list");
	if(list==null)
		list = listAll;
	
	List<String> rc_itemList = rcSvc.getAllRc_Item();
	pageContext.setAttribute("rc_itemList",rc_itemList);
	
	ProductReviewsService prSvc = new ProductReviewsService();
%>

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
<style class="list-search-style">

</style>
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

        <!-- Start Shop Area -->
        <section class="shop-area bg-ffffff pt-50 pb-50">
            <div class="container">
                <div class="row">
                    <div class="col-lg-4 col-md-12">
                        <aside class="widget-area">
                            <div class="widget widget_search">
                                <form class="search-form">
                                    <label>
                                        <span class="screen-reader-text">搜尋:</span>
                                        <input type="search" id="searchbar" class="search-field" placeholder="Search...">
                                    </label>
                                </form>
                            </div>
                            
                    		<!-- 租賃分類 -->
                    
                            <div class="widget widget_categories">
                                <h3 class="widget-title">租賃分類</h3>
    
                                <ul class="categories">
                                	<li>
                                        <a href="<%=request.getContextPath()%>/front_end/rental/rentalProductList.jsp" class="nav-link">
                                            <i class="flaticon-desktop-computer"></i>
                                            	所有商品
                                        </a>
                                    </li>
                                	<c:forEach var="rc_item" items="${rc_itemList}">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRc_itemList&rc_item=${rc_item}"
                                         class="nav-link">
                                            <i class="flaticon-desktop-computer"></i>
                                            	${rc_item}
                                        </a>
                                    </li>
          							</c:forEach>                                   
                                </ul>
                            </div>
                            
                            <!-- 租賃排行 -->

                            <div class="widget widget_best-seller-products">
                                <h3 class="widget-title">排行榜</h3>
                              <%if(TopRclist.size()==0){ %>
                              	 目前無租賃商品
                              <%} else { %>
                              <%for(RentalClassVO topRc :TopRclist) {%>							
                                <article class="item">
                                    <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=topRc.getRc_no()%>" class="thumb">                             
                                      <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=<%=topRc.getRc_no()%>" alt="image">                                       
                                    </a>
                                    <div class="info">
                                        <h4 class="title usmall">
                                            <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=topRc.getRc_no()%>">
                                            <%=topRc.getRc_name()%></a>
                                        </h4>
                                        <span>租賃次數:<%=topRc.getRc_total_count()%></span>
                                        <%if(topRc.getRc_total_score()!=0){ %>
                                        	<%Integer pr_num = prSvc.getListbyRc(topRc.getRc_no()).size();%>                                      	
	                                     	<ul class="rating">
	                                     		<%for(int i=1;i<=Math.round(topRc.getRc_total_score()/pr_num);i++){ %>
	                                            <li><i class='bx bxs-star'></i></li>
	                                            <%}%>
	                                        </ul>                      
                                        <%}%>                     
                                    </div>
                                </article>
                                <%} %> 
                                <%} %>             
                            </div>
                        </aside>
                    </div>
                    
                    <div class="col-lg-8 col-md-12">
                        <div class="products-filter-options">
                            <div class="row align-items-center">
                                <div class="col-lg-4 col-md-4">
                                    <div class="d-lg-flex d-md-flex align-items-center">
                                                                     
                                    </div>
                                </div>
    
                            </div>
                        </div>
                        
        				<!-- 租賃商品顯示 -->
        				
                        <div id="products-collections-filter" class="row">
                        
                        <%for(RentalClassVO rcVO: list){%>
							<%if(rcVO.getRc_status().equals("上架")){ %>                                             
                            <div class="col-lg-4 col-sm-6" id="product" data-title="<%=rcVO.getRc_name().toLowerCase()%>">
                                <div class="single-shop-products">
                                    <div class="shop-products-image">
                                        <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rcVO.getRc_no()%>">
                                        <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=<%=rcVO.getRc_no()%>" alt="image"
                                        width="250px" height="250px"></a>
                                        <ul class="shop-action">
                                            <li>
                                                <a href="<%=request.getContextPath()%>/back_end/ro/ro.do?action=showRoCreatePage&rc_no=<%=rcVO.getRc_no()%>">
                                                    <i class="flaticon-shopping-cart"></i>
                                                </a>
                                            </li>                          
                                        </ul>
                                    </div>
        
                                    <div class="shop-products-content">
                                        <h3>
                                            <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rcVO.getRc_no()%>">
                                            <%=rcVO.getRc_name()%></a>
                                        </h3>
                                        <span>NT$<%=rcVO.getRc_price()%>/天</span>
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
                </div>
            </div>
        </section>
        <!-- End Shop Area -->


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
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/js/jquery.min.js"></script>
<script>
	
	$('input#searchbar').on('change paste keyup',function(){
	    var value = $(this).val().toLowerCase();
	    console.log(value);
	    console.log(!value);
	    if(!value){
	      $('.list-search-style').html('');
	    }
	    else{
	      _searchStart(value);
	    }
	  function _searchStart(v){
	    $('.list-search-style').html(
	      '#products-collections-filter > div:not([data-title*="'+ v +'"]) {display:none;}'
	    );
	  }
	});	


</script>
</body>
</html>