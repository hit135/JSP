package kr.or.nextit.attach;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.nextit.attach.service.IAttachService;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.exception.BizNotFoundException;

@Controller
public class AttachController {
	
	@Autowired
	private IAttachService attachService;
	
	// 정규식으로 atachNo 받기
	@RequestMapping("/attach/download/{atchNo:^[0-9]+$}")
	public void attachDownload (@PathVariable("atchNo")int atchNo, HttpServletResponse response) throws BizNotFoundException, IOException {
		
		AttachVO attach = attachService.getAttach(atchNo);
		
		String originalName = attach.getAtchOriginalName();
		// URLEncoder.encode 뭐냐
		originalName = URLEncoder.encode(originalName, "utf-8");
		String filePath = attach.getAtchPath();
		String fileName = attach.getAtchFileName();
		File file = new File(filePath, fileName);
		
		if(file.isFile()) {
			response.setHeader("Content-type", "application/octect-stream");
			/*	- resp.setContentType("application/octect-stream");
				- 웹서버는 브라우저로 전송될 페이지가 html인 경우 text/html을 표준 MIME타입으로 지정합니다.
					MIME타입을 변경 또는 인코딩셋을 변경하고자 할때, setContentType메소드를 사용합니다.
				- octet-stream은 8비트 바이너리배열을 의미하며, application이 지정되지 않았거나 형식을 모를때 사용합니다.
				- 브라우저는 기본적으로 자신이 알고 있는 타입일 경우 기본적으로 브라우저 자체에 출력하려고 시도합니다.
					따라서 임의의 형식으로 넣는 것입니다. 그래야 인식할 수 없는 mime-type이기 때문에 브라우저가 다운로드를 처리합니다.
					즉, 브라우저는 octet-stream으로 MIME타입이 지정된 경우 바이너리 데이터로만 다운로드가 가능하게됩니다.*/
			response.setHeader("Content-Disposition", "attachment; filename=\"" + originalName + "\"");
			/*	- Content-Disposition는 응답 body에 해당 컨테츠에 대한 성향을 지정하는 속성이며
					주로 사용되는 기본 값은 inline으로 web에 전달되는 데이터 형태를 뜻한다.
					하지만 파일을 전달할 경우 attachment를 추가하여 filename을 함께 주면 body에 있는
					값을 다운로드하라고 web에게 알려주게 한다.
				- Content-Disposition, attachment:filename=파일이름
					-> 다운로드 창을 활성화 해서 다운로드 처리
				- Content Disposition, inline
					-> 다운로드 창을 활성하하지 않고, 브라우저에 직접 출력
				- 따라서 Content-Disposition에 attachment인 경우 다운로드를 뜻하며 fileName이 같이 사용되면
					"다른이름으로 저장" 을 뜻하며 대화상자의 파일이름이 fileName으로 사용되어짐
					, filename 뒤에 오는 문자열 은 항상 따옴표로 묶어야 합니다( \는 뒤에 "를 따옴표로 처리하겠다는 뜻임) */
			response.setHeader("Content-Transfer-Encoding", "binary");
			/* - 파일데이터를 binary(이진화) 하기 */
			//resp.setContentLength((int)file.length());
			/*	- 파일크기를 브라우저에 알려준다. (다운로드시 헤더에 세팅해 주지 않아도 브라우저는
					fileSize를 핸들러를 통해 체크 하므로 헤더에 사이즈를 세팅 할 필요는 없다.) */
			response.setHeader("Pragma", "no-cache;");
			/* 	- HTTP 1.0 하위 호환 */
			response.setHeader("Expires", "-1");
			/*	- 파일다운로드 제한시간, -1은 만료기간 없음*/
			
			// 방법1
			/*
			FileInputStream fis = new FileInputStream(filePath + File.separator + fileName);
			OutputStream out = response.getOutputStream();
			
			byte[] readBytes = new byte[1024];
			
			while (fis.read(readBytes) != -1) {
				out.write(readBytes);
			}
			if(fis != null) {
				fis.close();
			}
			if(out != null) {
				out.close();
			}
			*/
			
			// 방법2 spring의 FileCopyUtils.copy 사용
			FileInputStream fis = new FileInputStream(filePath + File.separator + fileName);
			FileCopyUtils.copy(fis, response.getOutputStream());
			
			attachService.increaseDownHit(atchNo);
		}else {
			printMessage(response, "해당 첨부파일이 존재하지 않습니다.");
		}
		
	}

	private void printMessage(HttpServletResponse response, String msg) throws IOException {
		response.setCharacterEncoding("utf-8");
		response.setHeader("Content-type", "text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<script type='text/javascript'>");
		out.println("		alert('"+ msg +"');");
		out.println("		self.close();");
		out.println("</script>");
		// self.close()로 창이 닫히지 않을 경우 페이지에 버튼을 생성해서 탭을 닫기
		out.println("<h4>첨부파일 문제 : "+ msg +"</h4>");
		out.println("<button onclick='self.close()'>닫기</button>");
		
	}
	
	
	
	
	
	
	
	
	
	

}
