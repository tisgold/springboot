package com.yedam.app.emp.web;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

import lombok.RequiredArgsConstructor;

@RestController // @Controller + 모든 메소드에 @ResponseBody를 적용
				// @ResponseBody : AJAX
// REST => 사실상 Model 객체를 사용하지 않음
@RequiredArgsConstructor
public class EmpRestController {
	private final EmpService empService; 
	
	@GetMapping("/restTest")
	public String restTest() {
		return "test/main";
	}
	
	// 전체조회 : GET + URI(자원 => emps)	
	@GetMapping("emps")
	public List<EmpVO> empList() {
		return empService.empList();
	}
	
	// 단건조회 : GET + URI(자원 => emps)
	@GetMapping("emps/{empId}")
	public EmpVO empInfo(@PathVariable Integer empId) {
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(empId);
		
		return empService.empInfo(empVO);
	}
	
	// 등록 : POST + URI(자원 => emps)
	@PostMapping("emps")
	public int empInsert(@RequestBody EmpVO empVO) {
		return empService.empInsert(empVO);
	}
	
	// 수정 : PUT + URI(자원 => emps)
	@PutMapping("emps/{empId}")
	public Map<String, Object> empUpdate(@PathVariable Integer empId,
			                             @RequestBody EmpVO empVO) {
		empVO.setEmployeeId(empId);
		
		return empService.empUpdate(empVO);
	}
	
	// 삭제 : DELETE + URI(자원 => emps)
	@DeleteMapping("emps/{empId}")
	public Map<String, Object> empDelete(@PathVariable Integer empId) {
		return empService.empDelete(empId);
	}
}
