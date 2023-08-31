package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.PersonVO1;

// java object 를 json 으로 변환
public class Ex02_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		
		
		// java object 생성 
		PersonVO1 personVO = new PersonVO1();
		
		try {
			// result.json 파일로 저장
			objectMapper.writeValue(new File("result1.json"), personVO);
			
			// byte[] 로 저장
			byte[] jsonBytes = objectMapper.writeValueAsBytes(personVO);
			System.out.println(Arrays.toString(jsonBytes));
			
			// string 으로 저장
			String jsonString = objectMapper.writeValueAsString(personVO);
			System.out.println(jsonString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
