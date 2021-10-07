package com.person_msg.model;

import java.sql.Timestamp;
import java.util.List;

public class Person_MsgService {
	
	private I_Person_MsgDAO dao;
	
	public Person_MsgService() {
		dao = new Person_MsgDAO();
	}
	
	public Person_MsgVO insertPersonMsg(Integer mem_no,Timestamp pm_date,String pm_content,String pm_status) {
		
		Person_MsgVO personMsgVO = new Person_MsgVO();
		
		personMsgVO.setMem_no(mem_no);
		personMsgVO.setPm_date(pm_date);
		personMsgVO.setPm_content(pm_content);
		personMsgVO.setPm_status(pm_status);
		personMsgVO = dao.insert(personMsgVO);
		return personMsgVO;
	}

	public Person_MsgVO updatePersonMsg(Integer pm_no,Integer mem_no,Timestamp pm_date,String pm_content,String pm_status) {
		
		Person_MsgVO personMsgVO = new Person_MsgVO();
		
		personMsgVO.setPm_no(pm_no);
		personMsgVO.setMem_no(mem_no);
		personMsgVO.setPm_date(pm_date);
		personMsgVO.setPm_content(pm_content);
		personMsgVO.setPm_status(pm_status);
		dao.update(personMsgVO);
		
		return personMsgVO;
	}

	public void deletePersonMsg(Integer pm_no) {
		dao.delete(pm_no);
	}

	public Person_MsgVO getOnePersonMsg(Integer pm_no) {
		return dao.findByPK(pm_no);
	}
	
	public List<Person_MsgVO> getOnePersonMsgVOByMemNO(Integer mem_no){
		return dao.findByMEM_NO(mem_no);
	}

	public List<Person_MsgVO> getAll(){
		return dao.getAll();
	}


}
