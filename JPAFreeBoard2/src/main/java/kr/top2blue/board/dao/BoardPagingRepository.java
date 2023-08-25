package kr.top2blue.board.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.top2blue.board.vo.Board;

public interface BoardPagingRepository extends JpaRepository<Board, Long> {

}
