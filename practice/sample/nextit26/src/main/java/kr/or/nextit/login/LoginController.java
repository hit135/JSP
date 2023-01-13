package kr.or.nextit.login;

import java.util.List;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.nextit.HomeController;
import kr.or.nextit.code.service.CommCodeServiceImpl;
import kr.or.nextit.code.service.ICommCodeService;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.member.service.IMemberService;
import kr.or.nextit.member.service.MailSendService;
import kr.or.nextit.member.service.MemberServiceImpl;
import kr.or.nextit.member.vo.MailAuthVO;
import kr.or.nextit.member.vo.MemberVO;

@Controller
public class LoginController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Inject
	private MailSendService mailSendService;
	
	@Autowired
	private ICommCodeService codeService;	
	
	@Inject
	private IMemberService memberService; 
	
	@ModelAttribute("jobList")
	public List<CodeVO> jobList(){
		return codeService.getCodeListByParent("JB00");
	}
	
	@ModelAttribute("hobbyList")
	public List<CodeVO> hobbybList(){
		return codeService.getCodeListByParent("HB00");
	}
	
	
	
	//@RequestMapping("/login")
	@GetMapping("/login")
	public String login () {
		// System.out.println("LoginController login");
		return "/login/login";
	}
	
	@RequestMapping("/login/join")
	public String join(Model model, @ModelAttribute("member") MemberVO member) {
		// System.out.println("LoginController join");
		
		logger.debug("(logger.debug) LoginController join");
		logger.info("(logger.info) LoginController join");
		
		
		return "/login/join";
	}
	
	@GetMapping("/login/{msg}")
	public String loginMsg(@PathVariable String msg , Model model) {
		// System.out.println("loginMsg msg : "+ msg );
		model.addAttribute("msg", msg);
		return "/login/login";
	}
 
	@RequestMapping("/login/loginCheck")
	public String loginCheck(@ModelAttribute MemberVO member
				, HttpServletRequest request, HttpServletResponse response) {
		// System.out.println("LoginController loginCheck");
		//MemberVO  member = new MemberVO();
		//BeanUtils.populate(member, request.getParameterMap());
		//IMemberService memberService = new MemberServiceImpl();
		try{
			boolean loginCheck =  memberService.loginCheck(member,request, response );
			if(loginCheck) {
				return "redirect:/home";
			}else {
				return "redirect:/login/fail";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "redirect:/login/error";
		}
	}

	/*
	 * @RequestMapping("/home") public String home() { return "/home/home"; }
	 */
	// 스프링 시큐리티 검수를 타서 >> 우리가 코드 처리해놓은 리멤버미가 처리가 안됨
	// 쿠키에 담아서 리스판스 쿠키에 넣어줬던 처리
	// 여기서 처리해줘야 한다
	@RequestMapping("/home")
	public String home(HttpSession session, HttpServletResponse response) {
		
		MemberVO member = (MemberVO) session.getAttribute("memberVO");
		String rememberMe = member.getRememberMe();
		if(rememberMe != null && rememberMe.equals("Y")) {
			Cookie cookie = new Cookie("rememberMe", member.getMemId());
			response.addCookie(cookie);
		}else {
			Cookie cookie = new Cookie("rememberMe", "");
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		
		return "/home/home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		// System.out.println("LoginController logout");
		request.getSession().removeAttribute("memberVO");
		return "redirect:/login";
	}
	
	@ResponseBody
	@RequestMapping("/join/idCheck")
	public boolean idCheck(@ModelAttribute("member") MemberVO member) {
		logger.info("member.getMemId() : " + member.getMemId());
		
		boolean result = memberService.idCheck(member.getMemId());
		
		
		
		return result;
	}
	
	
	
	@RequestMapping("/join/mailAuth")
	@ResponseBody
	public boolean mailAuth(@RequestParam(required = true) String mail) throws MessagingException {
		
		logger.info("mail: " + mail);
		boolean result = false;
		
		// 메일로 잘 받았으니 메일로 발송을 하면 된다
		String authKey = mailSendService.sendAuthMail(mail);
		
		if(authKey.equals("false")) {
			result = false;
		}else {
			memberService.registerMailAuth(mail, authKey);
			result = true;
		}
		
		// return "/login/join";
		return result;
	}
	
	// 인증하는 창
	@RequestMapping("/join/mailWindow")
	public String mailWindow() {
		return "/login/mailWindow";
	}
	
	// 인증키 비교하는 맵핑
	@RequestMapping("/join/authKeyCompare")
	@ResponseBody
	public boolean authKeyCompare(@ModelAttribute MailAuthVO mailAuthVO) {
		boolean result = memberService.authKeyCompare(mailAuthVO);
		
		return result;
	}
	
	
	
	
	
}
