package kr.human.jackson;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import kr.human.jackson.vo.MyDataVO;

// 내장 클래스
public class Ex05_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		
		try {
			MyDataVO myDataVO = mapper.readValue(new File("xml/data/MyData.xml"), MyDataVO.class);
			System.out.println(myDataVO);
			System.out.println(myDataVO.getObject());
			System.out.println(myDataVO.getObject().getSubobj());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
