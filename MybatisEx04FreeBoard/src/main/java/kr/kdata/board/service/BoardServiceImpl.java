package kr.kdata.board.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.board.dao.BoardDAO;
import kr.kdata.board.dao.CommentDAO;
import kr.kdata.board.vo.BCommentVO;
import kr.kdata.board.vo.BoardVO;
import kr.kdata.board.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Service("boardService")
@Slf4j
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Autowired
	private CommentDAO commentDAO;

	@Override
	public Paging<BoardVO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		log.info("selectList 호출 : {},{},{}", currentPage, sizeOfPage, sizeOfBlock);
		Paging<BoardVO> paging = null;
		//-----------------------------------------------------------------------------
		// 1. 페이지 계산을 위하여 전체개수를 구한다.
		int totalCount = boardDAO.selectCount();
		// 2. 페이지 계산을 한다.
		paging = new Paging<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
		// 3. 한페이지 분량의 글 목록을 가져온다.
		HashMap<String, Integer> map = new HashMap<>();
		map.put("startNo", paging.getStartNo());
		map.put("endNo", paging.getEndNo());
		List<BoardVO> list = boardDAO.selectList(map);
		// 4. 리스트의 각각의 대한 댓글의 개수를 가져온다.
		if(list!=null) {
			for(BoardVO vo : list) {
				vo.setCommentCount(commentDAO.selectCountByRef(vo.getId())); // 댓글개수 넣기
			}
		}
		// 5. 글목록을 페이징 객체에 넣어준다.
		paging.setList(list);
		//-----------------------------------------------------------------------------
		log.info("selectList 리턴 : {}", paging);
		return paging;
	}
	// 저장
	@Override
	public boolean insert(BoardVO boardVO) {
		log.info("insert 호출 : {}", boardVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(boardVO!=null) {
			// 이름이 있다면
			if (boardVO.getName()!=null && boardVO.getName().trim().length()>0) {
				// 비번이 있다면
				// 제목이 있다면
				// 내용이 있다면....
				boardDAO.insert(boardVO);
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("insert 리턴 : {}", result);
		return result;
	}
	// 내용보기
	@Override
	public BoardVO view(int id) {
		log.info("view 호출 : {}", id);
		BoardVO boardVO = null;
		//-----------------------------------------------------------------------------
		// 1. 해당 번호의 글을 읽어온다.
		boardVO = boardDAO.selectById(id);
		// 2. 해당 글이 존재한다면 댓글들을 모두 가져온다.
		if(boardVO!=null) {
			List<BCommentVO> commentList = commentDAO.selectListByRef(id);
			// 3. 댓글을 VO에 넣어준다.
			boardVO.setCommentList(commentList);
		}
		//-----------------------------------------------------------------------------
		log.info("view 리턴 : {}", boardVO);
		return boardVO;
	}

	@Override
	public boolean update(BoardVO boardVO) {
		log.info("update 호출 : {}", boardVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(boardVO!=null) {
			// 비번이 같다면 수정을 한다.
			BoardVO dbVO = boardDAO.selectById(boardVO.getId());
			if (dbVO!=null && dbVO.getPassword().equals(boardVO.getPassword())) {
				boardDAO.update(boardVO);
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("update 리턴 : {}", result);
		return result;
	}

	@Override
	public boolean delete(BoardVO boardVO) {
		log.info("update 호출 : {}", boardVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		// 객체가 존재하며
		if(boardVO!=null) {
			// 비번이 같다면 삭제를 한다.
			BoardVO dbVO = boardDAO.selectById(boardVO.getId());
			if (dbVO!=null && dbVO.getPassword().equals(boardVO.getPassword())) {
				// 원본글 삭제
				boardDAO.delete(boardVO.getId());
				// 원본에 대한 모든 댓글 삭제
				commentDAO.deleteByRef(boardVO.getId());
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("update 리턴 : {}", result);
		return result;
	}

	@Override
	public boolean commentInsert(BCommentVO bCommentVO) {
		log.info("commentInsert 호출 : {}", bCommentVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		if(bCommentVO!=null) {
			commentDAO.insert(bCommentVO);
			result = true;
		}
		//-----------------------------------------------------------------------------
		log.info("commentInsert 리턴 : {}", result);
		return result;
	}

	@Override
	public boolean commentUpdate(BCommentVO bCommentVO) {
		log.info("commentUpdate 호출 : {}", bCommentVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		if(bCommentVO!=null) {
			// 비번이 같을때만 수정해한다.
			BCommentVO dbVO = commentDAO.selectById(bCommentVO.getId());
			if(dbVO!=null && dbVO.getPassword().equals(bCommentVO.getPassword())) {
				commentDAO.update(bCommentVO);
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("commentUpdate 리턴 : {}", result);
		return result;
	}

	@Override
	public boolean commentDelete(BCommentVO bCommentVO) {
		log.info("commentDelete 호출 : {}", bCommentVO);
		boolean result = false;
		//-----------------------------------------------------------------------------
		if(bCommentVO!=null) {
			// 비번이 같을때만 삭제해한다.
			BCommentVO dbVO = commentDAO.selectById(bCommentVO.getId());
			if(dbVO!=null && dbVO.getPassword().equals(bCommentVO.getPassword())) {
				commentDAO.delete(bCommentVO.getId());
				result = true;
			}
		}
		//-----------------------------------------------------------------------------
		log.info("commentDelete 리턴 : {}", result);
		return result;
	}
	// 1개얻기
	@Override
	public BoardVO selectById(int id) {
		return boardDAO.selectById(id);
	}
	
	
}
