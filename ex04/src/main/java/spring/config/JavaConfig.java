package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;
import spring.service.MemberRegisterService;

@Configuration    // 이 자바클래스는 스프링 설정파일입니다.라고 지정하는 어노테이션
public class JavaConfig {// 스프링 설정 파일로 사용할 자바 클래스

	@Bean
	public MemberDao dao() {  //빈 객체 등록
		return new MemberDao();
	}
//  <bean id="dao" class="spring.dao.MemberDao" />
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(dao());// 생성자를 통한 빈 객체 주입
												// 생성자를 통한 객체 주입은 자동주입기능이 불가능
		
//		MemberRegisterService svc;
//		return svc;
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setDao(dao());				//set 메서드를 통한 빈객체 주입
		//infoPrinter.setPrinter(printer()); 		// set메서드에는 자동 주입 기능이 활성화되어 있음
		
		return infoPrinter;
		
	}
	
	
	
	
	
	
}
