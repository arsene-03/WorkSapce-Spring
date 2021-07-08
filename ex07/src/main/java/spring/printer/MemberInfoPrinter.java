package spring.printer;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter { //특정 회원 출력  
	
	private MemberDao dao;
	private MemberPrinter printer;

	public MemberInfoPrinter(MemberDao dao, MemberPrinter printer) {
		this.dao = dao;
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
