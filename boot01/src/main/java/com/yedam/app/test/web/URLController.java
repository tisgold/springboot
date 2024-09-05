package com.yedam.app.test.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // Bean 등록, Web과 관련된 부분 담당
public class URLController {
	//@RequestMapping(path="/test", method=RequestMethod.GET) // method : Inum
	@GetMapping("/test") // 경로명에 /test , test 동일
	@ResponseBody // AJAX, 순수하게 데이터만 반환하는 컨트롤러
	public String urlGetTest(String keyword) {
		return "Server Response : Get Method\n Select - " + keyword;
	}
	
	//@RequestMapping(path="/test", method=RequestMethod.POST)
	@PostMapping("/test")
	@ResponseBody
	public String urlPostTest(String keyword) {
		return "Server Response : Post Method\n Select - " + keyword;
	}
}
