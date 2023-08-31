package kr.human.jackson.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PersonVO1 {
	private String name;
	private int age;
	private boolean gender;
}
