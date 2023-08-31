package kr.human.jackson.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonVO2 {
	private String name;
	private int age;
	@JsonSerialize(converter=Gender2String.class)
	@JsonDeserialize(converter=String2Gender.class)
	private Boolean gender;
	@JsonSerialize(converter=Birthday2String.class)
	@JsonDeserialize(converter=String2Birthday.class)
	private LocalDate birthDay;
	
	// 컨버트 클래스
	public static class Gender2String extends StdConverter<Boolean, String> {
		@Override
		public String convert(Boolean value) {
			return value ? "남자" : "여자";
		}
	}
	public static class String2Gender extends StdConverter<String, Boolean> {
		@Override
		public Boolean convert(String value) {
			return value.equals("남자");
		}
	}
	
	public static class Birthday2String extends StdConverter<LocalDate, String>{
		@Override
		public String convert(LocalDate value) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(E요일)");
			return value.format(formatter);
		} 
	}
	public static class String2Birthday extends StdConverter<String, LocalDate>{
		@Override
		public LocalDate convert(String value) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(E요일)");
			return LocalDate.parse(value, formatter);
		}
	}
}
