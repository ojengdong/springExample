package kr.kdata.board.vo;

import lombok.Data;
// 컨트롤러에서 변수값을 받을때 사용
// @RequestParam int p
// @RequestParam int s
// @RequestParam int b
// @RequestParam int idx
// 위의 네줄대신
// @ModelAttribute CommVO cv;
@Data
public class CommVO {
	private int p;  // 현재페이지
	private int s;  // 페이지당 글수
	private int b;  // 하단 페이지 개수
	private int id; // 글번호
}
