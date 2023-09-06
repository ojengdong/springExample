package kr.kdata.react;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
	
	@GetMapping(value = {"/", "/home", "/main", "/index"})
	public String index() {
		return "redirect:/index.html";
	}
}
