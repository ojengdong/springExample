package kr.human.jackson.vo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardVo1 {
	private int idx;
	private String name;
	private String password;
	private String content;
	@JsonFormat(pattern = "yyyy-MM-dd(E) kk:mm:ss")
	private LocalDateTime regDate;
}
