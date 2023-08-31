package kr.kdata.poll.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.poll.dao.PollDAO;
import kr.kdata.poll.vo.PollVO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PollService {

   @Autowired
   private PollDAO pollDAO;

   // 투표 목록 보기
   public List<PollVO> getList() {
      log.info("서비스 getList() 호출");
      List<PollVO> list = null;
      // -------------------------------------------------------
      try {

         list = pollDAO.selectList();

      } catch (Exception e) {
         e.printStackTrace();
      }
      // -------------------------------------------------------
      log.info("서비스 getList() 리턴 : {}", list);
      return list;
   }

   // 투표 1개 얻기
   public PollVO getPoll(int id) {
      log.info("서비스 getList() 호출");
      PollVO pollVO = null;
      // -------------------------------------------------------
      try {

         pollVO = pollDAO.selectById(id);

      } catch (Exception e) {
         e.printStackTrace();
      }
      // -------------------------------------------------------
      log.info("서비스 getList() 리턴 : {}", pollVO);
      return pollVO;
   }

   // 투표 결과 얻기
   public void pollUpdate(Integer id, Integer poll) {
      log.info("서비스 pollUpdate({},{}) 호출", id, poll);

      // -------------------------------------------------------
      try {
         // 1. 해당번호의 투표를 읽어온다.
         PollVO pollVO = pollDAO.selectById(id);

         if (pollVO != null) { // 해당 투표가 있을 경우에만
            // 2.가져온 투표에서 투표수를 읽는다.
            List<Integer> counts = pollVO.getCounts();
            // 3. 투표한 번호의 숫자를 1 증가 시킨다.
            counts.set(poll, counts.get(poll) + 1);
            // 4. 변경된 리스트를 VO에 넣어준다.
            pollVO.setCounts(counts);
            // 5. 변경된 VO로 DB내용을 갱신한다.
            pollDAO.updatePoll(pollVO);

         }

      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   // 새로운 투표 저장하기 
   public boolean insert(PollVO pollVO) {
	   log.info("서비스 insert(){} 호출",pollVO);
	   boolean isInsert = false;
	   
	// -------------------------------------------------------
	   
	   try {
	        if(pollVO != null) {
	        	pollDAO.insert(pollVO);
	        }
	        isInsert = true;

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	   log.info("서비스 insert(){} 호출",pollVO);
	   return isInsert;
   }


}