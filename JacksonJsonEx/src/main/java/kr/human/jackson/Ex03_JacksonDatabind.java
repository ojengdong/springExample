package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.PersonVO1;

// json 으로 변환시 개행하여 포맷팅
public class Ex03_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			PersonVO1 personVO = objectMapper.readValue(new File("src/main/resources/data.json"), PersonVO1.class);
			
			// 포맷팅하여 스트링으로 변환
			String jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(personVO);
			System.out.println(jsonString); 
			// 포맷팅하여 파일로 저장
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("result2.json"), personVO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
