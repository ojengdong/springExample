package kr.top2blue.xml.vo;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

//https://www.hankyung.com/feed/all-news // xml태그보고 작성

// 이 클래스를 루트 태그로 사용하겠다.
// 클래스 이름과 루트태그의 이름이 다를 경우는 localName을 지정한다.
@JacksonXmlRootElement(localName = "rss")
@Data
public class HankyungRss {
	// 하위요소로 사용하겠다. 태그가 아닌 속성으로!
	@JacksonXmlProperty(isAttribute = true)
	private String version;
	private Channel channel;

	// 내부클래스로 만들어 접근
	// 채널 정보
	@Data // getter, setter만들기
	public static class Channel {
		private String title;
		private String link;
		private String language;
		private String copyright;
		private String pubDate;
		private String lastBuildDate;
		private String description;
		private Image image;
		// 목록을 감싸주고 있는 별도의 태그가 존재하지 않는다.
		// => 아이템태그를 감싸고 있는애가 있느냐
		@JacksonXmlElementWrapper(useWrapping = false)
		private List<Item> item; // 여러개이기 때문에
	}

	// 메인화면의 이미지 정보
	@Data
	public static class Image {
		private String title;
		private String url;
		private String link;
	}

	// 기사 1개의 정보를 저장한 클래스
	@Data
	public static class Item {
		private String title;
		private String link;
		private String image;
		private String author;
		private String pubDate;
	}
}