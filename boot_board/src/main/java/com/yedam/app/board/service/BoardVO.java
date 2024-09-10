package com.yedam.app.board.service;

import java.util.Date;

import lombok.Data;

@Data
//@Builder
public class BoardVO {
	private Integer bno;		// 번호, PRIMARY KEY
	private String title;		// 제목, NOT NULL
	private String contents;	// 내용, default : No Content
	private String writer;		// 작성자, NOT NULL, default : any
	private Date regdate;		// 작성일, NOT NULL
	private Date updatedate;	// 수정일, default : sysdate
	private String image;		// 이미지명
	
	/*
	 * CREATE TABLE board (
    	bno NUMBER(6,0) PRIMARY KEY,
    	title VARCHAR2(1000) NOT NULL,
    	contents VARCHAR2(4000) DEFAULT 'No Content',
    	writer VARCHAR2(1000) DEFAULT 'Any' NOT NULL,
    	regdate DATE NOT NULL,
    	updatedate DATE DEFAULT sysdate,
    	image VARCHAR2(2000)
		);
	 */
}
