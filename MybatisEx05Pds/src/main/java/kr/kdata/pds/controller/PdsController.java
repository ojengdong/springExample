package kr.kdata.pds.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import kr.kdata.pds.service.Board2Service;
import kr.kdata.pds.vo.Board2FileVO;
import kr.kdata.pds.vo.Board2VO;
import kr.kdata.pds.vo.CommVO;
import lombok.extern.slf4j.Slf4j;

@Controller // 컨트롤러
@RequestMapping(value = "/pds")
@Slf4j
public class PdsController {

	@Autowired
	private Board2Service board2Service;

	// 서버의 리소스를 접근하기위한 객체. 부트가 자동으로 로드해줍니다.
	@Autowired
	ResourceLoader resourceLoader;

	// 목록보기
	@GetMapping(value = { "/", "/list" })
	public String home(@ModelAttribute CommVO cv, Model model) {
		model.addAttribute("cv", cv);
		model.addAttribute("pv", board2Service.selectList(cv.getP(), cv.getS(), cv.getB()));
		return "board/list";
	}

	// 새글쓰기 폼
	@GetMapping(value = "/insert")
	public String insert(@ModelAttribute CommVO cv, Model model) {
		model.addAttribute("cv", cv);
		return "board/insert";
	}

	// 새글쓰기 완료
	@GetMapping(value = "/insertOk")
	public String insertOkGet() {
		return "redirect:/pds/list";
	}

	@PostMapping(value = "/insertOk")
	public String insertOkPost(@ModelAttribute CommVO cv, // 페이지 정보
			@ModelAttribute Board2VO vo, // 글 내용
			@RequestParam MultipartFile[] uploadFile, // 파일들...
			HttpServletRequest request, Model model) throws IOException {
		/*
		 * 넘어온값만 확인 용도 model.addAttribute("cv", cv); model.addAttribute("vo", vo);
		 * model.addAttribute("uploadFile", uploadFile);
		 * 
		 * return "board/ok";
		 */
		// 내용은 받았지만 파일은 받지 않았다.
		// 첨부파일 처리를 여기서 해준다.
		if (uploadFile != null && uploadFile.length > 0) { // 파일이 존재 한다면
			List<Board2FileVO> list = new ArrayList<>();
			String filePath = getFilePath(); // 파일 저장 경로
			log.info("서버 절대 경로 : " + filePath);
			for (MultipartFile file : uploadFile) { // 반복한다.
				if (!file.isEmpty()) { // 파일이 있다면
					String uuid = UUID.randomUUID().toString();
					String fileName = file.getOriginalFilename();
					String contentType = file.getContentType();

					File newFile = new File(filePath + uuid + "_" + fileName);
					file.transferTo(newFile);

					Board2FileVO board2FileVO = new Board2FileVO();
					board2FileVO.setUuid(uuid);
					board2FileVO.setFileName(fileName);
					board2FileVO.setContentType(contentType);
					list.add(board2FileVO);
				}
			}
			vo.setFileList(list);
		}

		// ip는 수동으로 넣어준다,
		vo.setIp(request.getRemoteAddr());

		// 서비스를 호출하여 실제로 DB에 저장을 해주자!!!
		if (board2Service.insert(vo)) {
			log.info("저장 성공!!!!");
		} else {
			log.info("저장 실패!!!!");
		}
		;

		return "redirect:/pds/list?p=1&b=" + cv.getB() + "&s=" + cv.getS();
	}

	// 내용보기
	@GetMapping("/view")
	public String view(@ModelAttribute CommVO cv, Model model) {
		Board2VO vo = board2Service.selectById(cv.getId(), cv.getMode());
		if (vo == null) { // 글이 존재하지 않으면 리스트로
			return "redirect:/pds/list?p=1&b=" + cv.getB() + "&s=" + cv.getS();
		}
		// 존재하면 내용보기로
		model.addAttribute("vo", vo);
		model.addAttribute("cv", cv);
		return "board/view";
	}

