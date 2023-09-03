package kr.kdata.board.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // 메인컨트롤러
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	// 시작 파일 지정
	@GetMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("serverTime", new Date());
		model.addAttribute("dbTime", 
				jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		return "index";
	}
	
}
