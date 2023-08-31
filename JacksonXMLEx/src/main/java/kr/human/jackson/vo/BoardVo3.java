package kr.human.jackson.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({ "password", "regDate" }) // 무시필드 일괄 지정
public class BoardVo3 {
	private int idx;
	private String name;
	private String password;
	private String content;
	private LocalDateTime regDate;
	
}
