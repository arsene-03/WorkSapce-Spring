package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import spring.dao.MemberDao;
import spring.service.MemberRegisterService;

@Configuration
@ImportResource("classpath:sub-config.xml")  //xml 설정파일을 가져와서 자바 설정 파일에 합치기
public class JavaMainConfig {

	@Bean
	public MemberDao dao() {  //빈 객체 등록
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(dao());// 생성자를 통한 빈 객체 주입
	}
}
