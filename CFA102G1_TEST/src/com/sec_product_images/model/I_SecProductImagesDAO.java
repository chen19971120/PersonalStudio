package com.sec_product_images.model;

import java.util.List;

public interface I_SecProductImagesDAO {
	public SecProductImagesVO insert(SecProductImagesVO secProductImages);
	public void update(SecProductImagesVO secProductImages);
	public void delete(Integer spim_no);
	public SecProductImagesVO findByPk(Integer spim_no);
	public List<SecProductImagesVO> getAll();
}
