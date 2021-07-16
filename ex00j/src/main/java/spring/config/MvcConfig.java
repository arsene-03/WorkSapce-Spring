package spring.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc  //<mvc:annotation-driven/>
public class MvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable(); // <mvc:default-servlet-handler/>
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
//		<mvc:view-resolvers>
//			<mvc:jsp prefix="/WEB-INF/views/"/>
//		</mvc:view-resolvers>
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
		             // /WEB-INF/views/member/memberList.jsp
	}

//	<bean id="messageSource"	
//		class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
//		<property name="basenames">
//			<list>
//				<value>classpath:message/label</value>
//			</list>
//		</property>
//		<property name="defaultEncoding" value="UTF-8"/>
//	</bean>
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource msgSrc = 
				new ResourceBundleMessageSource();
		msgSrc.setBasename("message.label");
		msgSrc.setDefaultEncoding("UTF-8");
		return msgSrc;
	}
	
	
}
