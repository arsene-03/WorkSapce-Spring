package spring.service;

import org.springframework.transaction.annotation.Transactional;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

public class ChangePasswordService {

	private MemberDao dao;

	public ChangePasswordService(MemberDao dao) {
		this.dao = dao;
	}

	@Transactional
	public void changePassword(String email, String oldPassword, String newPassword) {
		Member m = dao.selectByEmail(email);
		
		if(m==null) {
			throw new MemberNotFoundException();
		}
		
		m.changePassword(oldPassword, newPassword);
		
		dao.updateMember(m);
	}
	
	
	
}
