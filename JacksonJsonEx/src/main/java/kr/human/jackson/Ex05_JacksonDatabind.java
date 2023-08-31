package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.human.jackson.vo.BibleNameVO;

// Generic collections, Tree Model
public class Ex05_JacksonDatabind {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			Map<Integer, String> map = new HashMap<>();
			map.put(1, "한사람");
			map.put(2, "두사람");
			map.put(3, "세사람");
			map.put(4, "네사람");
			System.out.println(map);
			// 맵을 저장
			String mapData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
			System.out.println(mapData);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("map.json"), map);
			
			// 맵으로 읽기
			Map<Integer, String> map2 = objectMapper.readValue(mapData, Map.class);
			System.out.println(map2);
			
			List<String> list = Arrays.asList("한놈,두식이,석삼,너구리,오징어".split(","));
			System.out.println(list);
			// 리스트로 저장
			String listData = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
			System.out.println(listData);
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("list.json"), list);
			// 리스트로 읽기
			List<String> list2 = objectMapper.readValue(listData, List.class);
			System.out.println(list2);
			System.out.println("*".repeat(100));
			
			// VO의 List로 읽기
			List<BibleNameVO> bibleNameVOs = objectMapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/bibleName.json"), List.class);
			System.out.println(bibleNameVOs);
			System.out.println("*".repeat(100));
			for(Object vo : bibleNameVOs) { // BibleNameVO 로 받으면 에러남
				System.out.println(vo);
			}
			
			// VO의 List로 저장
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("bibleNameList.json"), bibleNameVOs);
			System.out.println("*".repeat(100));
			
			// VO의 List로 읽기 : 타입을 지정해서!!!
			List<BibleNameVO> bibleNameVOs2 = objectMapper.readValue(new File("bibleNameList.json"), new TypeReference<List<BibleNameVO>>() { });
			System.out.println(bibleNameVOs2);
			System.out.println("*".repeat(100));
			for(BibleNameVO vo : bibleNameVOs2) { // BibleNameVO 로 받음
				System.out.println(vo);
			}
			
			// Map의 List로 읽기
			List<Map<String, String>> mapList = objectMapper.readValue(new File("bibleNameList.json"), List.class);
			System.out.println(mapList);
			System.out.println("*".repeat(100));
			for(Object m : mapList) {
				Map<String, String> map3 = (Map<String, String>)m;
				System.out.println(map3.get("k") + "(" + map3.get("e") + ") : " + map3.get("y"));
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
