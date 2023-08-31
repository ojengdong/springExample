package kr.kdata.mybatis.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@GetMapping(value = {"/","/index","/main"})
	public String home(Model model) {
		model.addAttribute("today", new Date());
		model.addAttribute("dbTime",
				jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		return "index";
	}
}
