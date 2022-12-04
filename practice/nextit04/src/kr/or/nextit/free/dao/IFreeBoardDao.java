package kr.or.nextit.free.dao;

import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public interface IFreeBoardDao {

	int insertBoard(FreeBoardVO freeBoard);

	List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO);
	
	/**
	 * To get free board
	 * @author nextit
	 * @param boNo
	 * @return FreeBoardVO
	 */
	public FreeBoardVO getBoard(String boNo);

	/**
	 * To increase hit
	 * @author nextit
	 * @param boNo
	 * @return
	 */
	public int increaseHit(String boNo);

	/**
	 * To update free board
	 * @param freeBoard
	 * @return
	 */
	public int updateBoard(FreeBoardVO freeBoard);

	
	/**
	 * To delete free board
	 * @author nextit
	 * @param freeBoard
	 * @return
	 */
	public int deleteBoard(FreeBoardVO freeBoard);

	
	/**
	 * To get total row count
	 * @author pc32
	 * @param searchVO 
	 * @return int
	 */
	int getTotalRowCount(FreeBoardSearchVO searchVO);

 
}
