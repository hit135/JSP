package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardService {

	void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;

	List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) throws BizNotEffectedException;

	FreeBoardVO getBoard(String boNo) throws BizNotEffectedException;

	void increaseHit(String boNo) throws BizNotEffectedException;

	void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;
	
	void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	void hideBoard(String memId, String boNo) throws BizNotEffectedException;


}
