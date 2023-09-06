package kr.kdata.member.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kdata.member.service.TestService;

@Controller
public class HomeController {

	@Autowired
	private TestService testService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@GetMapping(value = {"/","/index","/home","/main"})
	public String home(Model model) {
		model.addAttribute("serverTime", new Date());
		model.addAttribute("dbTime1", jdbcTemplate.queryForObject("select sysdate from dual", Date.class));
		model.addAttribute("dbTime2", testService.selectToday());
		return "/index";
	}
	// 모든 쿠키 확인하기
	@GetMapping(value = "/cookie")
	public String getCookies(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		// HttpServletRequest는 클라이언트의 요청 정보를 해석할때 사용하는 객체이다.
		// 저장된 모든 쿠키를 읽는다.
		Cookie[] cookies = request.getCookies();
		// 쿠키가 존재한다면
		if(cookies!=null && cookies.length>0) { 
			// 반복문을 돌려 모든 쿠키를 읽어보자
			StringBuffer sb = new StringBuffer();
			for(Cookie cookie : cookies) {
				sb.append(cookie.getName() + " : "); // 키
				// 만약에 서버가 한글 쿠키를 지원하지 않는다면 디코딩해서 읽는다.
				// sb.append(cookie.getValue() + "<br>"); // 값
				sb.append(URLDecoder.decode(cookie.getValue(), "UTF-8") + "<br>"); // 값
			}
			model.addAttribute("cookie", sb.toString());
		}
		return "/viewAllCookies";
	}
	// 새로운 쿠키를 저장해 보자
	@GetMapping(value = "/addCookie")
	public String addCookie(HttpServletResponse response) throws UnsupportedEncodingException {
		// HttpServletResponse객체는 응답을 책임지는 객체
		// 쿠키를 만든다.(만약에 서버가 한글 쿠키를 지원하지 않는다면 인코딩해서 저장한다.
		Cookie cookie1 = new Cookie("name1", URLEncoder.encode("최고관리자","UTF-8"));
		Cookie cookie2 = new Cookie("name2", URLEncoder.encode("지나는이","UTF-8"));
		cookie2.setMaxAge(60*60*24*7); // 유효기간을 7일로 설정
		// 쿠키를 저장한다.
		response.addCookie(cookie1);
		response.addCookie(cookie2);
		
		return "redirect:/cookie";
	}
	
}
