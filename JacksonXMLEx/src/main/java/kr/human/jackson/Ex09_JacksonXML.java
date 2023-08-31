package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// XML 읽기
public class Ex09_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		String urlAddress = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml";
		urlAddress += "?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress += "&targetDt=20220907";
		
		try {
			JsonNode objectNode = mapper.readTree(new URL(urlAddress));
			System.out.println(objectNode);
			System.out.println("*".repeat(100));
			System.out.println(objectNode.get("boxofficeType"));
			System.out.println(objectNode.get("showRange"));
			System.out.println(objectNode.get("dailyBoxOfficeList"));
			System.out.println("=".repeat(100));
			for(JsonNode node : objectNode.get("dailyBoxOfficeList").get("dailyBoxOffice")) {
				System.out.println(node.get("rank") + ". " + node.get("movieNm"));
			}
			System.out.println("*".repeat(100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
