package kr.or.nextit.attach.service;

import kr.or.nextit.attach.vo.AttachVO;
import kr.or.nextit.exception.BizNotFoundException;

public interface IAttachService {

	AttachVO getAttach(int atchNo) throws BizNotFoundException;

	void increaseDownHit(int atchNo) throws BizNotFoundException;

	

}
