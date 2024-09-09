package com.yedam.app.upload.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class Uploadcontroller {
	@Value("${file.upload.path}")
	/* 환경변수 및 Properties 파일의 변수 값을 Read
	   1. run as -> run configurations -> Environments 에 file.upload.path 등록	   
	   2. application.properties에 file.upload.path=D:/upload/ 등록
	   java -jar -Dfile.upload.path=D:/upload/boot01.jar (실행 명령어 예제)
	*/
	private String uploadPath;
	
	@GetMapping("formUpload")
	public void formUploadPage() { // void : 경로가 파일명, return "formUpload"
		System.out.println(uploadPath);
	} 
	// classpath:/templates/formUpload.html
	
	// Content-Type : 'multipart/form-data' -> 통신용 인코딩을 안함
	// => MultipartResolver : Spring Boot -> AutoConfig
	// => StandardServletMultipartResolver : Bean
	// @RequestPart MultipartFile : 임시로 첨부된 파일을 담음
	@PostMapping("uploadForm")
	public String formUploadFile(@RequestPart MultipartFile[] files) { // <input multiple>
		for(MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getContentType());
			System.out.println(file.getSize());
			
			String fileName = file.getOriginalFilename();
			// String saveName = uploadPath + File.separator + fileName; 
			// File.separator : Windows => /
			String saveName = uploadPath + fileName;
			
			System.out.println("saveName : " + saveName);
			Path savePath = Paths.get(saveName);
			
			try {
				file.transferTo(savePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "redirect:formUpload";
	}
	
	@GetMapping("upload")
	public void uploadPage() {}
	
	@PostMapping("/uploadsAjax")
	@ResponseBody
	public List<String> uploadFile
			(@RequestPart MultipartFile[] uploadFiles) {
	    
		List<String> imageList = new ArrayList<>();
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	  
	        String fileName = uploadFile.getOriginalFilename();
	        
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성
	        String folderPath = makeFolder();
	        //UUID
	        String uuid = UUID.randomUUID().toString();
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        
	        Path savePath = Paths.get(saveName);
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        System.out.println("path : " + saveName);
	        try{
	        	uploadFile.transferTo(savePath);
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // DB에 해당 경로 저장
	        // 1) 사용자가 업로드할 때 사용한 파일명
	        // 2) 실제 서버에 업로드할 때 사용한 경로
	        imageList.add(setImagePath(uploadFileName));
	     }
	    
	    return imageList;
	}
	
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {
			uploadPathFoler.mkdirs();
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
	}
}
