package kr.top2.blue.calendar.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kr.top2.blue.calendar.vo.LunarVO;

public class LunarUtil {
	// 년월을 입력받아 1개월치의 음력 달력 내용을 읽어오는 메서드
	public static List<LunarVO> getLunarDate(int year, int month){
		List<LunarVO> list = new ArrayList<>();
		Document doc = null;
		try {
			// 이렇게만 하면 이번달 달력만 가져온다.
			// 원하는 년/월의 달력을 가져와야 한다.
			// 주소의 뒷부분을 "?search_year=2000&search_month=02"으로 만들어서 붙여야 한다,
			String query = String.format("?search_year=%04d&search_month=%02d", year, month);
			doc = Jsoup.connect("https://astro.kasi.re.kr/life/pageView/5" + query).get();
			Elements elements = doc.select("table tbody tr");
			for(Element el : elements) {
				LunarVO vo = new LunarVO(); // 1일치의 날짜
				
				System.out.println(el.select("td").get(0).text()); // 양력
				vo.setSolar(el.select("td").get(0).text()); // 양력
				vo.setLunar(el.select("td").get(1).text()); // 음력
				vo.setGanji(el.select("td").get(2).text()); // 간지
				vo.setDayOfWeek(el.select("td").get(3).text()); // 요일
				
				list.add(vo);// 리스트에 추가
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
}
