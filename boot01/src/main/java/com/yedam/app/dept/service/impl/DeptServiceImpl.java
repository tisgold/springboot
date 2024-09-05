package com.yedam.app.dept.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService {
	private DeptMapper deptMapper;
	
	@Autowired
	DeptServiceImpl(DeptMapper deptMapper) {
		this.deptMapper = deptMapper;
	}

	@Override
	public List<DeptVO> deptList() {
		return deptMapper.selectDeptAll();
	}

	@Override
	public DeptVO deptInfo(DeptVO deptVO) {
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int deptInsert(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		
		return result == 1 ? deptVO.getDepartmentId() : -1;
	}

	@Override
	public Map<String, Object> deptUpdate(DeptVO deptVO) {
		Map<String, Object> map = new HashMap<>();
		boolean isSuccess = false;
		
		int result = deptMapper.updateDeptInfo(deptVO.getDepartmentId(), deptVO);
		
		if(result == 1) {
			isSuccess = true;
		}
		
		map.put("result", isSuccess);
		map.put("target", deptVO);
		
		return map;
	}

	@Override
	public Map<String, Object> deptDelete(int deptId) {
		Map<String, Object> map = new HashMap<>();
		
		int result = deptMapper.deleteDeptInfo(deptId);
		
		if(result == 1) {
			map.put("departmentId", deptId);
		}
		
		return map;
	}

}
