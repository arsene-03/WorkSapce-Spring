package spring.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import spring.bean.Client2;

public class Main02 {

	public static void main(String[] args) {
		// xml 설정파일 이용
		GenericXmlApplicationContext ctx = 
				new GenericXmlApplicationContext("classpath:ctxConf2.xml");
		
		Client2 c2 = ctx.getBean("client2",Client2.class);
		
		c2.send();
		
		ctx.close();

	}

}
