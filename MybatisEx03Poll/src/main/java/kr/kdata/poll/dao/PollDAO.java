package kr.kdata.poll.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.kdata.poll.vo.PollVO;

@Mapper
public interface PollDAO {
	List<PollVO> selectList();
	PollVO selectById(Integer id);
	void updatePoll(PollVO pollVO);
	void insert(PollVO pollVO);
}
