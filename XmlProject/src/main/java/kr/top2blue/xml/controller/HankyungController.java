package kr.top2blue.xml.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.top2blue.xml.service.HankyungRssService;

@Controller
public class HankyungController {

	@Autowired
	private HankyungRssService rssService;
	// 메인화면
	@GetMapping(value = {"/"})
	public String home(Model model) {
		model.addAttribute("serverTime", 
				LocalDateTime
				.now()
				.format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E) hh:mm:ss")));
		return "index";
	}
	// it 뉴스
	@GetMapping(value = "/it")
	public String itRss(Model model) {
		model.addAttribute("rss", 
				rssService.getHankyungRss("https://www.hankyung.com/feed/it"));
		return "rss";
	}
	// 정치 뉴스
	@GetMapping(value = "/politics")
	public String politics(Model model) {
		model.addAttribute("rss", 
				rssService.getHankyungRss("https://www.hankyung.com/feed/politics"));
		return "rss";
	}
	
}
