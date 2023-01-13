package kr.or.nextit.free.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

@Mapper
public interface IFreeMapper {

	String getFreeBoardKey();

	int insertBoard(FreeBoardVO freeBoard);

	int getTotalRowCount(FreeBoardSearchVO searchVO);

	List<FreeBoardVO> getBaordList(FreeBoardSearchVO searchVO);

	FreeBoardVO getBoard(String boNo);

	int increaseHit(String boNo);

	int updateBoard(FreeBoardVO freeBoard);

	int deleteBoard(FreeBoardVO freeBoard);

	int checkAdmin(FreeBoardVO freeBoard);

}
