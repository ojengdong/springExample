package kr.top2.blue.calendar.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.top2.blue.calendar.util.LunarUtil;

@Controller
public class HomeController {

   @GetMapping(value = "/")
   public String home(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month,
         Model model) {
      // 년도가 넘어오지 않으면 현재 년도를 가지자
      if (year == null)
         year = Calendar.getInstance().get(Calendar.YEAR);
      // 월이 넘어오지 않으면 현재 월 가지자
      if (month == null)
         month = Calendar.getInstance().get(Calendar.MONTH) + 1;

      if (month < 1 || month > 13) {
         month = Calendar.getInstance().get(Calendar.MONTH) + 1;
      } else {
         if (month == 0) {
            month = 12;
            year--;
         } else if (month == 13) {
            month = 1;
            year++;
         }
      }

      if (year < 1) {
         year = Calendar.getInstance().get(Calendar.YEAR);
      }

      // 지정 년월의 음력데이터를 가져오자
      model.addAttribute("year", year);
      model.addAttribute("month", month);
      model.addAttribute("list", LunarUtil.getLunarDate(year, month));
      model.addAttribute("openTR","<tr>");
      model.addAttribute("closeTR","</tr>");
      return "index";
   }

   // 이번달 값만 읽어와보자
   @GetMapping("/test")
   public String test(Model model) {
      model.addAttribute("list", LunarUtil.getLunarDate(2023, 8));
      return "lunar01";
   }

   @GetMapping("/test2")
   public String test2(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month,
         Model model) {
      // 년도가 넘어오지 않으면 현재 년도를 가지자
      if (year == null)
         year = Calendar.getInstance().get(Calendar.YEAR);
      // 월이 넘어오지 않으면 현재 월 가지자
      if (month == null)
         month = Calendar.getInstance().get(Calendar.MONTH) + 1;
      // 지정 년월의 음력데이터를 가져오자
      model.addAttribute("list", LunarUtil.getLunarDate(year, month));
      return "lunar01";
   }
}