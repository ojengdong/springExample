package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BoxOffice2;

// XML 읽기
public class Ex11_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		// 옵션 지정이 가능하다.
		// 알 수 없는 속성이 발생할 때 예외를 방지
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// 빈 문자열을 null 객체 값으로 강제 변환 허용
		mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

		String urlAddress = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		urlAddress += "?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress += "&targetDt=20220907";
		
		try {
			BoxOffice2 boxOffice = mapper.readValue(new URL(urlAddress), BoxOffice2.class);
			System.out.println(boxOffice);
			System.out.println("*".repeat(100));
			System.out.println(boxOffice.getBoxofficeType());
			System.out.println(boxOffice.getShowRange());
			System.out.println("*".repeat(100));
			for(BoxOffice2.DailyBoxOfficeList vo : boxOffice.getDailyBoxOfficeList()) {
				System.out.println(vo.getRank()+". " + vo.getMovieNm());
			}
			System.out.println("*".repeat(100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
