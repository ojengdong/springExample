package kr.human.jackson;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.namespace.QName;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.dataformat.xml.XmlFactory;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;

// XML 만들기/읽기
public class Ex08_JacksonXML {
	public static void main(String[] args) {
		XmlMapper mapper = new XmlMapper();
		XmlFactory xmlFactory = new XmlFactory();

		try {
			// XML 만들기
			OutputStream os = new FileOutputStream("xml/data/test.xml");
			xmlFactory.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
			JsonGenerator generator = xmlFactory.createGenerator(os, JsonEncoding.UTF8);
			((ToXmlGenerator) generator).setNextName(new QName(null, "Person"));
			((ToXmlGenerator) generator).initGenerator();
			generator.writeStartObject();
			generator.writeStringField("message", "Hello world!");
			generator.writeStringField("name", "한사람");
			generator.writeNumberField("age",23);
			generator.writeBooleanField("gender",true);
			generator.writeEndObject();
			generator.close();
			os.close();
			System.out.println(Files.readString(Paths.get("xml/data/test.xml")));
			System.out.println("*".repeat(80));
			
			// XML 읽기
			JsonParser parser = mapper.createParser(new File("xml/data/test.xml"));
			JsonToken token = null;
			do {
				token = parser.nextToken(); // 전진
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
