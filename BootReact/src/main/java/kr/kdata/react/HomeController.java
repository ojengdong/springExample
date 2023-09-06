package kr.kdata.react;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 리액트로 하면 RestController로 한다.
@RestController
public class HomeController {
	
	@GetMapping("/hello")
	public String home(){
		return "Hello Boot Application!!히힣";
	}
	
}
