package com.sec_product_inform.model;

import java.util.List;

public class ProductInformService{
	private I_ProductInformDAO dao;
	
	public ProductInformService() {
		dao = new ProductInformDAO();
	}
	
	public ProductInformVO insertProductInform(String spi_name, Integer spc_no, String spi_content, Integer spi_pri, 
								Integer spi_stock, String spi_sta) {
		ProductInformVO productInformVO = new ProductInformVO();
		
		productInformVO.setSpi_name(spi_name);
		productInformVO.setSpc_no(spc_no);
		productInformVO.setSpi_content(spi_content);
		productInformVO.setSpi_pri(spi_pri);
		productInformVO.setSpi_stock(spi_stock);
		productInformVO.setSpi_sta(spi_sta);
		productInformVO = dao.insert(productInformVO);
		
		return productInformVO;
	}

	public ProductInformVO updateProductInform(Integer spi_no, String spi_name, Integer spc_no, String spi_content, Integer spi_pri, 
						Integer spi_stock, String spi_sta) {
		ProductInformVO productInformVO = new ProductInformVO();
		
		productInformVO.setSpi_no(spi_no);
		productInformVO.setSpi_name(spi_name);
		productInformVO.setSpc_no(spc_no);
		productInformVO.setSpi_content(spi_content);
		productInformVO.setSpi_pri(spi_pri);
		productInformVO.setSpi_stock(spi_stock);
		productInformVO.setSpi_sta(spi_sta);
		dao.update(productInformVO);
		
		return productInformVO;
	}

	public void deleteProductInform(Integer spi_no) {
		dao.delete(spi_no);
	}

	public ProductInformVO getOneProductInform(Integer spi_no) {
		return dao.findByPK(spi_no);
	}

	public ProductInformVO getOneProductInformByName(String spi_name) {
		return dao.findBySPI_Name(spi_name);
	}

	public List<ProductInformVO> getAll() {
		return dao.getAll();
	}
}