	// 수정하기 폼
	@GetMapping(value = "/update")
	public String updateForm(@ModelAttribute CommVO cv, Model model) {
		Board2VO vo = board2Service.selectById(cv.getId(), cv.getMode());
		if (vo == null) { // 글이 존재하지 않으면 리스트로
			return "redirect:/pds/list?p=1&b=" + cv.getB() + "&s=" + cv.getS();
		}
		// 존재하면 내용보기로
		model.addAttribute("board", vo);
		model.addAttribute("cv", cv);
		return "board/update";
	}

	// 수정하기 완료
	@GetMapping(value = "/updateOk")
	public String updateOkGet() {
		return "redirect:/pds/list";
	}

	@PostMapping(value = "/updateOk")
	public String updateOkPost(@ModelAttribute CommVO cv, // 페이지 정보
			@ModelAttribute Board2VO vo, // 글 내용
			@RequestParam(defaultValue = "") String delList, // 삭제파일 id들
			@RequestParam MultipartFile[] uploadFile, // 파일들...
			HttpServletRequest request, Model model) throws IOException {
		// 내용은 받았지만 파일은 받지 않았다.
		// 첨부파일 처리를 여기서 해준다.
		if (uploadFile != null && uploadFile.length > 0) { // 파일이 존재 한다면
			List<Board2FileVO> list = new ArrayList<>();
			String filePath = getFilePath(); // 파일 저장 경로
			log.info("서버 절대 경로 : " + filePath);
			for (MultipartFile file : uploadFile) { // 반복한다.
				if (!file.isEmpty()) { // 파일이 있다면
					String uuid = UUID.randomUUID().toString();
					String fileName = file.getOriginalFilename();
					String contentType = file.getContentType();

					File newFile = new File(filePath + uuid + "_" + fileName);
					file.transferTo(newFile);

					Board2FileVO board2FileVO = new Board2FileVO();
					board2FileVO.setUuid(uuid);
					board2FileVO.setFileName(fileName);
					board2FileVO.setContentType(contentType);
					board2FileVO.setRef(vo.getId()); // ref값을 원본의 id로 넣는다.
					list.add(board2FileVO);
				}
			}
			vo.setFileList(list);
		}

		// ip는 수동으로 넣어준다,
		vo.setIp(request.getRemoteAddr());

		// 서비스를 호출하여 실제로 DB에 저장을 해주자!!!
		if (board2Service.update(vo, delList, getFilePath())) {
			log.info("수정 성공!!!!");
		} else {
			log.info("수정 실패!!!!");
		}
		;

		return "redirect:/pds/list?p=1&b=" + cv.getB() + "&s=" + cv.getS();
	}

	// 파일을 다운로드할 메서드
	@GetMapping(value = "/download")
	public ResponseEntity<Resource> download(@ModelAttribute Board2FileVO vo) throws IOException {

		String filePath = getFilePath();

		// 실제 저장된 파일의 위치를 얻어온다.
		Path path = Paths.get(filePath + "/" + vo.getUuid() + "_" + vo.getFileName());
		String contentType = Files.probeContentType(path); // 파일 종류
		// 다운로드를 하려면 헤더의 값을 변경해야 한다.
		HttpHeaders headers = new HttpHeaders();
		// 첨부파일을 원본이름으로 바꿔서 다운로드를 해야한다.
		// 다운로드될 파일의 이름을 지정
		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(vo.getFileName(), StandardCharsets.UTF_8).build());
		// 파일의 종류 지정
		headers.add(HttpHeaders.CONTENT_TYPE, contentType);
		// 실제 파일의 리소스 객체
		Resource resource = new InputStreamResource(Files.newInputStream(path));
		// 값을 리턴하면 다운로드가 진행된다.
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	}

	// 파일 저장 경로 구하는 메서드
	private String getFilePath() throws IOException {
		String filePath = resourceLoader.getResource("/").getURI().toString() + "upload/";
		filePath = filePath.substring(6);
		File f = new File(filePath); // 파일 객체 생성
		if (!f.exists())
			f.mkdirs(); // 파일(폴더)이 존재하지 않으면 폴더를 생성한다.
		return filePath;
	}
}
