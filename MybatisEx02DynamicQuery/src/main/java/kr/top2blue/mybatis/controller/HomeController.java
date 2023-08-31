package kr.top2blue.mybatis.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.top2blue.mybatis.service.EmpService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private EmpService empService;
	
	@GetMapping(value = {"/","/index","/main"})
	public String home(Model model) {
		model.addAttribute("today", new Date());
		model.addAttribute("dbTime",
				jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		return "index";
	}
	
	@GetMapping("/list")
	public String list(
			@RequestParam(defaultValue = "")Integer no, 
			@RequestParam(defaultValue = "")String job, 
			Model model) {
		log.info("컨트롤러의 list 호출 : {}, {}", no, job);
		model.addAttribute("list", empService.selectAll(no, job));
		model.addAttribute("dept", empService.selectDeptNo());
		model.addAttribute("jobs", empService.selectJobs());
		model.addAttribute("no", no);
		model.addAttribute("job", job);
		return "list";
	}
}
