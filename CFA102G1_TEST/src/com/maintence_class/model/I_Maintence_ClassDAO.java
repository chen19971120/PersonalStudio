package com.maintence_class.model;

import java.util.List;


public interface I_Maintence_ClassDAO {
	
	public Maintence_ClassVO insert (Maintence_ClassVO maintence_classvo);
	public void update (Maintence_ClassVO maintence_classvo);
	public void delete (Integer mcl_no);
    public Maintence_ClassVO findByPK(Integer mcl_no);	
    public List<Maintence_ClassVO> getAll();

}
