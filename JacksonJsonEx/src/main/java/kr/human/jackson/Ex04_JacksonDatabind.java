package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.MyDataVO;

// 내장 클래스
public class Ex04_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			MyDataVO myDataVO = objectMapper.readValue(new File("src/main/resources/myData.json"), MyDataVO.class);
			
			// 포맷팅하여 스트링으로 변환
			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(myDataVO);
			System.out.println(jsonString); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
