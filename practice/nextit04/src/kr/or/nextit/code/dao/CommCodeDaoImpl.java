package kr.or.nextit.code.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.nextit.code.vo.CodeVO;
import kr.or.nextit.exception.DaoException;

public class CommCodeDaoImpl implements ICommCodeDao {

	@Override
	public List<CodeVO> getCodeListByParent(String commParent) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT						");
			sb.append("comm_cd						");
			sb.append(",comm_nm						");
			sb.append(",comm_parent				");
			sb.append(",comm_ord					");
			sb.append("from comm_code					");
			sb.append("where comm_parent = ?		");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, commParent);
			rs = pstmt.executeQuery();
			
			List<CodeVO> codeList = new ArrayList<CodeVO>();
			
			while(rs.next()) {
				CodeVO code = new CodeVO();
				code.setCommCd(rs.getString("comm_cd"));
				code.setCommNm(rs.getString("comm_nm"));
				code.setCommOrd(rs.getInt("comm_ord"));
				code.setCommParent(rs.getString("comm_parent"));
				
				codeList.add(code);
			}
			return codeList;
			
		} catch (Exception e) {
			throw new DaoException();
			
		}finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		//return null;
	}

}
