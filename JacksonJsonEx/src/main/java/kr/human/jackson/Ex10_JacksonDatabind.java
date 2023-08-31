package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.BoardVo1;

// Generic collections, Tree Model
public class Ex10_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			BoardVo1 boardVo1 = new BoardVo1(1, "한사람", "123456", "나는 내용", LocalDateTime.now());
			System.out.println(boardVo1);
			// 저장
			File file = new File("boardVo1.json");
			/*
			 Java 8 date/time type `java.time.LocalDateTime` not supported by default: 
			 add Module "com.fasterxml.jackson.datatype:jackson-datatype-jsr310" to enable handling...
			 메세지가 발생하면 
			 pom.xml의 의존성에 다음을 추가하고 
			<dependency>
				<groupId>com.fasterxml.jackson.datatype</groupId>
				<artifactId>jackson-datatype-jsr310</artifactId>
				<version>2.13.3</version>
			</dependency>
			
			코드에 
			 .registerModule(new JavaTimeModule()) 을 추가한다.
			 */
			System.out.println(objectMapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(boardVo1));

			
			objectMapper.registerModule(new JavaTimeModule()).writeValue(file, boardVo1);
			
			// 읽기
			BoardVo1 boardVo2 = objectMapper.readValue(file, BoardVo1.class);
			System.out.println(boardVo2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
