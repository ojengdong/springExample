package kr.kdata.pds.vo;

import lombok.Data;

/*
-- 파일테이블
CREATE SEQUENCE board2file_id_seq;
CREATE TABLE board2file(
	id NUMBER PRIMARY KEY,  -- 키필드
	ref NUMBER NOT NULL, -- 원본글 번호
	uuid varchar2(200) NOT NULL, -- 중복처리위한 키
	fileName varchar2(200) NOT NULL, -- 원본 파일명
	contentType varchar2(200) NOT NULL -- 파일 종류
);
 */
@Data
public class Board2FileVO {
	private int id;  // 키필드
	private int ref; // 원본글 번호
	private String uuid; // 중복제거처리 위한 키
	private String fileName; // 원본이름
	private String contentType; // 파일 종류
}
