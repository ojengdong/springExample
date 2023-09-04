package kr.kdata.pds.vo;

import java.util.Date;
import java.util.List;

import lombok.Data;

/*
-- 게시판 테이블
CREATE SEQUENCE board2_id_seq;
CREATE TABLE board2(
	id NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	readCount NUMBER DEFAULT 0,
	ip varchar2(200) NOT NULL
);
 */
@Data
public class Board2VO {
	private int id;
	private String name;
	private String password;
	private String subject;
	private String content;
	private Date   regDate;
	private int readCount;
	private String ip;
	
	private List<Board2FileVO> fileList; // 첨부파일들
}
