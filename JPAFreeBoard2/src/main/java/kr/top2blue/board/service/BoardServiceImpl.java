package kr.top2blue.board.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.top2blue.board.dao.BoardPagingRepository;
import kr.top2blue.board.dao.BoardRepository;
import kr.top2blue.board.vo.Board;
import kr.top2blue.board.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Service("boardService")
@Slf4j
public class BoardServiceImpl implements BoardService{

   @Autowired
   private BoardRepository boardRepository;

   // 목록보기
   @Override
   public List<Board> selectList() {
      // 첫줄에 받은 데이터 로그로 출력
      log.info("서비스의 selectList() 호출 ");
      // 두번째 줄에는 리턴될 자료형을 선언 
      List<Board> list = new ArrayList<>();
      
      // 로직을 수행한다.
      list = boardRepository.findAllByOrderByIdDesc();
      
      // 리턴 바로 전에 리턴되는 값을 로그로 출력
      log.info("서비스의 selectList() 리턴 : {}", list);
      // 마지막 줄에는 리턴
      return list;
   }

   @Override
   public boolean insert(Board board) {
      // 첫줄에 받은 데이터 로그로 출력
      log.info("서비스의 insert({}) 호출 ", board);
      // 두번째 줄에는 리턴될 자료형을 선언 
      boolean isSave = false;
      try {
         if(board!=null) {
            board.setRegdate(new Date());
            boardRepository.save(board);
            isSave = true;
         }
      }catch (Exception e) {
         e.printStackTrace();
      }
      // 리턴 바로 전에 리턴되는 값을 로그로 출력
      log.info("서비스의 insert({}) 리턴 : {}", board, isSave);
      return isSave;
   }
   // 1개얻기
   @Override
   public Board selectById(Long id) {
      Optional<Board> optional = boardRepository.findById(id); // 글1개 얻기
      if(optional.isPresent()) { // 존재한다면
         return optional.get(); // 글을 리턴
      }
      return null; // 없으면 null 리턴
   }
   // 수정 완료
   @Override
   public boolean update(Board board) {
      // DB에서 해당 글을 읽어와 넘어온 비번과 비번이 일치할떄만 수정을 수행한다.
      log.info("서비스의 update({}) 호출 ", board);
      boolean result = false;
      // 1.DB에서 해당 글번호의 글을 읽자.
      Optional<Board> optional = boardRepository.findById(board.getId()); // 글1개 얻기
      if(optional.isPresent()) { // 존재한다면
         Board dbBoard = optional.get(); // 글을 읽고
         // 해당 글을 읽어와 넘어온 비번과 비번이 일치할떄만 
         if(dbBoard.getPassword().equals(board.getPassword())) {
            // 수정을 처리한다.
            board.setRegdate(new Date()); // 작성일을 수정한 날짜로 변경
            boardRepository.save(board);
            result = true;
         }
      }
      log.info("서비스의 update({}) 리턴 : {}", board, result);
      return result;
   }
   // 삭제 완료
   @Override
   public boolean delete(Board board) {
      // DB에서 해당 글을 읽어와 넘어온 비번과 비번이 일치할떄만 수정을 수행한다.
      log.info("서비스의 delete({}) 호출 ", board);
      boolean result = false;
      // 1.DB에서 해당 글번호의 글을 읽자.
      Optional<Board> optional = boardRepository.findById(board.getId()); // 글1개 얻기
      if(optional.isPresent()) { // 존재한다면
         Board dbBoard = optional.get(); // 글을 읽고
         // 해당 글을 읽어와 넘어온 비번과 비번이 일치할떄만 
         if(dbBoard.getPassword().equals(board.getPassword())) {
            // 삭제를 처리한다.
            boardRepository.deleteById(board.getId());
            result = true;
         }
      }
      log.info("서비스의 delete({}) 리턴 : {}", board, result);
      return result;
   }

   // 페이징을 지원하는 DAO
   @Autowired
   BoardPagingRepository repository;
   
   @Override
   public Paging<Board> selectList2(int p, int s, int b) {
      int totalCount = (int)boardRepository.count(); // 전체 개수 구하고
      // 이순간 모든 계산이 완료된다.
      Paging<Board> paging = new Paging<>(totalCount, p, s, b);
      
      // 정렬 기준 !!!
      Sort sort = Sort.by("id").descending(); //ID 역순의 정렬 객체
      
      // 얻을 Pageable 객체 선언
      Pageable pageable = 
            // PageRequest.of (현재페이지, 페이지당 글수)
            // PageRequest.of (현재페이지, 페이지당 글수, 정렬기준)
            PageRequest.of(paging.getCurrentPage()-1, paging.getSizeOfPage(),sort);
      // 1페이지 분량의 데이터만 얻자
      Page<Board> page = repository.findAll(pageable);
      // 많은 정보를 가지고 있는 page객체에서 필요한 글목록만 얻어오자
      List<Board> list = page.getContent();
      // 페이징 객체에 주입
      paging.setList(list);
      
      return paging;
   }
}
