package kr.or.nextit.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import kr.or.nextit.common.vo.PagingVO;
import kr.or.nextit.common.vo.UserRoleVO;
import kr.or.nextit.exception.DaoException;
import kr.or.nextit.member.vo.MemberSearchVO;
import kr.or.nextit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {

	@Override
	public MemberVO getMember(String memId) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT 		            ");
			sb.append("        mem_id           ");
			sb.append("        ,mem_pass        ");
			sb.append("        ,mem_name        ");
			sb.append("        ,mem_bir         ");
			sb.append("        ,mem_zip         ");
			sb.append("        ,mem_add1        ");
			sb.append("        ,mem_add2        ");
			sb.append("        ,mem_hp          ");
			sb.append("        ,mem_mail        ");
			sb.append("        ,mem_job         ");
			sb.append("        ,mem_hobby       ");
			sb.append("        ,mem_mileage     ");
			sb.append("        ,mem_del_yn      ");
			sb.append("        ,mem_join_date   ");
			sb.append("        ,mem_edit_date   ");
			sb.append(" FROM member             ");
			sb.append(" WHERE mem_id = ?        ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MemberVO member  = new MemberVO();
				member.setMemId(rs.getString("mem_id")); 
				member.setMemPass(rs.getString("mem_pass")); 
				member.setMemName(rs.getString("mem_name"));  	  	
				member.setMemBir(rs.getString("mem_bir"));    		
				member.setMemZip(rs.getString("mem_zip"));  
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));    		
				member.setMemMail(rs.getString("mem_mail"));    		 
				member.setMemJob(rs.getString("mem_job"));    		
				member.setMemHobby(rs.getString("mem_hobby"));    		
				member.setMemMileage(rs.getInt("mem_mileage"));  
				member.setMemDelYn(rs.getString("mem_del_yn"));  
				member.setMemDelYn(rs.getString("mem_join_date"));  
				member.setMemDelYn(rs.getString("mem_edit_date"));  
					
				System.out.println("member : "+ member.toString());
				return member;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		} finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		
		}
				
		return null;
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append(" insert into member( 			 				 			");
			sb.append("      mem_id   	, mem_pass		, mem_name       	");
			sb.append("    , mem_bir  	, mem_zip		, mem_add1       		");
			sb.append("    , mem_add2 	, mem_hp		, mem_mail       		");
			sb.append("    , mem_job  	, mem_hobby		, mem_mileage  	 	");
			sb.append("    , mem_del_yn , mem_join_date , mem_edit_date  	");
			sb.append(" )values(										 				");
			sb.append("   	?				, ?			, ?				 				");
			sb.append("   	, ?				, ?			, ?	             			");
			sb.append("   	, ?				, ?			, ?	             			");
			sb.append("   	, ?				, ?			, 0  			 				");
			sb.append("   	, 'N'           , sysdate   , sysdate		 		");
			sb.append(" )																");
		
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt =1 ;
			pstmt.setString(cnt++, member.getMemId());
			pstmt.setString(cnt++, member.getMemPass());
			pstmt.setString(cnt++, member.getMemName());
			pstmt.setString(cnt++, member.getMemBir()); 
			pstmt.setString(cnt++, member.getMemZip());
			pstmt.setString(cnt++, member.getMemAdd1());
			pstmt.setString(cnt++, member.getMemAdd2());
			pstmt.setString(cnt++, member.getMemHp());
			pstmt.setString(cnt++, member.getMemMail());
			pstmt.setString(cnt++, member.getMemJob());
			pstmt.setString(cnt++, member.getMemHobby());
			
			int resultCnt = pstmt.executeUpdate();
			System.out.println("resultCnt : "+ resultCnt);
		
			return resultCnt;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		
		}
		//return 0;
	}

	@Override
	public int insertUserRole(MemberVO member) {
		// TODO Auto-generated method stub
		
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
sb.append("insert into member_role( user_id, user_role, user_role_nm) values( ?, 'ME','MEMEBER') ");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, member.getMemId());
			
			int resultCnt = pstmt.executeUpdate();
			return resultCnt;
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DaoException();
		}finally {
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		//return 0;
	}

	@Override
	public MemberVO loginCheck(MemberVO member) {
		// TODO Auto-generated method stub
		
		String memId = member.getMemId();
		String memPass = member.getMemPass();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb1 = new StringBuffer();
			//loginCheck 02
			sb1.append(" SELECT   											 	");
			sb1.append("      mem_id   	, mem_pass			, mem_name       	");
			sb1.append("    , mem_bir  	, mem_zip			, mem_add1       	");
			sb1.append("    , mem_add2 	, mem_hp			, mem_mail       	");
			sb1.append("    , mem_job  	, mem_hobby			, mem_mileage  	 	");
			sb1.append("    , mem_del_yn , mem_join_date 	, mem_edit_date 	");

			sb1.append(" FROM MEMBER");
			sb1.append(" WHERE mem_id = ? ");
			sb1.append(" AND mem_pass = ? ");
			sb1.append(" AND mem_del_yn = 'N' ");
			
			pstmt = conn.prepareStatement(sb1.toString());
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				//member.setMemId(rs.getString("mem_id")); //사실 이미 담겨있으므로 필요없음 
				//member.setMemPass(rs.getString("mem_pass")); //사실 이미 담겨있으므로 필요없음
				member.setMemName(rs.getString("mem_name"));  	  	
				member.setMemBir(rs.getString("mem_bir"));    		
				member.setMemZip(rs.getString("mem_zip"));  
				member.setMemAdd1(rs.getString("mem_add1"));
				member.setMemAdd2(rs.getString("mem_add2"));
				member.setMemHp(rs.getString("mem_hp"));    		
				member.setMemMail(rs.getString("mem_mail"));    		 
				member.setMemJob(rs.getString("mem_job"));    		
				member.setMemHobby(rs.getString("mem_hobby"));    		
				member.setMemMileage(rs.getInt("mem_mileage"));  
				member.setMemDelYn(rs.getString("mem_del_yn"));
				member.setMemJoinDate(rs.getString("mem_join_date"));  
				member.setMemEditDate(rs.getString("mem_edit_date"));
				
				return member;
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new DaoException();
		}finally {
			if(rs!=null){try{rs.close();}catch(Exception e){}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){}}
			if(conn!=null){try{conn.close();}catch(Exception e){}}
		}
		return null;
	}
 

	
	
	@Override
	public int deleteMember(MemberVO member) {
		// TODO Auto-generated method stub

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			sb.append("   update member set 		 	");
			sb.append("    	mem_del_yn ='Y' 			");
			sb.append("   where mem_id = ? 				");

			pstmt = conn.prepareStatement(sb.toString());
			System.out.println("member.getMemId()::"+member.getMemId());
			
			pstmt.setString(1, member.getMemId());
			int resultCnt = pstmt.executeUpdate();
			return resultCnt;
			
		}catch(Exception e){
			throw new DaoException();
		}finally{
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		//return 0;
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		Connection conn = null;  
		PreparedStatement pstmt = null; 	 
		ResultSet rs= null;		 
		
		try{
		 
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append(" update member set	  	   	     ");
			sb.append("   mem_pass		=	?  	   		 ");      
			sb.append("   ,mem_name		=	?      	     ");      
			sb.append("   ,mem_bir		=	?      		 ");      
			sb.append("   ,mem_zip		=	?      		 ");      
			sb.append("   ,mem_add1		=	?      	     ");      
			sb.append("   ,mem_add2		=	?      	     ");      
			sb.append("   ,mem_hp		=	?      		 ");      
			sb.append("   ,mem_mail		=	?      	     ");      
			sb.append("   ,mem_job		=	?		     ");      
			sb.append("   ,mem_hobby	=	?  		     ");      
			sb.append("   ,mem_mileage	=	?  		     ");      
			sb.append("   ,mem_edit_date	=	sysdate  ");
			
			sb.append(" where mem_id = ? ");
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt =1;
			pstmt.setString(cnt++ , member.getMemPassNew());
			pstmt.setString(cnt++,  member.getMemName());              
			pstmt.setString(cnt++,  member.getMemBir());              
			pstmt.setString(cnt++,  member.getMemZip());              
			pstmt.setString(cnt++,  member.getMemAdd1());              
			pstmt.setString(cnt++,  member.getMemAdd2());              
			pstmt.setString(cnt++,  member.getMemHp());              
			pstmt.setString(cnt++,  member.getMemMail());              
			pstmt.setString(cnt++,  member.getMemJob());              
			pstmt.setString(cnt++,  member.getMemHobby());              
			pstmt.setInt(cnt++,  	member.getMemMileage());              
			pstmt.setString(cnt++,  member.getMemId());              
			
			int resultCnt = pstmt.executeUpdate();
			return resultCnt;
			
		}catch(Exception e){
	 		System.out.println("updateMember : "+ e.getMessage());
			throw new DaoException(e.getMessage(), e);
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
	}

	
	// 역할 받아오는 함수
	@Override
	public List<UserRoleVO> getUserRole(MemberVO member) {
		
		Connection conn = null;  
		PreparedStatement pstmt = null; 	 
		ResultSet rs= null;		 
		
		try{
		 
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT 									");
			sb.append("user_id 									");
			sb.append(",user_role 								");
			sb.append(",user_role_nm 							");
			sb.append("FROM member_role							");
			sb.append("WHERE user_id = ?						");
			
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, member.getMemId());
			
			rs = pstmt.executeQuery();
			
			List<UserRoleVO> userRoleList = new ArrayList<UserRoleVO>();
			
			while(rs.next()) {
				UserRoleVO userRole = new UserRoleVO();
				userRole.setUserId(rs.getString("user_id"));
				userRole.setUserRole(rs.getString("user_role"));
				userRole.setUserRoleNm(rs.getString("user_role_nm"));
				
				
				userRoleList.add(userRole);
			}
			
			return userRoleList;
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
			
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		
		
		
		
		
		
		// return null;
	}

	
	// DB에서 멤버리스트 가져오는 함수
	@Override
	public List<MemberVO> getMemberListToDB(MemberSearchVO memSearchVO) {
		
		Connection conn = null;  
		PreparedStatement pstmt = null; 	 
		ResultSet rs= null;		
		
		try{
		 
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			

			sb.append("SELECT b.*                                                                                    	  "   ) ;
			sb.append("FROM (SELECT ROWNUM AS RNUM                                                                  	  "   ) ;
			sb.append("        , a.*                                                                                	  "   ) ;
			sb.append("      FROM (SELECT 		 mem_id                                                                   "   ) ;
			sb.append("                      , mem_pass                                                                   "   ) ;
			sb.append("                      , mem_name                                                                   "   ) ;
			sb.append("                      , mem_bir                                                                    "   ) ;
			sb.append("                      , mem_zip                                                                    "   ) ;
			sb.append("                      , mem_add1                                                                   "   ) ;
			sb.append("                      , mem_add2                                                                   "   ) ;
			sb.append("                      , mem_hp                                                                     "   ) ;
			sb.append("                      , mem_mail                                                                   "   ) ;
			sb.append("                      , b.comm_nm as mem_job                                                       "   ) ;
			sb.append("                      , c.comm_nm as mem_hobby                                                     "   ) ;
			sb.append("                      , mem_mileage                                                                "   ) ;
			sb.append("                      , mem_del_yn                                                                 "   ) ;
			sb.append("                      , TO_CHAR(mem_join_date, 'YYYY-MM-DD') AS mem_join_date                      "   ) ;
			sb.append("                      , TO_CHAR(mem_edit_date, 'YYYY-MM-DD') AS mem_edit_date                      "   ) ;
			sb.append("                   FROM member a, comm_code b, comm_code c                                         "   ) ;
			sb.append("                  WHERE mem_del_yn = 'N'                                                           "   ) ;
			sb.append("                    AND mem_job = b.comm_cd                                                        "   ) ;
			sb.append("                    AND mem_hobby = c.comm_cd                                                      "   ) ;
			sb.append("                    AND  mem_hobby = c.comm_cd                                                     "   ) ;
			
			// 검색 글자가 비어있지 않으면
			if(memSearchVO.getSearchWord() != null && ! memSearchVO.getSearchWord().equals("")) {
				switch (memSearchVO.getSearchType()) {
				case "ID":
					sb.append("AND mem_id LIKE '%' || ? || '%'			");
					break;

				case "NM":
					sb.append("AND mem_name LIKE '%' || ? || '%'			");
					break;
					
				case "HP":
					sb.append("AND mem_hp LIKE '%' || ? || '%'			");
					break;
				}
			}
			
			// 직업이 비어있지 않으면
			if(memSearchVO.getSearchJob() != null && ! memSearchVO.getSearchJob().equals("")) {
				sb.append("AND mem_job = ?						");
			}
			
			// 취미가 비어있지 않으면
			if(memSearchVO.getSearchHobby() != null && ! memSearchVO.getSearchHobby().equals("")) {
				sb.append("AND mem_hobby = ?					");
			}
						
			
			
			sb.append("                  ORDER BY mem_join_date) a) b                                                     "   ) ;
			sb.append("WHERE RNUM BETWEEN ? AND ?					                                                      "   ) ;
			sb.append("ORDER BY RNUM DESC		   					                                                      "   ) ;
			
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt = 1;
			if(memSearchVO.getSearchWord() != null && ! memSearchVO.getSearchWord().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchWord());
			}
			if(memSearchVO.getSearchJob() != null && ! memSearchVO.getSearchJob().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchJob());
			}
			if(memSearchVO.getSearchHobby() != null && ! memSearchVO.getSearchHobby().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchHobby());
			}
			pstmt.setInt(cnt++, memSearchVO.getLastRow());
			pstmt.setInt(cnt++, memSearchVO.getFirstRow());
			
			rs = pstmt.executeQuery();
			
			List<MemberVO> memberList = new ArrayList<MemberVO>();
			
			while(rs.next()) {
				MemberVO inputMember = new MemberVO();
				inputMember.setMemId(rs.getString("mem_id"));
				// inputMember.setMemPass(rs.getString("mem_pass"));
				inputMember.setMemName(rs.getString("mem_name"));
				inputMember.setMemBir(rs.getString("mem_bir"));
				inputMember.setMemZip(rs.getString("mem_zip"));
				inputMember.setMemAdd1(rs.getString("mem_add1"));
				inputMember.setMemAdd2(rs.getString("mem_add2"));
				inputMember.setMemHp(rs.getString("mem_hp"));
				inputMember.setMemMail(rs.getString("mem_mail"));
				inputMember.setMemJob(rs.getString("mem_job"));
				inputMember.setMemHobby(rs.getString("mem_hobby"));
				inputMember.setMemMileage(rs.getInt("mem_mileage"));
				inputMember.setMemDelYn(rs.getString("mem_del_yn"));
				inputMember.setMemJoinDate(rs.getString("mem_join_date"));
				inputMember.setMemEditDate(rs.getString("mem_edit_date"));
				inputMember.setRnum(rs.getString("RNUM"));
				
				memberList.add(inputMember);
			}
			
			return memberList;
			
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}
		
		
		
		
		// return null;
	}

	
	// 멤버의 총 로우 수 구하기.. (총 개수)
	// 검색 조건에 따라..
	@Override
	public int getTotalCount(MemberSearchVO memSearchVO) {
		
		Connection conn = null;  
		PreparedStatement pstmt = null; 	 
		ResultSet rs= null;		
		
		try{
		 
			conn = DriverManager.getConnection("jdbc:apache:commons:dbcp:study");
			StringBuffer sb = new StringBuffer();
			
			sb.append("SELECT COUNT(*) FROM member WHERE mem_del_yn = 'N' ");
			
			// 검색 글자가 비어있지 않으면
			if(memSearchVO.getSearchWord() != null && ! memSearchVO.getSearchWord().equals("")) {
				switch (memSearchVO.getSearchType()) {
				case "ID":
					sb.append("AND mem_id LIKE '%' || ? || '%'			");
					break;

				case "NM":
					sb.append("AND mem_name LIKE '%' || ? || '%'			");
					break;
					
				case "HP":
					sb.append("AND mem_hp LIKE '%' || ? || '%'			");
					break;
				}
			}
			
			// 직업이 비어있지 않으면
			if(memSearchVO.getSearchJob() != null && ! memSearchVO.getSearchJob().equals("")) {
				sb.append("AND mem_job = ?						");
			}
			
			// 취미가 비어있지 않으면
			if(memSearchVO.getSearchHobby() != null && ! memSearchVO.getSearchHobby().equals("")) {
				sb.append("AND mem_hobby = ?					");
			}
			
			
			pstmt = conn.prepareStatement(sb.toString());
			
			int cnt = 1;
			if(memSearchVO.getSearchWord() != null && ! memSearchVO.getSearchWord().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchWord());
			}
			if(memSearchVO.getSearchJob() != null && ! memSearchVO.getSearchJob().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchJob());
			}
			if(memSearchVO.getSearchHobby() != null && ! memSearchVO.getSearchHobby().equals("")) {
				pstmt.setString(cnt++, memSearchVO.getSearchHobby());
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int resultCount = rs.getInt(1);
				return resultCount;
			}
			
		
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new DaoException();
		}finally{
			if(rs!=null){try{rs.close();} catch(Exception e){} }
			if(pstmt!=null){try{pstmt.close();} catch(Exception e){} }
			if(conn!=null){try{conn.close();}catch(Exception e){} }
		}

		
		
		
		
		
		
		
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
