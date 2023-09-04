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
-- 파일테이블
-- DROP SEQUENCE file_id_seq;
CREATE SEQUENCE board2file_id_seq;
CREATE TABLE board2file(
	id NUMBER PRIMARY KEY,  -- 키필드
	ref NUMBER NOT NULL, -- 원본글 번호
	uuid varchar2(200) NOT NULL, -- 중복처리위한 키
	fileName varchar2(200) NOT NULL, -- 원본 파일명
	contentType varchar2(200) NOT NULL -- 파일 종류
);