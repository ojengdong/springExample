package kr.top2blue.xml.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.top2blue.xml.vo.HankyungRss;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class HankyungRssService {
	public HankyungRss getHankyungRss(String urlAddress) {
		log.info("getHankyungRss 호출 : {}", urlAddress);
		HankyungRss rss = null;
		
		XmlMapper xmlMapper = new XmlMapper();
		try {
			rss = xmlMapper.readValue(new URL(urlAddress), HankyungRss.class);
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.info("getHankyungRss : 리턴 {}", rss);
		return rss;
	}
}
