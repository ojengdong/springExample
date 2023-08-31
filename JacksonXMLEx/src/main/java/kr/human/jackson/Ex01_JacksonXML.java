package kr.human.jackson;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.PersonVO1;

// Java 객체를 XML로 변환하기
public class Ex01_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		try {
			PersonVO1 personVO1 = new PersonVO1("한사람", 23, true);
			System.out.println(personVO1);
			
			// 문자열로
			String xmlStr = mapper.writeValueAsString(personVO1);
			System.out.println(xmlStr);
			
			// 경로가 없으면 새로 생성하기
			String path = "xml/data/";
			File file = new File(path);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 파일로
			file = new File(path + "personVO1.xml");
			mapper.writeValue(file, personVO1);
			
			// 바이트 배열로
			byte[] array = mapper.writeValueAsBytes(personVO1);
			System.out.println(Arrays.toString(array));
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
