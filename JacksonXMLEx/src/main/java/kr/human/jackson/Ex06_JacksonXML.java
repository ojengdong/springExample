package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.BibleNameVO;

// Collections
public class Ex06_JacksonXML {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		
		try {
			Map<String, String> map = new HashMap<>();
			map.put("한사람","좋은사람");
			map.put("두사람","나쁜사람");
			map.put("세사람","보통사람");
			map.put("네사람","인간악질");
			System.out.println(map);
			System.out.println("*".repeat(100));
			// 맵을 XML로
			String xmlStr = mapper.writeValueAsString(map);
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));
			// 맵으로 읽기
			Map<String, String> map2 = mapper.readValue(xmlStr, HashMap.class);
			System.out.println(map2);
			System.out.println("*".repeat(100));		
			
			// 리스트를 XML로
			List<String> list = Arrays.asList("한놈,두식이,석삼,너구리,오징어".split(","));
			xmlStr = mapper.writeValueAsString(list);
			System.out.println(xmlStr);
			System.out.println("*".repeat(100));
			
			// 리스트로 읽기
			List<String> list2 = mapper.readValue(xmlStr, List.class);
			System.out.println(list2);
			System.out.println("*".repeat(100));
			
			// VO의 List로 읽기
			List<BibleNameVO> bibleNameVOs = mapper.readValue(new URL("https://firstproject-51141.firebaseapp.com/data/BibleNames.xml"), List.class);
			System.out.println(bibleNameVOs);
			System.out.println("*".repeat(100));
			
			// VO의 List로 저장
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("xml/data/bibleNameList.xml"), bibleNameVOs);

			// VO의 List로 읽기 : 타입을 지정해서!!!
			List<BibleNameVO> bibleNameVOs2 = mapper.readValue(new File("xml/data/bibleNameList.xml"), new TypeReference<List<BibleNameVO>>() { });
			System.out.println(bibleNameVOs2);
			System.out.println("*".repeat(100));
			
			// Map의 List로 읽기
			List<Map<String, String>> mapList = mapper.readValue(new File("xml/data/bibleNameList.xml"), List.class);
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
