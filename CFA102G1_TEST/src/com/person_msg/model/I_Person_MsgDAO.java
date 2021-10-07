package com.person_msg.model;

import java.util.List;


public interface I_Person_MsgDAO {
	
	public Person_MsgVO insert (Person_MsgVO person_msgvo);
	public void update (Person_MsgVO person_msgvo);
	public void delete (Integer pm_no);
    public Person_MsgVO findByPK(Integer pm_no);	
    public List<Person_MsgVO> findByMEM_NO(Integer mem_no);	
    public List<Person_MsgVO> getAll();

}
