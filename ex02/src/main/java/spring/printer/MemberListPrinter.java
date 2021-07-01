package spring.printer;

import java.util.Collection;

import spring.dao.MemberDAO;
import spring.vo.Member;

public class MemberListPrinter { //전체 회원 조회
	
	private MemberDAO dao;
	private MemberPrinter printer;
	
	
	public void setDao(MemberDAO dao) {
		this.dao = dao;
	}


	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}


	public void printAll() {
		Collection<Member> members = dao.selectAll();
		for(Member m:members) {
			printer.print(m);
		}
	}
}
