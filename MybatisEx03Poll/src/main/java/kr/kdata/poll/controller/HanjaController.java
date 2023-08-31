package kr.kdata.poll.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import kr.kdata.poll.service.HanjaService;
import kr.kdata.poll.vo.HanjaVO;

@Controller
@RequestMapping(value = "/hanja")
public class HanjaController {

	@Autowired
	private HanjaService hanjaService;
	
	@GetMapping(value = {"/","/list"})
	public String home(@RequestParam(defaultValue = "8급 74회") String find, Model model) {
		model.addAttribute("grade", hanjaService.getGrades()); // 시험회차 목록
		model.addAttribute("find", find); // 시험회차
		model.addAttribute("question", hanjaService.getList(find)); // 문제목록
		return "hanja/list";
	}
	@GetMapping(value = {"/result"})
	public String resultGet() {
		return "redirect:/hanja/list";
	}
	
	@PostMapping(value = {"/result"})
	public String resultPost(HttpServletRequest request, Model model) {
		model.addAttribute("grade", hanjaService.getGrades()); // 시험회차 목록
		String find = request.getParameter("find"); // 넘어온 find를 받는다.
		List<HanjaVO> list = hanjaService.getList(find); // 문제
		
		List<String> answer = new ArrayList<>(); // 답한 내용
		int count = 0;
		for(int i=0;i<list.size();i++) { // 
			String a = request.getParameter("answer"+(i+1));
			if(list.get(i).getA().equals(a)) {
				count++; // 맞은 개수
				answer.add("정답 : " + list.get(i).getA() + "\n답한값 : " + a +"\n 맞음");
			}else {
				answer.add("정답 : " + list.get(i).getA() + "\n답한값 : " + a +"\n 틀림");
			}
		}
		model.addAttribute("count", count);
		model.addAttribute("answer", answer);
		model.addAttribute("find", find); // 시험회차
		model.addAttribute("question", list); // 문제목록
		return "hanja/result";
	}

}
