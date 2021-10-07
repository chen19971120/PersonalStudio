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
  h4 {
    color: blue;
    display: inline;
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

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" name="form1">
<table id="ordertable">
	<tr>
		<th>訂單狀態:</th>
		<td><%=roVO.getRo_status()%>
		<input type="hidden" name="ro_status" value="<%=roVO.getRo_status()%>" />
		</td>
		<th>會員編號:</th>
		<td><%=roVO.getMem_no()%>
		<input type="hidden" name="mem_no" value="<%=roVO.getMem_no()%>" />
		</td>
	</tr>
	<tr>
		<th>訂單編號:<font color=red><b>*</b></font></th>
		<td><%=roVO.getRo_no()%>
		<input type="hidden" name="ro_no" value="<%=roVO.getRo_no()%>" />
		
		</td>
		<th>租賃商品編號:</th>
		<td><%=roVO.getRpl_no()%>
		<input type="hidden" name="rpl_no" value="<%=roVO.getRpl_no()%>" />
		</td>
	</tr>
	<tr>
		<th>租賃商品類別:</th>
		<td colspan="2">${rcSvc.getOneRentalClass(rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no).rc_name}</td>
		<td>${rcSvc.getOneRentalClass(rplSvc.getOneRentalProductList(roVO.rpl_no).rc_no).rc_item}</td>
	</tr>

	<tr>
		<th>運送方式:</th>
		<td colspan="3">
			<input type="radio" id="selftake" name="ro_ship_method" value="自取" ${(roVO.ro_ship_method=="自取")?'checked':'' } >
			<label for="selftake">自取</label>
			<input type="radio" id="delivery" name="ro_ship_method" value="宅配" ${(roVO.ro_ship_method=="宅配")?'checked':'' } >
			<label for="delivery">宅配</label>
		</td>
	</tr>
	<tr>
		<th>配送地址:</th>
		<td colspan="3">
		<input type="TEXT" name="ro_ship_addrs" size="40" value="<%=roVO.getRo_ship_addrs()%>" />
		
		</td>
	</tr>
	<tr>
		<th>租賃開始時間:</th>
		<td>
		<input type="TEXT" name="ro_starttime" size="7" value="<%=roVO.getRo_starttime()%>" id="start_date" />
		</td>
		<th>租賃結束時間:</th>
		<td>
		<input type="TEXT" name="ro_endtime" size="7" value="<%=roVO.getRo_endtime()%>" id="end_date" />
		</td>
	</tr>
	<tr>
		<th>租賃天數:</th>
		<td>
		<span id="showroday"><%=roVO.getRo_day()%> 天</span>
		<input type="hidden" name="ro_day" value="<%=roVO.getRo_day()%>" id="ro_day" />
		</td>
		<th>租賃價格/每天:</th>
		<td>
		<input type="TEXT" name="ro_price" size="3" value="<%=roVO.getRo_price()%>" id="ro_price" /> 元
		</td>

	</tr>
	<tr>
		<th>訂單金額:</th>
		<td>
		<span id="showrototalprice"><%=roVO.getRo_totalprice()%> 元</span>
		<input type="hidden" name="ro_totalprice" id="ro_totalprice" value="<%=roVO.getRo_totalprice()%>" />
		</td>
		<th>押金:</th>
		<td>
		<input type="TEXT" name="ro_deposit" size="3" value="<%=roVO.getRo_deposit()%>" /> 元
		</td>
	</tr>
	<tr>
		<th>商品狀態:</th>
		<td>
			<select size="1" name="ro_product_status" id="ro_product_status">
				<option value="" ${(roVO.ro_product_status=="null")?'selected':'' }>
				<option value="正常" ${(roVO.ro_product_status=="正常")?'selected':'' }>正常
				<option value="遺失" ${(roVO.ro_product_status=="遺失")?'selected':'' }>遺失
				<option value="毀損" ${(roVO.ro_product_status=="毀損")?'selected':'' }>毀損
			</select>
		</td>
		<th>押金狀態:</th>
		<td>
			<select size="1" name="ro_deposit_status" id="ro_deposit_status">
				<option value="" ${(roVO.ro_deposit_status=="null")?'selected':'' }>
				<option value="已收取" ${(roVO.ro_deposit_status=="已收取")?'selected':'' }>已收取
				<option value="部分退回" ${(roVO.ro_deposit_status=="部分退回")?'selected':'' }>部分退回
				<option value="全額退回" ${(roVO.ro_deposit_status=="全額退回")?'selected':'' }>全額退回
				<option value="沒收" ${(roVO.ro_deposit_status=="沒收")?'selected':'' }>沒收
			</select>	
		</td>
	</tr>
	<tr>
		<th>實際歸還時間:</th>
		<td colspan="3">
		<input type="TEXT" name="ro_return_date" id="ro_return_date" size="7"	value="<%=(roVO.getRo_return_date()==null?"":roVO.getRo_return_date())%>"  />
		</td>
	</tr>
	<tr>
		<th>歸還狀態:</th>
		<td>
			<select size="1" name="ro_return_status" id="ro_return_status">
				<option value="" ${(roVO.ro_return_status=="null")?'selected':'' }>
				<option value="提前" ${(roVO.ro_return_status=="提前")?'selected':'' }>提前
				<option value="準時" ${(roVO.ro_return_status=="準時")?'selected':'' }>準時
				<option value="逾期" ${(roVO.ro_return_status=="逾期")?'selected':'' }>逾期
				<option value="遺失" ${(roVO.ro_return_status=="遺失")?'selected':'' }>遺失
			</select>	
		</td>
		<th>逾期(提前)天數:</th>
		<td>
		<input type="TEXT" name="ro_delay_days" id="ro_delay_days" size="1" value="<%=roVO.getRo_delay_days()%>" /></td>
	</tr>

	<tr>
		<th>毀損扣除額:</th>
		<td>
		<input type="TEXT" name="ro_repaircost" id="ro_repaircost" size="3" value="<%=roVO.getRo_repaircost()%>" />
		</td>
		<th>實際歸還押金:</th>
		<td>
		<input type="TEXT" name="ro_return_deposit" id="ro_return_deposit" size="3" value="<%=roVO.getRo_return_deposit()%>" />
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ro_no" value="<%=roVO.getRo_no()%>">
<input type="submit" value="送出">
<input type="reset" value="回復原狀">
</FORM>
<FORM>
<input onclick="window.close();" value="取消" type="button">
</FORM>
<font style="color:red">(非必要請勿隨意修改)</font>
</body>

<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front_end/front_CSS_JS/assets/css/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>
<script>
		<c:if test="${closewindow!=null}">
		window.onload=function (){
			 window.opener.location="<%=request.getContextPath()%>/back_end/rentalOrder/listRo.jsp";
			 window.close();
			 }    
		</c:if>


        $.datetimepicker.setLocale('zh'); // kr ko ja en
        $('#start_date').datetimepicker({
           format: 'Y-m-d',
           timepicker: false,   //timepicker: false,	       	       
        });
        $('#end_date').datetimepicker({
            format: 'Y-m-d',
            timepicker: false,   //timepicker: false,	   
         });

        $.datetimepicker.setLocale('zh'); 
        $('#ro_return_date').datetimepicker({
           timepicker: false,  
	       format: 'Y-m-d',
        });
        
     // 當選定租賃日期,計算租賃天數
        $('#start_date').on('change',function(){
        	if($('#start_date').val()&&$('#end_date').val()){
        		var start = new Date($('#start_date').val());
        		var end = new Date($('#end_date').val());
        		var Days = parseInt(1+Math.abs(end - start)/(1000*60*60*24));
        		var str = Days
        		$('#ro_day').val(Days)
        		var price = $('#ro_price').val() * Days
        		$('#ro_totalprice').val(price);
        		document.getElementById("showroday").innerHTML = Days + " 天";
        		document.getElementById("showrototalprice").innerHTML = price + " 元";
        	}
        	
        });
        	
        $('#end_date').on('change',function(){
        	if($('#start_date').val()&&$('#end_date').val()){
        		var start = new Date($('#start_date').val());
        		var end = new Date($('#end_date').val());
        		var Days = parseInt(1+Math.abs(end - start)/(1000*60*60*24));
        		$('#ro_day').val(Days)
        		var price = $('#ro_price').val() * Days
        		$('#ro_totalprice').val(price);
        		document.getElementById("showroday").innerHTML = Days + " 天";
        		document.getElementById("showrototalprice").innerHTML = price + " 元";
        		
        	if($('#ro_return_date').val()==""){
            	$('#ro_delay_days').val(0);
            	$('#ro_return_status').val("");
            }else{
            	var delayDays = parseInt((new Date($('#ro_return_date').val())-new Date("<%=roVO.getRo_endtime()%>"))/(1000*60*60*24));
    			if (delayDays<0){
    				$('#ro_delay_days').val(Math.abs(delayDays));
    				$('#ro_return_status').val("提前");
    				var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val();	
    				$('#ro_return_deposit').val(money);
    			}else if (delayDays >0){
    				$('#ro_delay_days').val(delayDays);
    				$('#ro_return_status').val("逾期");
    			}else{
    				$('#ro_delay_days').val(0);
    				$('#ro_return_status').val("準時");
    			}  	
            }    		
        }
        });
        $('#ro_return_date').on('change',function(){
        	if($('#ro_return_date').val()==""){
        		$('#ro_delay_days').val(0);
        		$('#ro_return_status').val("");
        	}else{
        		var Days = parseInt((new Date($('#ro_return_date').val())-new Date("<%=roVO.getRo_endtime()%>"))/(1000*60*60*24));
        		
        		if (Days<0){
					$('#ro_delay_days').val(Math.abs(Days));
					$('#ro_return_status').val("提前");		
					var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val();
					$('#ro_return_deposit').val(money);
				}else if (Days ==0){
					$('#ro_delay_days').val(0);
					$('#ro_return_status').val("準時");
					var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val();
					$('#ro_return_deposit').val(money);
				}else{
					$('#ro_delay_days').val(Days);
					$('#ro_return_status').val("逾期");
					var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val()-2*<%=roVO.getRo_price()%>*$('#ro_delay_days').val();
						if(money<0)
							$('#ro_return_deposit').val(0);
						else
							$('#ro_return_deposit').val(money);
				}  	
        	}
        });
        
        $('#ro_repaircost').on('change',function(){
        	if($('#ro_return_deposit').val()){
				var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val()-<%=roVO.getRo_price()%>*$('#ro_delay_days').val();
        		if(money<0)
        			$('#ro_return_deposit').val(0);
        		else
        			$('#ro_return_deposit').val(money);
        	}	
        });
        
        	$(document).ready(function(){
        		$('input').attr('autocomplete', 'off');
        	});
</script>
</html>