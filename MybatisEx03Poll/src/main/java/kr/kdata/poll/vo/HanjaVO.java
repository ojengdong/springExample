package kr.kdata.poll.vo;

import lombok.Data;

// {"d":"8급","s":"68회","n":1,"q":"(여자)","e1":"南","e2":"女","e3":"北","e4":"木","a":"2"}
@Data
public class HanjaVO implements Comparable<HanjaVO> {
	private String d; // 급수
	private String s; // ?회
	private int    n; // 번호
	private String q; // 문제
	private String e1;// 보기
	private String e2;
	private String e3;
	private String e4;
	private String  a; // 정답
	
	@Override
	public int compareTo(HanjaVO o) {
		return n-o.getN(); // 번호 오름차순
		// return o.getN() - n; // 번호 내림차순
	}

}
