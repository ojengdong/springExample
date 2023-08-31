package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.BibleNameVO;
import kr.human.jackson.vo.PersonVO1;

// json 데이타를 java object 로 변환
public class Ex01_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		// File, URL, String 방식으로 데이타를 읽어올 수 있음.
		try {
			// File에서 읽기
			PersonVO1 personVO = objectMapper.readValue(new File("src/main/resources/data.json"), PersonVO1.class);
			System.out.println(personVO);
			
			// URL 에서 읽기
			// 파일 목록 : bible.json, bibleName.json, chunja.txt, chunja2.txt, data.json, dic.json, license.json
			BibleNameVO[] bibleNameVOs = objectMapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/bibleName.json"), BibleNameVO[].class);
			System.out.println(bibleNameVOs.length + "개");
			System.out.println(Arrays.toString(bibleNameVOs));
			// String 으로 읽기
			personVO = objectMapper.readValue("{\"name\":\"두사람\", \"age\":28,\"gender\":false}", PersonVO1.class);
			System.out.println(personVO);
			
			String obj = """
		               [{
		                  "name":"일식이",
		                  "age":33,
		                  "gender":false
		               },
		               {
		                  "name":"두식이",
		                  "age":45,
		                  "gender":true
		               },
		               {
		                  "name":"삼식이",
		                  "age":57,
		                  "gender":true
		               }]
		               
		               """; 
		         
		         PersonVO1[] personVOs = objectMapper.readValue(obj, PersonVO1[].class);
		         System.out.println(personVOs.length + "명이 있습니다.");
		         for(PersonVO1 p : personVOs) {
		            System.out.println(p.getName() + "(" + p.getAge() + "세, " 
		                     + (p.isGender()?"남자":"여자") + ")" );
		         }
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
