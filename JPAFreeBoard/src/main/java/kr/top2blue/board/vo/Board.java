package kr.top2blue.board.vo;

import java.util.Date;

import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Board {
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "generator", sequenceName = "board_id_seq", allocationSize = 1)
	private Long id;
	private String name;
	private String password;
	private String subject;
	private String content;
	@Column(name = "regdate", updatable = false)
	@LastModifiedDate
	private Date   regdate;
	// id를 뺀 생성자
	public Board(String name, String password, String subject, String content,Date regdate) {
		this.name = name;
		this.password = password;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
	}
}
