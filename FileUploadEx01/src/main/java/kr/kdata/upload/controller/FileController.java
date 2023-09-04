package kr.kdata.upload.controller;



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

import kr.kdata.upload.vo.FileVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/upload")
@Slf4j
public class FileController {
	
	// application.properties에 등록된 파일의 경로를 가져온다.
	// @Value("${spring.servlet.multipart.location}")
	// 실제 파일이 저장될 서버의 경로
	String filePath; 
	
	// 서버의 리소스를 접근하기위한 객체. 부트가 자동으로 로드해줍니다.
	@Autowired
	ResourceLoader resourceLoader;
	
	// 단순하게 폼만 띄운다.
	@GetMapping(value = "/uploadForm1")
	public String uploadForm1() {
		return "upload/uploadForm1";
	}
	
	@GetMapping(value = "/uploadForm2")
	public String uploadForm2() {
		return "upload/uploadForm2";
	}
	// 업로드 처리
	// Get방식으로 접근하면 Home으로 보낸다.
	@GetMapping(value = "/uploadOk1")
	public String uploadOkGet() {
		return "redirect:/";
	}
	// POST로 접근하면 업로드 처리를 해줘야 한다.
	@PostMapping(value = "/uploadOk1")
	public String uploadOkPost(	
			@RequestParam(defaultValue = "파일설명이다.")String note, // 일반값 받기
			@RequestParam MultipartFile[] file, // 파일을 배열로 받기
			Model model) throws IOException {
		// 파일 경로를 검색하기 위하여 서버의 절대 경로를 알아낸다.
		// resources/upload/ 폴더를 지정
		filePath = resourceLoader.getResource("/").getURI().toString() + "upload/";
		log.info("-".repeat(80));
		log.info(filePath);
		filePath = filePath.substring(6); // 앞의 6자리("file/")를 버린다.
		log.info(filePath);
		File f = new File(filePath); // 파일 객체 생성
		if(!f.exists()) f.mkdirs(); // 파일(폴더)이 존재하지 않으면 폴더를 생성한다. 
		log.info("-".repeat(80));
		
		model.addAttribute("filePath", filePath);
		model.addAttribute("note", note);
		
		// 이제 파일 처리를 하자.
		if(file!=null && file.length>0) { // 파일이 넘어 왔다면
			for(MultipartFile multipartFile : file) {
				if(!multipartFile.isEmpty()) { // 파일이 있다면
					// 원본이름
					String originalFileName = multipartFile.getOriginalFilename();
					// 파일 형식
					String contentType = multipartFile.getContentType();
					// 저장 파일이름
//					UUID.randomUUID() : 유일한 이름을 만들어 준다. 
//					파일 중복 처리를 위해서 사용합니다.
					String saveFileName = UUID.randomUUID() + "_" + originalFileName;
					// 파일의 크기
					long fileSize = multipartFile.getSize();
					
					model.addAttribute("originalFileName",originalFileName);
					model.addAttribute("contentType",contentType);
					model.addAttribute("saveFileName",saveFileName);
					model.addAttribute("fileSize", fileSize);
					
					// 파일이 업로드되면 임시폴더나 메모리로 업로드 된다.
					// 실제로 파일을 원하는 폴더로 복사한다.
					File newFile = new File(filePath + saveFileName);
					multipartFile.transferTo(newFile);
				}
			}
		}
		return "upload/uploadOk1";
	}
	
	// 업로드 처리
		// Get방식으로 접근하면 Home으로 보낸다.
		@GetMapping(value = "/uploadOk2")
		public String uploadOk2Get() {
			return "redirect:/";
		}
		// POST로 접근하면 업로드 처리를 해줘야 한다.
		@PostMapping(value = "/uploadOk2")
		public String uploadOk2Post(	
				@RequestParam MultipartFile[] file, // 파일을 배열로 받기
				Model model) throws IOException {
			// 파일 경로를 검색하기 위하여 서버의 절대 경로를 알아낸다.
			// resources/upload/ 폴더를 지정
			filePath = resourceLoader.getResource("/").getURI().toString() + "upload/";
			log.info("-".repeat(80));
			log.info(filePath);
			filePath = filePath.substring(6); // 앞의 6자리("file/")를 버린다.
			log.info(filePath);
			File f = new File(filePath); // 파일 객체 생성
			if(!f.exists()) f.mkdirs(); // 파일(폴더)이 존재하지 않으면 폴더를 생성한다. 
			log.info("-".repeat(80));
			
			model.addAttribute("filePath", filePath);
			
//			업로드된 모든 파일의 정보를 저장할 리스트
			List<FileVO> list = new ArrayList<>(); 
			
			// 이제 파일 처리를 하자.
			if(file!=null && file.length>0) { // 파일이 넘어 왔다면
				for(MultipartFile multipartFile : file) {
					if(!multipartFile.isEmpty()) { // 파일이 있다면
						// 원본이름
						String originalFileName = multipartFile.getOriginalFilename();
						// 파일 형식
						String contentType = multipartFile.getContentType();
						// 저장 파일이름
//						UUID.randomUUID() : 유일한 이름을 만들어 준다. 
//						파일 중복 처리를 위해서 사용합니다.
						String uuid = UUID.randomUUID().toString();
						String saveFileName = uuid + "_" + originalFileName;
						
						
						// 파일이 업로드되면 임시폴더나 메모리로 업로드 된다.
						// 실제로 파일을 원하는 폴더로 복사한다.
						File newFile = new File(filePath + saveFileName);
						multipartFile.transferTo(newFile);
						
//						저장정보를 리스트에 추가한다.
						list.add(new FileVO(uuid, originalFileName, contentType));
					}
				}
			}
			model.addAttribute("list", list);
			return "upload/uploadOk2";
		}
		
		// 파일을 다운로드할 메서드
	    @GetMapping(value = "/download")
	    public ResponseEntity<Resource> download(@ModelAttribute FileVO vo) throws IOException{
	        filePath = resourceLoader.getResource("/").getURI().toString() + "upload/";
	        filePath = filePath.substring(6);
	        File f = new File(filePath); //파일 객체 생성
	        if(!f.exists()) { //파일(폴더)이 존재하지 않으면 폴더를 생성한다.
	            f.mkdir();
	        }

	        Path path = Paths.get(filePath + "/" + vo.getUuid() + "_" + vo.getFileName());
	        log.info("실제 저장위치 : {}", path.toString());
	        String contentType = Files.probeContentType(path); // 파일 종류
	        // 다운로드를 하려면 헤더의 값을 변경해야 한다.
	        HttpHeaders headers = new HttpHeaders();
	        // 첨부파일을 원본이름으로 바꿔서 다운로드를 해야한다. 다운로드될 파일의 이름을 지정
	        headers.setContentDisposition(
	                ContentDisposition.builder("attachment").filename(vo.getFileName(),StandardCharsets.UTF_8).build());
	        // 파일의 종류 지정
	        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
	        // 실제 파일의 리소스 객체
	        Resource resource = new InputStreamResource(Files.newInputStream(path));
	        // 값을 리턴하면 다운로드가 진행된다.
	        return new ResponseEntity<>(resource, headers, HttpStatus.OK);

	    }
}
