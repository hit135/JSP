package kr.or.nextit.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.or.nextit.attach.mapper.IAttachMapper;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.common.util.NextITSqlSessionFactory;
import kr.or.nextit.common.vo.RoleInfoVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.exception.BizDuplicateKeyException;
import kr.or.nextit.exception.BizMailAuthException;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.member.MemberController;
import kr.or.nextit.member.mapper.IMemberMapper;
import kr.or.nextit.member.vo.MailAuthVO;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 서비스에서 콘트롤러를 가지고 올수 있다..
	// 이러면 문제..
	// 그래서 흐름 제어를 해줘야 한다
	// @Autowired
	// private  MemberController memberController;
	@Autowired
	private IMemberMapper memMapper;
	
	@Inject
	private IAttachMapper attachMapper;
	
	// 서블릿 설정에 올려놓은 암호 인코딩하는 객체 끌어쓰기
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void registerMember(MemberVO member) throws BizDuplicateKeyException, BizNotEffectedException, BizMailAuthException {
		
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			MemberVO vo = memMapper.getMember(member.getMemId());
			
			if(vo != null) {
				throw new BizDuplicateKeyException();
			}
			
			// 인증메일 확인
			Integer rowCount = memMapper.checkMailAuth(member.getMemMail());
			if(rowCount != 1) {
				throw new BizMailAuthException();
			}
			
			
			// 비밀번호 암호화 > 단방향임
			String encodedPw = passwordEncoder.encode(member.getMemPass());
			logger.info("encodedPw : "+ encodedPw);
			member.setMemPass(encodedPw);
			// $2a$10$nYiUYKw3LYuBfb0MHKUUYOfNXsfrmnbVZFPOfeZVR8l9XJs1V4QrC
			// $2a$10$K/bNTglVrfUW3vvEnHfRNe3CeiscY3uVZGG9hIZEHL0r5CgTvmGom
			
			int resultCnt1 =  memMapper.insertMember(member);
			if( resultCnt1 != 1) {
				throw new BizNotEffectedException();
			}
			int resultCnt2 = memMapper.insertUserRole(member);
			if( resultCnt2 != 1) {
				throw new BizNotEffectedException();
			}
			
			// 파일 정보 등록
			List<AttachVO> attachList = member.getAttachList();
			if(attachList != null) {
				for(AttachVO attach : attachList) {
					attach.setAtchParentNo(member.getMemId());
					attach.setAtchRegId(member.getMemId());
					attachMapper.insertAttach(attach);
				}
			}
			
		}
	}

	@Override
	public boolean loginCheck(MemberVO member, HttpServletRequest request, HttpServletResponse response) throws BizNotEffectedException {
		
		// SqlSession sqlSession = sqlSessionFactory.openSession(true); //true 자동커밋
		// IMemberDao memDao = sqlSession.getMapper(IMemberDao.class);
		
		MemberVO vo = null;
		if(member.getMemId() == null || member.getMemPass() == null) {
		 
			return false;
			
		}else {
			vo = memMapper.loginCheck(member);
		}
		try {
			if(vo == null) {
				return false;
			}else {
				
				// 암호화에서 salt를 사용하기 때문에 match라는 메서드를 사용
				// match는 boolean으로 반환해준다
				boolean match = passwordEncoder.matches(member.getMemPass(), vo.getMemPass());
				logger.info("match : "+ match);
				if(!match) {
					return false;
				}
				
				
			   List<UserRoleVO> userRoleList  = memMapper.getUserRole(member);

			    if(userRoleList != null) {
			    	vo.setUserRoleList(userRoleList);
			    }
				HttpSession session = request.getSession();
				session.setAttribute("memberVO", vo);

				String rememberMe = member.getRememberMe();
				if (rememberMe != null && rememberMe.equals("Y")) {
					Cookie cookie= new Cookie("rememberMe", member.getMemId());
					response.addCookie(cookie);
				}else{
					Cookie cookie= new Cookie("rememberMe", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		}
		
	}

	@Override
	public MemberVO getMember(String memId) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		
		// SqlSession sqlSession = sqlSessionFactory.openSession(true); //true 자동커밋
		// IMemberDao memDao = sqlSession.getMapper(IMemberDao.class);
		
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			member = memMapper.getMember(memId);
			if(member == null) {
				throw new BizNotEffectedException();
			}
		}
		
		// 프로필 이미지 불러오기 >> 사용자가 이미지 수정할 수도 있으니
		// 가장 최근 프로필 이미지를 가져올 것이다 >> 이미지 키값 번호가 가장 큰 수 >> 가장 최근 이미지
		// integer로 받으면 null 받아도 예외처리는 안난다
		
		// 여기서는 이미지를 바로 가져가는게 아니라
		// 이미지의 key 번호만 가져가서
		// 화면에서 이미지 key값으로 요청해서 이미지를 출력할 것이다
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memId", memId);
		map.put("atchCategory", "PROFILEPHOTO");
		Integer atchNo = attachMapper.getAttachNo(map);
		logger.info("atchNo : " + atchNo);
		
		if(atchNo != null) {
			member.setAtchNo(atchNo);
		}
		
		return member;
	
	}
 
	
	@Override
	public void removeMember(MemberVO member) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		// TODO Auto-generated method stub
		
		// SqlSession sqlSession = sqlSessionFactory.openSession(true); //true 자동커밋
		// IMemberDao memDao = sqlSession.getMapper(IMemberDao.class);

		MemberVO vo = null;
		if(member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memMapper.getMember(member.getMemId());
		}
		if( vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass()) ){
			throw new BizPasswordNotMatchedException();
		}
		int resultCnt = memMapper.deleteMember(member);
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
		
	
	}

	@Override
	public void modifyMember(MemberVO member)
			throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

		// SqlSession sqlSession = sqlSessionFactory.openSession(true); //true 자동커밋
		// IMemberDao memDao = sqlSession.getMapper(IMemberDao.class);
		
		
		MemberVO vo = null;
		if( member.getMemId() != null && ! member.getMemId().equals("")) {
			vo = memMapper.getMember(member.getMemId());
		}
		
		if(vo == null) {
			throw new BizNotFoundException();
		}
		if( !vo.getMemPass().equals(member.getMemPass())) {
			throw new BizPasswordNotMatchedException();
		}
		
		System.out.println("멤버 수정에서 비밀번호 수정 확인 :" + member);
		
		int resultCnt = memMapper.updateMember(member);
		
		if(resultCnt != 1){
			throw new BizNotEffectedException();
		}
		
		// 회원 정보 수정 후 파일 정보 등록
		List<AttachVO> attachList = member.getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				attach.setAtchParentNo(member.getMemId());
				attach.setAtchRegId(member.getMemId());
				attachMapper.insertAttach(attach);
			}
		}
		
	}
	
	
	@Override
	public List<MemberVO> getMemberList(MemberSearchVO searchVO) throws BizNotFoundException {
		// TODO Auto-generated method stub

		// SqlSession sqlSession = sqlSessionFactory.openSession(true); //true 자동커밋
		// IMemberDao memDao = sqlSession.getMapper(IMemberDao.class);

		int totalRowCount  = memMapper.getTotalRowCount(searchVO);
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		System.out.println("searchVO.toString() : "+searchVO.toString());
		List<MemberVO> memberVO = memMapper.getMemberList(searchVO);
		
		if(memberVO == null) {
			throw new BizNotFoundException();
		}
		return memberVO;
		
	}

	@Override
	public void removeMultiMember(String memMultiId) throws BizNotEffectedException {
		
		System.out.println("memMultiId: "+ memMultiId);
		ObjectMapper objectMapper = new ObjectMapper();
		List<Object> list = null;
		try {
			list = objectMapper.readValue(memMultiId, new TypeReference<List<Object>>() {} );
			System.out.println("list "+ list);
			System.out.println("list.size() "+ list.size());
			if(list.size() ==0) {
				throw new BizNotEffectedException();
			}
			for(int i=0; i<list.size(); i++) {
				String memId = (String) list.get(i);
				MemberVO member = new MemberVO();
				member.setMemId(memId);
				int resultCnt = memMapper.deleteMember(member);

				if(resultCnt == 0) {
					throw new BizNotEffectedException();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizNotEffectedException();
		}
		
	}

	@Override
	public MemberVO getMemberRole(String memId) throws BizNotEffectedException, BizNotFoundException {
		
		System.out.println("memId : "+ memId);
		MemberVO member = null;
		if(memId != null && ! memId.equals("")) {
			member = memMapper.getMember(memId);
		}
		if(member == null) {
			throw new BizNotFoundException();
		}
		List<UserRoleVO> userRoleList = memMapper.getUserRole(member);
		
		if(userRoleList == null) {
			throw new BizNotEffectedException();
		}
		member.setUserRoleList(userRoleList);
		return member;
		
	}

	@Override
	public List<RoleInfoVO> getRoleInfo() throws BizNotEffectedException {
		List<RoleInfoVO> roleInfoList = memMapper.getRoleInfo();
		
		if(roleInfoList == null) {
			throw new BizNotEffectedException();
		}
		return roleInfoList;

		
	}

	
	@Transactional
	@Override
	public void updateUserRole(String memId, String[] roles) {
	
		System.out.println("roles.length :"+ roles.length);
		memMapper.deleteUserRole(memId);
		
		if(roles.length >0 ) {
			for(int i=0; i<roles.length; i++) {
				// memMapper.insertMultiRole(memId, roles[i]);
				memMapper.insertMultiRole(null, roles[i]);
				
			}
		}
		
	}
	
	// 아이디 체크
	@Override
	public boolean idCheck(String memId) {
		
		int cnt = memMapper.idCheck(memId);
		
		if(cnt == 0) {
			return true;
		}
		
		return false;
	}

	// 엑셀 데이터 받은 걸로 멤버 등록
	@Override
	public void memberExcelUpload(MemberVO member) throws BizNotEffectedException {
		
		int resultCnt = memMapper.memberExcelUpload(member);
		if(resultCnt != 1) {
			throw new BizNotEffectedException();
		}
		
	}

	// 그리드 업데이트
	@Override
	public Boolean memberGridUpdate(MemberVO member) {
		
		int resultCnt = memMapper.memberGridUpdate(member);
		
		boolean result = true;
		if(resultCnt != 1) {
			return false;
		}
		
		return result;
	}

	// 그리드 멀티 아이디 삭제
	@Override
	public boolean memberGridMultiDelete(List<String> memId_Arr) {
		
		for(String memId : memId_Arr) {
			int resultCnt = memMapper.memberGridMultiDelete(memId);
			if(resultCnt != 1) {
				return false;
			}
		}
		return true;
	}

	// 인증번호 db에 저장
	@Override
	public void registerMailAuth(String mail, String authKey) {
		
		// 메일이 있나 없나 확인
		MailAuthVO mailAuth = memMapper.getMailAuth(mail);
		
		logger.info("mail:" + mail);
		logger.info("mailAuth : " + mailAuth);
		
		if(mailAuth == null) {
			memMapper.insertMailAuth(mail, authKey);
		}else {
			memMapper.updateMailAuth(mail, authKey);
		}
		
		
	}

	// 인증번호 비교하는 서비스 임플
	@Override
	public boolean authKeyCompare(MailAuthVO mailAuthVO) {
		
		MailAuthVO vo = memMapper.getMailAuth(mailAuthVO.getMail());
		
		if(vo == null) {
			return false;
		}else {
			// db와 사용자 입력 인증키가 같으면
			if(vo.getAuthKey().equals(mailAuthVO.getAuthKey())) {
				// db의 is_auth를 0에서 1로 바꿀것이다
				// 마지막 회원가입 절차시 is_auth의 값을 물어서
				// 1이면 통과
				memMapper.completeAuth(mailAuthVO.getMail());
				return true;
			}else {
				return false;
			}
		}
	}
	
	
	
	
	

}
