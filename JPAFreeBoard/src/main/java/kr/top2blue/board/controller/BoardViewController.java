package kr.top2blue.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.top2blue.board.service.BoardService;
import kr.top2blue.board.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Controller // 뷰(html)를 통하여 출력하는 컨트롤러
@Slf4j
public class BoardViewController {
	
	@Autowired
	private BoardService boardService;
	
	@GetMapping(value = {"/","/list"})
	public String selectList(Model model){
		log.info("BoardViewController의 selectList() 호출!!!");
		List<Board> list = null;
		// 결과 얻기
		list = boardService.selectList();
		model.addAttribute("list", list);		
		log.info("BoardViewController의 selectList() 리턴 : {}", list);
		// html 파일이름을 리턴한다.
		return "index";
	}
	
	@GetMapping("/insert")
	public String insert() {
		// 그냥 비어있는 폼을 띄우기만 할거면 아무 내용없이 html이름만 리턴한다.
		return "insert";
	}
	
	// Get방식으로 접근시 목록보기로 강제 이동시킨다.
	@GetMapping("/insertOk") // 누군가가 주소표시에서 직접 insertOk를 입력했다면 --- 장난쳤다면
	public String insertOkGet() {
		return "redirect:/";
	}
	
	@PostMapping("/insertOk") // Post전송일때만 처리
	public String insertOkPost(@ModelAttribute Board board) {
		if(board!=null) boardService.insert(board);
		return "redirect:/";
	}
	
	// 내용보기
	@GetMapping("/view")
	public String view(@RequestParam(required = false) Long id, Model model) {
		// 글번호 1개를 받아 해당 글이 존재할때 내용보기로 가고 글이 없으면 목록으로 간다.
		// 1. 글번호가 넘어왔으면 처리하고 없으면 목록으로 보낸다.
		if(id!=null) {
			// 글번호가 있다면 글을 읽어온다.
			Board board = boardService.selectById(id);
			if(board!=null) { // 글이 있다면
				model.addAttribute("board", board);
				// 뷰페이지로 간다.
				return "view";
			}
		}
		// 글번호가 없거나 해당 글번호의 글이 없다면 목록으로
		return "redirect:/";
	}
	// 수정하기 폼
	@GetMapping("/update")
	public String update(@RequestParam(required = false) Long id, Model model) {
		// 글번호 1개를 받아 해당 글이 존재할때 수정하기로 가고 글이 없으면 목록으로 간다.
		// 1. 글번호가 넘어왔으면 처리하고 없으면 목록으로 보낸다.
		if(id!=null) {
			// 글번호가 있다면 글을 읽어온다.
			Board board = boardService.selectById(id);
			if(board!=null) { // 글이 있다면
				model.addAttribute("board", board);
				// 수정페이지로 간다.
				return "update";
			}
		}
		// 글번호가 없거나 해당 글번호의 글이 없다면 목록으로
		return "redirect:/";
	}
	
	// 삭제하기 폼
	@GetMapping("/delete")
	public String delete(@RequestParam(required = false) Long id, Model model) {
		// 글번호 1개를 받아 해당 글이 존재할때 삭제하기로 가고 글이 없으면 목록으로 간다.
		// 1. 글번호가 넘어왔으면 처리하고 없으면 목록으로 보낸다.
		if(id!=null) {
			// 글번호가 있다면 글을 읽어온다.
			Board board = boardService.selectById(id);
			if(board!=null) { // 글이 있다면
				model.addAttribute("board", board);
				// 삭제페이지로 간다.
				return "delete";
			}
		}
		// 글번호가 없거나 해당 글번호의 글이 없다면 목록으로
		return "redirect:/";
	}
	
	// 수정하기 완료
	@GetMapping("/updateOk") // Get방식으로 접근시 무시하고 무조건 목록으로 간다.
	public String updateOkGet() {
		return "redirect:/";
	}
	
	@PostMapping("/updateOk") // Post방식으로 접근시 만 수정 처리를 하도록 한다.
	public String updateOkPost(@ModelAttribute Board board) {
		log.info("수정하기 호출 : {}", board);
		if(board!=null) {
			if(boardService.update(board)) { // 수정을 완료하고
				log.info("수정 완료!!!");
				return "redirect:/view?id="+board.getId(); // 내용보기로 간다.
			}else {
				log.info("수정 실패!!!");
			}
		}
		return "redirect:/";
	}
	
	// 삭제하기 완료
	@GetMapping("/deleteOk") // Get방식으로 접근시 무시하고 무조건 목록으로 간다.
	public String deleteOkGet() {
		return "redirect:/";
	}
	
	@PostMapping("/deleteOk") // Post방식으로 접근시 만 삭제 처리를 하도록 한다.
	public String deleteOkPost(@ModelAttribute Board board) {
		log.info("삭제하기 호출 : {}", board);
		if(board!=null) {
			if(boardService.delete(board)) { // 삭제를 완료하고
				log.info("삭제 완료!!!");
			}else {
				log.info("삭제 실패!!!");
			}
		}
		return "redirect:/";
	}
}
