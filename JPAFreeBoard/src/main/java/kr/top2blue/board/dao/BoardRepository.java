package kr.top2blue.board.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import kr.top2blue.board.vo.Board;

public interface BoardRepository extends CrudRepository<Board, Long>{

	// findAll() : 오름 차순으로 가져온다.
	// findByOrderBy필드명[Asc|Desc]필드명[Asc|Desc]필드명[Asc|Desc]....
	// findAllByOrderBy필드명[Desc]
	List<Board> findAllByOrderByIdDesc(); // 글번호 내림차순
	// 이름 오름차순, 이름이 같으면 제목 내림차순
	// List<Board> findAllByOrderByNameAscSubjectDesc(); 
}
