package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BoxOffice;

// XML 읽기
public class Ex10_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		String urlAddress = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		urlAddress += "?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress += "&targetDt=20220907";
		
		try {
			BoxOffice boxOffice = mapper.readValue(new URL(urlAddress), BoxOffice.class);
			System.out.println(boxOffice);
			System.out.println("*".repeat(100));
			System.out.println(boxOffice.getBoxofficeType());
			System.out.println(boxOffice.getShowRange());
			System.out.println("*".repeat(100));
			for(BoxOffice.DailyBoxOfficeList vo : boxOffice.getDailyBoxOfficeList()) {
				System.out.println(vo.getRank()+". " + vo.getMovieNm());
			}
			System.out.println("*".repeat(100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
