package com.rentalProductImages.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class TestRentalProductImagesJDBCDAO {
	public static void main(String[] args) throws IOException {
		I_RentalProductImagesDAO dao = new RentalProductImagesJDBCDAO();
		
		//新增
//		RentalProductImagesVO rpi1 = new RentalProductImagesVO();
//		rpi1.setRc_no(6);
//		byte[] pic = getPictureByteArray("C:/images/1.jpg");
//		rpi1.setRpi_img(pic);
//		rpi1 = dao.insert(rpi1);
//		System.out.println(rpi1.getRpi_no());
		
		//修改
		RentalProductImagesVO rpi1 = new RentalProductImagesVO();
		rpi1.setRpi_no(1);
		rpi1.setRc_no(2);
		byte[] pic = getPictureByteArray("C:/images/2jpg.jpg");
		rpi1.setRpi_img(pic);
		dao.update(rpi1);
		
		//刪除
//		dao.delete(1);
		
		//查詢byPK
//		RentalProductImagesVO rpi1 = dao.findByPK(2);
//		System.out.println(rpi1.getRc_no());
		//查詢All
//		List<RentalProductImagesVO> list = dao.getAll();
//		for(RentalProductImagesVO item:list)
//			System.out.println(item.getRc_no());
	}
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}
