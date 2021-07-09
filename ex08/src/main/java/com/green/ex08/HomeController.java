package com.green.ex08;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping("/hello") // hello.html로 요청이 오면 어떻게 처리할 것인가
	public String hello(Model model, @RequestParam(value="name", required=false)String name) {
		model.addAttribute("greeting",name+"님 안녕하세요");
		
		return "hello";
	}
	@RequestMapping("/hello2")
	public String hello2(Model model, @RequestParam(value="name", required=false)String name) {
		model.addAttribute("greeting",name+"님 안녕하세요");
		
		return "hello";
	}
}
