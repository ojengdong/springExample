-- 게시판 테이블 
CREATE SEQUENCE board2_id_seq;
CREATE TABLE board2 (
	id NUMBER PRIMARY KEY,
	name varchar2(200) NOT NULL,
	password varchar2(200) NOT NULL,
	subject varchar2(200) NOT NULL,
	content varchar2(2000) NOT NULL,
	regDate timestamp DEFAULT sysdate,
	readCount NUMBER DEFAULT 0,
	ip varchar2(200) NOT NULL
);


-- 파일 테이블
CREATE SEQUENCE FILE_id_seq;

CREATE TABLE boardfile_id_seq (
	id NUMBER PRIMARY KEY,
	REF NUMBER NOT NULL,
	uuid varchar2(200) NOT NULL,
	fileName varchar2(200) NOT NULL,
	contentType varchar2(200) NOT NULL
);