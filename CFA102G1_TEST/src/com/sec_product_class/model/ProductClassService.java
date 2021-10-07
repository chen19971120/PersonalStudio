package com.sec_product_class.model;

import java.util.List;

public class ProductClassService{
	private I_ProductClassDAO dao;
	
	public ProductClassService() {
		dao = new ProductClassDAO();
	}

	public ProductClassVO addProductClass(String spc_name) {
		
		ProductClassVO productClassVO = new ProductClassVO();
		productClassVO.setSpc_name(spc_name);
		productClassVO = dao.insert(productClassVO);
		return productClassVO;
	}

	public ProductClassVO updateProductClass(Integer spc_no, String spc_name) {
		ProductClassVO productClassVO = new ProductClassVO();
		productClassVO.setSpc_no(spc_no);
		productClassVO.setSpc_name(spc_name);
		dao.update(productClassVO);
		return productClassVO;
	}

	public void deleteProductClass(Integer spc_no) {
		dao.delete(spc_no);
	}

	public ProductClassVO getOneProductClass(Integer spc_no) {
		return dao.findByPK(spc_no);
	}

	public List<ProductClassVO> getAll() {
		return dao.getAll();
	}
}
