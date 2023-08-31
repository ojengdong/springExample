package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import kr.human.jackson.vo.PersonVO2;

// XML 읽기
public class Ex15_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		try {
			PersonVO2 personVO1 = new PersonVO2("한사람", 22, true, LocalDate.of(2000, 10, 12));
			System.out.println(personVO1);
			// 저장
			File file = new File("personVO2.json");

			mapper.registerModule(new JavaTimeModule()).writeValue(file, personVO1);
		
			System.out.println(mapper.registerModule(new JavaTimeModule()).writerWithDefaultPrettyPrinter().writeValueAsString(personVO1));
		
			// 읽기
			PersonVO2 personVO2 = mapper.readValue(file, PersonVO2.class);
			System.out.println(personVO2);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
