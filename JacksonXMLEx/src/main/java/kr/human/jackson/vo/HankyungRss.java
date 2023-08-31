package kr.human.jackson.vo;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "rss")
public class HankyungRss {
	@JacksonXmlProperty(isAttribute = true)
	private String version;
	private Channel Channel;
	
	@Data
	public static class Channel{
		private String title;
		private String link;
		private String language;
		private String copyright;
		private String pubDate;
		private String lastBuildDate;
		private String description;
		private Image image;
		@JacksonXmlElementWrapper(useWrapping = false)
		private List<Item> item;
	}

	@Data
	public static class Image{
		private String title;
		private String url;
		private String link;
	}
	
	@Data
	public static class Item{
		private String title;
		private String link;
		private String image;
		private String author;
		private String pubDate;
	}
}
