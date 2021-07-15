package spring.service;

import java.util.Date;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistionMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {

	private MemberDao dao;// = new MemberDao();
	
	public MemberRegisterService(MemberDao dao) {
		this.dao = dao;
	}

	public void regist(RegisterRequest req) {  //회원 가입 기능
		System.out.println("출력 여부 확인6");
		Member m = dao.selectByEmail(req.getEmail());
		System.out.println("m 이메일 : "+m.getEmail());
		
		if(m!=null) {
			throw new AlreadyExistionMemberException("이메일 중복 : "+req.getEmail());
		}
		
		Member newMember = new Member(
				req.getEmail(),req.getPassword(), req.getName(), new Date());
		
		dao.insertMember(newMember);		
	}
}
