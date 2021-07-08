package spring.main;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.dao.MemberDao;
import spring.printer.MemberPrinter;
import spring.vo.Member;

public class Main01 {
	
	private static MemberDao dao;

	public static void main(String[] args) {

		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:appCTX.xml");
		
		dao = ctx.getBean("dao",MemberDao.class);
		
		selectByEmail();
		selectAll();
		selectCnt();
		update();
		insert();
		selectAll();
		selectCnt();
		delete();
		selectAll();
		selectCnt();
		

	}
	

	private static void delete() {
		System.out.println("데이터 삭제");
		dao.deleteMember("park@naver.com");
		System.out.println("삭제가 완료되었습니다.");
		
	}


	private static void insert() {
		System.out.println("데이터 입력");
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		String str = dateFormat.format(new Date());
		
		Member m = new Member(str+"@naver.com","1234",str,new Date());
		
		dao.insertMember(m);
		System.out.println(m.getCode() + "행 데이터 추가");
	}


	private static void selectCnt() {
		System.out.println("전체 데이터 개수 세기");
		int total = dao.count();
		System.out.println("전체 데이터 수 : "+total);
		
	}


	private static void update() {
		System.out.println("비밀번호 변경");
		Member m = dao.selectByEmail("hong@naver.com");
		String oldPw = m.getPassword();

		String newPw;
		
		if(oldPw.equals("1234")) {
			newPw = "4321";
		}else if(oldPw.equals("4321")){
			newPw = "1234";
		}else {
			newPw = "1234";
		}
		m.changePassword(oldPw, newPw);
		
		dao.updateMember(m);
		System.out.println("암호변경 : "+oldPw+" >>> "+newPw);
		
	}

	private static void selectAll() {
		System.out.println("전체 데이터 조회");
		List<Member> memberList = dao.selectAll();
		for(Member m : memberList) {
			System.out.println(m.getCode()+" : "+m.getName() + " : "+m.getEmail() + " : "+m.getPassword());
		}
		
	}

	private static void selectByEmail() {
		System.out.println("이메일로 데이터 조회");
		String email = "hong@naver.com";
		Member m = dao.selectByEmail(email);
		
		if(m==null) {
			System.out.println(" 해당 이메일이 없습니다.");
			 return;
		}else {
			MemberPrinter p = new MemberPrinter();
			p.print(m);
		}
		
	}

}
