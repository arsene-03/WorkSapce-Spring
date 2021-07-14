package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.AuthService;
import spring.validator.LoginCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.LoginCommand;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private AuthService authService;

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String form(LoginCommand logincommand) {
		return "login/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(LoginCommand logincommand, Errors errors, HttpSession session1, HttpServletRequest session2) {
		//검증
		new LoginCommandValidator().validate(logincommand, errors);
		
		if(errors.hasErrors()) { //에러가 발견되었는가
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = 
					authService.authenticate(logincommand.getEmail(), logincommand.getPassword());
			// session에 로그인 정보를 저장
// 			1번쨰 방법
			session1.setAttribute("authInfo", authInfo);

			
			return "login/loginSuccess";
		} catch (IdPasswordNotMatchingException e) { // 이메일이 없거나, 비밀번호가 오류인 경우
			 errors.reject("idPasswordNotMatching");
			 return "login/loginForm";
		}
		
	
	}
	
	
	
	
}
