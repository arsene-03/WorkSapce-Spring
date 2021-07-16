package spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
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
	public String form(LoginCommand logincommand,
			@CookieValue(value="rememberEmail", required=false) Cookie rememberEmail) {
		if(rememberEmail != null) {
			logincommand.setEmail(rememberEmail.getValue());
			logincommand.setRememberEmail(true);
		}
		
		return "login/loginForm";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(
			LoginCommand logincommand, 
			Errors errors, 
			HttpSession session1, 
			HttpServletRequest req, 
			HttpServletResponse response) {
		// 검증
		new LoginCommandValidator().validate(logincommand, errors);
		
		if(errors.hasErrors()) { // 에러가 발견되었는가??
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = 
					authService.authenticate(logincommand.getEmail(), logincommand.getPassword());
		// 세션에 로그인 정보를 저장
			// 1번째 방법
//			session1.setAttribute("authInfo", authInfo);
			// 2번째 방법
			HttpSession session2 = req.getSession();
			session2.setAttribute("authInfo", authInfo);
			
			// 이메일 저장용 쿠키 생성
			Cookie rememberCookie = new Cookie("rememberEmail",logincommand.getEmail());
			
			rememberCookie.setPath("/");
			if(logincommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*365);
			}else {
				rememberCookie.setMaxAge(0);
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		}catch(IdPasswordNotMatchingException e) {// 이메일이 없거나 , 비밀번호가 틀렸을 경우
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}

	
}
