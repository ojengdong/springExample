package kr.kdata.board.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.board.vo.BCommentVO;

@Mapper
public interface CommentDAO {
	
	int selectCountByRef(int ref);
	List<BCommentVO> selectListByRef(int ref);
	void insert(BCommentVO vo);
	void update(BCommentVO vo);
	void delete(int id);
	void deleteByRef(int ref);
	BCommentVO selectById(int id);
}
