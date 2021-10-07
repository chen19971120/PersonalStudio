package com.sec_order.controller;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.stream.Collectors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.sec_order.model.SecOrderService;
import com.sec_order.model.SecOrderVO;
import com.sec_order_list.model.SecOrderListService;
import com.sec_order_list.model.SecOrderListVO;
import com.sec_product_inform.model.ProductInformService;
import com.sec_product_inform.model.ProductInformVO;


/**
 * Application Lifecycle Listener implementation class checkPay_Sta
 *
 */
@WebListener
public class checkPay_Sta implements ServletContextListener {
	Timer timer;
	final static long secondsInDay = 86400000; //一天的ms

    public void contextDestroyed(ServletContextEvent sce)  { 
    	timer.cancel();
    }

    public void contextInitialized(ServletContextEvent sce)  { 
        timer = new Timer();
        timer.schedule(new checkSecOrder(), 1000 , secondsInDay); //86400000 = 24*60*60*1000(1天檢查一次)
         
    }
    
    class checkSecOrder extends TimerTask{
		public void run() {
			SecOrderService secOrderSvc = new SecOrderService();
			SecOrderListService secOrderListSvc = new SecOrderListService();
			ProductInformService productInformSvc = new ProductInformService();
			
			//取出付款狀態為"待付款"，且訂單狀態不是"取消訂單"的訂單。
			List<SecOrderVO> secOrderVOs = secOrderSvc.getAll()
													  .stream()
													  .filter(s -> s.getSo_pay_sta().equals("待付款"))
													  .filter(s -> !(s.getSo_sta().equals("取消訂單")))
													  .collect(Collectors.toList());
			
			for(SecOrderVO secOrderVO : secOrderVOs) {
				long currentTime = System.currentTimeMillis();
				long orderTime =   secOrderVO.getSo_purtime().getTime();
				
				//檢查訂單是否逾期付款(24小時)
				if( (currentTime - orderTime) >= secondsInDay) {					
					//取出該筆訂單的商品明細
					Integer So_no = secOrderVO.getSo_no();
					List<SecOrderListVO> secOrderListVOs = secOrderListSvc.getAll()
																		  .stream()
																		  .filter(s -> s.getSo_no().equals(So_no))
																		  .collect(Collectors.toList());
					//更新每個商品的狀態
					for(SecOrderListVO secOrderListVO : secOrderListVOs) {
						Integer Spi_no = secOrderListVO.getSpi_no(); //商品編號
						Integer quantity = secOrderListVO.getSol_proamot(); //購買數量
						
						ProductInformVO productInformVO = productInformSvc.getOneProductInform(Spi_no);
						
						if(productInformVO.getSpi_sta().equals("下架")) {
							productInformVO.setSpi_sta("上架"); //更新狀態
						}
						
						Integer currentStock = productInformVO.getSpi_stock();
						productInformVO.setSpi_stock(currentStock+quantity); //更新庫存
						
						productInformSvc.updateProductInform(
								productInformVO.getSpi_no(), productInformVO.getSpi_name(), productInformVO.getSpc_no(), 
								productInformVO.getSpi_content(), productInformVO.getSpi_pri(), productInformVO.getSpi_stock(), 
								productInformVO.getSpi_sta());
					}
					
					//更新訂單狀態為"取消訂單"
					secOrderVO.setSo_sta("取消訂單");
					
					secOrderSvc.updateSecOrder(
							secOrderVO.getSo_no(), secOrderVO.getSo_purtime(), secOrderVO.getMem_no(), secOrderVO.getSo_sta(), 
							secOrderVO.getSo_pay_sta(), secOrderVO.getSo_ship_sta(), secOrderVO.getCi_no(), secOrderVO.getSo_totalpri(), 
							secOrderVO.getSo_prodel(), secOrderVO.getSo_deladrs(), secOrderVO.getSo_paymthd(), secOrderVO.getSo_shipdate(), 
							secOrderVO.getSo_delcost(), secOrderVO.getSo_discount_price());
				}
			}
		}
    }
}
