package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// Collections
public class Ex07_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();

		try {
			// 들여쓰기 지정
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			// 리스트를 파일로 저장
			List<String> list = Arrays.asList("한놈,두식이,석삼,너구리,오징어".split(","));
			mapper.writeValue(new File("xml/data/list.xml"), list);

			// 리스트를 JsonNode로 읽기
			JsonNode listNode = mapper.readTree(new File("xml/data/list.xml"));
			System.out.println(listNode);
			System.out.println("*".repeat(80));

			for (JsonNode node : listNode.get("item")) {
				System.out.println(node.asText());
			}
			System.out.println("=".repeat(80));

			// 맵을 파일로 저장
			Map<String, String> map = new HashMap<>();
			map.put("한사람", "좋은사람");
			map.put("두사람", "나쁜사람");
			map.put("세사람", "보통사람");
			map.put("네사람", "인간악질");
			mapper.writeValue(new File("xml/data/map.xml"), map);

			// 맵을 JsonNode로 읽기
			JsonNode mapNode = mapper.readTree(new File("xml/data/map.xml"));
			System.out.println(mapNode);
			System.out.println(mapNode.get("한사람").asText());

			// JsonNode를 Map으로 변환
			Map<String, String> result = mapper.convertValue(mapNode, new TypeReference<Map<String, String>>() {
			});
			System.out.println(result);
			System.out.println("*".repeat(80));
			for (String key : result.keySet()) {
				System.out.println(key + " : " + result.get(key));
			}
			System.out.println("=".repeat(80));
			
			JsonNode objectNode = mapper.readTree(new File("xml/data/MyData.xml"));
			System.out.println(objectNode);
			String name = objectNode.get("string").asText();
			int number = objectNode.get("number").asInt();
			JsonNode arrayNode = objectNode.get("array");
			System.out.println(name);
			System.out.println(number);
			System.out.println(arrayNode + " : " + arrayNode.getNodeType());
			System.out.println("*".repeat(80));
			for(JsonNode node : arrayNode.get("array")) {
				System.out.println(node.asInt());
			}
			System.out.println("*".repeat(80));
			
			JsonNode innerObjectNode = objectNode.get("object");
			System.out.println(innerObjectNode.get("property"));
			System.out.println(innerObjectNode.get("subobj").get("numero"));
			System.out.println(innerObjectNode.get("subobj").get("arr").get("arr"));
			System.out.println(innerObjectNode.get("subobj").get("arr").get("arr").get(0));
			System.out.println(innerObjectNode.get("subobj").get("arr").get("arr").get(1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
