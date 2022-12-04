package kr.or.nextit.code.dao;

import java.util.List;

import kr.or.nextit.code.vo.CodeVO;

public interface ICommCodeDao {

	List<CodeVO> getCodeListByParent(String commParent);

}
