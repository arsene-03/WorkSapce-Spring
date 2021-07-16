package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.ChangePasswordService;
import spring.validator.ChangePwdCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.ChangePwdCommand;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	
	private ChangePasswordService changePasswordService;

	public void setChangePasswordService(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

	// 1. GET  => form 태그로 연결
	// 1.1 Model객체를 이용하는 방법
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		model.addAttribute("changePwdCommand",new ChangePwdCommand());
//		return "edit/changePwdForm";
//	}
	// 1.2 @ModelAttribute어노테이션를 이용하는 방법
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(@ModelAttribute("changePwdCommand")ChangePwdCommand changePwdCommand) {
//	
//		return "edit/changePwdForm";
//	}
	// 1.3 POST와 같은 커맨드 객체를 사용하는 경우 model을 생략할수 있다
	@RequestMapping(method=RequestMethod.GET)
	public String form(ChangePwdCommand changePwdCommand,HttpSession session) {
		
//		// 로그인이 되어 있는지 검증하는 코드를 작성
//		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
//		
//		if(authInfo==null) {//로그인이 안되어 있음
//			return "redirect:/login";
//		}
		// 인터셉터가 있다면 필요없는 기능
	
		return "edit/changePwdForm";
	}
	
	// 2. POST  => 서비스 기능 제공 
	@RequestMapping(method=RequestMethod.POST)
	public String submit(ChangePwdCommand changePwdCommand,Errors errors,HttpSession session) {
		
		new ChangePwdCommandValidator().validate(changePwdCommand, errors);
		
		if(errors.hasErrors()) {
			return "edit/changePwdForm";
		}
		
		AuthInfo authInfo = (AuthInfo)session.getAttribute("authInfo");
		
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(),
					changePwdCommand.getCurrentPassword(),
					changePwdCommand.getNewPassword());
			
			return "edit/changePwd";
		}catch(IdPasswordNotMatchingException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
		
	}
	
}
