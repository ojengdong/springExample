package kr.kdata.mybatis.dao;

import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.mybatis.vo.TestVO;

@Mapper
public interface TestDAO {
	// resultType이 리턴타입이고
	// 메서드명이 id와 일치해야하고
	// paramaeterType이 ()안의 매개변수여야 한다.
	String selectNowString();
	Date   selectNowDate();
	int    selectDouble(int num);
	int    selectMul(HashMap<String, Integer> map);
	HashMap<String, Object>    selectSumMul(HashMap<String, Integer> map);
	TestVO selectSumMul2(TestVO TestVO);
}
