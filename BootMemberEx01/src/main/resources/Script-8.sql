-- 회원관리
-- 시퀀스
CREATE SEQUENCE member_id_seq;
-- 테이블
-- 사용자아이디
-- 비밀번호
-- 이름
-- 전화
-- 이메일
-- 우편번호
-- 일반주소
-- 상세주소
DROP TABLE MEMBER;
CREATE TABLE member(
	id NUMBER PRIMARY KEY, -- 키필드
	userid varchar2(50) UNIQUE NOT NULL,
	password varchar2(50) NOT NULL,
	username varchar2(50) NOT NULL,
	phone varchar2(50) NOT NULL,
	email varchar2(50) NOT NULL,
	postcode varchar2(10) NOT NULL,
	addr1 varchar2(100) NOT NULL,
	addr2 varchar2(100) NOT NULL,
	use NUMBER DEFAULT 0 -- (0은 미인증, 1 인증, 2 휴면 ....)
);

INSERT INTO MEMBER VALUES 
(member_id_seq.nextval,'admin','admin','최고관리자',' ',' ',' ',' ',' ',0); 

INSERT INTO MEMBER VALUES 
(member_id_seq.nextval,'root','root','최고관리자',' ',' ',' ',' ',' ',0); 

INSERT INTO MEMBER VALUES 
(member_id_seq.nextval,'master','master','최고관리자',' ',' ',' ',' ',' ',0); 

INSERT INTO MEMBER VALUES 
(member_id_seq.nextval,'webmaster','webmaster','최고관리자',' ',' ',' ',' ',' ',0); 

SELECT * FROM MEMBER;