package kr.human.jackson.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BibleNameVO {
	private int i;		// 번호
	private String k;	// 한글명
	private String e;	// 영어명
	private String y;	// 약어
}
