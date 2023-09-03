package kr.kdata.board.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.board.vo.BoardVO;

@Mapper
public interface BoardDAO {
	//--------------------------------------------
	// mapper파일의 태그 1개당 메서드 1개
	// resultType이 리턴 타입
	// parameterType이 매개변수 타입
	// id가 메서드명
	//--------------------------------------------
	
	// 1. 전체 개수 얻기 : 페이징
	int selectCount();
	
	// 2. 1개 얻기 : 내용보기/수정/삭제
	BoardVO  selectById(int id);
	
	// 3. 1페이지 얻기 : 목록보기
	List<BoardVO> selectList(HashMap<String, Integer> map);
	
	// 4. 저장하기
	void insert(BoardVO boardVO);
	
	// 5. 수정하기
	void update(BoardVO boardVO);
	
	// 6. 삭제하기 
	void delete(int id);
}
