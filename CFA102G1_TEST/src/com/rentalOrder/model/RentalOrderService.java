package com.rentalOrder.model;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import com.rentalProductList.model.*;

public class RentalOrderService {
	private I_RentalOrderDAO dao;
	
	public RentalOrderService() {
		dao = new RentalOrderDAO();
	}
	
	public RentalOrderVO insertRentalOrder(Integer mem_no, Integer rpl_no,String ro_ship_method,String ro_ship_addrs, 
			Date ro_starttime,Date ro_endtime, Integer ro_day, Integer ro_price, 
			Integer ro_totalprice, Integer ro_deposit) {
		RentalOrderVO rentalOrderVO = new RentalOrderVO();
		
		rentalOrderVO.setMem_no(mem_no);
		rentalOrderVO.setRpl_no(rpl_no);
		rentalOrderVO.setRo_ship_method(ro_ship_method);
		rentalOrderVO.setRo_ship_addrs(ro_ship_addrs);
		rentalOrderVO.setRo_starttime(ro_starttime);
		rentalOrderVO.setRo_endtime(ro_endtime);
		rentalOrderVO.setRo_day(ro_day);
		rentalOrderVO.setRo_price(ro_price);
		rentalOrderVO.setRo_totalprice(ro_totalprice);
		rentalOrderVO.setRo_deposit(ro_deposit);
		
		rentalOrderVO = dao.insert(rentalOrderVO);
		
		return rentalOrderVO;
	}
	
	public RentalOrderVO updateRentalOrder(Integer ro_no, Integer mem_no, Integer rpl_no, String ro_status,
			String ro_ship_method, String ro_ship_addrs, Date ro_starttime, Date ro_endtime, 
			Date ro_oncerentendtime, Date ro_return_date, Integer ro_day, Integer ro_price,
			Integer ro_totalprice, Integer ro_deposit, String ro_deposit_status, String ro_return_status, 
			String ro_product_status, Integer ro_repaircost, Integer ro_delay_days,Integer ro_return_deposit) 
	{
		RentalOrderVO rentalOrderVO = new RentalOrderVO();

		rentalOrderVO.setRo_no(ro_no);
		rentalOrderVO.setMem_no(mem_no);
		rentalOrderVO.setRpl_no(rpl_no);
		rentalOrderVO.setRo_status(ro_status);
		rentalOrderVO.setRo_ship_method(ro_ship_method);
		rentalOrderVO.setRo_ship_addrs(ro_ship_addrs);
		rentalOrderVO.setRo_starttime(ro_starttime);
		rentalOrderVO.setRo_endtime(ro_endtime);
		rentalOrderVO.setRo_oncerentendtime(ro_oncerentendtime);
		rentalOrderVO.setRo_return_date(ro_return_date);
		rentalOrderVO.setRo_day(ro_day);
		rentalOrderVO.setRo_price(ro_price);
		rentalOrderVO.setRo_totalprice(ro_totalprice);
		rentalOrderVO.setRo_deposit(ro_deposit);
		rentalOrderVO.setRo_deposit_status(ro_deposit_status);
		rentalOrderVO.setRo_return_status(ro_return_status);
		rentalOrderVO.setRo_product_status(ro_product_status);
		rentalOrderVO.setRo_repaircost(ro_repaircost);
		rentalOrderVO.setRo_delay_days(ro_delay_days);
		rentalOrderVO.setRo_return_deposit(ro_return_deposit);
		
		dao.update(rentalOrderVO);

		return rentalOrderVO;
	}
	
	public RentalOrderVO updateRentalOrder(RentalOrderVO roVO) 
	{
		dao.update(roVO);
		return roVO;
	}

	public void deleteRentalOrder(Integer ro_no) {
		dao.delete(ro_no);
	}

	public RentalOrderVO getOneRentalOrder(Integer ro_no) {
		return dao.findByPK(ro_no);
	}

	public List<RentalOrderVO> getAll() {
		return dao.getAll();
	}
	
	//依照rc_no找商品待租改成預約 並回傳商品編號  
	public Integer findOneRplForRent(Integer rc_no) {
		RentalProductListService rplSvc = new RentalProductListService();
		List<RentalProductListVO> rpllist = rplSvc.getAll().stream()
				.filter(e -> e.getRpl_status().equals("待租"))
				.filter(e -> e.getRc_no().equals(rc_no))
				.collect(Collectors.toList());
		if(rpllist.size()==0)
			return 0;
		RentalProductListVO rplVO = rpllist.get(0);
		rplVO.setRpl_status("預約");
		rplSvc.updateRentalProductListByVO(rplVO);
		return rplVO.getRpl_no();
	}
	
