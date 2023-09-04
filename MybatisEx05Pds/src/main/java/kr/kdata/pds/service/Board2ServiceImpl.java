package kr.kdata.pds.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.pds.dao.Board2DAO;
import kr.kdata.pds.dao.Board2FileDAO;
import kr.kdata.pds.vo.Board2FileVO;
import kr.kdata.pds.vo.Board2VO;
import kr.kdata.pds.vo.Paging;
import lombok.extern.slf4j.Slf4j;

@Service("board2Service")
@Slf4j
public class Board2ServiceImpl implements Board2Service {

	@Autowired
	private Board2DAO board2dao;
	
	@Autowired
	private Board2FileDAO board2FileDAO;

	@Override
	public Paging<Board2VO> selectList(int currentPage, int sizeOfPage, int sizeOfBlock) {
		log.info("selectList({},{},{}) 호출!!!", currentPage,sizeOfPage,sizeOfBlock);
		Paging<Board2VO> paging = null;
		//------------------------------------------------------------------------------------
		try {
			// 1. 전체 개수를 구한다.
			int totalCount = board2dao.selectCount();
			// 2. 페이지 계산을 한다.
			paging = new Paging<>(totalCount, currentPage, sizeOfPage, sizeOfBlock);
			// 3. 1페이지 분량의 글목록을 가져온다.
			if(totalCount>0) {
				HashMap<String, Integer> map = new HashMap<>();
				map.put("startNo", paging.getStartNo());
				map.put("endNo", paging.getEndNo());
				// 1페이지 얻기
				List<Board2VO> list = board2dao.selectList(map);
				// 각각의 글에 첨부 파일을 구해서 넣어준다.
				if(list!=null) {
					for(Board2VO vo : list) {
						vo.setFileList(board2FileDAO.selectByRef(vo.getId()));
					}
				}
				// 페이빙 객체에 글 목록을 넣어준다.
				paging.setList(list);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//------------------------------------------------------------------------------------
		log.info("selectList({},{},{}) 리턴 :", currentPage,sizeOfPage,sizeOfBlock, paging);
		return paging;
	}

	@Override
	public Board2VO selectById(int id, int mode) {
		log.info("selectById({},{}) 호출!!!", id, mode);
		Board2VO vo = null;
		//------------------------------------------------------------------------------------
		try {
			vo = board2dao.selectById(id);
			if(vo!=null) {
				if(mode==1) {
					board2dao.updateReadCount(id); // 조회수 증가 : DB의 조회수 증가
				}
				// 이미 가져온 VO는 조회수가 증가전이다.
				// 다시 DB에서 가져오면 속도가 떨어진다. 
				// 그냥 조회수를 1증가 시키는것이 더 효율적이다.
				vo.setReadCount(vo.getReadCount()+1);
				vo.setFileList(board2FileDAO.selectByRef(id));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		//------------------------------------------------------------------------------------
		log.info("selectById({},{}) 리턴 : {}", id, mode, vo);
		return vo;
	}

	@Override
	public boolean insert(Board2VO board2vo) {
		log.info("insert({}) 호출!!!", board2vo);
		boolean result = false;
		//------------------------------------------------------------------------------------
		if(board2vo!=null) {
			// 글 저장
			board2dao.insert(board2vo);
			// 파일 저장
			for(Board2FileVO vo : board2vo.getFileList()) {
				board2FileDAO.insert(vo);
			}
		}
		
		//------------------------------------------------------------------------------------
		log.info("insert({}) 리턴 : {} !!!", board2vo, result);		
		return result;
	}

	@Override
	public boolean update(Board2VO board2vo, String delList, String filePath) {
		log.info("update({},{},{}) 호출!!!", board2vo, delList, filePath);
		boolean result = false;
		//------------------------------------------------------------------------------------
		if(board2vo!=null) {
			// 글 수정
			board2dao.update(board2vo);
			// 파일 저장
			for(Board2FileVO vo : board2vo.getFileList()) {
				board2FileDAO.insert2(vo);
			}
			// 삭제 파일을 삭제한다.
			if(delList!=null && delList.length()>0) {
				String[] delFile = delList.trim().split(" ");
				if(delFile!=null && delFile.length>0) {
					for(String s : delFile) {
						// 파일 삭제
						// 1. 서버에 저장된 파일 삭제
						Board2FileVO board2FileVO = board2FileDAO.selectById(Integer.parseInt(s));
						String fileName = board2FileVO.getUuid() + "_" + board2FileVO.getFileName();
						File file = new File(filePath, fileName);
						if(!file.exists()){ // 파일이 존재하면
							file.delete(); // 파일 삭제
						}
						// 2. db의 정보를 삭제
						board2FileDAO.deleteById(Integer.parseInt(s));
					}
				}
			}
		}
		
		//------------------------------------------------------------------------------------
		log.info("update({},{},{}) 리턴 : {} !!!", board2vo,delList,filePath, result);		
		return result;
	}

	@Override
	public boolean delete(Board2VO board2vo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
