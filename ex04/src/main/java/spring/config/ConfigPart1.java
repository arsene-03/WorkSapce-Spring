package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.dao.MemberDao;
import spring.service.MemberRegisterService;

@Configuration
public class ConfigPart1 {
	
	@Bean
	public MemberDao dao() {  //빈 객체 등록
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(dao());// 생성자를 통한 빈 객체 주입
	}
}
