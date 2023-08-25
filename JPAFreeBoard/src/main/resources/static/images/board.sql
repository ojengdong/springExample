-- 댓글이 있는 자유게시판 만들자
-- 1. 게시판에 사용할 시퀀스
CREATE SEQUENCE board_id_seq;
-- 2. 게시판 테이블 생성
CREATE TABLE board(
	id NUMBER PRIMARY KEY,  -- 키필드
	name varchar2(100) NOT NULL, -- 작성자
	password varchar2(100) NOT NULL, -- 암호
	subject varchar2(300) NOT NULL, -- 제목
	content varchar2(2000) NOT NULL, -- 내용
	regdate timestamp DEFAULT sysdate -- 작성일
);
-- 3. 댓글 테이블에 사용할 시퀀스
CREATE SEQUENCE comment_id_seq;
-- 4. 댓글 테이블 생성
CREATE TABLE bcomment(
	id NUMBER PRIMARY KEY,  -- 키필드
	REF NUMBER NOT NULL, -- 원본글 번호
	name varchar2(100) NOT NULL, -- 작성자
	password varchar2(100) NOT NULL, -- 암호
	content varchar2(2000) NOT NULL, -- 내용
	regdate timestamp DEFAULT sysdate -- 작성일
);

SELECT * FROM user_objects;
