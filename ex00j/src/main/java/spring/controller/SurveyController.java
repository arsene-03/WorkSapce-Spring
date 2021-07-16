package spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.survey.AnsweredData;
import spring.survey.Question;

@Controller
@RequestMapping("/survey")	// 컨트롤러 내부의 모든 메서드의 공통 경로를 묶어줄 때
public class SurveyController {
	
	private List<Question> createQuestions(){
		Question q1 = new Question("당신의 역할은 무엇인가요?",Arrays.asList("서버",	"프론트","풀스택"));
		Question q2 = new Question("자주 사용하는 개발도구는 무엇인가요?",Arrays.asList("이클립스","인텔리제이","VS코드"));
		Question q3 = new Question("하고 싶은 말을 적어주세요.");
		
		return Arrays.asList(q1,q2,q3);	// 컬렉션(배열)로 만들어주는 것
										// '이것이 자바다' 1권 참고
	}
	
//	@RequestMapping(value="survey",method=RequestMethod.GET)
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		// model : 데이터를 담아서 view에 전달해줄 객체(request.setAttribute)
//		List<Question> questions = createQuestions();
//		
//		model.addAttribute("questions",questions); // 뷰에 데이터를 보내기 위해 사용하는 객체 : model
//		
//		return "survey/surveyForm";	// 뷰의 경로와 파일명을 알려주는 객체 View
//	}
	
	// ModelAndView 객체를 사용하는 방법
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();	// Model객체와 View객체의 통합된 형태
		
		List<Question> questions = createQuestions(); // 질문 제작
		
		mav.addObject("questions",questions);		  // Model의 역할
		mav.setViewName("survey/surveyForm");  		  // View의 역할
		return mav;
	}
	
//	@RequestMapping(value="survey",method=RequestMethod.POST)
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
