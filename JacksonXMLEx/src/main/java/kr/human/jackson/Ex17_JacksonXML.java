package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// XML 읽기
public class Ex17_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		String urlAddress = "https://rss.hankyung.com/feed/it.xml";
		try {
			JsonNode objectNode = mapper.readTree(new URL(urlAddress));
			//System.out.println(objectNode);
			//System.out.println("*".repeat(100));
			System.out.println(objectNode.get("channel").get("title"));
			System.out.println(objectNode.get("channel").get("link"));
			System.out.println(objectNode.get("channel").get("image"));
			System.out.println("*".repeat(100));
			System.out.println(objectNode.get("channel").get("image").get("title"));
			System.out.println(objectNode.get("channel").get("image").get("url"));
			System.out.println(objectNode.get("channel").get("image").get("link"));
			System.out.println("*".repeat(100));
			for(JsonNode node : objectNode.get("channel").get("item")) {
				// System.out.println(node);
				System.out.println(node.get("title"));
 				System.out.println(node.get("link"));
				System.out.println(node.get("image"));
				System.out.println(node.get("author"));
				System.out.println(node.get("pubDate"));
				System.out.println("-".repeat(100));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
