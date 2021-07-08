package spring.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.bean.Client2;
import spring.config.JavaConfig;

public class Main03 {

	public static void main(String[] args) {
		// java 설정 파일 사용
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		
		Client2 c2 = ctx.getBean("client2",Client2.class);
		
		c2.send();
		
		ctx.close();

	}

}
