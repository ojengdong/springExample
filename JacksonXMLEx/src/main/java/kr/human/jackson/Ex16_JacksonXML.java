package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.FestivalVO;

// XML 읽기
public class Ex16_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		// mapper.setDefaultUseWrapper(false);
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			// File에서 읽기
			FestivalVO festivalVO = objectMapper.readValue(new File("src/main/resources/전국문화축제표준데이터.json"), FestivalVO.class);
			System.out.println(festivalVO.getFields().size() + "개 항목");
			System.out.println(festivalVO.getRecords().size() + "개의 축제");
			mapper.writeValue(new File("xml/data/전국문화축제표준데이터.xml"), festivalVO);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
