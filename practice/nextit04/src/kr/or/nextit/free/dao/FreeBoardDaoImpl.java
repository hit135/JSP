package kr.or.nextit.free.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.exception.DaoException;
import kr.or.nextit.free.vo.FreeBoardSearchVO;
import kr.or.nextit.free.vo.FreeBoardVO;

public class FreeBoardDaoImpl implements IFreeBoardDao {

	@Override
	public int insertBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb1 = new StringBuffer();
			sb1.append("SELECT 'BO1-'||TO_CHAR(SYSDATE, 'YYYY')||'-'||LPAD(seq_free_board.nextval, 6,'0') AS bo_no FROM DUAL" );
			pstmt = conn.prepareStatement(sb1.toString());
			rs = pstmt.executeQuery();
			
			String bo_no = null;
			if(rs.next()) {
				bo_no = rs.getString("bo_no");
			}
			System.out.println("bo_no : "+ bo_no);

			
			StringBuffer sb2 = new StringBuffer();
			sb2.append(" INSERT INTO free_board (                              	");
			sb2.append("      bo_no         , bo_title    , bo_category        	");
			sb2.append("    , bo_writer     , bo_pass     , bo_content         	");
			sb2.append("    , bo_ip         , bo_hit      , bo_reg_date        	");
			sb2.append("    , bo_del_yn	    				                  		");
			sb2.append(" ) VALUES  (                                           	");
			sb2.append("      ?  			, ? 			, ?                			");
			sb2.append("    , ?             , ?           	, ?                	");
			sb2.append("    , ?             , 0           	, sysdate           ");
			sb2.append("    , 'N'				                              		"); 
			sb2.append(" )                                                     	");

			pstmt= conn.prepareStatement(sb2.toString());
			int cnt=1;

			pstmt.setString(cnt++, bo_no);
			pstmt.setString(cnt++, freeBoard.getBoTitle());
			pstmt.setString(cnt++, freeBoard.getBoCategory());
			pstmt.setString(cnt++, freeBoard.getBoWriter());
			pstmt.setString(cnt++, freeBoard.getBoPass());
			pstmt.setString(cnt++, freeBoard.getBoContent());
			pstmt.setString(cnt++, freeBoard.getBoIp());


