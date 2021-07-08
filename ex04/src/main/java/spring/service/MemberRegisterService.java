package spring.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistingMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {  //서블릿 >   폼에서 입력받은 데이터를 DAO를 통해 데이터베이스에 저장    [중요]

	private MemberDao dao;
	
	@Autowired
	public MemberRegisterService(MemberDao dao) {
		this.dao = dao;
	}

	public void regist(RegisterRequest req) {
		//MemberDao dao = new MemberDao();
		
		Member member = dao.selectByEmail(req.getEmail());
		if(member != null) {
			throw new AlreadyExistingMemberException("이메일 중복 : "+req.getEmail());
		}
		Member newMember = new Member(
				req.getEmail(),req.getPassword(),req.getName(),new Date());
		
		dao.insert(newMember);
		
	}
}
