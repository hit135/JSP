package kr.or.nextit.code.service;

import java.util.List;

import kr.or.nextit.code.dao.CommCodeDaoImpl;
import kr.or.nextit.code.dao.ICommCodeDao;
import kr.or.nextit.code.vo.CodeVO;

public class CommCodeServiceImpl implements ICommCodeService{

	
	ICommCodeDao commCodeDao = new CommCodeDaoImpl();
	
	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
		
		List<CodeVO> codeList = commCodeDao.getCodeListByParent(commParent);
		
		return codeList;
	}

}
