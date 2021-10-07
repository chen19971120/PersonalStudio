<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import=" java.text.DecimalFormat"%>
<%@ page import="com.sec_product_class.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	Map<Integer, Integer> Quamap2 = (Map<Integer, Integer>) session.getAttribute("Quamap"); 
	Boolean flag = false;
	if(Quamap2 != null && Quamap2.size() > 0){
		flag = true;
	}
	
	ProductClassService productClassSvc = new ProductClassService();
	List<ProductClassVO> classList = productClassSvc.getAll();
	request.setAttribute("classList", classList);
%>


        <!-- Start 修改購物車顏色 -->
        <style>
            /* 購物車圖案顏色 */
            .middle-header-optional li a i {
                color: #292929;
            }
            
            /* 價格顏色 */
            .middle-header-optional li {
                color: #292929;
            }
        </style>
		<!-- End 修改購物車顏色 -->

        <!-- Start Navbar Area 導覽列-->
        <div class="navbar-area p-relative">
            <div class="main-responsive-nav">
                <div class="container">
                    <div class="main-responsive-menu">
                        <div class="logo">
                            <a href="<%=request.getContextPath()%>/front_end/index.jsp">
                                <img src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/logo.png" style="height:35px;width:92px;" alt="logo">
                            </a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="main-navbar">
                <div class="container">
                    <nav class="navbar navbar-expand-md navbar-light">
                        <a class="navbar-brand" href="<%=request.getContextPath()%>/front_end/index.jsp">
                            <img src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/img/logo.png" style="height:35px;width:92px;" alt="image">
                        </a>

                        <div class="collapse navbar-collapse mean-menu">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a href="<%=request.getContextPath()%>/front_end/index.jsp" class="nav-link active">首頁</a>
                                </li>

                                <li class="nav-item">
                                    <a href="<%=request.getContextPath()%>/front_end/rental/rentalProductList.jsp" class="nav-link">3C租賃</a>
                                </li>

                                <li class="nav-item">
                                    <a href="#" class="nav-link">
                                    	二手商城
                                    	<i class='bx bx-chevron-down'></i>
                                    </a>
                                    
                                    <ul class="dropdown-menu">
                                        <li class="nav-item">
                                            <a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?action=showAllProduct&spc_no=0" class="nav-link">所有商品</a>
                                        </li>
                                        
                                        <c:forEach var="classList" items="${classList}">
                                        	<li class="nav-item">
                                            	<a href="<%=request.getContextPath()%>/secProductInfo/ProductInfo.do?action=showAllProduct&spc_no=${classList.spc_no}" class="nav-link">
                                            		${classList.spc_name}
                                            	</a>
                                        	</li>
                                        </c:forEach>
                                    </ul>
                                </li>

                                <li class="nav-item">
                                    <a href="<%=request.getContextPath()%>/front_end/appraisal_case/appraisalCase.jsp" class="nav-link">回收估價</a>
                                </li>

                                <li class="nav-item">
                                    <a href="<%=request.getContextPath()%>/front_end/consultation/consult.jsp" class="nav-link">聯絡我們</a>
                                </li>
                            </ul>

                            <div class="others-option d-flex align-items-center">
								<ul class="middle-header-optional">
                                    <li>
                                        <a href="<%=request.getContextPath()%>/front_end/secProductInfo/cart.jsp">
                                        	<i class="flaticon-shopping-cart"></i>
                                        	
                                        	<span><%=flag?Quamap2.get(998):0%></span>
                                        </a>
                                    </li>
                                    <li>$<%=new DecimalFormat(",###").format(flag?Quamap2.get(999):0)%></li>
                                </ul>
                            </div>
                        </div>
                    </nav>
                </div>
            </div>

            <div class="others-option-for-responsive">
                <div class="container">
                    <div class="dot-menu">
                        <div class="inner">
                            <div class="circle circle-one"></div>
                            <div class="circle circle-two"></div>
                            <div class="circle circle-three"></div>
                        </div>
                    </div>
                    
                    <div class="container">
                        <div class="option-inner">
                            <div class="others-option d-flex align-items-center">
                                <div class="option-item">
                                    <span>
                                        Hotline:
                                        <a href="">(03)397-1234</a>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Navbar Area 導覽列-->