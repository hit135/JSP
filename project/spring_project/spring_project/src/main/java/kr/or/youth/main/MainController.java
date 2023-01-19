package kr.or.youth.main;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.main.service.IMainService;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;
import kr.or.youth.policy.vo.codeVO;

@Controller
public class MainController {
	
	@Inject
	IMainService mainService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/")
	public String main(@ModelAttribute("searchVO") SearchVO searchVO, Model model) {
		
		logger.info("Main process에 도착");
		
		try {
			// 페이징 처리
			// 리스트 불러오기
			List<YouthPolicy> policyList = mainService.getPolicyList(searchVO);
			// System.out.println("policyList : " + policyList);
			model.addAttribute("policyList", policyList);
			
			// 선택 박스 불러오기
			// 취업상태
			List<codeVO> empList = mainService.getSelectList("emp_00");
			// System.out.println("empList : " + empList);
			model.addAttribute("empList", empList);
			// 학력상태
			List<codeVO> eduList = mainService.getSelectList("edu_00");
			model.addAttribute("eduList", eduList);
			// 전공상태
			List<codeVO> majList = mainService.getSelectList("maj_00");
			model.addAttribute("majList", majList);
			
		} catch (BizNotEffectedException bne) {
			bne.printStackTrace();
			model.addAttribute("bne", bne);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("e", e);
		}
		
		return "main/main";
	}
	
	
	@RequestMapping("/members")
	public String members() {
		return "main/members";
	}
	
	
	
}
