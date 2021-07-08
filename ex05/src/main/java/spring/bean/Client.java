package spring.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean,DisposableBean{//스프링 빈으로 등록 예정
	//						   빈 생성시 사용할 메서드,  빈 소멸시 사용할 메서드
	private String host;
	
	public void setHost(String host) {
		this.host = host;
		System.out.println("Client.setHost()메서드 동작");
	}
	
	public void send() {
		System.out.println("Client.send()메서드 동작 : "+host+"에게 보냄");
	}

	@Override
	public void destroy() throws Exception {
		// 빈이 소멸될 때 자동으로 실행되는 메서드
		System.out.println("Client.destroy()메서드 동작");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// 빈이 생성될때 자동으로 실행되는 메서드
		System.out.println("Client.afterPropertiesSet()메서드 동작");
	}
}

