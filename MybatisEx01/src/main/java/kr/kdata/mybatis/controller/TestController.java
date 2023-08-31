package kr.kdata.mybatis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.kdata.mybatis.service.TestService;

@Controller
@RequestMapping("/test") // 이제부터 만드는 주소는 앞에 모두 /test가 붙는다.
public class TestController {
	
	@Autowired
	private TestService testService; 

	@RequestMapping("/today")
	public String today(Model model) {
		model.addAttribute("today1", testService.getToday1());
		model.addAttribute("today2", testService.getToday2());
		model.addAttribute("double1", testService.getDouble(8));
		model.addAttribute("double2", testService.getDouble(23));
		model.addAttribute("mul", testService.getMul(12,73));
		model.addAttribute("result", testService.getSumMul(11,22));
		model.addAttribute("vo", testService.getSumMul2(22,57));
		return "test/test"; // templates/test/test.html
	}
}
