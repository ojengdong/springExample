package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// 스트리밍 파서, 생성기
public class Ex08_JacksonDatabind {
	public static void main(String[] args) {
		// com.fasterxml.jackson.databind.ObjectMapper 인스턴스 생성
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			File file = new File("test.json");
			// 스트리밍 생성기
			JsonGenerator generator = objectMapper.createGenerator(file, JsonEncoding.UTF8);
			generator.writeStartObject();
			generator.writeStringField("message", "Hello world!");
			generator.writeStringField("name", "한사람");
			generator.writeNumberField("age",23);
			generator.writeBooleanField("gender",true);
			generator.writeEndObject();
			generator.close();
			
			JsonNode jsonNode = objectMapper.readTree(file);
			System.out.println(jsonNode);
			System.out.println("*".repeat(80));

			System.out.println(jsonNode.get("message").asText());
			System.out.println(jsonNode.get("name").asText());
			System.out.println(jsonNode.get("age").asInt());
			System.out.println(jsonNode.get("gender").asBoolean() ? "남자":"여자");
			System.out.println("*".repeat(80));
			
			// 스트리밍 파서
			JsonParser parser = objectMapper.createParser(file);
			//System.out.println(parser);
			//System.out.println("*".repeat(80));
			JsonToken token = null;
			do {
				token = parser.nextToken(); // 전진
				// System.out.println(token);
				if(token==JsonToken.FIELD_NAME) { // 필드이름이라면
					System.out.print(parser.getCurrentName() + " : "); // 필드이름 읽고
					token = parser.nextToken(); // 다음으로 이동
					System.out.println(parser.getText()); // 내용읽고
				}
			}while(token != JsonToken.END_OBJECT); // 객체 끝이 아닌동안
			System.out.println("*".repeat(80));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
