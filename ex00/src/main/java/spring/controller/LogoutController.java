package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogoutController {

	@RequestMapping("/logout")
	public String logout(HttpSession session1,HttpServletRequest req) {
		session1.invalidate();
		
		HttpSession session2 = req.getSession();
		session2.invalidate();
		
		return "redirect:/main";
	}
}
