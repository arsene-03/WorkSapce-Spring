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
		//����
		new LoginCommandValidator().validate(logincommand, errors);
		
		if(errors.hasErrors()) { //������ �߰ߵǾ��°�
			return "login/loginForm";
		}
		try {
			AuthInfo authInfo = 
					authService.authenticate(logincommand.getEmail(), logincommand.getPassword());
			// session�� �α��� ������ ����
// 			1���� ���
			session1.setAttribute("authInfo", authInfo);

			
			return "login/loginSuccess";
		} catch (IdPasswordNotMatchingException e) { // �̸����� ���ų�, ��й�ȣ�� ������ ���
			 errors.reject("idPasswordNotMatching");
			 return "login/loginForm";
		}
		
	
	}
	
	
	
	
}
