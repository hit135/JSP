package kr.or.youth.main.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.youth.common.util.YouthPolicySessionFactory;
import kr.or.youth.common.vo.PagingVO;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.vo.MemberVO;
import kr.or.youth.main.dao.IMainDao;
import kr.or.youth.policy.vo.SearchVO;
import kr.or.youth.policy.vo.YouthPolicy;
import kr.or.youth.policy.vo.codeVO;

@Service
public class MainServiceImpl implements IMainService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	IMainDao mainDao;
	
	
	// 정책 리스트 페이징에 맞춰서 가져오기
	@Override
	public List<YouthPolicy> getPolicyList(SearchVO searchVO) throws BizNotEffectedException {
		
		// total count 가져오기
		int totalCount = mainDao.getTotalCount(searchVO);
		searchVO.setTotalRowCount(totalCount);
		// System.out.println("DB를 갔다온 토탈 카운트 : " + searchVO);
		searchVO.pageSetting();
		
		// System.out.println("pagingVO에 값이 잘 들어갔니~" + searchVO);
		
		// 리스트 불러오기
		// System.out.println("서비스 딴의 getPolicyList에 들어옴");
		List<YouthPolicy> policyList = mainDao.getPolicyList(searchVO);
		
		if(policyList == null) {
			throw new BizNotEffectedException();
		}
		
		return policyList;
		
	}

	
	// 선택박스 코드들 가져오기
	@Override
	public List<codeVO> getSelectList(String perentId) throws BizNotEffectedException {

		
		// db에서 perentId에 따라 코드 리스트 가져오기
		List<codeVO> selList = mainDao.getSelectList(perentId);
		
		if(selList == null) {
			throw new BizNotEffectedException();
		}
		
		return selList;

	}


	// 멤버리스트 다 받아오기
	@Override
	public List<MemberVO> getMemberList() throws BizNotEffectedException {
		
		List<MemberVO> memberList = mainDao.getMemberList();
		// System.out.println("마이바티스에서 받아온 memberList :" + memberList);
		return memberList;
	}
	

}
