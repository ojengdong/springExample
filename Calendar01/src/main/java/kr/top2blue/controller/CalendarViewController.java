package kr.top2blue.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.top2blue.vo.DateUtil;
import kr.top2blue.vo.Days;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CalendarViewController {
	
	// ?변수=값&변수=값 : @RequestParam 자료형 변수명 으로 받고
	// /2023/4 : URL주소로 받으려면 @Pathvariable로 받는다,
	@GetMapping(value = "/")
	public String home(Model model) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		model.addAttribute("serverTime", new Date());
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		return "index";
	}
	
	@GetMapping(value = "/ex01/{year}/{month}")
	public String ex01(@PathVariable Integer year, @PathVariable Integer month, Model model) {
		Calendar cal = Calendar.getInstance();
		// 값이 넘어오지 않았을 경우 처리
		if(year==null)  year = cal.get(Calendar.YEAR);
		if(month==null) month = cal.get(Calendar.MONTH)+1;
		if(month==0) { // 월이 0이면 이전년도 12월로 간다.
			year--;
			month = 12;
		}
		if(month==13) { // 월이 13이면 다음년도 1월로 간다.
			year++;
			month = 1;
		}
		// 월이 1~12를 벗어나면 안된다.
		if(month<1 || month>12)  month = cal.get(Calendar.MONTH)+1;
		// 년도가 0년 이하는 될수 없다.
		if(year<=0) year = cal.get(Calendar.YEAR);
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		log.info("년 : {}", year);
		log.info("월 : {}", month);
		
		
		// 1일의 요일
		int week = DateUtil.getDayOfWeek(year, month, 1);
		int lastday = DateUtil.getLastday(year, month);
		log.info("1일의 요일 : {}", week);
		log.info("이달의 마지막 날짜 : {}", lastday);
		// 1 달치 달력을 만들어서 넘어가자
		List<Days> list = new ArrayList<>();
		for(int i=0;i<week;i++) list.add(new Days()); // 앞에 빈칸
		// 날짜
		for(int i=1;i<=lastday;i++) list.add(new Days(i, DateUtil.getDayOfWeek(year, month, i)));
		// 마지막 날짜가 토요일이 아니면 나머지 빈칸을 채워준다.
		if(DateUtil.getDayOfWeek(year, month, lastday)<6) {
			for(int i=DateUtil.getDayOfWeek(year, month, lastday);i<6;i++) list.add(new Days());
		}
		for(Days days : list) {
			log.info("날짜와 요일 : {}", days);
		}
		// 1개월치의 달력을 모델을 저장
		model.addAttribute("list", list);
		model.addAttribute("openTR", "<tr>");
		model.addAttribute("closeTR", "</tr>");
		
		return "ex01";
	}
}
