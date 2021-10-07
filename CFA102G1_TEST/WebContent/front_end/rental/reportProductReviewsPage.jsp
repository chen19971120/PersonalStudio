<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.productReviews.model.*"%>
<%@ page import="com.member.model.*"%>
<% 	
	MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
	ProductReviewsVO prVO = (ProductReviewsVO) session.getAttribute("prVO");
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
        
        <title>評價檢舉</title>

        <link rel="icon" type="image/png" href="../front_CSS_JS/assets/img/favicon.png">
<style>
.ptb-50 {
    padding-top: 10px;
    padding-bottom: 10px;
}

</style>

</head>
<body>

		<!-- Start Contact Area -->
        <section class="contact-area ptb-50">
            <div class="container">
                <div class="row">
                    <div class="col-lg-7 col-md-12">
                        <div class="contact-form">
                            <div class="tile">
                                <h4>評價檢舉-#<%=prVO.getPr_no() %></h4>
                        
                            </div>	
                            <form id="contactForm" METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/rep/rep.do">
                                <input type="hidden" name="action" value="insert">
                                <input type="hidden" name="mem_no" value="${memberVO.mem_no}">  
                                <input type="hidden" name="pr_no" value="<%=prVO.getPr_no()%>"> 
                                <div class="row">
                                	<div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>檢舉會員編號:#${memberVO.mem_no}</label>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                	<div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <label>檢舉原因:</label>
                                            <select name="rep_contentnum" id="rep_contentnum">
                                            	<option value="1">不實評價</option>
                                            	<option value="2">不適當內容</option>
                                            	<option value="3">其他</option>
                                            </select>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>
                                	
                                    <div class="col-lg-12 col-md-12">
                                        <div class="form-group">
                                            <textarea name="message" id="message" cols="20" rows="3" required data-error="Please enter your message"
                                            disabled="disabled" style="background-color:#E9ECEF" class="form-control"></textarea>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                    </div>                          

                                    <div class="col-1">     	
                                        <input type="submit" class="btn btn-primary" value="送出" style="padding:10">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>                
                </div>
            </div>
        </section>
        <!-- End Contact Area -->
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/js/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
	$('#rep_contentnum').on('change',function(){
		console.log($('#rep_contentnum').val())
		if($('#rep_contentnum').val()==3){
			$('#message').removeAttr("disabled");
			$('#message').removeAttr("style");
		}else{
			$('#message').attr("disabled","disabled");
			$('#message').attr("style","background-color:#E9ECEF");	
			$('#message').val("");
					
		}
	});
</script>
</body>
</html>