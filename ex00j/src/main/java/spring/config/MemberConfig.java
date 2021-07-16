package spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.dao.MemberDao;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
@EnableTransactionManagement   // <tx:annotation-driven transaction-manager="transactionManager"/>
public class MemberConfig {
	
	@Autowired
	private DataSource dataSource;
	
//	@Bean
//	public DataSource dataSource() {//id="dataSource"
//		ComboPooledDataSource ds = new ComboPooledDataSource();
//		try {
//			ds.setDriverClass("oracle.jdbc.OracleDriver");
//		} catch (PropertyVetoException e) {
//			e.printStackTrace();
//		}
//		ds.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:XE");
//		ds.setUser("greenJSP");
//		ds.setPassword("jsp1234");
//		return ds;
//	}
	
	@Bean //id="transactionManager" 
	public DataSourceTransactionManager transactionManager() {  
		DataSourceTransactionManager txMgr = new DataSourceTransactionManager();
		txMgr.setDataSource(dataSource);
		return txMgr;
	}
	
	
	@Bean//id="memberDao"
	public MemberDao memberDao() {
		return new MemberDao(dataSource);
	}
	
	@Bean//id="changePwdSvc"
	public ChangePasswordService changePwdSvc() {
		return new ChangePasswordService(memberDao());
	}
	
	@Bean//id="memberRegSvc"
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
			
	@Bean//id="authSvc"
	public AuthService authSvc() {
		AuthService as = new AuthService();
		as.setDao(memberDao());
		return as;
	}
}
