package kr.or.nextit.code.service;

import java.util.List;

import kr.or.nextit.code.vo.CodeVO;

public interface ICommCodeService {

	/**
	 * To get code list
	 * @author ssam
	 * @param commParent
	 * @return List<CodeVO>
	 */
	public List<CodeVO> getCodeListByParent(String commParent);
}
