package spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.bean.Client;

@Configuration
public class JavaConfigPrototype {

	@Bean
	@Scope("prototype")
	public Client client() {
		Client c = new Client();
		c.setHost("프로토서버");
		return c;
	}
}
