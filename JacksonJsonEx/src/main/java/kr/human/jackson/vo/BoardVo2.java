package kr.human.jackson.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
	private LocalDateTime regDate;
	
}
