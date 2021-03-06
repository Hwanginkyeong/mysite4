package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {
	
	public String restore(MultipartFile file) {
		System.out.println("FileService.restore()");
		String saveDir = "/Users/hwanginkyeong/javaStudy/upload";
		
		//원본파일이름 
		String orgName = file.getOriginalFilename();
		
		//확장자 
		String exName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				
		//저장파일이름
		String saveName = System.currentTimeMillis() +UUID.randomUUID().toString() + exName;
		System.out.println(saveName);
		
		
		//파일패스 생성 
		String filePath = saveDir + "/"+ saveName;
		
		//파일 사이즈 
		long fileSize = file.getSize(); 
		
		//파일 업로드 
		try {
			byte[] fileData = file.getBytes();		
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bout = new BufferedOutputStream(out);
			
			bout.write(fileData);
			bout.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		//DB에 저장 
		
		
		
		
		//
		return saveName;
		
		
		
		
	}

}
