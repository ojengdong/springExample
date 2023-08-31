package kr.kdata.poll;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import kr.kdata.poll.dao.PollDAO;
import kr.kdata.poll.vo.PollVO;

@SpringBootTest
class MybatisEx03PollApplicationTests {

//	VO 정상 작동 테스트
	@Test
	void contextLoads() {
		PollVO pollVO = new PollVO();
	      List<String> items = Arrays.asList("한놈,두식이,석삼,너구리".split(","));
	      List<Integer> counts = Arrays.asList(11,22,33,44);
	      pollVO.setItems(items);
	      pollVO.setCounts(counts);
	      System.out.println("저장결과 : " + pollVO);
	      System.out.println("총투표수 : " + pollVO.getTotalCounts() + "표");
	      System.out.println("항목 : " + pollVO.getItems());
	      System.out.println("투표 : " + pollVO.getCounts());
	}
	
//	DAO 작동 테스트
	@Autowired
	private PollDAO pollDAO;
	
	@Test
	public void datTest() {
		System.out.println("-".repeat(60));
		System.out.println("목록얻기");
		System.out.println(pollDAO.selectList());
		System.out.println("-".repeat(60));
		System.out.println("1개얻기");
		PollVO pollVO = pollDAO.selectById(1);
		System.out.println("바꾸기 전 : " + pollVO);
		System.out.println("-".repeat(60));
//		1번 항목의 투표수를 증가
		List<Integer> counts = pollVO.getCounts(); // 리스트를 얻어서 
		counts.set(0, counts.get(0)+1); // 리스트 값을 변경하고
		pollVO.setCounts(counts); // 다시 붙여서 저장
		
		pollDAO.updatePoll(pollVO); // 수정
		
		System.out.println("바뀐내용 : " + pollDAO.selectById(1));
	}

}
