package kr.or.nextit.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.exception.BizNotEffectedException;
import kr.or.nextit.reply.mapper.IReplyMapper;
import kr.or.nextit.reply.vo.ReplyPagingVO;
import kr.or.nextit.reply.vo.ReplyVO;

@Service("replyService")
public class ReplyServiceImpl implements IReplyService {
	
	@Autowired
	private IReplyMapper replyMapper;
	
	
	@Override
	public void replyRegister(ReplyVO replyVO) throws BizNotEffectedException {
		System.out.println("replyVO : " + replyVO.toString());
		
		int cnt = replyMapper.replyRegister(replyVO);
		if(cnt != 1) {
			throw new BizNotEffectedException();
		}
		
		
	}


	@Override
	public List<ReplyVO> getRplyListByParent(ReplyPagingVO replyPagingVO) {
		
		int totalRowCount = replyMapper.getTotalRowCount(replyPagingVO);
		replyPagingVO.setTotalRowCount(totalRowCount);
		replyPagingVO.pageSetting();
		
		List<ReplyVO> replyList = replyMapper.getReplyListByParent(replyPagingVO);
		System.out.println("서비스 딴에서 replyList : " + replyList);
		
		return replyList;
	}


	
	@Override
	public void replyDelete(ReplyVO replyVO) throws BizNotEffectedException {
		
		int cnt = replyMapper.replyDelete(replyVO);
		
		if(cnt != 1) {
			throw new BizNotEffectedException();
		}
		
		
		
	}

	// 댓글 수정
	@Override
	public void replyUpdate(ReplyVO replyVO) throws BizNotEffectedException {
		
		int cnt = replyMapper.replyUpdate(replyVO);
			
		if(cnt != 1) {
			throw new BizNotEffectedException();
		}
	}
	
	
	
	
	
	
	
	
	
}
