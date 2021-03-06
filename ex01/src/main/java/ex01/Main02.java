package ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main02 {

	public static void main(String[] args) {
		// 스프링을 이용한 코드
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Greeter g = ctx.getBean("gt",Greeter.class);
		String msg = g.greet("홍길동");
		
		System.out.println(msg);
		
		ctx.close();
	}

}
