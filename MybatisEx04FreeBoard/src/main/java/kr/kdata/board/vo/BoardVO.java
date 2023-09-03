package kr.kdata.board.vo;
/*
CREATE TABLE board(
	id NUMBER PRIMARY KEY,  -- 키필드
	name varchar2(100) NOT NULL, -- 작성자
	password varchar2(100) NOT NULL, -- 암호
	subject varchar2(300) NOT NULL, -- 제목
	content varchar2(2000) NOT NULL, -- 내용
	regdate timestamp DEFAULT sysdate -- 작성일
);
*/

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private int id;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date   regDate;
	
	// 추가
	private int commentCount; // 댓글의 개수 --- 목록보기
	private List<BCommentVO> commentList; // 댓글목록 --- 내용보기
}
