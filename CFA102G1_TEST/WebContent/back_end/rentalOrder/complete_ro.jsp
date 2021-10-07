<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.rentalOrder.model.*"%>

<%
	RentalOrderVO roVO = (RentalOrderVO) request.getAttribute("roVO");
%>
<jsp:useBean id="rcSvc" scope="page" class="com.rentalClass.model.RentalClassService" />
<jsp:useBean id="rplSvc" scope="page" class="com.rentalProductList.model.RentalProductListService" />
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>訂單完成/取消</title>

<style>
	form { display: inline; }
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
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
	margin-top: 1px;
	margin-bottom: 1px;
	text-align:center;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
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

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>訂單完成/取消</h3>
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
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" id="form2">
	<table id="ordertable">
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
			<th>訂單總價格:</th>
			<td><%=roVO.getRo_totalprice()%> 元</td>
			<th>押金:</th>
			<td><%=roVO.getRo_deposit()%> 元</td>
		</tr>
		<tr>
			<th>商品狀態:</th>
			<td>
				<select size="1" name="ro_product_status" id="ro_product_status">
				<option value="正常" ${(roVO.ro_product_status=="正常")?'selected':'' }>正常
				<option value="遺失" ${(roVO.ro_product_status=="遺失")?'selected':'' }>遺失
				<option value="毀損" ${(roVO.ro_product_status=="毀損")?'selected':'' }>毀損
				</select>
			</td>
			<th>押金狀態:</th>
			<td><%=(roVO.getRo_deposit_status()==null?"":roVO.getRo_deposit_status())%></td>
		</tr>
		<tr>
			<th>實際歸還時間:</th>
			<td colspan="3">
			<input name="ro_return_date" id="return_date" type="text" >
			</td>
		</tr>
		<tr>
			<th>歸還狀態:</th>
			<td><span id="returnstatus"></span>
			<input type="hidden" name="ro_return_status" id="return" value="">
			</td>
			<th>逾期(提前)天數:</th>
			<td>
			<span id="showday"></span>
			<input type="hidden" name="ro_delay_days" id="returndays" value="0" size="1">
			</td>
		</tr>
		<tr>
			<th>毀損扣除額:</th>
			<td><input type=${(roVO.ro_product_status=="毀損")?'text':'hidden'} name="ro_repaircost" size="5" id="ro_repaircost" value="0"/></td>
			<th>應歸還押金:</th>
			<td>
			<span id="returnmoney"></span>
			<input type="hidden" name="ro_return_deposit" size="1" id="ro_return_deposit" value="<%=roVO.getRo_deposit()%>"/>
			</td>
		</tr>
	</table>
	<br>
	<input type="hidden" name="action" value="Complete_RO">
	<input type="hidden" name="ro_no" value="<%=roVO.getRo_no()%>">
	<input type="submit" value="完成訂單" id="send" >
	</FORM>
<FORM>
<input onclick="window.close();" value="關閉視窗" type="button">
</FORM>
<br>
<br>
<input type="checkbox" id="cancelcheck">取消訂單
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/ro/ro.do" id="form1">
<input type="hidden" name="action" value="cancelRo">
<input type="hidden" name="ro_no" value="<%=roVO.getRo_no()%>">
<input type="hidden" value="取消訂單" id="cancelsend" >
</FORM>
<script>
        $.datetimepicker.setLocale('zh'); 
        $('#return_date').datetimepicker({
           timepicker: false,  
	       format: 'Y-m-d',
        });
        
        $('#form2').on('change',function(){
			if ($('#ro_repaircost').val()>=0){
				$('#send').removeAttr("disabled");
				if($('#return').val()=="提前"){
	        		var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val();
	        		$('#returndays').val(Math.abs($('#returndays').val()));
	        	}
	        	else{
	        		var money = <%=roVO.getRo_deposit()%>-$('#ro_repaircost').val()-2*$('#returndays').val()*<%=roVO.getRo_price()%>;        		
	        	}
				if(money<0)
					money=0;
	        	$('#ro_return_deposit').val(money);
	        	document.getElementById("returnmoney").innerHTML = money + " 元";
			}
			else{
				document.getElementById("returnmoney").innerHTML = '<font color="red">'+"輸入錯誤"+'</font>';
				$('#send').attr("disabled","disabled");
			}
        });
        
             
