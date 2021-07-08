package spring.service;

import java.util.Date;

import javax.annotation.Resource;

import spring.dao.MemberDao;
import spring.exception.AlreadyExistionMemberException;
import spring.vo.Member;
import spring.vo.RegisterRequest;

public class MemberRegisterService {

	@Resource(name="memberDao")
	private MemberDao dao;// = new MemberDao();
	
//	@Autowired(required = false)
//	@Resource(name="memberDao")    생성자에는 사용 불가
//	public MemberRegisterService(MemberDao dao) {
//		this.dao = dao;
//	}

	public void regist(RegisterRequest req) {  //회원 가입 기능
		Member m = dao.selectByEmail(req.getEmail());
		
		if(m!=null) {
			throw new AlreadyExistionMemberException("이메일 중복 : "+req.getEmail());
		}
		
		Member newMember = new Member(
				req.getEmail(),req.getPassword(), req.getName(), new Date());
		
		dao.insertMember(newMember);
		
	}
}
