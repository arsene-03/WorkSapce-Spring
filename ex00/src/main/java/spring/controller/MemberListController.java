package spring.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.MemberDao;
import spring.vo.ListCommand;
import spring.vo.Member;

@Controller
public class MemberListController {

	private MemberDao dao;

	public void setDao(MemberDao dao) { //스프링 빈으로 부터 주입
		this.dao = dao;
	}
	
	@RequestMapping("/member/list")
	public String list(ListCommand listCommand,Errors errors,Model model) {
		
		if(errors.hasErrors()) {
			return "member/memberList"; // 에러 코드를 가져간다.
			// 날짜 패턴이 잘못되어서 발생하는 에러는 typeMisMatch.java.util.Date
		}
		
		if(listCommand.getFrom() !=null && listCommand.getTo() !=null) {
			List<Member> members= 
					dao.selectByRegDate(listCommand);
			
			model.addAttribute("members",members);
		}
		
		
		return "member/memberList";
	}
	
	
	
	
	
}
