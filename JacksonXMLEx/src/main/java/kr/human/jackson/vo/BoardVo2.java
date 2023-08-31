package kr.human.jackson.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JacksonXmlRootElement(localName = "게시글")
public class BoardVo2 {
	@JsonProperty("글번호") // 이름 변경
	private int idx;
	@JsonProperty("작성자")
	private String name;
	@JsonIgnore // 무시
	private String password;
	@JsonProperty("내용")
	private String content;
	@JsonProperty("작성일")
	@JsonFormat(pattern = "yyyy-MM-dd(E) kk:mm:ss")
	private LocalDateTime regDate;
	
}
