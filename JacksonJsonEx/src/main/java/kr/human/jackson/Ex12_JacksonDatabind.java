package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.BoardVo3;

// Generic collections, Tree Model
public class Ex12_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			BoardVo3 boardVo1 = new BoardVo3(1, "한사람", "123456", "나는 내용", LocalDateTime.now());
			System.out.println(boardVo1);
			// 저장
			File file = new File("boardVo3.json");

			objectMapper.registerModule(new JavaTimeModule()).writeValue(file, boardVo1);
		
			System.out.println(objectMapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(boardVo1));
		
			// 읽기
			BoardVo3 boardVo2 = objectMapper.readValue(file, BoardVo3.class);
			System.out.println(boardVo2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
