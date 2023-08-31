package kr.human.jackson;


import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BibleNameVO;

//XML을 들여쓰기하기
public class Ex04_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		try {
			// URL 에서 읽기
			// 파일 목록 : bible.json, bibleName.json, chunja.txt, chunja2.txt, data.json, dic.json, license.json, BibleNames.xml
			BibleNameVO[] bibleNameVOs = mapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/BibleNames.xml"), BibleNameVO[].class);
			System.out.println(bibleNameVOs.length + "개");
			System.out.println("*".repeat(100));
			
			String xmlStr = mapper.writeValueAsString(bibleNameVOs);
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));

			// 들여쓰기 지정
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			xmlStr = mapper.writeValueAsString(bibleNameVOs);
			
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
