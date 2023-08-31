package kr.human.jackson.vo;

import java.time.LocalDateTime;

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
	private LocalDateTime regDate;
}
