package kr.or.nextit.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.nextit.code.vo.CodeVO;

@Mapper
public interface ICommCodeMapper {

	List<CodeVO> getCodeListByParent(String commParent);

}
