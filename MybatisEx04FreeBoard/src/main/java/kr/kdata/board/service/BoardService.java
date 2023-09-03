package kr.kdata.board.service;

import kr.kdata.board.vo.BCommentVO;
import kr.kdata.board.vo.BoardVO;
import kr.kdata.board.vo.Paging;

public interface BoardService {
	// 기능 1개당 메서드 1개
	
	// 1. 목록보기
	Paging<BoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	// 3. 새글쓰기
	boolean insert(BoardVO boardVO);
	// 2. 내용보기
	BoardVO view(int id);
	// 4. 수정하기
	boolean update(BoardVO boardVO);
	// 5. 삭제하기
	boolean delete(BoardVO boardVO);
	// 6. 댓글 쓰기
	boolean commentInsert(BCommentVO bCommentVO);
	// 7. 댓글 수정
	boolean commentUpdate(BCommentVO bCommentVO);
	// 8. 댓글 삭제
	boolean commentDelete(BCommentVO bCommentVO);
	// 9. 1개 얻기
	BoardVO selectById(int id);
	
}
