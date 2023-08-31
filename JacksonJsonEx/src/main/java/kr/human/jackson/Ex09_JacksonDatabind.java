package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.BoxOffice2;


// Generic collections, Tree Model
public class Ex09_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		// 옵션 지정이 가능하다.
		// 알 수 없는 속성이 발생할 때 예외를 방지
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		// JSON 빈 문자열(')을 null 객체 값으로 강제 변환 허용
		objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
		
		String urlAddress = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		urlAddress += "?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress += "&targetDt=20220825";
		try {
			BoxOffice2 boxOffice = objectMapper.readValue(new URL(urlAddress), BoxOffice2.class);
			System.out.println(boxOffice);
			System.out.println(boxOffice.getBoxOfficeResult());
			System.out.println(boxOffice.getBoxOfficeResult().getBoxofficeType());
			System.out.println(boxOffice.getBoxOfficeResult().getShowRange());
			for(BoxOffice2.DailyBoxOfficeList bo : boxOffice.getBoxOfficeResult().getDailyBoxOfficeList()) {
				System.out.println(bo);
			}
			System.out.println("-".repeat(100));
			
			for(BoxOffice2.DailyBoxOfficeList bo : boxOffice.getBoxOfficeResult().getDailyBoxOfficeList()) {
				System.out.println(bo.getRank() + "위 : " + bo.getMovieNm() + "(" + bo.getOpenDt() + ")");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
