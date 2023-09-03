package kr.kdata.board.vo;

import java.util.Date;

import lombok.Data;
/*
CREATE TABLE bcomment(
	id NUMBER PRIMARY KEY,  -- 키필드
	REF NUMBER NOT NULL, -- 원본글 번호
	name varchar2(100) NOT NULL, -- 작성자
	password varchar2(100) NOT NULL, -- 암호
	content varchar2(2000) NOT NULL, -- 내용
	regdate timestamp DEFAULT sysdate -- 작성일
);
 */
@Data
public class BCommentVO {
	private int id;
	private int ref;
	private String name;
	private String password;
	private String content;
	private Date   regDate;
}
