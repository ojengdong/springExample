package kr.top2blue.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.top2blue.board.service.BoardService;
import kr.top2blue.board.vo.Board;
import lombok.extern.slf4j.Slf4j;

@RestController // 뷰가 없이 내용을 직접 출력하는 컨트롤러
@Slf4j
@RequestMapping(value = "/api") // 앞으로 생성하는 모든 주소 앞에 /api를 붙여준다.
public class BoardApiController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/","/list"})
	public List<Board> selectList(){
		log.info("BoardApiController의 selectList() 호출!!!");
		List<Board> list = null;
		// 결과 얻기
		list = boardService.selectList();
				
		log.info("BoardApiController의 selectList() 리턴 : {}", list);
		return list;
	}
	
}
