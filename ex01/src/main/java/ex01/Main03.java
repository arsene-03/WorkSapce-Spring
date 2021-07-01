package ex01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main03 {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		Greeter g1 = ctx.getBean("gt",Greeter.class);
		Greeter g2 = ctx.getBean("gt",Greeter.class);
		
		System.out.println("(g1==g2) : "+(g1==g2));

		ctx.close();


	}

}
