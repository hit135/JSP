package kr.or.nextit.free.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.or.nextit.attach.mapper.IAttachMapper;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.common.util.NextITSqlSessionFactory;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.mapper.IFreeMapper;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

@Service("freeBoardService")
public class FreeBoardServiceImpl implements IFreeBoardService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private BCryptPasswordEncoder passwordEncoder;
	
	@Inject
	private IFreeMapper freeDao;
	
	@Inject
	private IAttachMapper attachMapper;

	// SqlSessionFactory sqlSessionFactory = NextITSqlSessionFactory.getSqlSessionFactory();

	@Override
	public void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {
		// TODO Auto-generated method stub
		System.out.println("FreeBoardServiceImpl registerBoard");
		
		// 게시글 비밀번호 암호화 > 단방향임
		String encodedBoardPw = passwordEncoder.encode(freeBoard.getBoPass());
		logger.info("encodedBoardPw : "+ encodedBoardPw);
		freeBoard.setBoPass(encodedBoardPw);
		
		String boNo = freeDao.getFreeBoardKey();
		System.out.println("boNo: "+ boNo);
		freeBoard.setBoNo(boNo);
		
		int resultCnt = freeDao.insertBoard(freeBoard);
		
		if(resultCnt != 1) {
			throw new BizNotEffectedException();
		}
		
		// 파일 정보 등록
		List<AttachVO> attachList = freeBoard.getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				attach.setAtchParentNo(boNo);
				attach.setAtchRegId(freeBoard.getBoWriter());
				attachMapper.insertAttach(attach);
			}
		}
		
		

	}


	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) throws BizNotEffectedException {
		int totalRowCount = freeDao.getTotalRowCount(searchVO);
		
		searchVO.setTotalRowCount(totalRowCount);
		searchVO.pageSetting();
		System.out.println("searchVO.toString() "+ searchVO.toString());
		
		List<FreeBoardVO> freeBoardList = freeDao.getBaordList(searchVO);
		
		if(freeBoardList == null) {
			throw new BizNotEffectedException();
		}
		return freeBoardList;		
	}

	@Override
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException {
		
		System.out.println("getBoard_boNo: "+ boNo);
		FreeBoardVO freeBoard = freeDao.getBoard(boNo);
		
		if(freeBoard == null ) {
			throw new BizNotEffectedException();
		}
		
		// 프리보드에 파일들 가져가기
		List<AttachVO> attachList = attachMapper.getAttachList(boNo, "FREE");
		freeBoard.setAttachList(attachList);
		
		
		return freeBoard;
		
	}


	@Override
	public void increaseHit(String boNo) throws BizNotEffectedException {
		int cnt = freeDao.increaseHit(boNo);
		
		if( cnt != 1) {
			throw new BizNotEffectedException();
		}
	}

	@Override
	public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO vo = freeDao.getBoard(freeBoard.getBoNo());
		logger.info("vo : "+ vo);
		
		if( vo==null) {
			throw new BizNotFoundException();
		}
		
		// 암호화에서 salt를 사용하기 때문에 match라는 메서드를 사용
		// match는 boolean으로 반환해준다
		boolean match = passwordEncoder.matches(freeBoard.getBoPass(), vo.getBoPass());
		logger.info("match : "+ match);

		if(!match) {
			throw new BizPasswordNotMatchedException();
		}

		int resultCnt = freeDao.updateBoard(freeBoard);
		if(resultCnt != 1 ){ 
			throw new BizNotEffectedException(); 
		}
		
		// 첨부파일 번호에 따라 db에서 정보 지우기 >> 여기서는 파일은 서버에서 안지웠다
		int[] delAtchNos = freeBoard.getDelAtchNos();
		// 배열의 toString
		logger.info("delAtchNos : " + Arrays.toString(delAtchNos));
		
		// 반복문으로 해도되는데 Map으로 진행 해보기
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("delAtchNos", delAtchNos);
		if(delAtchNos != null && delAtchNos.length > 0) {
			attachMapper.deleteAttaches(map);
		}
		
		// 추가되는 파일 테이블에 파일 정보 넣기
		// 파일 정보 등록
		List<AttachVO> attachList = freeBoard.getAttachList();
		if(attachList != null) {
			for(AttachVO attach : attachList) {
				attach.setAtchParentNo(freeBoard.getBoNo());
				attach.setAtchRegId(freeBoard.getBoWriter());
				attachMapper.insertAttach(attach);
			}
		}
		
	}

	
	@Override
	public void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {
		FreeBoardVO  vo = freeDao.getBoard(freeBoard.getBoNo());
		if( vo==null) {
			throw new BizNotFoundException();
		}
		
		if(!vo.getBoPass().equals(freeBoard.getBoPass())) { 
			throw new BizPasswordNotMatchedException(); 
		}
	
		int resultCnt = freeDao.deleteBoard(freeBoard); 
		if(resultCnt != 1 ){ 
			throw new BizNotEffectedException(); 
		}
	}


	@Override
	public void hideBoard(String memId, String boNo) throws BizNotEffectedException {
		FreeBoardVO freeBoard = new FreeBoardVO();
		freeBoard.setBoWriter(memId);
		freeBoard.setBoNo(boNo);
		
		int checkAdmin = freeDao.checkAdmin(freeBoard);
		if( checkAdmin != 1) {
			throw new BizNotEffectedException();
		}
		
		int resultCnt = freeDao.deleteBoard(freeBoard); 
		if(resultCnt != 1 ){ 
			throw new BizNotEffectedException(); 
		}
	}
	
	
	
	
	
}
