package kr.human.jackson;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BibleNameVO;
import kr.human.jackson.vo.PersonVO1;

// XML을 Java 객체로 변환하기
public class Ex02_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		
		try {
			// 문자열 읽기
			String xmlStr = Files.readString(Paths.get("xml/data/personVO1.xml"));
			System.out.println(xmlStr);
			
			// XML 문자열을 자바 객체로
			PersonVO1 personVO1 = mapper.readValue(xmlStr, PersonVO1.class);
			System.out.println(personVO1);
			
			// File에서 읽기
			File file = new File("xml/data/personVO1.xml");
			PersonVO1 personVO2 = mapper.readValue(file, PersonVO1.class);
			System.out.println(personVO2);

			// URL 에서 읽기
			// 파일 목록 : bible.json, bibleName.json, chunja.txt, chunja2.txt, data.json, dic.json, license.json, BibleNames.xml
			BibleNameVO[] bibleNameVOs = mapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/BibleNames.xml"), BibleNameVO[].class);
			System.out.println(bibleNameVOs.length + "개");
			for(BibleNameVO vo : bibleNameVOs) {
				System.out.println(vo);
			}
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
