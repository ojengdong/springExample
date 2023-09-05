package kr.kdata.pds.service;

import kr.kdata.pds.vo.Board2VO;
import kr.kdata.pds.vo.Paging;

public interface Board2Service {
	// 목록보기
	Paging<Board2VO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock);
	// 내용보기
	Board2VO selectById(int id, int mode); // mode는 조회수 증가여부
	// 저장하기
	boolean insert(Board2VO board2vo);
	// 수정하기
	boolean update(Board2VO board2vo, String delList, String filePath);
	// 삭제하기
	boolean delete(Board2VO board2vo, String filePath);
}
