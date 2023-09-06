package kr.kdata.member.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kdata.member.service.TestService;

@RestController
@RequestMapping(value = "/test")
public class TestController {

	@Autowired
	private TestService testService;
	
	@GetMapping(value = { "/", "/today"})
	public Date selectToday() {
		return testService.selectToday();
	}
}
