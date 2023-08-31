package  kr.top2blue.JsonProject.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import  kr.top2blue.JsonProject.vo.BoxOffice;
import  kr.top2blue.JsonProject.vo.BoxOffice2;
import  kr.top2blue.JsonProject.vo.DataVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JsonService {

	public List<DataVO> readDataVO(){
		log.info("한자 사자성어 읽기 시작");
		List<DataVO> list = null;
		// json 파일을 읽기위한 jackson 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			URL url = new URL("https://firstproject-51141.firebaseapp.com/data/data.json");
			DataVO[] vos = objectMapper.readValue(url, DataVO[].class);
			list = Arrays.asList(vos); // 배열을 리스트로 변환
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("한자 사자성어 개수 : " + (list!=null ? list.size() : "0") + "개");
		return list;
	}
	// 일별 박스오피스
	public BoxOffice getBoxOffice(String targetDt) {
		BoxOffice boxOffice = null;
		// 실제로 넘어온 날짜의 박스오피스 정보를 읽어와야 한다.
		// 연결할 주소를 만들어 주어야 한다.
		String urlAddress="";
		urlAddress +="http://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		urlAddress +="?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress +="&targetDt=" + targetDt;
		
		// json 파일을 읽기위한 jackson 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			URL url = new URL(urlAddress);
			boxOffice = objectMapper.readValue(url, BoxOffice.class);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("박스오피스 결과 : {}", boxOffice);
		return boxOffice;
	}
	
	// 주간/주말 박스오피스
	public BoxOffice2 getBoxOffice2(String targetDt) {
		BoxOffice2 boxOffice = null;
		// 실제로 넘어온 날짜의 박스오피스 정보를 읽어와야 한다.
		// 연결할 주소를 만들어 주어야 한다.
		String urlAddress="";
		urlAddress +="https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json";
		urlAddress +="?key=f5eef3421c602c6cb7ea224104795888";
		urlAddress +="&targetDt=" + targetDt;
		
		// json 파일을 읽기위한 jackson 객체 생성
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			
			URL url = new URL(urlAddress);
			boxOffice = objectMapper.readValue(url, BoxOffice2.class);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			e.printStackTrace();
		} catch (DatabindException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("박스오피스 결과 : {}", boxOffice);
		return boxOffice;
	}
}
