package com.sec_product_images.model;

import java.util.List;

public class SecProductImagesService {
	private I_SecProductImagesDAO dao;
	
	public SecProductImagesService() {
		dao = new SecProductImagesDAO();
	}
	
	public SecProductImagesVO insertSecProductImages(Integer spi_no, byte[] spim_img) {
		
		SecProductImagesVO secProductImagesVO = new SecProductImagesVO();
		
		secProductImagesVO.setSpi_no(spi_no);
		secProductImagesVO.setSpim_img(spim_img);
		secProductImagesVO = dao.insert(secProductImagesVO);
		return secProductImagesVO;
	}

	public SecProductImagesVO updateSecProductImages(Integer spim_no, Integer spi_no, byte[] spim_img) {
		
		SecProductImagesVO secProductImagesVO = new SecProductImagesVO();
		
		secProductImagesVO.setSpim_no(spim_no);
		secProductImagesVO.setSpi_no(spi_no);
		secProductImagesVO.setSpim_img(spim_img);
		dao.update(secProductImagesVO);
		return secProductImagesVO;
	}

	public void deleteSecProductImages(Integer spim_no) {
		dao.delete(spim_no);
	}

	public SecProductImagesVO getOneSecProductImages(Integer spim_no) {
		return dao.findByPk(spim_no);
	}

	public List<SecProductImagesVO> getAll() {
		return dao.getAll();
	}
}
