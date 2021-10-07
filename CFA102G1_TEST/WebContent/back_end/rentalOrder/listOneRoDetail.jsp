<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.rentalOrder.model.*"%>

<%
	RentalOrderVO roVO = (RentalOrderVO) request.getAttribute("roVO");
%>
<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />
<jsp:useBean id="rplSvc" scope="page" class="com.rentalProductList.model.RentalProductListService" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>租賃訂單查看/修改</title>

<style>
  form { display: inline; }
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h3 {
    color: black;
    display: block;
    margin: 5px;
  }
</style>

<style>
  table {
	width: 500px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>租賃訂單明細</h3>
	</td></tr>
</table>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" name="form1">
<table id="ordertable">
	<tr>
		<th>訂單狀態:</th>
		<td><%=roVO.getRo_status()%></td>
		<th>會員編號:</th>
		<td><%=roVO.getMem_no()%></td>
	</tr>
	<tr>
		<th>訂單編號:<font color=red><b>*</b></font></th>
		<td><%=roVO.getRo_no()%></td>
		<th>租賃商品編號:</th>
		<td><%=roVO.getRpl_no()%></td>
	</tr>
	<tr>
		<th>租賃商品類別:</th>
		<td colspan="2">${rcSvc.getOneRentalClass(rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no).rc_name}</td>
		<td>${rcSvc.getOneRentalClass(rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no).rc_item}</td>
	</tr>

	<tr>
		<th>運送方式:</th>
		<td colspan="3">
			<%=roVO.getRo_ship_method()%>
		</td>
	</tr>
	<tr>
		<th>配送地址:</th>
		<td colspan="3">
		<%=roVO.getRo_ship_addrs()%>
		</td>
	</tr>
	<tr>
		<th>租賃開始時間:</th>
		<td><%=roVO.getRo_starttime()%></td>
		<th>租賃結束時間:</th>
		<td><%=roVO.getRo_endtime()%></td>
	</tr>
	<tr>
		<th>租賃天數:</th>
		<td><%=roVO.getRo_day()%> 天	</td>
		<th>租賃價格/每天:</th>
		<td><%=roVO.getRo_price()%> 元</td>

	</tr>
	<tr>
		<th>訂單金額:</th>
		<td><%=roVO.getRo_totalprice()%> 元</td>
		<th>押金:</th>
		<td><%=roVO.getRo_deposit()%> 元</td>
	</tr>
	<tr>
		<th>商品狀態:</th>
		<td><%=(roVO.getRo_product_status()==null?"":roVO.getRo_product_status())%></td>
		<th>押金狀態:</th>
		<td><%=(roVO.getRo_deposit_status()==null?"":roVO.getRo_deposit_status())%></td>
	</tr>
	<tr>
		<th>實際歸還時間:</th>
		<td colspan="3">${(roVO.ro_return_date=="1970-01-01")?"":roVO.ro_return_date}</tr>

	<tr>
		<th>歸還狀態:</th>
		<td><%=(roVO.getRo_return_status()==null?"":roVO.getRo_return_status())%></td>
		<th>逾期(提前)天數:</th>
		<td><%=(roVO.getRo_delay_days()==0?"":roVO.getRo_delay_days())%></td>
	</tr>
	<tr>
		<th>毀損扣除額:</th>
		<td><%=(roVO.getRo_repaircost()==0?"":roVO.getRo_repaircost())%></td>
		<th>實際歸還押金:</th>
		<td><%=(roVO.getRo_return_deposit()==0?"":roVO.getRo_return_deposit())%></td>
	</tr>
</table>
<br>
<%if(!(roVO.getRo_status().equals("取消")||roVO.getRo_status().equals("結束")||roVO.getRo_status().equals("結束-商品毀損")||roVO.getRo_status().equals("結束-商品遺失"))){ %>
<input type="hidden" name="action" value="getOne_For_Update">
<input type="hidden" name="ro_no" value="<%=roVO.getRo_no()%>">
<input type="submit" value="修改">
<%} %>
</FORM>
<FORM>
<input onclick="window.close();" value="關閉視窗" type="button">
</FORM>
<font style="color:red">(非必要請勿隨意修改)</font>
<script src="<%=request.getContextPath()%>/back_end/back_CSS_JS/assets/vendors/jquery/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$('input').attr('autocomplete', 'off');
	});
</script>
</body>
</html>