package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.BoardVo2;

// XML 읽기
public class Ex13_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		try {
			BoardVo2 boardVo1 = new BoardVo2(1, "한사람", "123456", "나는 내용", LocalDateTime.now());
			System.out.println(boardVo1);
			// 저장
			File file = new File("xml/data/boardVo2.xml");
			System.out.println(mapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(boardVo1));
			mapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValue(file, boardVo1);
			
			// 읽기
			BoardVo2 boardVo2 = mapper.readValue(file, BoardVo2.class);
			System.out.println(boardVo2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
