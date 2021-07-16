package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.exception.AlreadyExistionMemberException;
import spring.service.MemberRegisterService;
import spring.validator.RegisterRequestValidator;
import spring.vo.RegisterRequest;

@Controller
@RequestMapping("/register")
public class RegisterController {
	private MemberRegisterService memberRegisterService;

	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}

//	@RequestMapping("/register/step1")
	@RequestMapping("/step1")
	public String handlerStep1() {
		return "register/step1";	// WEB-INF/views/register/step1.jsp
	}
	
	// 값을 읽어오는 첫번째 방법 : httpServletRequest 객체 사용 방법
//	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST방식으로 전송되었을 때 처리할 코드
//	public String handlerStep2(HttpServletRequest request) { // 서블릿에서 사용하는 방법하고 똑같다.
//		String agreeParam = request.getParameter("agree");
//		if(agreeParam==null || !agreeParam.equals("true")) {
//			return "register/step1";	// WEB-INF/views/register/step1.jsp
//		}else {
//			return "register/step2";
//		}
//	}
	
	// 값을 읽어오는 두번째 방법 : @RequestParam 어노테이션 이용 방법
//	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST방식으로 전송되었을 때 처리할 코드
	@RequestMapping(value="/step2",method=RequestMethod.POST)
	public String handlerStep2(
			@RequestParam(value="agree", defaultValue="false") Boolean agree,Model model) {
										// 넘어온 값이 없을 때 기본값으로 사용
		if(!agree) {
			return "register/step1";
		}else {
			
			model.addAttribute("formData",new RegisterRequest());
											// 빈 커맨드 객체를 생성해서 오류가 없도록 step2.jsp에 보내주어야 함
											// 입력받은건 없기 때문에 커맨드 객체는 안 만들어질 것
			return "register/step2";
		}
	}
	
	// /register/step2 get방식으로 전송 받았을 때 에러페이지를 보여주지 않고 정상적인 경로로 보내는 법
//	@RequestMapping(value="/register/step2",method=RequestMethod.GET)
	@RequestMapping(value="/step2",method=RequestMethod.GET)
	public String handlerStep2Get() {
//		return "register/step1";	// 이 방식의 단점 : 이렇게 보내면 주소창은 정상적으로 표현, 보여지는 페이지는 step1
		return "redirect:register/step1";	// register/step1 매핑 시작
		// 전체 주소를 작성할 수도 있다.
//		return "redirect:http://localhost:8090/ex00/register/step1";
	}
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법 : 1.httpServletRequest 객체 사용
//	@RequestMapping(value="/register/step3",method=RequestMethod.POST)
//	public String handlerStep3_1(HttpServletRequest request) {
//		String email = request.getParameter("email");
//		String name = request.getParameter("name");
//		String password = request.getParameter("password");
//		String confirmPwd = request.getParameter("confirmPassword");
//		System.out.println("*******************");
//		System.out.println("이메일 : "+email);
//		System.out.println("이름 : "+name);
//		System.out.println("비밀번호 : "+password);
//		System.out.println("재확인 : "+confirmPwd);
//		System.out.println("*******************");
//
//		return "register/step3";
//	}
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법 : 2.@RequestParam 어노테이션 사용
//		@RequestMapping(value="/register/step3",method=RequestMethod.POST)
//		public String handlerStep3_2(
//				@RequestParam(value="email") String email,
//				@RequestParam(value="name") String name,
//				@RequestParam(value="password") String password,
//				@RequestParam(value="confirmPassword") String confirmPassword
//				) {
//			
//			System.out.println("*******************");
//			System.out.println("이메일 : "+email);
//			System.out.println("이름 : "+name);
//			System.out.println("비밀번호 : "+password);
//			System.out.println("재확인 : "+confirmPassword);
//			System.out.println("*******************");
//			return "register/step3";
//		}
	
	// step2에서 보내온 4개의 데이터를 받아주는 방법 : 3. 이미 만들어둔 RegisterRequest 객체 이용
//	@RequestMapping(value="/register/step3",method=RequestMethod.POST)
//	public String handlerStep3_3(RegisterRequest regReq) {	// 커맨드 객체 : 반드시 가져야할 메서드 -> setter 메서드
															// 넘겨받을 파라미터의 이름과 같은 이름을 가진 것이 필요
	@RequestMapping(value="/step3",method=RequestMethod.POST)
	public String handlerStep(@ModelAttribute("formData") RegisterRequest regReq, Errors errors) {
//		String email = regReq.getEmail();
//		String name = regReq.getName();
//		String password = regReq.getPassword();
//		String confirmPassword = regReq.getConfirmPassword();
//		
//		System.out.println("*******************");
//		System.out.println("이메일 : "+email);
//		System.out.println("이름 : "+name);
//		System.out.println("비밀번호 : "+password);
//		System.out.println("재확인 : "+confirmPassword);
//		System.out.println("*******************");
		
		
		// 스프링이 알아서 검증 => 검증 기준 필요
		new RegisterRequestValidator().validate(regReq, errors);
		System.out.println("출력 여부 확인4");
		if(errors.hasErrors()) { // 검증 후 에러코드가 있는지 체크
			return "register/step2";
		}
		System.out.println("출력 여부 확인5");
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(AlreadyExistionMemberException e) {
			System.out.println("중복 에러 발생");
			errors.rejectValue("email", "duplicate");//이메일이 중복이 됩니다.
			return "register/step2";
		}		
	}
	
}
