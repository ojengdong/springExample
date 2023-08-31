package kr.human.jackson;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.HankyungRss;

// XML 읽기
public class Ex18_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		// String urlAddress = "https://rss.hankyung.com/feed/it.xml";
		String urlAddress = "https://rss.hankyung.com/feed/land.xml";
		try {
			HankyungRss rss = mapper.readValue(new URL(urlAddress), HankyungRss.class);
			System.out.println(rss);
			System.out.println("*".repeat(100));
			System.out.println(rss.getChannel().getTitle());
			System.out.println(rss.getChannel().getLink());
			System.out.println(rss.getChannel().getLanguage());
			System.out.println(rss.getChannel().getPubDate());
			System.out.println(rss.getChannel().getLastBuildDate());
			System.out.println(rss.getChannel().getDescription());
			System.out.println("-".repeat(100));
			System.out.println(rss.getChannel().getImage().getTitle());
			System.out.println(rss.getChannel().getImage().getLink());
			System.out.println(rss.getChannel().getImage().getUrl());
			System.out.println("=".repeat(100));
			for(HankyungRss.Item item : rss.getChannel().getItem()) {
				System.out.println(item.getTitle());
				System.out.println(item.getLink());
				System.out.println(item.getAuthor());
				System.out.println("-".repeat(100));
			}
			System.out.println("=".repeat(100));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
