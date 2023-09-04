package kr.kdata.pds.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.kdata.pds.service.TestService;

@Controller // 메인컨트롤러
public class HomeController {

	@Autowired
	private TestService testService;
	
	// 시작 파일 지정
	@GetMapping(value = {"/","/index","/home"})
	public String home(Model model) {
		model.addAttribute("serverTime", new Date());
		model.addAttribute("dbTime", testService.getToday()); 
		return "index";
	}
	
}
