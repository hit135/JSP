package kr.or.nextit.common.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import kr.or.nextit.attach.vo.AttachVO;

@Component
public class NextITFileUpload {

	public List<AttachVO> fileUpload(MultipartFile[] boFiles, String category, String path) throws IllegalStateException, IOException {
		// 파일을 업로드하고 그 정보를 db로 담아야 한다
		
		List<AttachVO> attachList = new ArrayList<AttachVO>();
		
		for(MultipartFile boFile : boFiles) {
			AttachVO attach = getAttachInfoAndFileUpload(boFile, category, path);
			attachList.add(attach);
		}
		return attachList;
	}
	
	
	// 프로퍼티즈 가져와서 쓰기
	// @Value{[]} 유틸에 올려놓은 프로퍼티즈를 가져올 수 있다
	// appConfig.properties를 context-common.xml에 유틸에 올려놓고
	// 가져와서 씀
	@Value("#{util['file.upload.path']}")
	private String uploadPath;
	

	// 파일 업로드 처리
	private AttachVO getAttachInfoAndFileUpload(MultipartFile boFile, String category, String path) throws IllegalStateException, IOException {
		// 경로 잡아주기
		// String filePath = "/home/pc32/upload";
		// File.separator는 해당 운영체제에 맞는 /나 \\를 붙여준다
		String filePath = uploadPath + File.separator + path; 	//  /home/pc32/upload/free

		// 파일 이름 난소화
		String fileName = UUID.randomUUID().toString();
		// 파일 업로드
		File saveFile = new File( filePath + File.separator + fileName);
		if(!saveFile.getParentFile().exists()) {
			saveFile.getParentFile().mkdirs(); // 해당 폴더가 없으면 만들기
		}
		// 다했당 ,, 파일 업로드 끝
		boFile.transferTo(saveFile);
		
		// db에 파일 정보 넣기위해 vo 채우기
		AttachVO attach = new AttachVO();
		attach.setAtchCategory(category); 	// FREE
		attach.setAtchFileName(fileName); 	// 난소화된 파일 이름
		attach.setAtchOriginalName(boFile.getOriginalFilename()); 	// test01.txt
		attach.setAtchFileSize(boFile.getSize());
		attach.setAtchConvertSize(convertSize(boFile.getSize())); 	// byte로 보면 어려우니
		attach.setAtchContentType(boFile.getContentType()); 		// txt 등
		attach.setAtchPath(filePath);
		
		return attach;
	}

	private DecimalFormat df = new DecimalFormat("#,###.0");
	
	
	// byte는 보기 힘드니 size 변경
	private String convertSize(long size) {
		
		// 1k 미만
		if(size < 1024) {
			return size + " Bytes";
		}else if(size < (1024 * 1024)) {	// 1M미만
			return df.format(size / 1024.0 ) + "KB";
		}else if(size < (1024 * 1024 * 1024)) {	// 1G미만
			return df.format(size / (1024.0 * 1024.0) ) + "MB";
		}else {	// 1G이상
			return df.format(size / (1024.0 * 1024.0 * 1024.0) ) + "GB";
		}
	}


	
	
	
	
	
	
	
	
	
	
	
}
