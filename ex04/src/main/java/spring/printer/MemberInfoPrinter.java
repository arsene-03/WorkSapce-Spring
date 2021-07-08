package spring.printer;

import org.springframework.beans.factory.annotation.Autowired;

import spring.dao.MemberDao;
import spring.vo.Member;

public class MemberInfoPrinter {  ///   [중요]

	private MemberDao dao;
	private MemberPrinter printer;
	
	@Autowired
	public void setDao(MemberDao dao) {
		this.dao = dao;
	}
	@Autowired
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}

	public void printMemberInfo(String email) {
		Member m = dao.selectByEmail(email);
		if(m == null) {
			System.out.println("데이터 없음 ");
			return;
		}
		printer.print(m);
		System.out.println();
	}
}
