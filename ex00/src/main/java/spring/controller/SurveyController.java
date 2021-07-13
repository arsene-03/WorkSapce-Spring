package spring.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import spring.survey.AnsweredData;
import spring.survey.Question;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private List<Question> createQuestions(){
		Question q1 = new Question("����� ������ �����ΰ���?",Arrays.asList("����","����Ʈ","Ǯ����"));
		Question q2 = new Question("���� ����ϴ� ���ߵ����� �����ΰ���?",Arrays.asList("��Ŭ����","���ڸ�����","VSCODE"));
		Question q3 = new Question("�ϰ� ���� ���� �����ּ���.");
		
		return Arrays.asList(q1,q2,q3); 
	}
	
////	@RequestMapping(value="survey",method=RequestMethod.GET)
//	@RequestMapping(method=RequestMethod.GET)
//	public String form(Model model) {
//		
//		List<Question> questions = createQuestions();
//		
//		model.addAttribute("questions",questions);
//		
//		return "survey/surveyForm";
//	}
	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		
		List<Question> questions = createQuestions();
		
		mav.addObject("questions",questions);
		mav.setViewName("survey/surveyForm");
		
		return mav;
	}
//	@RequestMapping(value="survey",method=RequestMethod.POST)
	@RequestMapping(method=RequestMethod.POST)
	public String submit(@ModelAttribute("ansData") AnsweredData data) {
		return "survey/submitted";
	}
}
