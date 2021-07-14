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
		return "register/step1";  // /WEB-INF/views/register/step1.jsp
	}
	
	// 媛믪쓣 �씫�뼱�삤�뒗 1踰덉㎏ 諛⑸쾿 httpServeltRequest 媛앹껜 �궗�슜 �븯�뒗 諛⑸쾿
//	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST諛⑹떇�쑝濡� �쟾�넚�릺�뿀�쓣 �븣 泥섎━�븷 肄붾뱶
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
	
	// 媛믪쓣 �씫�뼱 �삤�뒗 2踰덉㎏ 諛⑸쾿 @RequestParam �뼱�끂�뀒�씠�뀡 �씠�슜 諛⑸쾿
//	@RequestMapping(value="/register/step2",method=RequestMethod.POST) // POST諛⑹떇�쑝濡� �쟾�넚�릺�뿀�쓣 �븣 泥섎━�븷 肄붾뱶
	@RequestMapping(value="/step2",method=RequestMethod.POST)
	public String handlerStep2(@RequestParam(value="agree", defaultValue="false") Boolean agree, Model model) {
					//										�꽆�뼱�삩 媛믪씠 �뾾�쓣 �븣 湲곕낯媛믪쑝濡� �궗�슜

		if(!agree) {
			return "register/step1"; 
		}else {
			model.addAttribute("formData",new RegisterRequest());
			// 빈 커맨드 객체를 생성해서 오류가 없도록 step2에 보내주어야 한다
			return "register/step2";
		}
		  
	}
	
	// /register/step2   get諛⑹떇�쑝濡� �쟾�넚�쓣 諛쏆븯�쓣 �븣 �뿉�윭�럹�씠吏�瑜� 蹂댁뿬二쇱� �븡怨� �젙�긽�쟻�씤 寃쎈줈濡� 蹂대궡二쇰뒗 踰�
	
//	@RequestMapping(value="/register/step2",method=RequestMethod.GET)
	@RequestMapping(value="/step2",method=RequestMethod.GET)
	public String handlerStep2Get() {
//		return "register/step1";   二쇱냼李쎌뿉�뒗 /step2濡� 蹂댁뿬吏꾨떎
		return "redirect:/register/step1";// register/step1 留ㅽ븨�쓣 �떆�옉�븳�떎.
		// �쟾泥� 二쇱냼瑜� �옉�꽦�븷 �닔�룄 �엳�떎.
//		return "redirect:http://localhost:8090/ex00/register/step1";
	}
	
	
	// step2�뿉�꽌 蹂대궡�삩 4媛쒖쓽 �뜲�씠�꽣瑜� 諛쏆븘二쇰뒗 諛⑸쾿  1. httpServletRequest 媛앹껜瑜� �궗�슜�븯�뒗 諛⑸쾿
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
	
	// step2�뿉�꽌 蹂대궡�삩 4媛쒖쓽 �뜲�씠�꽣瑜� 諛쏆븘二쇰뒗 諛⑸쾿 2.@RequestParam �뼱�끂�뀒�씠�뀡瑜� �궗�슜�븯�뒗 諛⑸쾿
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
	
	// step2�뿉�꽌 蹂대궡�삩 4媛쒖쓽 �뜲�씠�꽣瑜� 諛쏆븘二쇰뒗 諛⑸쾿 3.�씠誘� 留뚮뱾�뼱�몦 RegisterRequest 媛앹껜瑜� �씠�슜�븳 諛⑸쾿
	//@RequestMapping(value="/register/step3",method=RequestMethod.POST) 
	@RequestMapping(value="/step3",method=RequestMethod.POST)
	//public String handlerStep3_3(RegisterRequest regReq) { // 而ㅻ㎤�뱶 媛앹껜 : 諛섎뱶�떆 媛��졇�빞�븷 硫붿꽌�뱶 : setter硫붿꽌�뱶
		public String handlerstep3(@ModelAttribute("formData") RegisterRequest regReq, Errors errors) {
														//  �꽆寃⑤컺�쓣 �뙆�씪誘명꽣�쓽 �씠由꾧낵 媛숈� �씠由꾩쓣 媛�吏� �븘�뱶媛� 議댁옱�빐�빞 �븿
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
		
		//검증의 기준을 만들어 줘야함
		new RegisterRequestValidator().validate(regReq, errors);
		
		if(errors.hasErrors()) { // 검증 후 에러코드가 있는지??
			return "register/step2";
		}
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		}catch(AlreadyExistionMemberException e) {
			errors.rejectValue("email", "duplicate"); // 이메일이 중복이 됩니다.
			return "register/step2";
		}
		
	}
	
}
