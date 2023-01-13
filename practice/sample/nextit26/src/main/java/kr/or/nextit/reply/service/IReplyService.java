package kr.or.nextit.reply.service;

import java.util.List;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.reply.vo.ReplyPagingVO;
import kr.or.nextit.reply.vo.ReplyVO;

public interface IReplyService {
	
	public void replyRegister(ReplyVO replyVO) throws BizNotEffectedException;

	public List<ReplyVO> getRplyListByParent(ReplyPagingVO replyPagingVO);

	public void replyDelete(ReplyVO replyVO) throws BizNotEffectedException;

	public void replyUpdate(ReplyVO replyVO) throws BizNotEffectedException;

	
	
	

}
