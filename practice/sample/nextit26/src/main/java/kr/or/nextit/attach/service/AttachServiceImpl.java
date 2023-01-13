package kr.or.nextit.attach.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.nextit.attach.mapper.IAttachMapper;
import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.exception.BizNotFoundException;

@Service
public class AttachServiceImpl implements IAttachService {
	
	@Inject
	private IAttachMapper attachMapper;
	
	@Override
	public AttachVO getAttach(int atchNo) throws BizNotFoundException {
		
		AttachVO attach = attachMapper.getAttach(atchNo);
		
		if(attach == null) {
			throw new BizNotFoundException();
		}
		return attach;
	}

	
	@Override
	public void increaseDownHit(int atchNo) throws BizNotFoundException {
		int cnt = attachMapper.increaseDownHit(atchNo);
		if( cnt == 0) {
			throw new BizNotFoundException();
		}
	}

}
