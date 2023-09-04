package kr.kdata.upload.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(value = {"/","/index","/home"})
	public String home(Model model) {
		model.addAttribute("today", new Date());
		return "index";
	}
}
