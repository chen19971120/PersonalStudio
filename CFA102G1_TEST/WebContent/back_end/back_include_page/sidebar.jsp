<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
	<!-- ****Start 漢堡選單**** -->
	<header class="mb-3">
		<a href="#" class="burger-btn d-block d-xl-none">
			<i class="bi bi-justify fs-3"></i>
		</a>
	</header>
	
	<style>
	#div1{
	 float: right;
     left: -40%;
     position: relative;
     }
	</style>
	<!-- ****End 漢堡選單**** -->
	
	<!-- ****Start sidebar排版**** -->
    <div id="sidebar" class="active">
        <div class="sidebar-wrapper active">
        
			<!-- ****Start 叉叉按鈕**** -->
            <div class="sidebar-header">
                <div class="d-flex justify-content-between">
                    <div class="toggler">
                        <a href="#" class="sidebar-hide d-xl-none d-block"><i class="bi bi-x bi-middle"></i></a>
                    </div>
                </div>
            </div>
            <!-- ****End 叉叉按鈕**** -->
            
        	<!-- ****Start右上方人物卡片**** -->
            <div class="card">
                <div class="card-body py-4 px-5">
                    <div class="d-flex align-items-center">
                        <div class="avatar avatar-xl">
                            <img src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/images/faces/1.jpg" alt="Face 1">
                        </div>
                        <div class="ms-3 name">
                            <h5 class="font-bold">${sessionScope.staffVO.staff_name}</h5>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ****End右上方人物卡片**** -->

            <!--****Start sidebar-menu**** -->
            <div class="sidebar-menu">
                <ul class="menu">

                    <li class="sidebar-item  ">
                        <a href="<%=request.getContextPath()%>/back_end/login/select_page.jsp" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>員工管理</span>
                        </a>
                    </li>
                    
                   <li class="sidebar-item  ">
                        <a href="<%=request.getContextPath()%>/back_end/login/select_page_member.jsp" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>會員資料</span>
                        </a>
                    </li>                    

                    <li class="sidebar-item  has-sub">
                        <a href="#" class='sidebar-link'>
                            <i class="bi bi-stack"></i>
                            <span>二手商城管理</span>
                        </a>
                        <ul class="submenu ">
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/secProductInfo/select_page.jsp">管理二手商品</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/secOrder/secOrder_select_page.jsp">管理二手商品訂單</a>
                            </li>

                     

                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/coupon_information/select_page.jsp">管理優惠券</a>
                            </li>                                                           
                                                       
                        </ul>
                    </li>
					
					<li class="sidebar-item  has-sub">
                        <a href="#" class='sidebar-link'>
                            <i class="bi bi-stack"></i>
                            <span>租賃商城管理</span>
                        </a>
                        <ul class="submenu ">
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/rentalClass/listRc.jsp">管理租賃商品</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/rentalOrder/listRo.jsp">管理租賃訂單</a>
                            </li>
                            
                            
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/reportProductReviews/listRpr.jsp">租賃商品評價</a>
                            </li>                                                   
                        </ul>
                    </li>

                    <li class="sidebar-item  has-sub">
                        <a href="#" class='sidebar-link'>
                            <i class="bi bi-stack"></i>
                            <span>估價管理</span>
                        </a>
                        <ul class="submenu ">
                            <li class="submenu-item ">
                                <a href="<%=request.getContextPath()%>/back_end/appraisal_case/select_page.jsp">管理估價案件</a>
                            </li>
                            
                            
                        </ul>
                    </li>                    
                    
                    
                    
                    <li class="sidebar-item  ">
                        <a href="<%=request.getContextPath()%>/back_end/consultation/select_page.jsp" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>諮詢表單管理</span>
                        </a>
                    </li>                                  

                    <li class="sidebar-item  ">
                        <a href="<%=request.getContextPath()%>/back_end/store_information/select_page.jsp" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>商家資訊管理</span>
                        </a>
                    </li> 
                </ul>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/staff/staff.do" >
                <div id="div1">
              
             
               <input type="hidden" name="action" value="login_out">
               <input type="submit" value="員工登出" class="btn btn-primary">
            
                </div>
                </form>
            <!--****End sidebar-menu**** -->

            <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>
         </div>
        </div>
    </div>
    <!-- ****End sidebar排版**** -->