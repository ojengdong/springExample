package kr.top2blue.JsonProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.top2blue.JsonProject.service.JsonService;
import kr.top2blue.JsonProject.vo.BoxOffice;
import kr.top2blue.JsonProject.vo.BoxOffice2;

@Controller
public class JsonController {

	@Autowired
	private JsonService jsonService;
	
	@GetMapping("/hanjas")
	public String hanjas(Model model) {
		model.addAttribute("list", jsonService.readDataVO());
		return "hanjas";
	}
	
//	일별 박스오피스
	@GetMapping("/boxOffice")
	public String boxOffice(@RequestParam(defaultValue = "20230101") String targetDt, Model model) {
		BoxOffice result = jsonService.getBoxOffice(targetDt);
		model.addAttribute("result", result.getBoxOfficeResult());
		return "boxOffice";
	}
	
//	주간/주말 박스오피스
	@GetMapping("/boxOffice2")
	public String boxOffice2(@RequestParam(defaultValue = "20230101") String targetDt, Model model) {
		BoxOffice2 result = jsonService.getBoxOffice2(targetDt);
		model.addAttribute("result", result.getBoxOfficeResult());
		return "BoxOffice2";
	}
}
