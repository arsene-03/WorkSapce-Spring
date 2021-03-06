package spring.service;

import spring.dao.MemberDAO;
import spring.exaption.MemberNotFoundExcaption;
import spring.vo.Member;

public class ChangePasswordService {
	
	private MemberDAO dao;
	
	public ChangePasswordService(MemberDAO dao) {
		this.dao = dao;
	}
	
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member m = dao.selectByEmail(email);
		
		if(m==null) {
			throw new MemberNotFoundExcaption();
		}
		m.changePassword(oldPassword, newPassword);
		
		dao.updateMember(m);
	}
}
