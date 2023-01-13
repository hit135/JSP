package kr.or.nextit.reply.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.reply.vo.ReplyPagingVO;
import kr.or.nextit.reply.vo.ReplyVO;

@Mapper
public interface IReplyMapper {

	public int replyRegister(ReplyVO replyVO);

	public List<ReplyVO> getReplyListByParent(ReplyPagingVO replyPagingVO);

	public int getTotalRowCount(ReplyPagingVO replyPagingVO);

	public int replyDelete(ReplyVO replyVO);

	public int replyUpdate(ReplyVO replyVO);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
