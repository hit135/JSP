package practice01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Day01_Organize extends HttpServlet {
	// 서블릿 시작
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		// super.init();
		System.out.println("서블릿 init 메소드 실행");
	}
	
	// 서블릿 서비스 호출
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// super.service(req, resp);
		resp.setContentType("text/html; charset=utf-8");
		System.out.println("서블릿 service 메소드 실행");
		
		if(req.getMethod().equals("GET")) {
			fn_get(req, resp);
		}else if(req.getMethod().equals("POST")) {
			fn_post(req, resp);
		}
	}
	
	private void fn_get(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("get 방식으로 요청");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>JSP Practice 01</title>");
		out.print("</head>");
		out.print(
				"<body>\n" + 
				"    <h1>웹 프로그램 기초</h1>\n" + 
				"    <hr>\n" + 
				"    <p><h2>1.서버</h2> \n" + 
				"        서버는 물리 서버와 논리 서버가 있다. <br>\n" + 
				"        보통 서버를 칭할 때는 논리 서버를 일컫는다.<br>\n" + 
				"        <br>\n" + 
				"        <h2>2.URI</h2>\n" + 
				"        서버에 붙으려면 주소를 알아야 그 서버에 붙을 수 있다<br>\n" + 
				"        그래서 정해진 규약(프로토콜)이 있는데 이는<br>\n" + 
				"        http(프로토콜)://ip주소:port번호/경로?쿼리문&쿼리문...<br>\n" + 
				"        이 주소 모든 것을 포함한 것을 URI<br>\n" + 
				"        뒤의 쿼리문을 뺀 것을 URL<br>\n" + 
				"        앞의 프로토콜을 뺀 것을 URN<br>\n" + 
				"        보통 통칭 URL로 부른다<br>\n" + 
				"        이런 형식을 가지고 있다.<br>\n" + 
				"        <br>\n" + 
				"        여기서 ip주소와 port번호는 접속하기 복잡하기 때문에 도메인을 쓴다<br>\n" + 
				"        <br>\n" + 
				"        URI의 쿼리는 GET방식으로 데이터를 요청할 때 쓴다<br>\n" + 
				"        쿼리문의 형태는 KEY=VALUE 형태이다<br>\n" + 
				"        <br>\n" + 
				"        <h2>3.요청과 응답</h2>\n" + 
				"        http(s)프로토콜은 요청과 응답의 구조로 되어있다.<br>\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image01.png\" alt=\"\"> \n" + 
				"        <br>\n" + 
				"        요청방식에는 크게 get방식과 post방식으로 나뉜다.<br>\n" + 
				"         1) get 방식<br>\n" + 
				"             URI로 필요한 모든 정보를 요청함<br>\n" + 
				"             그래서 누가봐도 상관 없는 데이터를 얻을 때 많이 쓴다<br>\n" + 
				"             또한 255자라는 한계가 존재한다<br>\n" + 
				"         2) post 방식<br>\n" + 
				"             URI에 모든 정보를 요청하지 않음<br>\n" + 
				"             따로 요청 헤더 밑에 공백을 주고<br>\n" + 
				"             바디를 설정해.. 바디에 필요한 정보를 요청<br>\n" + 
				"             보안성이 높다<br>\n" + 
				"         # 요청할 때 타입을 명시해주는데<br>\n" + 
				"           우리가 주로 쓸 타입은 html이므로 text/html; utf-8(한글이 포함된 언어형식)<br>\n" + 
				"           만약 text/plain; utf-8 으로 요청을 한다면<br>\n" + 
				"          html 형식이 아닌 평문이 응답한다<br>\n" + 
				"          <br>\n" + 
				"        웹 브라우저의 요청방식에 의해 요청된 것을<br>\n" + 
				"        웹 서버는 응답에 담아 웹 브라우저로 다시 전송해준다.<br>\n" + 
				"        여기서 응답될 때 정적자원과 동적자원으로 나뉜다<br>\n" + 
				"            1) 정적자원(정적페이지)<br>\n" + 
				"                html, css, 이미지 등<br>\n" + 
				"                웹서버가 응답해서 동일한 결과를 출력해준다<br>\n" + 
				"            2) 동적자원(동적페이지)<br>\n" + 
				"                jsp, php, asp 등<br>\n" + 
				"                서블릿컨테이너 또는 WAS가 응답해서 쿼리문자열에 따라 다른 검색 결과를 출력해준다<br>\n" + 
				"                <br>\n" + 
				"        <h2>4.웹서버, WAS</h2>\n" + 
				"        그렇다면 웹서버는 무엇이면 서블릿컨테이너, WAS는 무엇일까<br>\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image2.png\" alt=\"\">\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image3.png\" alt=\"\"> \n" + 
				"        <br>\n" + 
				"            1) 웹서버<br>\n" + 
				"                웹 서버는 우리가 화면구현 시간에 썼던 라이브 서버와 같이<br>\n" + 
				"                단순한 HTML파일을 제공한다.<br>\n" + 
				"                <img src=\"http://127.0.0.1:5500/submit/jps_image/image4.jpg\" alt=\"\"> \n" + 
				"            2) 서블릿 컨테이너<br>\n" + 
				"                단순하게 생각해서 jsp 시간에 배울 TOMCAT이다.<br>\n" + 
				"            3) WAS<br>\n" + 
				"                웹서버와 서블릿컨테이너를 결합한 형태이다.<br>\n" + 
				"                Jeus, JBoss, WebLogic 등등<br>\n" + 
				"                즉, WAS가 서블릿 컨테이너를 포함하고 있다<br>\n" + 
				"                하지만, TOMCAT이 WAS에서 기능하는 웹 서버를 처리하는 부분을 어느정도 구현가능하기 때문에<br>\n" + 
				"                WAS와 서블릿 컨테이너를 헷갈릴 수 있다.<br>\n" + 
				"                TOMCAT도 한계가 존재하기 때문에 서블릿 컨테이너라고 본다.<br>\n" + 
				"                <img src=\"http://127.0.0.1:5500/submit/jps_image/image5.jpg\" alt=\"\"> <br>\n" + 
				"        개발자들은 서블릿 컨테이너와 WAS를 구분지어 말하지 않고<br>\n" + 
				"        편의상 WAS라고 통칭한다.<br>\n" + 
				"        <br>\n" + 
				"        그렇다면, WAS가 웹서버가 처리하는 부분도 처리할 수 있는데<br>\n" + 
				"        우리는 데이터를 요청하고 응답할 때<br>\n" + 
				"        Client --- Web Server --- WAS --- DB<br>\n" + 
				"        이런 형식을 취한다.<br>\n" + 
				"        이는 웹서버에서 정적인 자원을 처리하고 WAS에서 동적인 자원을 처리하는<br>\n" + 
				"        분산 효율 때문에 이런 구조를 사용한다.<br>\n" + 
				"        <br><br>\n" + 
				"        \n" + 
				"        <h2>5.서블릿</h2>\n" + 
				"        JSP이전에 java를 이용해 웹사이트를 구현했다<br>\n" + 
				"        이를 서블릿이라고 부른다<br>\n" + 
				"        하지만 서블릿 방식은 너무너무 불편했기 때문에<br>\n" + 
				"        jps방식이 나왔고<br>\n" + 
				"        이에 대한 편의로 Spring이 나왔으면<br>\n" + 
				"        또 Spring에 대한 편의로 Spring Boot가 나왔다<br>\n" + 
				"        <br>\n" + 
				"        하지만, MVC(Model View Controller) 패턴을 지원하는 프레임워크를 만들 때<br>\n" + 
				"        서블릿 기반 코드를 구현하기도 함으로 서블릿이 무엇인지 알아는 두어야 한다.</p>\n" + 
				"</body>");
		out.print("</html>");
	}
	
	private void fn_post(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("post 방식으로 요청");
		PrintWriter out = resp.getWriter();
		out.print("<html>");
		out.print("<head>");
		out.print("<title>JSP Practice 01</title>");
		out.print("</head>");
		out.print(
				"<body>\n" + 
				"    <h1>웹 프로그램 기초</h1>\n" + 
				"    <hr>\n" + 
				"    <p><h2>1.서버</h2> \n" + 
				"        서버는 물리 서버와 논리 서버가 있다. <br>\n" + 
				"        보통 서버를 칭할 때는 논리 서버를 일컫는다.<br>\n" + 
				"        <br>\n" + 
				"        <h2>2.URI</h2>\n" + 
				"        서버에 붙으려면 주소를 알아야 그 서버에 붙을 수 있다<br>\n" + 
				"        그래서 정해진 규약(프로토콜)이 있는데 이는<br>\n" + 
				"        http(프로토콜)://ip주소:port번호/경로?쿼리문&쿼리문...<br>\n" + 
				"        이 주소 모든 것을 포함한 것을 URI<br>\n" + 
				"        뒤의 쿼리문을 뺀 것을 URL<br>\n" + 
				"        앞의 프로토콜을 뺀 것을 URN<br>\n" + 
				"        보통 통칭 URL로 부른다<br>\n" + 
				"        이런 형식을 가지고 있다.<br>\n" + 
				"        <br>\n" + 
				"        여기서 ip주소와 port번호는 접속하기 복잡하기 때문에 도메인을 쓴다<br>\n" + 
				"        <br>\n" + 
				"        URI의 쿼리는 GET방식으로 데이터를 요청할 때 쓴다<br>\n" + 
				"        쿼리문의 형태는 KEY=VALUE 형태이다<br>\n" + 
				"        <br>\n" + 
				"        <h2>3.요청과 응답</h2>\n" + 
				"        http(s)프로토콜은 요청과 응답의 구조로 되어있다.<br>\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image01.png\" alt=\"\"> \n" + 
				"        <br>\n" + 
				"        요청방식에는 크게 get방식과 post방식으로 나뉜다.<br>\n" + 
				"         1) get 방식<br>\n" + 
				"             URI로 필요한 모든 정보를 요청함<br>\n" + 
				"             그래서 누가봐도 상관 없는 데이터를 얻을 때 많이 쓴다<br>\n" + 
				"             또한 255자라는 한계가 존재한다<br>\n" + 
				"         2) post 방식<br>\n" + 
				"             URI에 모든 정보를 요청하지 않음<br>\n" + 
				"             따로 요청 헤더 밑에 공백을 주고<br>\n" + 
				"             바디를 설정해.. 바디에 필요한 정보를 요청<br>\n" + 
				"             보안성이 높다<br>\n" + 
				"         # 요청할 때 타입을 명시해주는데<br>\n" + 
				"           우리가 주로 쓸 타입은 html이므로 text/html; utf-8(한글이 포함된 언어형식)<br>\n" + 
				"           만약 text/plain; utf-8 으로 요청을 한다면<br>\n" + 
				"          html 형식이 아닌 평문이 응답한다<br>\n" + 
				"          <br>\n" + 
				"        웹 브라우저의 요청방식에 의해 요청된 것을<br>\n" + 
				"        웹 서버는 응답에 담아 웹 브라우저로 다시 전송해준다.<br>\n" + 
				"        여기서 응답될 때 정적자원과 동적자원으로 나뉜다<br>\n" + 
				"            1) 정적자원(정적페이지)<br>\n" + 
				"                html, css, 이미지 등<br>\n" + 
				"                웹서버가 응답해서 동일한 결과를 출력해준다<br>\n" + 
				"            2) 동적자원(동적페이지)<br>\n" + 
				"                jsp, php, asp 등<br>\n" + 
				"                서블릿컨테이너 또는 WAS가 응답해서 쿼리문자열에 따라 다른 검색 결과를 출력해준다<br>\n" + 
				"                <br>\n" + 
				"        <h2>4.웹서버, WAS</h2>\n" + 
				"        그렇다면 웹서버는 무엇이면 서블릿컨테이너, WAS는 무엇일까<br>\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image2.png\" alt=\"\">\n" + 
				"        <img src=\"http://127.0.0.1:5500/submit/jps_image/image3.png\" alt=\"\"> \n" + 
				"        <br>\n" + 
				"            1) 웹서버<br>\n" + 
				"                웹 서버는 우리가 화면구현 시간에 썼던 라이브 서버와 같이<br>\n" + 
				"                단순한 HTML파일을 제공한다.<br>\n" + 
				"                <img src=\"http://127.0.0.1:5500/submit/jps_image/image4.jpg\" alt=\"\"> \n" + 
				"            2) 서블릿 컨테이너<br>\n" + 
				"                단순하게 생각해서 jsp 시간에 배울 TOMCAT이다.<br>\n" + 
				"            3) WAS<br>\n" + 
				"                웹서버와 서블릿컨테이너를 결합한 형태이다.<br>\n" + 
				"                Jeus, JBoss, WebLogic 등등<br>\n" + 
				"                즉, WAS가 서블릿 컨테이너를 포함하고 있다<br>\n" + 
				"                하지만, TOMCAT이 WAS에서 기능하는 웹 서버를 처리하는 부분을 어느정도 구현가능하기 때문에<br>\n" + 
				"                WAS와 서블릿 컨테이너를 헷갈릴 수 있다.<br>\n" + 
				"                TOMCAT도 한계가 존재하기 때문에 서블릿 컨테이너라고 본다.<br>\n" + 
				"                <img src=\"http://127.0.0.1:5500/submit/jps_image/image5.jpg\" alt=\"\"> <br>\n" + 
				"        개발자들은 서블릿 컨테이너와 WAS를 구분지어 말하지 않고<br>\n" + 
				"        편의상 WAS라고 통칭한다.<br>\n" + 
				"        <br>\n" + 
				"        그렇다면, WAS가 웹서버가 처리하는 부분도 처리할 수 있는데<br>\n" + 
				"        우리는 데이터를 요청하고 응답할 때<br>\n" + 
				"        Client --- Web Server --- WAS --- DB<br>\n" + 
				"        이런 형식을 취한다.<br>\n" + 
				"        이는 웹서버에서 정적인 자원을 처리하고 WAS에서 동적인 자원을 처리하는<br>\n" + 
				"        분산 효율 때문에 이런 구조를 사용한다.<br>\n" + 
				"        <br><br>\n" + 
				"        \n" + 
				"        <h2>5.서블릿</h2>\n" + 
				"        JSP이전에 java를 이용해 웹사이트를 구현했다<br>\n" + 
				"        이를 서블릿이라고 부른다<br>\n" + 
				"        하지만 서블릿 방식은 너무너무 불편했기 때문에<br>\n" + 
				"        jps방식이 나왔고<br>\n" + 
				"        이에 대한 편의로 Spring이 나왔으면<br>\n" + 
				"        또 Spring에 대한 편의로 Spring Boot가 나왔다<br>\n" + 
				"        <br>\n" + 
				"        하지만, MVC(Model View Controller) 패턴을 지원하는 프레임워크를 만들 때<br>\n" + 
				"        서블릿 기반 코드를 구현하기도 함으로 서블릿이 무엇인지 알아는 두어야 한다.</p>\n" + 
				"</body>");
		out.print("</html>");
	}
	
	// 서블릿 닫기
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		// super.destroy();
		System.out.println("서블릿 destroy 메소드 실행");
	}
	
	
	
	
	

}
