package kr.top2blue.xml;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.top2blue.xml.vo.HankyungRss;

@SpringBootTest
class XmlProjectApplicationTests {

	@Test
	void contextLoads() {
		XmlMapper xmlMapper = new XmlMapper();
		try {
			URL url = new URL("https://www.hankyung.com/feed/it");
			
			HankyungRss rss = xmlMapper.readValue(url, HankyungRss.class);
			System.out.println(rss.getChannel().getTitle());
			System.out.println("-".repeat(80));
			for(HankyungRss.Item item : rss.getChannel().getItem()) {
				System.out.println(item.getTitle() + "(" + item.getPubDate() + ")");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
