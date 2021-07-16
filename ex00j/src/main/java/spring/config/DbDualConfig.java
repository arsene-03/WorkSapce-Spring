package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DbDualConfig {

	@Configuration
	@Profile("real")
	public static class DataSourceReal{
		@Bean
		public DataSource dataSource() {//id="dataSource"
			ComboPooledDataSource ds = new ComboPooledDataSource();
			try {
				ds.setDriverClass("oracle.jdbc.OracleDriver");
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			ds.setJdbcUrl("jdbc:oracle:thin:@db.interstander.com:41521:XE");
			ds.setUser("greenJSP");
			ds.setPassword("jsp1234");
			return ds;
		}
	}
	
	@Configuration
	@Profile("test")
	public static class DataSourceTest{
		@Bean
		public DataSource dataSource() {//id="dataSource"
			ComboPooledDataSource ds = new ComboPooledDataSource();
			try {
				ds.setDriverClass("oracle.jdbc.OracleDriver");
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
			ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
			ds.setUser("greenJSP");
			ds.setPassword("jsp1234");
			return ds;
		}
	}
}
