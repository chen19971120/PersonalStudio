<%@page import="java.util.stream.Collectors"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.sec_product_inform.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<%
	List<ProductInformVO> list = (List<ProductInformVO>) request.getAttribute("afterFiterProduct");
	if(list == null){
		ProductInformService productInformSvc = new ProductInformService();
		list = productInformSvc.getAll()
							   .stream()
							   .filter(i -> i.getSpi_stock().intValue() > 0)
							   .filter(i -> i.getSpi_sta().equals("上架"))
								.collect(Collectors.toList());
	}
	pageContext.setAttribute("list",list);
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
		
		<style>

			.page-numbers-2 {
  			width: 35px;
 			height: 35px;
 			margin: 0 3px;
  			display: inline-block;
  			background-color: #ffffff;
  			line-height: 35px;
  			color: #292929;
  			-webkit-box-shadow: 0 2px 10px 0 #d8dde6;
          	box-shadow: 0 2px 10px 0 #d8dde6;
   			font-size: 14px;
   			font-weight: bold;
  			border-radius: 50px;
   			text-align: center;
			}
			
			.page-numbers-2.current, .page-numbers-2:hover, .page-numbers-2:focus {
  			background: #113366;
  			color: #ffffff;
 			-webkit-box-shadow: 0 2px 10px 0 #d8dde6;
        	box-shadow: 0 2px 10px 0 #d8dde6;
}
			
			
		</style>  
        
        <title>YSM3C - 二手租賃商城</title>

        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/favicon.png">
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

        <!-- Start Shop Area -->
        <section class="shop-area bg-ffffff pt-50 pb-50">
            <div class="container">
                <div class="row">
                
                	<%@ include file="page1.file" %> 
					<!-- Start 顯示單個商品 -->
                	<c:forEach var="productList" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
                    <div class="col-lg-3 col-sm-6">
                        <div class="single-shop-products">
                        
                            <div class="shop-products-image">
                                <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=${productList.spi_no}&action=showProductDetail">
									<img src="<%=request.getContextPath()%>/secProductImg/ProductImg.do?spi_no=${productList.spi_no}&action=showShopImage" alt="image" 
										style="width:250px;height:250px;">
                                </a>
                            </div>

                            <div class="shop-products-content">
                                <h3>
                                    <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?spi_no=${productList.spi_no}&action=showProductDetail">
                                    	${productList.spi_name}
                                    </a>
                                </h3>                                                            
                                <span>$<fmt:formatNumber type="number" maxFractionDigits="3" value="${productList.spi_pri}"/></span>                             
                            </div>
                            
                        </div>
                    </div>
					</c:forEach>
 					<!-- End 顯示單個商品 -->     
					
					<!-- Start 分頁 -->
                    <div class="col-lg-12 col-md-12">
                        <div class="pagination-area">  							
	  							<%for(int i=1 ; i<=pageNumber ; i++){ %>
									<!--將當前頁面按鈕設為藍底白字 -->
	  								<%if(i==whichPage) {%>
	  									<A href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?whichPage=<%=i%>&action=showAllProduct" class="page-numbers-2" style="background-color: #113366; color: #ffffff;"><%=i %></A>&nbsp;
	  								<%} else{%>
	  									<A href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?whichPage=<%=i%>&action=showAllProduct" class="page-numbers-2" ><%=i %></A>&nbsp;	  									
	  								<%} %>
	  							<%} %>
                        </div>
                    </div>
					<!-- End 分頁 -->           
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
</body>
</html>