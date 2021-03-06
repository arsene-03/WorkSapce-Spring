package spring.service;

import java.util.Date;

import spring.dao.MemberDAO;
import spring.exaption.AlreadyExistionMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {
	
	private MemberDAO dao; //new MemberDAO();
	
	
			
	
	public MemberRegisterService(MemberDAO dao) {
		super();
		this.dao = dao;
	}




	public void regist(RegisterRequest req) { //회원 가입 기능
		
		Member m =dao.selectByEmail(req.getEmail());
		
		if(m!=null) {
			throw new AlreadyExistionMemberException(req.getEmail()+"은 이미 사용중인 이메일 입니다.");
		}
		
		Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), new Date());
		
		dao.insertMember(newMember);
	}
}
