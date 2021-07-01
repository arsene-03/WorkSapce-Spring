package spring.printer;

import spring.dao.MemberDAO;
import spring.vo.Member;

public class MemberInfoPrinter { 	//특정 회원 정보 출력
	
	private MemberDAO dao;
	private MemberPrinter printer;
	
	



	public MemberInfoPrinter(MemberDAO dao, MemberPrinter printer) {
	
		this.dao = dao;
		this.printer = printer;
	}





	public void printMemterInfo(String email) {
		Member member= dao.selectByEmail(email);
		
		if(member == null) {
			System.out.println("데이터 없음");
			return;
		}
		printer.print(member);
		System.out.println();
	}
}
