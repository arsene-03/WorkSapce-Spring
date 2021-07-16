package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DbDualPropertyConfig {

	@Configuration
	@Profile("real")
	@PropertySource("classpath:message/db.properties")
	public static class DataSourceReal{
		
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
		@Value("${db.driver}")
		private String driver;
		
		@Value("${db.real.jdbcUrl}")
		private String realJdbcUrl;
		@Value("${db.real.user}")
		private String realUser;
		@Value("${db.real.password}")
		private String realPassword;
		
		@Bean
		public DataSource dataSource() {//id="dataSource"
			ComboPooledDataSource ds = new ComboPooledDataSource();
			try {
				ds.setDriverClass(driver);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			ds.setJdbcUrl(realJdbcUrl);
			ds.setUser(realUser);
			ds.setPassword(realPassword);
			return ds;
		}
	}
	
	@Configuration
	@Profile("test")
	@PropertySource("classpath:message/db.properties")
	public static class DataSourceTest{
		
		@Bean
		public static PropertySourcesPlaceholderConfigurer propertyConfig() {
			return new PropertySourcesPlaceholderConfigurer();
		}
		
		@Value("${db.driver}")
		private String driver;
		
		@Value("${db.test.jdbcUrl}")
		private String testJdbcUrl;
		@Value("${db.test.user}")
		private String testUser;
		@Value("${db.test.password}")
		private String testPassword;
		
		@Bean
		public DataSource dataSource() {//id="dataSource"
			ComboPooledDataSource ds = new ComboPooledDataSource();
			try {
				ds.setDriverClass(driver);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			ds.setJdbcUrl(testJdbcUrl);
			ds.setUser(testUser);
			ds.setPassword(testPassword);
			return ds;
		}
	}
}
