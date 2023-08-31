package kr.human.jackson.vo;

import java.util.List;

import lombok.Data;

@Data
public class MyDataVO {
	private String string;
	private int number;
	private List<Integer> array;
	private InnerData object;
	
	@Data
	static class InnerData{
		private String property;
		private InnerData2 subobj;
	}

	@Data
	static class InnerData2{
		private String[] arr;
		private int numero;
	}
}
