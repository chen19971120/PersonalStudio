package com.sec_product_inform.model;

import java.util.List;

public interface I_ProductInformDAO {
	public ProductInformVO insert(ProductInformVO productInformVO);
	public void update(ProductInformVO productInformVO);
	public void delete(Integer spi_no);
	public ProductInformVO findByPK(Integer spi_no);
	public ProductInformVO findBySPI_Name(String spi_name);
	public List<ProductInformVO> getAll();
}
