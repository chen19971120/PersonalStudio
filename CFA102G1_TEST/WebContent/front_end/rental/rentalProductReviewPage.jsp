<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.rentalClass.model.*"%>
<%@ page import="com.rentalProductList.model.*"%>
<%@ page import="com.rentalOrder.model.*"%>
<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />
<jsp:useBean id="rplSvc" scope="page" class="com.rentalProductList.model.RentalProductListService" />
<% 	
	Integer ro_no = (Integer) request.getAttribute("ro_no");
	RentalOrderService roSvc = new RentalOrderService();
	RentalOrderVO roVO = roSvc.getOneRentalOrder(ro_no);
	pageContext.setAttribute("roVO",roVO);
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

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
        
<style>
	.rating {
	  --dir: right;
	  --fill: gold;
	  --fillbg: rgba(100, 100, 100, 0.15);
	  --heart: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 21.328l-1.453-1.313q-2.484-2.25-3.609-3.328t-2.508-2.672-1.898-2.883-0.516-2.648q0-2.297 1.57-3.891t3.914-1.594q2.719 0 4.5 2.109 1.781-2.109 4.5-2.109 2.344 0 3.914 1.594t1.57 3.891q0 1.828-1.219 3.797t-2.648 3.422-4.664 4.359z"/></svg>');
	  --star: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 24 24"><path d="M12 17.25l-6.188 3.75 1.641-7.031-5.438-4.734 7.172-0.609 2.813-6.609 2.813 6.609 7.172 0.609-5.438 4.734 1.641 7.031z"/></svg>');
	  --stars: 5;
	  --starsize: 3rem;
	  --symbol: var(--star);
	  --value: 1;
	  --w: calc(var(--stars) * var(--starsize));
	  --x: calc(100% * (var(--value) / var(--stars)));
	  block-size: var(--starsize);
	  inline-size: var(--w);
	  position: relative;
	  touch-action: manipulation;
	  -webkit-appearance: none;
	}
	[dir="rtl"] .rating {
	  --dir: left;
	}
	.rating::-moz-range-track {
	  background: linear-gradient(to var(--dir), var(--fill) 0 var(--x), var(--fillbg) 0 var(--x));
	  block-size: 100%;
	  mask: repeat left center/var(--starsize) var(--symbol);
	}
	.rating::-webkit-slider-runnable-track {
	  background: linear-gradient(to var(--dir), var(--fill) 0 var(--x), var(--fillbg) 0 var(--x));
	  block-size: 100%;
	  mask: repeat left center/var(--starsize) var(--symbol);
	  -webkit-mask: repeat left center/var(--starsize) var(--symbol);
	}
	.rating::-moz-range-thumb {
	  height: var(--starsize);
	  opacity: 0;
	  width: var(--starsize);
	}
	.rating::-webkit-slider-thumb {
	  height: var(--starsize);
	  opacity: 0;
	  width: var(--starsize);
	  -webkit-appearance: none;
	}
	.rating, .rating-label {
	  display: block;
	  font-family: ui-sans-serif, system-ui, sans-serif;
	}
	.rating-label {
	  margin-block-end: 1rem;
	}
	
	/* NO JS */
	.rating--nojs::-moz-range-track {
	  background: var(--fillbg);
	}
	.rating--nojs::-moz-range-progress {
	  background: var(--fill);
	  block-size: 100%;
	  mask: repeat left center/var(--starsize) var(--star);
	}
	.rating--nojs::-webkit-slider-runnable-track {
	  background: var(--fillbg);
	}
	.rating--nojs::-webkit-slider-thumb {
	  background-color: var(--fill);
	  box-shadow: calc(0rem - var(--w)) 0 0 var(--w) var(--fill);
	  opacity: 1;
	  width: 1px;
	}
	[dir="rtl"] .rating--nojs::-webkit-slider-thumb {
	  box-shadow: var(--w) 0 0 var(--w) var(--fill);
	}
</style>        
        
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


 <!-- Start Checkout Area -->
		<section class="checkout-area ptb-50">
            <div class="container">
                  <form METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/pr/pr.do" enctype="multipart/form-data">    
                    <div class="row">
                    	<div class="col-lg-2 col-md-12">
                    	</div>
                    	<div class="col-lg-2 col-md-12">
                                <img src="<%=request.getContextPath()%>/rpi/DBGifReader?action=showRcFirstImg&id=${rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no}" alt="image"
               					style="max-width:100%" > 
                    	</div>
                        <div class="col-lg-4 col-md-12">
                        
                        <div class="billing-details">
                                <h3 class="title">評價</h3>
                        </div>           
                        <div>
                              <h6>訂單編號:#${roVO.ro_no}</h6>      
                        </div>
                          
                        <div class="card-content">
                        <div class="card-body">
                        
                                <div class="form-body">
                                    <div class="row">
                                        <div class="col-md-4">
                                            <label>評價分數</label>
                                        </div>
                                        <div class="col-md-8 form-group">
											<label class="rating-label">
											  <input
											    class="rating rating--nojs"
											    max="5"
											    step="1"
											    type="range"
											    value="5"
											    name = "pr_score"
											    >
											</label>
                                        </div>
                                        <div class="col-md-4">
                                            <label>評價內容</label>
                                        </div>
                                        <div class="col-md-8 form-group">
                                            <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="pr_content"></textarea>
                                        </div>
                                        <div class="col-md-4">
                                            <label>上傳圖片</label>
                                        </div>
                                        <div class="col-md-8 form-group">
                                             <input class="form-control form-control-sm" id="img1input" type="file" name="pr_images">
                                        </div>
                                        
                                        <div class="col-sm-12 d-flex justify-content-end">
                                            <input type="submit" class="btn btn-primary me-1 mb-1" value="送出評價">                                        
                                        	<input type="hidden" name="action" value="insert">
						                    <input type="hidden" name="ro_no" value="${roVO.ro_no}">
						                    <input type="hidden" name="rc_no" value="${rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no}">                                            
                                        </div>
                                    </div>
                                </div>
                        </div>
                    </div>                   
                        </div> 
                        <div class="col-lg-2 col-md-12"> 
                        	<img id="img1show" src="" />             
                    	</div>                       
						<div class="col-lg-2 col-md-12">
                    	</div>
                    </div>
                </form>
            </div>
        </section>
        <!-- End Checkout Area --> 	
		
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
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
</script>
<script>
img1input.onchange = evt => {
	  const [file] = img1input.files
	  if (file) {
		  img1show.src = URL.createObjectURL(file)
	  }
	  var img = document.getElementById("img1show");
	  img.setAttribute("max-width", "100%");
	}
</script>				
</body>
</html>