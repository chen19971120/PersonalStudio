package com.sec_order.model;

import java.sql.Timestamp;
import java.util.List;

import com.sec_order_list.model.SecOrderListVO;

public class SecOrderService{
	private I_SecOrderDAO dao;

	public SecOrderService() {
		dao = new SecOrderDAO();
	}
	
	public SecOrderVO insertSecOrder(Timestamp so_purtime, Integer mem_no, String so_sta, String so_pay_sta, 
			String so_ship_sta, Integer ci_no, Integer so_totalpri, String so_prodel, String so_deladrs,
			String so_paymthd, Timestamp so_shipdate, Integer so_delcost, Integer so_discount_price) {
		SecOrderVO secOrderVO = new SecOrderVO();
		
		secOrderVO.setSo_purtime(so_purtime);
		secOrderVO.setMem_no(mem_no);
		secOrderVO.setSo_sta(so_sta);
		secOrderVO.setSo_pay_sta(so_pay_sta);
		secOrderVO.setSo_ship_sta(so_ship_sta);
		secOrderVO.setCi_no(ci_no);
		secOrderVO.setSo_totalpri(so_totalpri);
		secOrderVO.setSo_prodel(so_prodel);
		secOrderVO.setSo_deladrs(so_deladrs);
		secOrderVO.setSo_paymthd(so_paymthd);
		secOrderVO.setSo_shipdate(so_shipdate);
		secOrderVO.setSo_delcost(so_delcost);
		secOrderVO.setSo_discount_price(so_discount_price);
		secOrderVO = dao.insert(secOrderVO);
	
		return secOrderVO;
	}

	public SecOrderVO updateSecOrder(Integer so_no, Timestamp so_purtime, Integer mem_no, String so_sta, String so_pay_sta, 
			String so_ship_sta, Integer ci_no, Integer so_totalpri, String so_prodel, String so_deladrs,
			String so_paymthd, Timestamp so_shipdate, Integer so_delcost, Integer so_discount_price ) {
		SecOrderVO secOrderVO = new SecOrderVO();
		
		secOrderVO.setSo_no(so_no);
		secOrderVO.setSo_purtime(so_purtime);
		secOrderVO.setMem_no(mem_no);
		secOrderVO.setSo_sta(so_sta);
		secOrderVO.setSo_pay_sta(so_pay_sta);
		secOrderVO.setSo_ship_sta(so_ship_sta);
		secOrderVO.setCi_no(ci_no);
		secOrderVO.setSo_totalpri(so_totalpri);
		secOrderVO.setSo_prodel(so_prodel);
		secOrderVO.setSo_deladrs(so_deladrs);
		secOrderVO.setSo_paymthd(so_paymthd);
		secOrderVO.setSo_shipdate(so_shipdate);
		secOrderVO.setSo_delcost(so_delcost);
		secOrderVO.setSo_discount_price(so_discount_price);
		dao.update(secOrderVO);
	
		return secOrderVO;		
	}

	public void deleteSecOrder(Integer so_no) {
		dao.delete(so_no);
	}

	public SecOrderVO getOneSecOrder(Integer so_no) {
		return dao.findByPK(so_no);
	}

	public List<SecOrderVO> getOneSecOrderByMem(Integer mem_no) {
		return dao.findByMem_NO(mem_no);
	}

	public List<SecOrderVO> getAll() {
		return dao.getAll();
	}
	
	public SecOrderVO insertSecOrderWithList(Timestamp so_purtime, Integer mem_no, String so_sta, String so_pay_sta, 
			String so_ship_sta, Integer ci_no, Integer so_totalpri, String so_prodel, String so_deladrs,
			String so_paymthd, Timestamp so_shipdate, Integer so_delcost, Integer so_discount_price, 
			List<SecOrderListVO> secOrderListVOs) {
		
		SecOrderVO secOrderVO = new SecOrderVO();
		
		secOrderVO.setSo_purtime(so_purtime);
		secOrderVO.setMem_no(mem_no);
		secOrderVO.setSo_sta(so_sta);
		secOrderVO.setSo_pay_sta(so_pay_sta);
		secOrderVO.setSo_ship_sta(so_ship_sta);
		secOrderVO.setCi_no(ci_no);
		secOrderVO.setSo_totalpri(so_totalpri);
		secOrderVO.setSo_prodel(so_prodel);
		secOrderVO.setSo_deladrs(so_deladrs);
		secOrderVO.setSo_paymthd(so_paymthd);
		secOrderVO.setSo_shipdate(so_shipdate);
		secOrderVO.setSo_delcost(so_delcost);
		secOrderVO.setSo_discount_price(so_discount_price);
		
		secOrderVO = dao.insertWithList(secOrderVO, secOrderListVOs);
		
		return secOrderVO;
	
	}

}
