package kr.or.youth.login.service;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.youth.common.util.YouthPolicySessionFactory;
import kr.or.youth.exception.BizNotEffectedException;
import kr.or.youth.login.dao.ILoginDao;
import kr.or.youth.login.vo.MemberVO;

@Service
public class LoginServiceImpl implements ILoginService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private ILoginDao loginDao;
	
	// 암호화 객체
	@Inject
	private BCryptPasswordEncoder passWordEncoder;
	
	// 멤버 db로 등록하기
	@Override
	public void registeMember(MemberVO member) throws BizNotEffectedException {
		// 멤버 비밀번호 암호화
		String encodePass = passWordEncoder.encode(member.getMemPass());
		logger.info("encodePass : "+ encodePass);
		member.setMemPass(encodePass);
		
		int resultCnt = loginDao.insertMember(member);
		if(resultCnt == 0) {
			throw new BizNotEffectedException();
		}
	}

	
	// 입력받은 memId에 따라 멤버 가져오기
	// 여기서 비밀번호 매치 시키자
	@Override
	public MemberVO getMember(MemberVO member) throws BizNotEffectedException {
		
		MemberVO dbMember = new MemberVO();
		dbMember = loginDao.getMember(member.getMemId());
		
		if(dbMember == null) {
			throw new BizNotEffectedException(); 
		}
		
		// 암호화된 비밀번호와 사용자 입력 비밀번호 매치
		boolean match = passWordEncoder.matches(member.getMemPass(), dbMember.getMemPass());
		
		if(match) {
			dbMember.setMemPass("good");
		}else {
			dbMember.setMemPass("fail");
		}
		
		return dbMember;
	}


	// 멤버에 100포인트 추가
	@Override
	public void updatePoint(String memId) throws BizNotEffectedException {
		
		logger.info("dao로 가는 memId : " + memId);
		int resultCnt = loginDao.updatePoint(memId);
		if(resultCnt == 0) {
			throw new BizNotEffectedException();
		}
		
	}


	// 회원 이름에 따른 모든 출석한 날 얻기
	@Override
	public String getMemAttendDate(String memId) throws BizNotEffectedException {
	
		// System.out.println("서비스 딴의 memId : " + memId);
		String memAttendDate = loginDao.getMemAttendDate(memId);
		// System.out.println("loginDao.getMemAttendDate(memId) : " + loginDao.getMemAttendDate(memId));
		// System.out.println("서비스딴의 memAttendDate : " + memAttendDate);
		
		// null일때 따로 하자!
		if(memAttendDate == null) {
			throw new BizNotEffectedException();
		}else {
			return memAttendDate;
		}
		
	}
	
	
	
	
	
}
