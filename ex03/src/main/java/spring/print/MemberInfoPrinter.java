package spring.print;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter { //특정 회원 출력  
	
//	@Autowired(required = false)
	private MemberDao dao;
	private MemberPrinter printer;
	
//	@Autowired
//	public MemberInfoPrinter(MemberDao dao, @Qualifier("chk01") MemberPrinter printer) {
//		this.dao = dao;
//		this.printer = printer;
//	}

	@Resource(name="memberDao")
//	@Qualifier("값")
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
//
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

	
	
	public void printMemberInfo(String email) {
		Member member = dao.selectByEmail(email);
		
		if(member == null) {
			System.out.println("데이터 없음 ");
			return;
		}
		printer.print(member);
		System.out.println();
		
	}

}
