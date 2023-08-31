package kr.human.jackson.vo;

import java.util.List;

import lombok.Data;

@Data
public class MyDataVO2 {
	private List<String> names;
	private ObjectVO object;
	
	
	@Data
	public static class ObjectVO{
		private double data1;
		private int data2;
	}
}
