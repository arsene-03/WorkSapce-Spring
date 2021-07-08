package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.printer.MemberInfoPrinter;
import spring.printer.MemberPrinter;

@Configuration
public class JavaSubConfig {

	@Autowired
	private MemberDao dao;
	
	@Bean
	public MemberPrinter printer() {
		return new MemberPrinter();
	}

	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setDao(dao);				//set 메서드를 통한 빈객체 주입
		infoPrinter.setPrinter(printer()); 		// set메서드에는 자동 주입 기능이 활성화되어 있음
		
		return infoPrinter;
		
	}
}
