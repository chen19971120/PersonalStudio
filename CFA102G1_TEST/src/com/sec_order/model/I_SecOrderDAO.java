package com.sec_order.model;

import java.util.List;

import com.sec_order_list.model.SecOrderListVO;

public interface I_SecOrderDAO {
	public SecOrderVO insert(SecOrderVO secOrder);
	public void update(SecOrderVO secOrder);
	public void delete(Integer so_no);
	public SecOrderVO findByPK(Integer so_no);
	public List<SecOrderVO> findByMem_NO(Integer mem_no);
	public List<SecOrderVO> getAll();
	
	public SecOrderVO insertWithList(SecOrderVO secOrder, List<SecOrderListVO> secOrderListVOs);
}
