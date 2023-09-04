package kr.kdata.pds.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.pds.vo.Board2VO;

@Mapper
public interface Board2DAO {
	//<!-- 1. 전체 개수 얻기  -->
	int selectCount();
	//<!-- 2. 1개 얻기  -->
	Board2VO selectById(int id);
	//<!-- 3. 1페이지 얻기  -->
	List<Board2VO> selectList(HashMap<String, Integer> map);
	//<!-- 4. 저장하기  -->
	void insert(Board2VO board2vo);
	//<!-- 5. 수정하기  -->
	void update(Board2VO board2vo);
	//<!-- 6. 삭제하기  -->
	void delete(int id);
	//<!-- 7. 조회수 증가하기  -->
	void updateReadCount(int id);
}
