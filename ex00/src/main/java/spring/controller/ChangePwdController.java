package spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.exception.IdPasswordNotMatchingException;
import spring.service.AuthService;
import spring.service.ChangePasswordService;
import spring.validator.ChangePwdCommandValidator;
import spring.vo.AuthInfo;
import spring.vo.ChangePwdCommand;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
	
	private ChangePasswordService changePasswordService;

	/*
	 * public void setChangePasswordSvc(ChangePasswordService changePasswordService)
	 * { this.changePasswordService = changePasswordService; }
	 */
	public ChangePwdController(ChangePasswordService changePasswordService) {
		this.changePasswordService = changePasswordService;
	}

//  1-1 model 객체를 이용하는 방법
//	@RequestMapping(method = RequestMethod.GET)
//	public String form(Model model) {
//		model.addAttribute("changePwdCommand",new ChangePwdCommand());
//		return "edit/changePwdForm";
//	}
	
//	1-2 @ModelAttribute를 사용
//	@RequestMapping(method = RequestMethod.GET)
//	public String form(@ModelAttribute("changePwdCommand")ChangePwdCommand changePwdCommand) {
//		
//		return "edit/changePwdForm";
//	}
	
//	1-3 POST와 같은 커멘드 객체를 사용하는 경우 model로 생략할 수있다.
	@RequestMapping(method = RequestMethod.GET)
	public String form(ChangePwdCommand changePwdCommand) {
		
		return "edit/changePwdForm";
	}



	@RequestMapping(method = RequestMethod.POST)
	public String submit(ChangePwdCommand changePwdCommand, Errors errors, HttpSession session) {
		
		new ChangePwdCommandValidator().validate(changePwdCommand, errors);
		
		if(errors.hasErrors()) {
			return "edit/changePwd";
		}
		
		AuthInfo authInfo = (AuthInfo) session.getAttribute("AuthInfo");
		
		try {
			changePasswordService.changePassword(
					authInfo.getEmail(), 
					changePwdCommand.getCurrentPassword(), 
					changePwdCommand.getNewPassword());
			
			return "edit/changePwd";
		} catch (IdPasswordNotMatchingException e) {
			errors.rejectValue("currentPassword", "notMatching");
			return "edit/changePwdForm";
		}
	}
}
