package kr.human.jackson;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BibleNameVO;

// XML을 들여쓰기하기
public class Ex03_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		
		try {
			// URL 에서 읽기
			// 파일 목록 : bible.json, bibleName.json, chunja.txt, chunja2.txt, data.json, dic.json, license.json, BibleNames.xml
			BibleNameVO[] bibleNameVOs = mapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/BibleNames.xml"), BibleNameVO[].class);
			System.out.println(bibleNameVOs.length + "개");
			System.out.println("*".repeat(100));
			
			// 한줄로 출력하기
			String xmlStr = mapper.writeValueAsString(bibleNameVOs);
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));

			// 들여쓰기 하여 출력하기
			xmlStr = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(bibleNameVOs);
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));
			
			// 들여쓰기 해서 파일로 저장
			File file = new File("xml/data/BibleNames2.xml");
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, bibleNameVOs);
			
			// 문자열 읽기
			xmlStr = Files.readString(Paths.get("xml/data/BibleNames2.xml"));
			System.out.println(xmlStr);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
