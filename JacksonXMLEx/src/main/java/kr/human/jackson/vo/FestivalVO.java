package kr.human.jackson.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
"fields":[{"id":"축제명"},{"id":"개최장소"},{"id":"축제시작일자"},{"id":"축제종료일자"},{"id":"축제내용"},{"id":"주관기관"},{"id":"주최기관"},{"id":"후원기관"},{"id":"전화번호"},{"id":"홈페이지주소"},{"id":"관련정보"},{"id":"소재지도로명주소"},{"id":"소재지지번주소"},{"id":"위도"},{"id":"경도"},{"id":"데이터기준일자"},{"id":"제공기관코드"},{"id":"제공기관명"}]

"records":[
			{
				"축제명":"[SEMI 예천곤충엑스포] 2022예천곤충축제",
				"개최장소":"곤충생태원 및 예천읍시가지",
				"축제시작일자":"2022-08-06",
				"축제종료일자":"2022-08-15",
				"축제내용":"살아있는 곤충세상 속으로, 전시체험, 공식행사, 학술행사, 연계행사",
				"주관기관":"(재)예천문화관광재단",
				"주최기관":"예천군",
				"후원기관":"문화체육관광부, 경상북도, 예천군",
				"전화번호":"054-650-6037",
				"홈페이지주소":"http://www.insect-expo.org/",
				"관련정보":"",
				"소재지도로명주소":"경상북도 예천군 효자면 은풍로 1045",
				"소재지지번주소":"경상북도 예천군 효자면 고항리 577",
				"위도":"36.8244359683",
				"경도":"128.4586462892",
				"데이터기준일자":"2022-06-10",
				"제공기관코드":"5230000",
				"제공기관명":"경상북도 예천군"
			}
		]
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "축제목록")
public class FestivalVO {
	@JacksonXmlProperty(localName = "field")
	@JacksonXmlElementWrapper(useWrapping = true, localName = "fields")
	private List<Field> fields;
	@JacksonXmlProperty(localName = "record")
	//@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlElementWrapper(useWrapping = true, localName = "records")
	private List<Record> records;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Field{
		private String id;
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Data
	public static class Record{
		@JsonProperty("축제명")
		private String name;
		@JsonProperty("개최장소")
		private String place;
		@JsonProperty("축제시작일자")
		@JsonSerialize(converter=Date2String.class)
		@JsonDeserialize(converter=String2Date.class)
		private LocalDate startDate;
		@JsonProperty("축제종료일자")
		@JsonSerialize(converter=Date2String.class)
		@JsonDeserialize(converter=String2Date.class)
		private LocalDate endDate;
		@JsonProperty("축제내용")
		private String content;
		@JsonProperty("주관기관")
		private String hostInstitution;
		@JsonProperty("주최기관")
		private String hostOrganization;
		@JsonProperty("후원기관")
		private String sponsoringOrganization;
		@JsonProperty("전화번호")
		private String phone;
		@JsonProperty("홈페이지주소")
		private String homepage;
		@JsonProperty("관련정보")
		private String information;
		@JsonProperty("소재지도로명주소")
		private String doroAddr;
		@JsonProperty("소재지지번주소")
		private String jibunAddr;
		@JsonProperty("위도")
		private String latitude;
		@JsonProperty("경도")
		private String longitude;
		@JsonProperty("데이터기준일자")
		@JsonSerialize(converter=Date2String.class)
		@JsonDeserialize(converter=String2Date.class)
		private LocalDate baseDate;
		@JsonProperty("제공기관코드")
		private String providerCode;
		@JsonProperty("제공기관명")
		private String providerName;
	}
	
	public static class Date2String extends StdConverter<LocalDate, String>{
		@Override
		public String convert(LocalDate value) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return value.format(formatter);
		} 
	}
	public static class String2Date extends StdConverter<String, LocalDate>{
		@Override
		public LocalDate convert(String value) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(value, formatter);
		}
	}
}
