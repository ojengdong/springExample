package kr.kdata.mybatis.service;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.mybatis.dao.TestDAO;
import kr.kdata.mybatis.vo.TestVO;

@Service
public class TestService {
	@Autowired
	private TestDAO testDAO;
	
	public String getToday1() {
		return testDAO.selectNowString(); // DB 날짜를 String으로 가져오기
	}
	
	public Date getToday2() {
		return testDAO.selectNowDate(); // DB 날짜를 Date으로 가져오기
	}
	
	public int getDouble(int num) {
		return testDAO.selectDouble(num); // 정수 1개를 넘겨줘야 한다.
	}
	
	public int getMul(int num1, int num2) {
		HashMap<String, Integer>map = new HashMap<>();
		map.put("num1",num1); // 키값 mapper에서 사용한 변수명과 일치해야 한
		map.put("num2",num2); // 
		return testDAO.selectMul(map); // 정수 2개 HashMap에 담아 넘겨줘야한다.
	}
	
	public HashMap<String, Object>    getSumMul(int n1, int n2){
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", n1); // 키값은 mapper에서 사용한 변수명과 일치해야 한다.
		map.put("num2", n2);
		return testDAO.selectSumMul(map); // value는 모든 자료형을 받아야 하므로 Object타입이어야 한다. 
		
	}
	
	   public TestVO    getSumMul2(int n1, int n2){
		      TestVO testVO = new TestVO();
		      testVO.setNum1(n1);
		      testVO.setNum2(n2);
		      return testDAO.selectSumMul2(testVO); 
		   }
	
}
