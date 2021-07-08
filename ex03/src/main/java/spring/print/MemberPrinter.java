package spring.print;

import spring.vo.Member;

public class MemberPrinter {
	
	private int num;
	
	public MemberPrinter(int num) {
		this.num = num;
	}

	public void print(Member member) {
		System.out.printf(
				"회원정보 : 이메일=%s, 이름=%s, 등록일=%tF \n",
				member.getEmail(),member.getName(),member.getRegisterDate());
		
		System.out.println("선택된 객체 번호 : "+num);
		
	}
}
