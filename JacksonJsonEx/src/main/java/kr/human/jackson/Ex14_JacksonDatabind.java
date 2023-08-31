package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.FestivalVO;

// json 데이타를 java object 로 변환
public class Ex14_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		// File, URL, String 방식으로 데이타를 읽어올 수 있음.
		try {
			// File에서 읽기
			FestivalVO festivalVO = objectMapper.readValue(new File("src/main/resources/전국문화축제표준데이터.json"), FestivalVO.class);

			for (FestivalVO.Field  field: festivalVO.getFields()) {
				System.out.println(field);				
			}
			for (FestivalVO.Record  record: festivalVO.getRecords()) {
				System.out.println(record);				
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
