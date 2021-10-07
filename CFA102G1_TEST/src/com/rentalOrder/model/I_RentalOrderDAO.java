package com.rentalOrder.model;

import java.util.List;

public interface I_RentalOrderDAO {
	public RentalOrderVO insert(RentalOrderVO RentalOrderVO);
	public void update(RentalOrderVO RentalOrderVO);
	public void delete(Integer ro_no);
	public RentalOrderVO findByPK(Integer ro_no);
	public List<RentalOrderVO> getAll();
	
	//計時器
	public void Cancelunpaidorder();
	public void ProlongRoOnTime();
	public void DelayChangeRoByDay();
	public void DeadlineChangeRplandRo();
}
