package spring.printer;

import spring.vo.Member;

public class MemberPrinter {
	
	public void print(Member member) {
		System.out.printf("회원정보 : 이메일 =%s, 이름=%s, 등록일=%tF \n",member.getEmail(),member.getName(),member.getRegisterDate());
	}
}