			int resultCnt= pstmt.executeUpdate();
			return resultCnt;

			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DaoException();
		}finally {
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		//return 0;
	}

	@Override
	public List<FreeBoardVO> getBoardList(FreeBoardSearchVO searchVO) {
		// TODO Auto-generated method stub
		
		// 이제 여기서 페이징 처리하는게 문제인데..

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			
			// 검색 조건 추가!!!!!!!!!!!// 검색 조건 추가!!!!!!!!!!!// 검색 조건 추가!!!!!!!!!!!
			
			sb.append("   select																			             ");		
			sb.append("       d.*                                                                            ");
			sb.append("   from(                                                                              ");
			sb.append("       select                                                                         ");
			sb.append("           rownum as rnum                                                             ");
			sb.append("           ,c.*                                                                       ");
			sb.append("       from (                                                                         ");
			sb.append("           select  				                                                       ");
			sb.append("              bo_no   				                                                    ");
			sb.append("             ,bo_title 			                                                        ");
			sb.append("             ,bo_category   		                                                     ");
			sb.append("             ,bo_writer 			                                                     ");
			sb.append("             ,bo_pass    			                                                    ");
			sb.append("             ,bo_content 			                                                    ");
			sb.append("             ,bo_ip    			                                                        ");
			sb.append("             ,bo_hit 				                                                    ");
			sb.append("             ,to_char(bo_reg_date, 'YYYY-MM-DD') as bo_reg_date   	                 ");
			sb.append("             ,to_char(bo_mod_date, 'YYYY-MM-DD') as bo_mod_date 	                     ");
			sb.append("             ,bo_del_yn 			                                                     ");
			sb.append("             ,bo_del_id 			                                                     ");
			sb.append("             ,bo_del_date 			                  				                     ");
			sb.append("             ,b.comm_nm  as bo_category_nm  						                        ");
			sb.append("           from free_board a, comm_code b						                            ");
			sb.append("           where a.bo_category = b.comm_cd						                        ");
			sb.append("           and a.bo_del_yn = 'N'                                                      ");
			
			// 검색 부분을 처리한 것!!
			if(searchVO.getSearchWord() != null && ! searchVO.getSearchWord().equals("")) {
				switch (searchVO.getSearchType()) {
				case "T" :
					sb.append(" AND bo_title LIKE '%' || ? || '%' ");
					break;
				case "W" :
					sb.append(" AND bo_writer LIKE '%' || ? || '%' ");
					break;
				case "C" :
					sb.append(" AND bo_content LIKE '%' || ? || '%' ");
					break;
				}
			}
			
			// 분류 부분도 처리해야함!
			if(searchVO.getSearchCategory() != null && ! searchVO.getSearchCategory().equals("")) {
				sb.append(" AND bo_category = ? " 							);
			}
			
			sb.append("           order by bo_no ) c                                    ");
			sb.append("       order by rnum desc ) d                                                         ");
			sb.append("WHERE rnum between ? and ?																");
			
			
			
			
			// sb.append("   from free_board 			                                                        ");
			// sb.append("   where bo_del_yn = 'N' 			                                                        ");
			
			
			int cnt = 1;
			pstmt = conn.prepareStatement(sb.toString());
			
			// 워드 부분 추가
			if(searchVO.getSearchWord() != null && ! searchVO.getSearchWord().equals("")){
				pstmt.setString(cnt++, searchVO.getSearchWord()); 
			}
			
			// 분류 부분 추가
			if(searchVO.getSearchCategory() != null && ! searchVO.getSearchCategory().equals("")) {
				pstmt.setString(cnt++, searchVO.getSearchCategory()); 
			}
			
			pstmt.setInt(cnt++, searchVO.getLastRow());
			pstmt.setInt(cnt++, searchVO.getFirstRow());
			
			
			rs = pstmt.executeQuery();
			
			List<FreeBoardVO> freeBoardList = new ArrayList<FreeBoardVO>();
			
			while(rs.next()) {
				FreeBoardVO freeBoard = new FreeBoardVO();
				freeBoard.setBoNo(rs.getString("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				//패스워드는 가져가면 안됨
				//freeBoard.setBoPass(rs.getString("bo_pass"));  
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoIp(rs.getString("bo_ip"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
				freeBoard.setBoDelYn(rs.getString("bo_del_id"));
				freeBoard.setBoDelYn(rs.getString("bo_del_date"));
				freeBoard.setBoCategoryNm(rs.getString("bo_category_nm"));
				
				
				freeBoard.setRnum(rs.getString("rnum"));
				
				freeBoardList.add(freeBoard);
			}
			
			return freeBoardList;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DaoException();
		}finally {
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		
		//return null;
	}


	@Override
	public FreeBoardVO getBoard(String boNo) {
		// TODO Auto-generated method stub

		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			sb.append("   select  				                                ");
			sb.append("    bo_no   				                                ");
			sb.append("   ,bo_title 			                                ");
			sb.append("   ,bo_category   		                                ");
			sb.append("   ,bo_writer 			                                ");
			sb.append("   ,bo_pass    			                                ");
			sb.append("   ,bo_content 			                                ");
			sb.append("   ,bo_ip    			                                ");
			sb.append("   ,bo_hit 				                                ");
			sb.append("   ,to_char(bo_reg_date, 'YYYY-MM-DD') as bo_reg_date   	");
			sb.append("   ,to_char(bo_mod_date, 'YYYY-MM-DD') as bo_mod_date 	");
			sb.append("   ,bo_del_yn 			                                "); 
			sb.append("   ,bo_del_id 			                                "); 
			sb.append("   ,bo_del_date 			                                ");
			sb.append("   ,b.comm_nm  as bo_category_nm 	                    "); 
			sb.append("   from free_board a, comm_code b	                    ");
			sb.append("   where a.bo_category = b.comm_cd	                    ");
			sb.append("   and a.bo_no= ?					                    ");

			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, boNo);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				FreeBoardVO freeBoard=new FreeBoardVO();
				freeBoard.setBoNo(rs.getString("bo_no"));
				freeBoard.setBoTitle(rs.getString("bo_title"));
				freeBoard.setBoCategory(rs.getString("bo_category"));
				freeBoard.setBoWriter(rs.getString("bo_writer"));
				freeBoard.setBoPass(rs.getString("bo_pass"));
				freeBoard.setBoContent(rs.getString("bo_content"));
				freeBoard.setBoIp(rs.getString("bo_ip"));
				freeBoard.setBoHit(rs.getInt("bo_hit"));
				freeBoard.setBoRegDate(rs.getString("bo_reg_date"));
				freeBoard.setBoModDate(rs.getString("bo_mod_date"));
				freeBoard.setBoDelYn(rs.getString("bo_del_yn"));
				freeBoard.setBoDelYn(rs.getString("bo_del_id"));
				freeBoard.setBoDelYn(rs.getString("bo_del_date"));
				freeBoard.setBoCategoryNm(rs.getString("bo_category_nm"));
				
				return freeBoard;
			}
		}catch (Exception e){
			throw new DaoException("getBoard"+e.getMessage(), e);
			
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}	
		return null;
	}


	@Override
	public int increaseHit(String boNo) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			sb.append(" update free_board set ");
			sb.append(" bo_hit = bo_hit+1 ");
			sb.append(" where bo_no = ? ");
		
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, boNo);
			
			int resultCnt = pstmt.executeUpdate();
			return resultCnt;
			
		}catch (Exception e) {
			throw new DaoException("increseHit : "+ e.getMessage(), e);
		}finally {
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		//return 0;
	}

	@Override
	public int updateBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub

		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			
			sb.append(" UPDATE free_board SET              ");
			sb.append("      bo_title      = ?             ");
			sb.append("    , bo_category   = ?             ");
			sb.append("    , bo_content    = ?             ");
			sb.append("    , bo_ip         = ?             ");
			sb.append("    , bo_mod_date   = sysdate       ");
			sb.append(" WHERE bo_no        = ?             ");
			pstmt=conn.prepareStatement(sb.toString());
			int cnt=1;
			pstmt.setString(cnt++, freeBoard.getBoTitle());
			pstmt.setString(cnt++, freeBoard.getBoCategory());
			pstmt.setString(cnt++, freeBoard.getBoContent());
			pstmt.setString(cnt++, freeBoard.getBoIp());
			pstmt.setString(cnt++, freeBoard.getBoNo());
			
			int resultCnt= pstmt.executeUpdate();
			return resultCnt; 
			
		}catch (Exception e){
			throw new DaoException("updateBoard : "+ e.getMessage(), e);
		}finally{
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){}}
			if(conn!=null){try{conn.close();} catch(Exception e){}}
		}	
		//return 0;
	}

	
	@Override
	public int deleteBoard(FreeBoardVO freeBoard) {
		// TODO Auto-generated method stub

		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb=new StringBuffer();
			sb.append(" UPDATE free_board SET         	");
			sb.append(" bo_del_yn = 'Y'             	");
			sb.append(" ,bo_del_id = ?                  ");
			sb.append(" ,bo_del_date = sysdate          ");
			sb.append(" WHERE bo_no = ?       		    ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, freeBoard.getBoWriter());
			pstmt.setString(2, freeBoard.getBoNo());

			int resultCnt= pstmt.executeUpdate();
			return resultCnt;

		}catch (Exception e){
			throw new DaoException("deleteBoard:"+ e.getMessage(), e );
		}finally{
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){}}
			if(conn!=null){try{conn.close();} catch(Exception e){}}
		}
		//return 0;
	}

	
	// 토탈 로우 카운트 구하는 함수
	@Override
	public int getTotalRowCount(FreeBoardSearchVO searchVO) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT COUNT(*)			 ");
			sb.append("  FROM free_board		 ");
			sb.append(" WHERE bo_del_yn = 'N'	 ");
			
			// 위에 where절 없으면 where 1=1 이거나 and에 전부 where를 주거나..
			// 검색 조건 추가 >> searchWord
			if(searchVO.getSearchWord() != null && ! searchVO.getSearchWord().equals("")) {
				switch (searchVO.getSearchType()) {
				case "T":
					sb.append("AND bo_title LIKE '%' || ? || '%'			");
					break;
				case "W":
					sb.append("AND bo_writer LIKE '%' || ? || '%'			");
					break;
				case "C":
					sb.append("AND bo_content LIKE '%' || ? || '%'			");
					break;
				}
			}
			
			// 검색 조건 추가 >> searchCategory
			if(searchVO.getSearchCategory() != null && ! searchVO.getSearchCategory().equals("")) {
				sb.append("AND bo_category = ?									");
			}
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			// 값을 넣어주자!
			// 위의 조건을 타면.. 검색에 조건을 줬으면 
			int cnt = 1;
			if(searchVO.getSearchWord() != null && ! searchVO.getSearchWord().equals("")) {
				pstmt.setString(cnt++, searchVO.getSearchWord());
			}
			if(searchVO.getSearchCategory() != null && ! searchVO.getSearchCategory().equals("")) {
				pstmt.setString(cnt++, searchVO.getSearchCategory());
			}
			
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int resultCnt = rs.getInt(1);
				return resultCnt;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}	
		
		// 이거 안지우낭..? 아 안지워도 되징
		return 0;
	}

}
