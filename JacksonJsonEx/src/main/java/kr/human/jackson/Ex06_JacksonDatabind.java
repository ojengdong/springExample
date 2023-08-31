package kr.human.jackson;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// Generic collections, Tree Model
public class Ex06_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			// Maps, Lists 및 기타 "단순한" 개체 유형(문자열, 숫자, 부울)을 처리하는 것은 간단할 수 있지만 개체 순회는 성가실 수 있습니다 . 
			// 잭슨의 나무 모델 이 유용할 수 있는 곳은 다음과 같습니다.
			
			// List를 readTree로 읽기
			JsonNode listNode = objectMapper.readTree(new File("list.json"));
			System.out.println(listNode);
			System.out.println("*".repeat(80));
			for(JsonNode node : listNode) {
				System.out.println(node.asText());
			}
			System.out.println("=".repeat(80));
			
			// Map을 readTree로 읽기
			JsonNode mapNode = objectMapper.readTree(new File("map.json"));
			System.out.println(mapNode);
			// JsonNode를 Map으로 변환
			Map<Integer, String> result = objectMapper.convertValue(mapNode, new TypeReference<Map<Integer, String>>(){});
			System.out.println(result);
			System.out.println("*".repeat(80));
			for(int key : result.keySet()) {
				System.out.println(key + " : " + result.get(key));
			}
			System.out.println("=".repeat(80));
			
			
			JsonNode objectNode = objectMapper.readTree(new File("src/main/resources/myData.json"));
			System.out.println(objectNode);
			String name = objectNode.get("string").asText();
			int number = objectNode.get("number").asInt();
			JsonNode arrayNode = objectNode.get("array");
			System.out.println(name);
			System.out.println(number);
			System.out.println(arrayNode + " : " + arrayNode.getNodeType());
			System.out.println("*".repeat(80));
			System.out.println(arrayNode.get(0).asInt());
			System.out.println(arrayNode.get(1).asInt());
			System.out.println(arrayNode.get(2).asInt());
			System.out.println("*".repeat(80));
			for(JsonNode node : arrayNode) {
				System.out.println(node.asInt());
			}
			System.out.println("*".repeat(80));
			JsonNode jsonNode = objectNode.get("object");
			System.out.println(jsonNode);
			System.out.println(objectNode.get("object").get("property").asText());
			System.out.println(objectNode.get("object").get("subobj").get("arr").get(0).asText());
			System.out.println(objectNode.get("object").get("subobj").get("arr").get(1).asText());
			System.out.println(objectNode.get("object").get("subobj").get("numero").asInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
