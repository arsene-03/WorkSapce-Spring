package spring.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.config.JavaMainConfig;
import spring.printer.MemberInfoPrinter;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

public class Main01 {

	public static void main(String[] args) {
		// 스프링 설정 파일 불러오기 -xml
//		GenericXmlApplicationContext ctx1 = new GenericXmlApplicationContext("classpath:aaa.xml");
		
		//스프링 설정파일 불러오기 - java클래스
//		ApplicationContext ctx2 = new AnnotationConfigApplicationContext(JavaConfig.class);
		
		// java 설정 파일이 두개 이상 있는 경우 스프링 설정파일 불러오기
//		ApplicationContext ctx2 = 
//				new AnnotationConfigApplicationContext(ConfigPart1.class,ConfigPart2.class);
		
		//두개 이상 java설정파일을 하나로 합쳐서 설정불러오기
//		ApplicationContext ctx2 = 
//				new AnnotationConfigApplicationContext(ConfigPartMain.class);
		
		//java와 xml 서로 다른 설정파일을 하나로 합쳐서 설정 불러오기  (JAVA <= XML)
//		ApplicationContext ctx2 = 
//				new AnnotationConfigApplicationContext(JavaMainConfig.class);
		
		//java와 xml 서로 다른 설정파일을 하나로 합쳐서 설정 불러오기  (XML <= JAVA)
		ApplicationContext ctx2 = 
				new GenericXmlApplicationContext("classpath:main-config.xml");
		
		RegisterRequest regReq = new RegisterRequest();
		regReq.setName("홍길동");
		regReq.setEmail("hong@naver.com");
		regReq.setPassword("1234");
		regReq.setConfirmPassword("1234");
		
		// 회원 가입 
		MemberRegisterService regSvc = ctx2.getBean("memberRegSvc",MemberRegisterService.class);
		
		regSvc.regist(regReq);// 회원 가입 기능 작동 => DAO에서 map에 등록
		
		//정보를 출력
		MemberInfoPrinter infoPrinter = ctx2.getBean("infoPrinter",MemberInfoPrinter.class);
		
		infoPrinter.printMemberInfo("hong@naver.com"); //이메일을 이용한 회원 정보 출력
		
	}

}
