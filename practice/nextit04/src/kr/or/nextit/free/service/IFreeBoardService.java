package kr.or.nextit.free.service;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.exception.BizNotFoundException;
import kr.or.nextit.exception.BizPasswordNotMatchedException;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardService {

	void registerBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;
 
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) throws BizNotEffectedException;
	
	/**
	 * To get free board
	 * @author nextit
	 * @param boNo
	 * @return FreeBoardVO
	 * @throws BizNotEffectedException
	 */
	public FreeBoardVO getBoard(String boNo) throws BizNotEffectedException;
	
	
	/**
	 * To increase hit
	 * @author nextit
	 * @param boNo
	 * @throws BizNotEffectedException
	 */
	public void increaseHit(String boNo) throws BizNotEffectedException;
	
	
	/**
	 * To modify free board
	 * @author nextit
	 * @param freeBoard
	 * @throws BizNotFoundException
	 * @throws BizPasswordNotMatchedException
	 * @throws BizNotEffectedException
	 */
	public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

	
	/**
	 * To delete free board
	 * @author nextit
	 * @param freeBoard
	 * @throws BizNotFoundException
	 * @throws BizPasswordNotMatchedException
	 * @throws BizNotEffectedException
	 */
	public void deleteBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException;

}
