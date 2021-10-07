package com.rentalProductImages.model;

import java.util.List;

public interface I_RentalProductImagesDAO {
	public RentalProductImagesVO insert(RentalProductImagesVO RentalProductImagesVO);
	public void update(RentalProductImagesVO RentalProductImagesVO);
	public void delete(Integer rpi_no);
	public RentalProductImagesVO findByPK(Integer rpi_no);
	public List<RentalProductImagesVO> getAll();
}
