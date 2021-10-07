<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>YSM-3C</title>
	
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="../back_CSS_JS/assets/css/bootstrap.css">
    <link rel="stylesheet" href="../back_CSS_JS/assets/vendors/iconly/bold.css">
    <link rel="stylesheet" href="../back_CSS_JS/assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
    <link rel="stylesheet" href="../back_CSS_JS/assets/vendors/bootstrap-icons/bootstrap-icons.css">
    <link rel="stylesheet" href="../back_CSS_JS/assets/css/app.css">
    <link rel="shortcut icon" href="../back_CSS_JS/assets/images/favicon.svg" type="image/x-icon">

</head>
<body>

	<!-- ****Start 漢堡選單**** -->
	<header class="mb-3">
		<a href="#" class="burger-btn d-block d-xl-none">
			<i class="bi bi-justify fs-3"></i>
		</a>
	</header>
	<!-- ****End 漢堡選單**** -->
	
	<!-- ****Start sidebar排版**** -->
    <div id="sidebar" class="active">
        <div class="sidebar-wrapper active">
        
        
        	<!-- ****Start右上方人物卡片**** -->
            <div class="card">
                <div class="card-body py-4 px-5">
                    <div class="d-flex align-items-center">
                        <div class="avatar avatar-xl">
                            <img src="../back_CSS_JS/assets/images/faces/1.jpg" alt="Face 1">
                        </div>
                        <div class="ms-3 name">
                            <h5 class="font-bold">管理員ID</h5>
                            <h6 class="text-muted mb-0">這行不知道要衝三小</h6>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ****End右上方人物卡片**** -->

            <!--****Start sidebar-menu**** -->
            <div class="sidebar-menu">
                <ul class="menu">

                    <li class="sidebar-item  ">
                        <a href="application-email.html" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>員工管理</span>
                        </a>
                    </li>
                    
                    <li class="sidebar-item  ">
                        <a href="application-email.html" class='sidebar-link'>
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
                                <a href="component-alert.html">管理二手商品</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="component-badge.html">管理二手商品訂單</a>
                            </li>

                            <li class="submenu-item ">
                                <a href="component-badge.html">管理活動資訊</a>
                            </li>   

                            <li class="submenu-item ">
                                <a href="component-badge.html">管理優惠券</a>
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
                                <a href="component-alert.html">管理租賃商品</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="component-badge.html">管理租賃訂單</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="component-badge.html">管理商品歸還</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="component-badge.html">租賃商品評價</a>
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
                                <a href="component-alert.html">管理估價案件</a>
                            </li>
                            
                            <li class="submenu-item ">
                                <a href="component-alert.html">回收重新上架</a>
                            </li>
                        </ul>
                    </li>                    
                    
                    <li class="sidebar-item  ">
                        <a href="application-email.html" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>維修案件管理</span>
                        </a>
                    </li>
                    
                    <li class="sidebar-item  ">
                        <a href="application-email.html" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>諮詢表單</span>
                        </a>
                    </li>                                  

                    <li class="sidebar-item  ">
                        <a href="application-email.html" class='sidebar-link'>
                            <i class="bi bi-envelope-fill"></i>
                            <span>商家資訊管理</span>
                        </a>
                    </li> 
                </ul>
            </div>
            <!--****End sidebar-menu**** -->

            <button class="sidebar-toggler btn x"><i data-feather="x"></i></button>

        </div>
    </div>
    <!-- ****End sidebar排版**** -->
    
    
    


	<script src="../back_CSS_JS/assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="../back_CSS_JS/assets/js/bootstrap.bundle.min.js"></script>
	<script src="../back_CSS_JS/assets/vendors/apexcharts/apexcharts.js"></script>
	<script src="../back_CSS_JS/assets/js/pages/dashboard.js"></script>
	<script src="../back_CSS_JS/assets/js/main.js"></script>	
</body>
</html>