package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.BoardVo3;

// XML 읽기
public class Ex14_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		try {
			BoardVo3 boardVo1 = new BoardVo3(1, "한사람", "123456", "나는 내용", LocalDateTime.now());
			System.out.println(boardVo1);
			// 저장
			File file = new File("xml/data/boardVo3.xml");
			System.out.println(mapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(boardVo1));
			mapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValue(file, boardVo1);
			
			// 읽기
			BoardVo3 boardVo2 = mapper.readValue(file, BoardVo3.class);
			System.out.println(boardVo2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
