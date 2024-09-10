package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.board.mapper.BoardMapper;
import com.yedam.app.board.service.BoardVO;

@SpringBootTest
class BootBoardApplicationTests {
	@Autowired
	BoardMapper boardMapper;
	
	//@Test
	void contextLoads() {
	}
	
	//@Test
	void boardInsert() {		
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("First Board");
		boardVO.setWriter("Tester");
		boardVO.setRegdate(new Date());
		
		/*		
		BoardVO boardVO = BoardVO.builder().title("First Board")		
										   .writer("Tester")
										   .regdate(new Date())
										   .build();
		*/
		System.err.println("before : " + boardVO.getBno());
		int result = boardMapper.insertBoardInfo(boardVO);
		System.err.println("after : " + boardVO.getBno());
		assertEquals(result, 1);
	}
	
	//@Test
	void updateBoard() {
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(1002);
		boardVO.setTitle("Second Board");
		boardVO.setWriter("Tester");
		boardVO.setRegdate(new Date());
		
		int result = boardMapper.updateBoardInfo(boardVO);
		assertEquals(result, 1);
	}
	
	@Test
	void boardList() {
		List<BoardVO> boards = boardMapper.selectBoardAll();
		for(BoardVO board : boards) {
			System.out.println(board);
		}
	}
	
}
