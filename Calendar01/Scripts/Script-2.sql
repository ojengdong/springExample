-- 투표하기 테이블 작성
-- 키필드
-- 투표제목
-- 항목 : 항목1|항목2|항목3...
-- 투표 : 0|0|0

-- 시퀀스 만들기
CREATE SEQUENCE poll_id_seq;
CREATE TABLE poll(
	id NUMBER PRIMARY KEY, -- 키필드
	subject varchar2(300) NOT NULL, -- 투표제목
	item varchar2(1000) NOT NULL, -- 투표항목
	cnt varchar2(1000) NOT NULL -- 투표결과
);

INSERT INTO poll VALUES (poll_id_seq.nextval,'제일 좋아하는 과목은?','HTML|CSS|JAVA|SPRING|JS','0|0|0|0|0');

INSERT INTO poll VALUES (poll_id_seq.nextval,'제일 싫어하는 과목은?','HTML|CSS|JAVA|SPRING|JS','5|12|23|11|7');

INSERT INTO poll VALUES 
(poll_id_seq.nextval,'이과정은?','아주좋다|좋다|나쁘다|아주나쁘다','2|6|8|4');