//         	根據商品狀態改變輸入項目
        $('#ro_product_status').on('change',function(){
        	if($("#ro_product_status :selected").val()=="毀損"){
        		productDamage();
        	}else if ($("#ro_product_status :selected").val()=="遺失"){
        		productLost();
        	}else {
        		productNormal();	
        	}
           	});	

        function productNormal(){
    		$('#return_date').attr("type","text");	//還原歸還日期選項
    		$('#return_date').val("");					//設定日期空字串
    		$('#ro_repaircost').attr("type","hidden");	//顯示毀損值輸入
    		$('#ro_repaircost').val(0);					//設定毀損值0
       		document.getElementById("showday").innerHTML = "";	//還原逾期天數顯示表格
    		document.getElementById("returnstatus").innerHTML = "";	//還原歸還狀態顯示表格
    		$('#returndays').val(0);	//設定逾期天數0
    		$('#return').val("");		//設定歸還狀態空字串	
        };
        function productLost(){
        	$('#return_date').attr("type","hidden");	//隱藏歸還日期選項
        	$('#ro_repaircost').attr("type","hidden");		//隱藏毀損額
        	$('#return_date').val("");						//設定歸還日期value
        	$('#returndays').val(0);						//設定逾期天數0
        	$('#return').val("遺失");							//設定歸還狀態空字串	
        	$('#ro_repaircost').val(<%=roVO.getRo_deposit()%>);   	//設定毀損金額等於押金
    		document.getElementById("showday").innerHTML = "";
			document.getElementById("returnstatus").innerHTML = "遺失";
        };
        function productDamage(){
    		$('#return_date').attr("type","text");	//還原歸還日期選項
    		$('#return_date').val("");					//設定日期空字串
    		$('#ro_repaircost').attr("type","text");	//顯示毀損值輸入
    		$('#ro_repaircost').val(0);					//設定毀損值0
       		document.getElementById("showday").innerHTML = "";	//還原逾期天數顯示表格
    		document.getElementById("returnstatus").innerHTML = "";	//還原歸還狀態顯示表格
    		$('#returndays').val(0);	//設定逾期天數0
    		$('#return').val("");		//設定歸還狀態空字串
        }; 			  
//         根據日期選擇改變
        $('#return_date').on('change',function(){
        	if($('#return_date').val()==""){
        		document.getElementById("showday").innerHTML = "";
        		document.getElementById("returnstatus").innerHTML = "";
        		$('#returndays').val(0);
        		$('#return').val("");
        	}else{
        		var Days = parseInt((new Date($('#return_date').val())-new Date("<%=roVO.getRo_endtime()%>"))/(1000*60*60*24));
				if (Days<0){
					document.getElementById("showday").innerHTML = Math.abs(Days) + " 天";
					document.getElementById("returnstatus").innerHTML = "提前";
					$('#returndays').val(Days);
					$('#return').val("提前");				
	
				}else if (Days ==0){
					document.getElementById("showday").innerHTML = Math.abs(Days) + " 天";
					$('#returndays').val(Days);
					document.getElementById("returnstatus").innerHTML = "準時";
					$('#return').val("準時");
				}else{
					document.getElementById("showday").innerHTML = Days + " 天";
					$('#returndays').val(Days);
					document.getElementById("returnstatus").innerHTML = "逾期";
					$('#return').val("逾期");
				} 		
        	}
        });
                          		        		        		
         $('#cancelcheck').click(function(){
        	if($('#cancelcheck').prop("checked"))
        		$('#cancelsend').attr("type","submit");
        	else
        		$('#cancelsend').attr("type","hidden");
         });  
         
         <c:if test="${closewindow!=null}">
	         window.onload=function (){
	        	 window.opener.location="<%=request.getContextPath()%>/back_end/rentalOrder/listRo.jsp";
	        	 window.close();
	        	 }    
         </c:if>

         	$(document).ready(function(){
         		$('input').attr('autocomplete', 'off');
         	});
</script>
</body>
</html>