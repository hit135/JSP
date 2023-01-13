package kr.or.nextit.code.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.nextit.code.mapper.ICommCodeMapper;
import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.common.util.NextITSqlSessionFactory;

@Service
public class CommCodeServiceImpl implements ICommCodeService{

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ICommCodeMapper codeMapper;
	
	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
		
		// log4j에서는 {}가능 >> 여기에 글이 들어옴
		logger.info("(logger.info) {}"
					, (commParent == null) ? "commParent is null" : commParent);
		// logger.info("(logger.info) + commParent);
		
		List<CodeVO> codeList = codeMapper.getCodeListByParent(commParent);
		
		return codeList;
	}

}
