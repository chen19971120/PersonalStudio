<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.stream.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalProductImages.model.*"%>
<%@ page import="com.productReviews.model.*"%>
<% 	
	//取得商品類別編號rc_no
	Integer rc_no = (Integer) request.getAttribute("rc_no");
	//利用rc_no呼叫getOneRentalClass取得該VO
	RentalClassService rcSvc = new RentalClassService();
	RentalClassVO rcVO = rcSvc.getOneRentalClass(rc_no);
	pageContext.setAttribute("rcVO",rcVO);
	//取得rc_no所有照片編號
	RentalProductImagesService rpiSvc = new RentalProductImagesService();
	List<RentalProductImagesVO> list = rpiSvc.getOneRentalClassImages(rcVO.getRc_no());
	pageContext.setAttribute("list",list);
	//取得rc_no所有評價
	ProductReviewsService prSvc = new ProductReviewsService();
	List<ProductReviewsVO> prlist = prSvc.getListbyRc(rc_no);
	//計算rc_no 評價平均分數到小數點1位
	Double total_score = (double) rcVO.getRc_total_score();
	Double pr_num = (double) prlist.size();
	Double score_avg = 0.0;
	if(pr_num==0)
		score_avg=0.0;
	else
		score_avg = Math.round(total_score*10.0/pr_num)/10.0;
	//取得相關種類商品資訊
	List<RentalClassVO> rc_related_List = rcSvc.getOneRc_item(rcVO.getRc_item()).stream()
				.filter(e -> e.getRc_status().equals("上架"))
				.collect(Collectors.toList());
%>

