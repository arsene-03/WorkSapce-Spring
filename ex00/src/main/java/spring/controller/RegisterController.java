package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistionMemberException;
import spring.service.MemberRegisterService;
import spring.vo.RegisterRequest;

@Controller
public class RegisterController {
	
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

	@RequestMapping("/register/step1")
	public String handlerStep1() {
		return "register/step1";  // /WEB-INF/views/register/step1.jsp
	}
	
	// 값을 읽어오는 1번째 방법 httpServeltRequest 객체 사용 하는 방법
//	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST방식으로 전송되었을 때 처리할 코드
//	public String handlerStep2(HttpServletRequest request) {
//		
//		String agreeParam = request.getParameter("agree");
//		if(agreeParam==null || !agreeParam.equals("true")) {
//			return "register/step1"; 
//		}else {
//			return "register/step2";
//		}
//		  
//	}
	
	// 값을 읽어 오는 2번째 방법 @RequestParam 어노테이션 이용 방법
	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST방식으로 전송되었을 때 처리할 코드
	public String handlerStep2(@RequestParam(value="agree", defaultValue="false") Boolean agree) {
					//										넘어온 값이 없을 때 기본값으로 사용

		if(!agree) {
			return "register/step1"; 
		}else {
			return "register/step2";
		}
		  
	}
	
	// /register/step2   get방식으로 전송을 받았을 때 에러페이지를 보여주지 않고 정상적인 경로로 보내주는 법
	
	@RequestMapping(value="/register/step2",method=RequestMethod.GET)
	public String handlerStep2Get() {
//		return "register/step1";   주소창에는 /step2로 보여진다
		return "redirect:/register/step1";// register/step1 매핑을 시작한다.
		// 전체 주소를 작성할 수도 있다.
//		return "redirect:http://localhost:8090/ex00/register/step1";
	}
	
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법  1. httpServletRequest 객체를 사용하는 방법
//	@RequestMapping(value="/register/step3",method=RequestMethod.POST) 
//	public String handlerStep3_1(HttpServletRequest request) {
//		String email = request.getParameter("email");
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
//		
//		System.out.println("*****************");
//		System.out.println("email : "+email);
//		System.out.println("name : "+name);
//		System.out.println("password : "+password);
//		System.out.println("confirmPassword : "+confirmPassword);
//		System.out.println("*****************");
//		
//		return "register/step3";
//	}
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법 2.@RequestParam 어노테이션를 사용하는 방법
//	@RequestMapping(value="/register/step3",method=RequestMethod.POST) 
//	public String handlerStep3_2( 
//			@RequestParam(value="email") String email,
//			@RequestParam(value="name") String name,
//			@RequestParam(value="password") String password,
//			@RequestParam(value="confirmPassword") String confirmPassword) {
//		
//		System.out.println("*****************");
//		System.out.println("email : "+email);
//		System.out.println("name : "+name);
//		System.out.println("password : "+password);
//		System.out.println("confirmPassword : "+confirmPassword);
//		System.out.println("*****************");
//		
//		return "register/step3";
//	}
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법 3.이미 만들어둔 RegisterRequest 객체를 이용한 방법
	@RequestMapping(value="/register/step3",method=RequestMethod.POST) 
	public String handlerStep3_3(RegisterRequest regReq) { // 커맨드 객체 : 반드시 가져야할 메서드 : setter메서드
														//  넘겨받을 파라미터의 이름과 같은 이름을 가진 필드가 존재해야 함
//		String email = regReq.getEmail();
//		String name = regReq.getName();
//		String password = regReq.getPassword();
//		String confirmPassword = regReq.getConfirmPassword();
//		
//		System.out.println("*****************");
//		System.out.println("email : "+email);
//		System.out.println("name : "+name);
//		System.out.println("password : "+password);
//		System.out.println("confirmPassword : "+confirmPassword);
//		System.out.println("*****************");
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(AlreadyExistionMemberException e) {
			return "register/step2";
		}
		
	}
	
}
