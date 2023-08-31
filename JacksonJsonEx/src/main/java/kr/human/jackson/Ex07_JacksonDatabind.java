package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.BoxOffice;

// Generic collections, Tree Model
public class Ex07_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		String urlAddress = "http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		urlAddress += "?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress += "&targetDt=20230828";
		try {
			BoxOffice boxOffice = objectMapper.readValue(new URL(urlAddress), BoxOffice.class);
			System.out.println(boxOffice);
			System.out.println(boxOffice.getBoxOfficeResult());
			System.out.println(boxOffice.getBoxOfficeResult().getBoxofficeType());
			System.out.println(boxOffice.getBoxOfficeResult().getShowRange());
			for(BoxOffice.DailyBoxOfficeList bo : boxOffice.getBoxOfficeResult().getDailyBoxOfficeList()) {
				System.out.println(bo);
			}
			System.out.println("-".repeat(100));
			
			for(BoxOffice.DailyBoxOfficeList bo : boxOffice.getBoxOfficeResult().getDailyBoxOfficeList()) {
				System.out.println(bo.getRank() + "위 : " + bo.getMovieNm() + "(" + bo.getOpenDt() + ")");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
