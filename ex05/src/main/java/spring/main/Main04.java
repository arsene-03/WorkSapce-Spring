package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import spring.bean.Client;
import spring.config.JavaConfigPrototype;

public class Main04 {

	public static void main(String[] args) {
		useXMl();
		System.out.println("=======================================");
		useJava();
	}

	private static void useJava() {
		AnnotationConfigApplicationContext ctx = new
				AnnotationConfigApplicationContext(JavaConfigPrototype.class);
		
		Client c1 = ctx.getBean("client",Client.class);
		Client c2 = ctx.getBean("client",Client.class);
		System.out.println("자바 설정파일을 이용한 프로토타입");
		System.out.println("(c1 == c2) =>"+(c1==c2));
	}

	private static void useXMl() {
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:ctxConfPrototype.xml");
		Client c1 = ctx.getBean("client",Client.class);
		Client c2 = ctx.getBean("client",Client.class);
		System.out.println("XML 설정파일을 이용한 프로토타입");
		System.out.println("(c1 == c2) =>"+(c1==c2));

	}

}
