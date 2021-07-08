package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import spring.dao.MemberDao;
import spring.service.MemberRegisterService;

@Configuration
@Import(ConfigPartSub.class) //다른 자바 설정파일을 가져와서 하나의 설정파일로 합치는 방법
//@Import({ConfigPartSub.class,ConfigPartThird.class})// 두개 이상 설정파일을 합칠때는 배열 형태로 합쳐야 한다.										
public class ConfigPartMain {
	
	@Bean
	public MemberDao dao() {  //빈 객체 등록
		return new MemberDao();
	}

	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(dao());// 생성자를 통한 빈 객체 주입
	}
}
