package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.exception.IdPasswordNotMatchingException;
import spring.exception.MemberNotFoundException;
import spring.service.ChangePasswordService;

public class Main02 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:appCTX.xml");
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc",ChangePasswordService.class);
		
		try {
			cps.changePassword("hong@naver.com", "4321", "1234");
			System.out.println("암호 변경이 정상적으로 진행되었습니다.");
		}catch(MemberNotFoundException e) {
			System.out.println("회원 데이터가 존재하지 않습니다.");
		}catch(IdPasswordNotMatchingException e) {
			System.out.println("암호가 올바르지 않습니다.");
		}

		ctx.close();
	}

}