<jsp:useBean id="roSvc" scope="page" class="com.rentalOrder.model.RentalOrderService" />

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
<link rel="stylesheet" href="../../back_end/back_CSS_JS/assets/vendors/dripicons/webfont.css">
<style>
div .review-item {
	background-color:#F2F4F4;
}

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

 <!-- Start Products Details Area -->
        <section class="products-details-area ptb-50">
            <div class="container">
                <div class="products-details-desc">
                    <div class="row align-items-center">
                        <div class="col-lg-6 col-md-6">
                            <div class="main-products-image">
                                <div class="slider slider-nav">
                                	 <c:forEach var="rpiVO" items="${list}">
                                	 	<div><img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showImgByRpino&id=${rpiVO.rpi_no}" alt="image"></div>
                                	 </c:forEach>                              
                                </div>
                                <div class="slider slider-for">
                                	<c:forEach var="rpiVO" items="${list}">
                                	 	<div><img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showImgByRpino&id=${rpiVO.rpi_no}" 
                                	 	style="width:600px;height:500px" alt="image"></div>	
                                	</c:forEach>                                    
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-6 col-md-6">
                            <div class="product-content content-two">
                                <h3>${rcVO.rc_name}</h3>
                                <div class="product-review">
                                    <div class="rating">
								<%if(score_avg!=0) {%>
								(<%=score_avg%>/5.0)
									<%for(int i=1;i<=Math.round(score_avg);i++){ %>
	                                   <i class='bx bxs-star'></i>
	                                <%} %>  
								<%}%>       	            
                                    </div>
                                </div>

                                <div class="price">
                                    <span class="new-price">NT$${rcVO.rc_price}/天</span><br>
                                    <span class="new-price">押金:${rcVO.rc_deposit}元</span>
                                </div>
                               	<div>
								     <ul>
								     
								     	<li>僅提供預約未來 3 - 7 天  </li>
		                                <li>最短租賃天數: 7  天 </li>
		                               	<li>下單後需當天付款, 否則訂單將自動取消 </li>
		                                <li>租賃期間內享有免費維修服務（不包括人為損害）</li>
		                                </ul>
								</div>
                                
                                <div class="product-add-to-cart">
                                <c:choose>
						            <c:when test="${rcVO.rc_storage==0}">
										<font style="color:red;font-weight:bold">目前暫無庫存</font>		
						            </c:when>
						            <c:otherwise>
							            <button type="submit" class="default-btn" 
							            onclick="location.href='<%=request.getContextPath()%>/back_end/ro/ro.do?action=showRoCreatePage&rc_no=${rcVO.rc_no}'">
	                                        <i class="flaticon-shopping-cart"></i>
	                                     		  立即預約
	                                        <span></span>
	                                    </button>
	                                    <span></span>
	                                    <label>&nbsp;&nbsp;&nbsp;&nbsp; 庫存: ${rcVO.rc_storage}</label>
	                                    
						            </c:otherwise>
						        </c:choose>                                                                                                                                  
                                </div>                            
                            </div>
                        </div>
                    </div>
                </div>

                <div class="products-details-tabs">
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item"><a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab" aria-controls="description">Description</a></li>
                        <li class="nav-item"><a class="nav-link" id="reviews-tab" data-toggle="tab" href="#reviews" role="tab" aria-controls="reviews">Reviews</a></li>
                    </ul>

                    <div class="tab-content" id="myTabContent">
                        <div class="tab-pane fade show active" id="description" role="tabpanel">
                            <h2>商品資訊</h2>               
                            <p>${rcVO.rc_detail}</p>                   
                        </div>

                        <div class="tab-pane fade" id="reviews" role="tabpanel">
                            <div class="products-review-form">
                                <h3>使用者評價
                                <%if(prlist.size()!=0){ %>
                                	(<%=prlist.size()%>)
                                <%} %>        
                                </h3>
                                <div class="review-title">
                                    <div class="rating">
                                    	<%if(score_avg!=0) {%>
											(<%=score_avg%>/5.0)
												<%for(int i=1;i<=Math.round(score_avg);i++){ %>
				                                   <i class='bx bxs-star'></i>
				                                <%} %>  
										<%}%>                                    
                                    </div>
                                </div>
                                                                                           
                                <%if(prlist.size()==0){ %>
                                	目前尚無評價
                                <%} %>
                                <%if(prlist.size()!=0){ %>
								<%for(ProductReviewsVO prVO: prlist){ %>
                                <div class="review-comments">
                                    <div class="review-item" style="padding-top:0px">	
                                        <div class="rating">
                                        <%if(prVO.getPr_images()!=null) {%>
                                    		<img src="<%=request.getContextPath()%>/back_end/pr/pr.do?action=showPrImg&pr_no=<%=prVO.getPr_no()%>" 
                                    		style="height:70px" alt="image">
                                    	<%} %> 
                                    	
                                        <%for(int i=1;i<=prVO.getPr_score();i++){ %>
                                            <i class='bx bxs-star'></i>
                                        <%} %> 
                                        <div class="icon dripicons-wrong" align="right">&nbsp;&nbsp;
                                        <a href="javascript:showRepPage(<%=prVO.getPr_no()%>)">檢舉</a></div>            
                                        </div>
                                        <h3><%=prVO.getPr_content() %></h3>
                                        <span><strong><%=roSvc.getOneRentalOrder(prVO.getRo_no()).getRo_return_date() %></strong></span>
                                    
                                    </div>                       
                                </div>
								<%} %>
								<%} %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Products Details Area -->
       
        <!-- Start Related Products Area -->
        <section class="arrivals-products-area pt-50 pb-20">
            <div class="container">
                <div class="section-title">
                    <h2>相關商品</h2>
                </div>
                
                <div class="row">
                	<%if(rc_related_List.size()==1){ %>
                		&nbsp;&nbsp;&nbsp;&nbsp;目前無相關商品
                	<%} else {%>
                	<%for(int i=0;i<rc_related_List.size();i++){%>
                	<%RentalClassVO rc_related_ListVO = rc_related_List.get(i);%>
                	<%if(!rc_related_ListVO.getRc_no().equals(rc_no)){ %>
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-arrivals-products">
                            <div class="arrivals-products-image">
                                <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rc_related_ListVO.getRc_no()%>">
                                <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=<%=rc_related_ListVO.getRc_no()%>" alt="image"></a>
                                <ul class="arrivals-action">
                                    <li>
                                        <a href="cart.html" data-tooltip="tooltip" data-placement="top" title="Add to Cart">
                                            <i class="flaticon-shopping-cart"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>

                            <div class="arrivals-products-content">
                                <h3>
                                    <a href="<%=request.getContextPath()%>/back_end/rc/rc.do?action=showRcDetail&rc_no=<%=rc_related_ListVO.getRc_no()%>">
                                    <%=rc_related_ListVO.getRc_name()%></a>
                                </h3>
                                <span>$<%=rc_related_ListVO.getRc_price()%></span>             
                                <%if(rc_related_ListVO.getRc_total_score()!=0){ %>
                                        	<%Integer related_pr_num = prSvc.getListbyRc(rc_related_ListVO.getRc_no()).size();%>                                      	
	                                     	<ul class="rating">
	                                     		<%for(int j=1;j<=Math.round(rc_related_ListVO.getRc_total_score()/related_pr_num);j++){ %>
	                                            <li><i class='bx bxs-star'></i></li>
	                                            <%}%>
	                                        </ul>                      
                                <%}%> 
                            </div>
                        </div>
                    </div>
                    <%}%>
                    <%}%>     	
                 <%}%>    
                </div>
            </div>
        </section>
        <!-- End Arrivals Products Area -->       
       
        
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
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
</script>
<script>
    function showRepPage(data){
    	document.open('<%=request.getContextPath()%>/back_end/rep/rep.do?pr_no='+data+'&action=showRepPage', '' ,'height=400,width=650,left=700,top=200,resizable=yes,scrollbars=yes');
   	}   
</script>		
</body>
</html>