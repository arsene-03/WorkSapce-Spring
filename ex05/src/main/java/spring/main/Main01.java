package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.bean.Client;

public class Main01 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:ctxConf.xml");
	// 스프링 컨테이너를 초기화
	//   => 빈 객체 생성 => 빈객체들의 의존성 주입 => 빈 객체의 초기화
	//    afterPropertiesSet()             setHost()	
	Client client = ctx.getBean("client",Client.class);
	//  만들어진 빈 객체를 사용하기 위해 불러오는 과정
	
	client.send();
	// 빈 객체를 사용함
	// send()
	
	ctx.close();
	// 컨테이너 종료  => 내부에 생성된 빈 객체 소멸
	//destroy()
	
	}

}
