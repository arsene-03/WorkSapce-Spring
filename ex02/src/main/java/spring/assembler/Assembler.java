package spring.assembler;

import spring.dao.MemberDAO;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

public class Assembler { //필요한 기능 클래스를 가져다 사용할 수 있도록 준비는 클래스
							//스프링 : 컨테이너
	
	private MemberDAO dao;
	private MemberRegisterService regSvc;
	private ChangePasswordService pwdSvc;
	
	public Assembler() {
		dao = new MemberDAO();
		regSvc = new MemberRegisterService(dao);
		pwdSvc = new ChangePasswordService(dao);
	}

	public MemberDAO getDao() {
		return dao;
	}

	public MemberRegisterService getRegSvc() {
		return regSvc;
	}

	public ChangePasswordService getPwdSvc() {
		return pwdSvc;
	}
	
	
}
