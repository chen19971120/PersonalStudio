package com.sec_order_list.model;

import java.util.List;

public class SecOrderListService{
	private I_SecOrderListDAO dao;
	
	public SecOrderListService() {
		dao = new SecOrderListDAO();
	}
	
	public SecOrderListVO insertSecOrderList(Integer so_no, Integer spi_no, Integer sol_proamot, Integer sol_pri) {
		SecOrderListVO secOrderListVO = new SecOrderListVO();
		
		secOrderListVO.setSo_no(so_no);
		secOrderListVO.setSpi_no(spi_no);
		secOrderListVO.setSol_proamot(sol_proamot);
		secOrderListVO.setSol_pri(sol_pri);
		secOrderListVO = dao.insert(secOrderListVO);
		
		return secOrderListVO;
	}

	public SecOrderListVO updateSecOrderList(Integer sol_no, Integer so_no, Integer spi_no, Integer sol_proamot, Integer sol_pri) {
		SecOrderListVO secOrderListVO = new SecOrderListVO();
		
		secOrderListVO.setSol_no(sol_no);
		secOrderListVO.setSo_no(so_no);
		secOrderListVO.setSpi_no(spi_no);
		secOrderListVO.setSol_proamot(sol_proamot);
		secOrderListVO.setSol_pri(sol_pri);
		dao.update(secOrderListVO);
		
		return secOrderListVO;
	}

	public void deleteSecOrderList(Integer sol_no) {
		dao.delete(sol_no);
	}

	public SecOrderListVO getOneSecOrderList(Integer sol_no) {
		return dao.findByPk(sol_no);
	}

	public List<SecOrderListVO> getAll() {
		return dao.getAll();
	}
}
