<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
    
       <!-- Start Top Header Area -->
        <div class="top-header-area">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6">
                        <div class="top-header-content">
                           <a href="<%=request.getContextPath()%>/front_end/coupon_information/coupon_information.jsp">
                            <span>
                                <i class="flaticon-check"></i>
                               		優惠劵好康看這裡~
                            </span>
                           </a> 
                        </div>
                    </div>

                    <div class="col-lg-6 text-right">
                        <ul class="top-header-optional">
                        
                            <c:choose>
            <c:when test="${memberVO == null}">
                            
                            <li>
                                <span><a href="<%=request.getContextPath()%>/front_end/login/login.jsp">登錄</a> | <a href="<%=request.getContextPath()%>/front_end/login/register.jsp">註冊</a></span>
                            </li>
            </c:when>
            <c:otherwise>
                             <li>
                                <span><a href="<%=request.getContextPath()%>/front_end/member/memberInfo.jsp">${memberVO.mem_name} </a>您好 
                               
                             </span>
                             </li>
                            <li>
                            
                              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/member/member.do" >
                              
              
                               <span>
                                <input type="hidden" name="action" value="login_out">
                                <input type="submit" value="會員登出" class="btn btn-outline-light">
            
                               </span>
                             </form>
                             
                             </li>
            </c:otherwise>
        </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Top Header Area -->