package kr.kdata.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kdata.board.service.BoardService;
import kr.kdata.board.vo.BCommentVO;
import kr.kdata.board.vo.BoardVO;
import kr.kdata.board.vo.CommVO;
import kr.kdata.board.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/board")
@Slf4j
public class BoardController {

	@Autowired
	private BoardService boardService;
	//-----------------------------------------------------------------------------------------
	// 목록보기
	@RequestMapping(value = {"/","/list"})
	public String getList(@ModelAttribute CommVO cv, Model model) {
		Paging<BoardVO> paging = boardService.selectList(cv.getP(), cv.getS(), cv.getB());
		model.addAttribute("pv", paging);
		model.addAttribute("cv", cv);
		return "board/list";
	}
	//-----------------------------------------------------------------------------------------
	// 저장하기
	// 입력폼
	@GetMapping(value = "/insert")
	public String insert(@ModelAttribute CommVO cv, Model model) {
		model.addAttribute("cv", cv);
		return "board/insert";
	}
	// 입력내용 저장
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/insertOk")
	public String insertOkGet() {
		return "redirect:/board/"; 
	}
	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/insertOk")
	public String insertOkPost(@ModelAttribute CommVO cv, @ModelAttribute BoardVO vo) {
		// 1. 서비스를 호출하여 저장을 수행한다.
		if(vo!=null) {
			boardService.insert(vo);
		}
		// 2. 1페이지로 이동한다.
		return "redirect:/board/?p=1&s=" + cv.getS() + "&b=" + cv.getB();
	}
	//-----------------------------------------------------------------------------------------
	// 수정하기
	// 입력폼
	@GetMapping(value = "/update")
	public String update(@ModelAttribute CommVO cv, Model model) {
		model.addAttribute("cv", cv);
		model.addAttribute("board", boardService.selectById(cv.getId()));
		return "board/update";
	}
	// 수정내용 저장
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/updateOk")
	public String updateOkGet() {
		return "redirect:/board/"; 
	}
	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/updateOk")
	public String updateOkPost(@ModelAttribute CommVO cv, @ModelAttribute BoardVO vo) {
		// 1. 서비스를 호출하여 수정을 수행한다.
		if(vo!=null) {
			boardService.update(vo);
		}
		// 2. 1페이지로 이동한다.
		return "redirect:/board/view?id="+cv.getId()+"&p="+cv.getP()+"&s=" + cv.getS() + "&b=" + cv.getB();
	}
	//-----------------------------------------------------------------------------------------
	// 삭제하기
	// 삭제폼
	@GetMapping(value = "/delete")
	public String delete(@ModelAttribute CommVO cv, Model model) {
		model.addAttribute("cv", cv);
		model.addAttribute("board", boardService.selectById(cv.getId()));
		return "board/delete";
	}
	// 내용 삭제
	// GET 요청시는 처리하지 않고 목록으로 간다.
	@GetMapping(value = "/deleteOk")
	public String deleteOkGet() {
		return "redirect:/board/"; 
	}
	// POST요청시만 저장을 수행한다.
	@PostMapping(value = "/deleteOk")
	public String deleteOkPost(@ModelAttribute CommVO cv, @ModelAttribute BoardVO vo) {
		// 1. 서비스를 호출하여 삭제를 수행한다.
		if(vo!=null) {
			boardService.delete(vo);
		}
		// 2. 1페이지로 이동한다.
		return "redirect:/board/?p="+cv.getP()+"&s=" + cv.getS() + "&b=" + cv.getB();
	}
	//-----------------------------------------------------------------------------------------
	// 내용보기
	@GetMapping(value = "/view")
	public String view(@ModelAttribute CommVO cv, Model model) {
		BoardVO boardVO = boardService.view(cv.getId());
		if(boardVO!=null) {
			model.addAttribute("cv", cv);
			model.addAttribute("vo", boardVO);
			// 줄바꿈 처리를 위해 두개의 변수를 추가한다.
			model.addAttribute("br", "<br>");
			model.addAttribute("newLine", "\n");
			return "board/view";
		}else {
			return "redirect:/board/"; 
		}
	}
	
	
	
	
	//-----------------------------------------------------------------------------------------
	// 댓글저장
	@PostMapping(value = "/commentInsert")
	@ResponseBody
	public boolean commentInsert(@ModelAttribute BCommentVO vo) {
		log.info("댓글 저장 호출 : {}", vo);
		boolean result = false;
		try {
			result = boardService.commentInsert(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("댓글 저장 리턴 : {}", result);
		return result;
	}
	//-----------------------------------------------------------------------------------------
	// 댓글수정
	@PutMapping(value = "/commentUpdate")
	@ResponseBody
	public boolean commentUpdate(@ModelAttribute BCommentVO vo) {
		log.info("댓글 수정 호출 : {}", vo);
		boolean result = false;
		try {
			result = boardService.commentUpdate(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("댓글 수정 리턴 : {}", result);
		return result;
	}
	//-----------------------------------------------------------------------------------------
	// 댓글삭제
	@DeleteMapping(value = "/commentDelete")
	@ResponseBody
	public boolean commentDelete(@ModelAttribute BCommentVO vo) {
		log.info("댓글 삭제 호출 : {}", vo);
		boolean result = false;
		try {
			result = boardService.commentDelete(vo);
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("댓글 삭제 리턴 : {}", result);
		return result;
	}
	
	
}











