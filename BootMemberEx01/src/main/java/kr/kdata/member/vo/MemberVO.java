package kr.kdata.member.vo;

import lombok.Data;

/*
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
 */
@Data
public class MemberVO {
	private int 	id;
	private String 	userid;
	private String 	password;
	private String 	username;
	private String 	phone;
	private String 	email;
	private String 	postcode;
	private String 	addr1;
	private String 	addr2;
	private int 	use;
	// 테이블에는 없지만 로그인 화면에서 사용하기 위해서 등록 
	private boolean saveID;
}
