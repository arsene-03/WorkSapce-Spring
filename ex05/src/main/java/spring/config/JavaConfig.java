package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.bean.Client2;

@Configuration
public class JavaConfig {//xml 대체

	@Bean(initMethod="connect",destroyMethod="close")
	public Client2 client2() {
		Client2 c2 = new Client2();
		c2.setHost("자바서버");
		return c2;
	}
}
