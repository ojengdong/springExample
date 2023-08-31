package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.PersonVO2;

// Generic collections, Tree Model
public class Ex13_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			PersonVO2 personVO1 = new PersonVO2("한사람", 22, true, LocalDate.of(2000, 10, 12));
			System.out.println(personVO1);
			// 저장
			File file = new File("personVO2.json");

			objectMapper.registerModule(new JavaTimeModule()).writeValue(file, personVO1);
		
			System.out.println(objectMapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(personVO1));
		
			// 읽기
			PersonVO2 personVO2 = objectMapper.readValue(file, PersonVO2.class);
			System.out.println(personVO2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
