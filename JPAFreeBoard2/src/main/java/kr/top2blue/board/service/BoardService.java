package kr.top2blue.board.service;

import java.util.List;

import kr.top2blue.board.vo.Board;
import kr.top2blue.board.vo.Paging;

public interface BoardService {
	// 1. 목록보기
	List<Board> selectList();
	
//	-----------------------------
//	페이징이 처리된 내용을 받자
	Paging<Board> selectList2(int p, int s, int b);
	
//	-----------------------------
	
	// 2. 새글쓰기
	boolean insert(Board board);
	// 3. 내용보기
	Board selectById(Long id);
	// 4. 수정하기
	boolean update(Board board);
	// 5. 삭제하기
	boolean delete(Board board);
}