	//將訂單狀態未付款改成已付款,待出貨,  押金狀態改成已收取
	public void payForOrder(Integer ro_no) {
		RentalOrderVO roVO = dao.findByPK(ro_no);
		roVO.setRo_status("已付款,待出貨");
		roVO.setRo_deposit_status("已收取");
		dao.update(roVO);
	}
	
	//將訂單狀態已付款,待出貨改成租賃中, 租賃商品狀態預約改成租賃中
	public void changeOneRoStatusToOnRent(Integer ro_no) {
		RentalOrderVO roVO = dao.findByPK(ro_no);
		roVO.setRo_status("租賃中");
		dao.update(roVO);
		RentalProductListService rplSvc = new RentalProductListService();
		RentalProductListVO rplVO = rplSvc.getOneRentalProductList(roVO.getRpl_no());
		rplVO.setRpl_status("租賃中");
		rplSvc.updateRentalProductListByVO(rplVO);
	}
	//依照訂單狀態取得roVO list
	public List<RentalOrderVO> getListByRoStatus(String ro_status) {
		List<RentalOrderVO> list = dao.getAll().stream()
				.filter(e -> e.getRo_status().equals(ro_status))
				.collect(Collectors.toList());
		Collections.sort(list,Collections.reverseOrder());
		return list;
	}
	
	public List<RentalOrderVO> getListByMem_no(Integer mem_no) {
		List<RentalOrderVO> list = dao.getAll().stream()
				.filter(e -> e.getMem_no().equals(mem_no))
				.collect(Collectors.toList());
		Collections.sort(list,Collections.reverseOrder());
		return list;
	}
	
	//續租訂單付款
	public void payforProlongOrder(Integer ro_no) {
		RentalOrderVO roVO = dao.findByPK(ro_no);
		List<RentalOrderVO> list = dao.getAll().stream()
				.filter(e -> e.getRpl_no().equals(roVO.getRpl_no()))
				.filter(e -> e.getRo_status().equals("租賃中"))
				.collect(Collectors.toList());			
		RentalOrderVO roVO_old = list.get(0);
		roVO_old.setRo_oncerentendtime(roVO.getRo_starttime());		
		dao.update(roVO_old);
		roVO.setRo_status("續租-付款完成");
		dao.update(roVO);		
	}
	//取消訂單
	public void cancelOrder(Integer ro_no) {
		RentalOrderVO roVO = dao.findByPK(ro_no);
		roVO.setRo_status("取消");
		roVO.setRo_oncerentendtime(Date.valueOf("1970-01-01"));
		roVO.setRo_return_date(Date.valueOf("1970-01-01"));
		roVO.setRo_deposit_status("");
		roVO.setRo_return_status("");
		roVO.setRo_product_status("");
		roVO.setRo_repaircost(0);
		roVO.setRo_delay_days(0);
		roVO.setRo_return_deposit(0);
		dao.update(roVO);
		
		RentalProductListService rplSvc = new RentalProductListService();
		RentalProductListVO rplVO = rplSvc.getOneRentalProductList(roVO.getRpl_no());
		
		if("預約".equals(rplVO.getRpl_status())) {
			rplVO.setRpl_status("待租");		
			rplSvc.updateRentalProductListByVO(rplVO);
		} else if("租賃中".equals(rplVO.getRpl_status())) {
			List<RentalOrderVO> list = dao.getAll().stream()
					.filter(e -> e.getRpl_no().equals(roVO.getRpl_no()))
					.filter(e -> e.getRo_status().equals("租賃中"))
					.collect(Collectors.toList());
			if(list.size()==0) {
				rplVO.setRpl_status("整備");
				rplSvc.updateRentalProductListByVO(rplVO);
				return;
			}
			RentalOrderVO roVO_old = list.get(0);
			roVO_old.setRo_oncerentendtime(Date.valueOf("1970-01-01"));		
			dao.update(roVO_old);
		}
	}
	
	//計時器內容
	public void executeTimerTask() {
		dao.ProlongRoOnTime();
		dao.Cancelunpaidorder();
		dao.DelayChangeRoByDay();
		dao.DeadlineChangeRplandRo();
	}
	
}
