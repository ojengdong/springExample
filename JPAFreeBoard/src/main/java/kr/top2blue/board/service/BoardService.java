package kr.top2blue.board.service;

import java.util.List;

import kr.top2blue.board.vo.Board;

public interface BoardService {
	// 1. 목록보기
	List<Board> selectList();
	// 2. 새글쓰기
	boolean insert(Board board);
	// 3. 내용보기
	Board selectById(Long id);
	// 4. 수정하기
	boolean update(Board board);
	// 5. 삭제하기
	boolean delete(Board board);
}
