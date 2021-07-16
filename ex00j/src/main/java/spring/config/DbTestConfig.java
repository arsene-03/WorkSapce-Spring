package spring.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@Profile("test")
public class DbTestConfig {

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
