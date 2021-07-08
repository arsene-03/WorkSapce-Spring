package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.print.MemberInfoPrinter;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main01 {

	public static void main(String[] args) {
		ApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:appCTX01.xml");
		
		MemberRegisterService mRegSvc = ctx.getBean("memberRegSvc",MemberRegisterService.class);
		
		RegisterRequest req = new RegisterRequest();
		req.setEmail("hong@naver.com");
		req.setName("홍길동");
		req.setPassword("1234");
		req.setConfirmPassword("1234");
		
		mRegSvc.regist(req);  //회원 가입
		
		MemberInfoPrinter mInfoPrinter = ctx.getBean("infoPrinter",MemberInfoPrinter.class);
		
		mInfoPrinter.printMemberInfo("hong@naver.com");
	}

}
