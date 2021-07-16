package spring.controller;

import org.springframework.beans.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.dao.MemberDao;
import spring.exception.MemberNotFoundException;
import spring.vo.Member;

@Controller
public class MemberDetailController {
	
	private MemberDao dao;

	public void setDao(MemberDao dao) { //스프링 빈으로 부터 주입
		this.dao = dao;
	}
	
	@RequestMapping("/member/detail/{code}")//   /member/detail?code=숫자
	public String detail(@PathVariable("code") Long memberId, Model model) {
		// 특정 누군가의 정보를 보고 싶은 것!!
		// 필요한 정보??  특정 누군가를 식별할 수 있는 정보 => 기본키(유니크키) : code=>memberId
		// 화면에 보여주고 싶은 정보?? 검색한 특정 누군가의 정보 (member)
		
		Member member = dao.selectByCode(memberId);
		
		if(member==null) {
			throw new MemberNotFoundException();
		}
		
		model.addAttribute("member",member);
		
		return "member/memberDetail";
	}
	
//	예외 처리 코드
//	try {
//		
//	}catch(MemberNotFoundException e) {
//		
//	}
	
	@ExceptionHandler(MemberNotFoundException.class)
	public String handlerMemberNotFoundException(MemberNotFoundException e) {
		return "member/noMember";
	}
	
	@ExceptionHandler(TypeMismatchException.class)
	public String handlerTypeMismatchException(TypeMismatchException e) {
		return "member/invalidate";
	}
	
	
	
}
