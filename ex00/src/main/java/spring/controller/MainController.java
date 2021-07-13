package spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/")
	public String main2() {
		return "main";
	}
}
