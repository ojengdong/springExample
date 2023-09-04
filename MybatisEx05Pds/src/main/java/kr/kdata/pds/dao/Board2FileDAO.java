package kr.kdata.pds.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.pds.vo.Board2FileVO;

@Mapper
public interface Board2FileDAO {
	// <!-- 1. 지정 ref의 모든 파일목록 얻기 -->
	List<Board2FileVO> selectByRef(int ref);
	// <!-- 2. 저장 -->
	// 저장
	void insert(Board2FileVO board2FileVO);
	// 수정
	void insert2(Board2FileVO board2FileVO);
	// <!-- 3. 지정 ref의 모든 파일 지우기 -->
	void deleteByRef(int ref);
	// <!-- 4. 지정 id의 파일 지우기 -->
	void deleteById(int id);
	// <!-- 5. 1개 얻기 -->
	Board2FileVO selectById(int id);
}
