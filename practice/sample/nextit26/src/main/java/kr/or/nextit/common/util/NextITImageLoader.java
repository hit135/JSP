package kr.or.nextit.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.or.nextit.attach.service.IAttachService;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.exception.BizNotFoundException;

// 여기 @Component 대신 @Controller를 붙인 이유..
// @RestController를 써보기 위해서..
// @Controller
@RestController
public class NextITImageLoader {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private IAttachService attachService;
	/*
	//  방법 1 >> 원시적인 방식 >> 내가 코드 짜주기
	
	// 이미지 파일은 바이너리 파일 범주에 들어감.. 
	// 바이너리는 0과 1으로 구성된 데이터이며 이러한 바이너리 데이터를
	// 주고 받을 때는 바이트 형태로 리턴해주면 된다
	@RequestMapping(value = "/image/{atchNo:^[0-9]+$}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImageByteArray(@PathVariable("atchNo") int atchNo) throws BizNotFoundException, IOException {
		
		// 이미지 파일 정보 불러오기
		AttachVO attach = attachService.getAttach(atchNo);
		// path랑 파일 네임
		String filePath = attach.getAtchPath();
		String fileName = attach.getAtchFileName();
		logger.info("filePath : " + filePath + " , fileName : " + fileName);
		
		// 바이너리로 뿌려줄것임
		FileInputStream fis = null;
		// ByteArrayOutputStream는 stringBuffer같은 개념
		// 그리고 baos.toByteArray()를 쓰기 위해 선언한다
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 바이트 배열에 넣어서 던져줄 것임
		byte[] byteArray = null;
		
		try {
			// 해당 파일 위치 잡아주고
			fis = new FileInputStream(filePath + File.separator + fileName);
			
			// 해당 파일 가져오기 1024byte 단위로 읽을것임
			byte[] readBytes = new byte[1024];
			
			// 읽을게 계속 있으면 계속 읽어서 baos에 넣어줄것임
			while( (fis.read(readBytes)) != -1 ) {
				baos.write(readBytes);
			}
			byteArray = baos.toByteArray();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			fis.close();
			baos.close();
		}
		
		
		return byteArray;
	}
	
	*/
	
	
	/* 방법2 common-io 라이브러리 사용
	@RequestMapping(value = "/image/{atchNo:^[0-9]+$}", method = RequestMethod.GET)
	@ResponseBody
	public byte[] getImageByteArrayByIOUtils(@PathVariable("atchNo") int atchNo) throws BizNotFoundException, IOException {
		
		// 이미지 파일 정보 불러오기
		AttachVO attach = attachService.getAttach(atchNo);
		// path랑 파일 네임
		String filePath = attach.getAtchPath();
		String fileName = attach.getAtchFileName();
		logger.info("filePath : " + filePath + " , fileName : " + fileName);
		
		// 파일 연결
		FileInputStream fis = null;
		byte[] byteArray = null;
		try {
			fis = new FileInputStream(filePath + File.separator + fileName);
			// 라이브러리 common-io
			// 굉장히 간단하군
			byteArray = IOUtils.toByteArray(fis);
		} catch (Exception e) {
				e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		
		
		return byteArray;
	}
	*/
	
	
	/*
	// 방법3 @ResponseBody 대신  ResponseEntity<> 사용하기 
	@RequestMapping(value = "/image/{atchNo:^[0-9]+$}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getImageResponseEntity(@PathVariable("atchNo") int atchNo){
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			
			// 이미지 파일 정보 불러오기
			AttachVO attach = attachService.getAttach(atchNo);
			// path랑 파일 네임
			String filePath = attach.getAtchPath();
			// 난소화된 파일 네임
			String fileName = attach.getAtchFileName();
			// 실제 이름
			String fileOriginalName = attach.getAtchOriginalName();
			logger.info("filePath : " + filePath + " , fileName : " + fileName);
			
			// 바로 파일 취득
			File file = new File(filePath + File.separator + fileName);
			
			// 파일 확장자 스트링 취득
			fileOriginalName = fileOriginalName.substring(fileOriginalName.lastIndexOf(".")+1);
			// 스프링 프레임워크 MediaType을 이용해서 실제 확장자 가져오기??
			// mType 에는 MediaType.IMAGE_GIF같은 것들이 담긴다
			MediaType mType = NextITMediaUtils.getMediaType(fileOriginalName);
			
			logger.info("mType : " + mType);
			
			// 이 방법을 쓰는 이유는 헤더를 쓰기 위해서!
			// 이전까지는 binary로 던져서 출력했다면
			// 이번에는 이게 이미지야.. 이렇게 헤더에 던지고 이미지 출력
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", mType.toString());
			// 흠 이줄은 뭐냐..
			// ResponseEntity<>(바디, 헤더, 스테이트코드)
			entity = new ResponseEntity<byte[]>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
	*/
	
	
	// 방법4 >> @ResponseBody 어노테이션 안쓰기!
	// @RestController를 쓰면 ResponseBody를 안써도 된다.
	// 그러나 하위에 @ResponseBody로 안보낼것인데
	// 저 어노테이션을 쓰면 모든 데이터가 body가 넘어가 버린다
	@RequestMapping(value = "/image/{atchNo:^[0-9]+$}", method = RequestMethod.GET)
	// @ResponseBody
	public byte[] getImageByteArray(@PathVariable("atchNo") int atchNo) throws BizNotFoundException, IOException {
		
		// 이미지 파일 정보 불러오기
		AttachVO attach = attachService.getAttach(atchNo);
		// path랑 파일 네임
		String filePath = attach.getAtchPath();
		String fileName = attach.getAtchFileName();
		logger.info("filePath : " + filePath + " , fileName : " + fileName);
		
		// 바이너리로 뿌려줄것임
		FileInputStream fis = null;
		// ByteArrayOutputStream는 stringBuffer같은 개념
		// 그리고 baos.toByteArray()를 쓰기 위해 선언한다
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 바이트 배열에 넣어서 던져줄 것임
		byte[] byteArray = null;
		
		try {
			// 해당 파일 위치 잡아주고
			fis = new FileInputStream(filePath + File.separator + fileName);
			
			// 해당 파일 가져오기 1024byte 단위로 읽을것임
			byte[] readBytes = new byte[1024];
			
			// 읽을게 계속 있으면 계속 읽어서 baos에 넣어줄것임
			while( (fis.read(readBytes)) != -1 ) {
				baos.write(readBytes);
			}
			byteArray = baos.toByteArray();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			fis.close();
			baos.close();
		}
		
		
		return byteArray;
	}
	
	
}
