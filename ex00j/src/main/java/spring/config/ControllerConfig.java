package spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import spring.controller.ChangePwdController;
import spring.controller.LoginController;
import spring.controller.LogoutController;
import spring.controller.MemberDetailController;
import spring.controller.MemberListController;
import spring.controller.RegisterController;
import spring.controller.SurveyController;
import spring.dao.MemberDao;
import spring.interceptor.AuthCheckInterceptor;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.service.MemberRegisterService;

@Configuration
public class ControllerConfig extends WebMvcConfigurerAdapter{
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private AuthService authSvc;
	@Autowired
	private ChangePasswordService changePwdSvc;
	@Autowired
	private MemberDao memberDao;
	
	//<mvc:view-controller path="/main" view-name="main"/>
	//<mvc:view-controller path="/" view-name="main"/>
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/main").setViewName("main"); //  /main으로 들어오면  main.jsp로 연결
		registry.addViewController("/").setViewName("main"); //  / 으로 들어오면  main.jsp로 연결
	}
	
	
	@Bean //id="registerController"
	public RegisterController registerController() {
		RegisterController rc = new RegisterController();
		rc.setMemberRegisterService(memberRegSvc);
		return rc;
	}

	@Bean//id="surveyController"
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean//id="loginController"
	public LoginController loginController() {
		LoginController lc = new LoginController();
		lc.setAuthService(authSvc);
		return lc;
	}
	
	@Bean//id="logoutController"
	public LogoutController logoutController() {
		return new LogoutController();
	}
	
	@Bean//id="changePwdController"	
	public ChangePwdController changePwdController() {
		ChangePwdController cpc = new ChangePwdController();
		cpc.setChangePasswordService(changePwdSvc);
		return cpc;
	}
	@Bean//id="memberListController"	
	public MemberListController memberListController() {
		MemberListController mlc = new MemberListController();
		mlc.setDao(memberDao);
		return mlc;
	}
	
	@Bean//id="memberDetailController"	
	public MemberDetailController memberDetailController() {
		MemberDetailController mdc = new MemberDetailController();
		mdc.setDao(memberDao);
		return mdc;
	}

	
	//<mvc:interceptors>
//	1. 인터셉터를 '어디에' 적용할 것인가?
//	2. '어떤' 인터셉터를 사용할 것인가?
	
	@Bean 
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**");
	}
	
	
	
	
	
	
	
	
}
