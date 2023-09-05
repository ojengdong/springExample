package kr.kdata.email.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping(value = {"/", "/index", "/home", "/main"})
	public String home(Model model) {
		model.addAttribute("serverTime", new Date());
		return "/index";
	}
	
//	우편번호 예제
	@GetMapping(value = {"/post"})
	public String post() {
		return "/post";
	}
}
