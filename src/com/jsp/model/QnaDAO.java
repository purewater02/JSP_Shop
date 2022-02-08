package com.jsp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class QnaDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static QnaDAO instance = null;
	
	private QnaDAO() {}
	
	public static QnaDAO getInstance() {
		if(instance == null) {
			instance = new QnaDAO();
		}
		return instance;
	}
	
	public void openConn() {
		try {
			//1단계: JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			//2단계: lookup() 메서드를 이용하여 매칭 커넥션 찾기
			DataSource ds = 
					(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			//3단계:
			con = ds.getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	
	//DB에 연결된 객체를 종료하는 메서드.
	public void closeConn(ResultSet rs, PreparedStatement pstmt, Connection con) {
	
		try {
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getQnaTotal( ) {
		int result = 0;
		try {
			openConn();
			sql = "select count(*) from jsp_qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	

	public List<QnaDTO> getQnaList(Paging paging) {
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();
		List<QnaDTO> list = new ArrayList<QnaDTO>();		
		try {
			openConn();
			sql = "select * from (select * from (select row_number() over(order by bGroup desc, bStep asc) as row_num, jsp_qna.* from jsp_qna) where row_num >= ?) where row_num <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnaDTO dto = new QnaDTO();
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);			
		}
		return list;
	}

	public QnaDTO getQnaDetail(int bNo) {
		QnaDTO dto = new QnaDTO();		
		try {
			openConn();
			sql = "select * from jsp_qna where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public void replyUpdate(int bGroup, int bStep) {		
		try {
			openConn();
			sql = "update jsp_qna set bStep = bStep + 1 where bGroup = ? and bStep > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bGroup);
			pstmt.setInt(2, bStep);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}

	public int replyQna(QnaDTO dto) {
		int result = 0, count = 0;		
		try {
			openConn();
			sql = "select max(bNo) from jsp_qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into jsp_qna values(?, ?, ?, ?, sysdate, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbWriter());
			pstmt.setString(4, dto.getbCont());			
			pstmt.setString(5, dto.getbPwd());
			pstmt.setInt(6, dto.getbGroup());
			pstmt.setInt(7, dto.getbStep()+1);
			pstmt.setInt(8, dto.getbIndent()+1);
			result = pstmt.executeUpdate();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return result;
	}

	public int deleteQna(int bNo) {
		int result = 0;		
		try {
			openConn();			
			sql = "delete from jsp_qna where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			
			sql = "update jsp_qna set bNo = bNo - 1 where bNo > ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			pstmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public List<QnaDTO> getQnaList1(Paging paging, String id, String id1) {
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();

		List<QnaDTO> list = new ArrayList<QnaDTO>();		
		try {
			openConn();
			sql = "select * from ("
					+ "select * from ("
					+ "select row_number() over(order by bGroup desc, bStep asc) as row_num, q.* from jsp_qna q where bgroup in (select bgroup from jsp_qna where bwriter = ?))  "
					+ "where row_num >= ?) "
					+ "where row_num <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);			
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnaDTO dto = new QnaDTO();
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));
				dto.setbGroup(rs.getInt("bGroup"));
				dto.setbStep(rs.getInt("bStep"));
				dto.setbIndent(rs.getInt("bIndent"));
				list.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);			
		}
		return list;
	}

	//추가됨
	public int insertQna(QnaDTO dto) {
		int result = 0, group = 0;	
		int count = getQnaTotal()+1;
		try {	
			openConn();
			sql = "select max(bgroup) from jsp_qna";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				group = rs.getInt(1) + 1;
			}			
			sql = "insert into jsp_qna values(?,?,?,?,sysdate,?,?,0,0)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbWriter());
			pstmt.setString(4, dto.getbCont());
			pstmt.setString(5, dto.getbPwd());
			pstmt.setInt(6, group);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return result;
	}
	
	//추가됨
	public List<QnaDTO> getQnaFilterList(Paging paging, String keyword, String option) {
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();
		List<QnaDTO> list = new ArrayList<QnaDTO>();		
		if(option.equals("title")) {
			try {
				openConn();
				sql = "select * from (select * from (select row_number() over(order by bGroup desc, bStep asc) as row_num, jsp_qna.* from jsp_qna where bTitle like ?) where row_num >= ?) where row_num <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);			
			}
		}else if(option.equals("cont")) {
			try {
				openConn();
				sql = "select * from (select * from (select row_number() over(order by bGroup desc, bStep asc) as row_num, jsp_qna.* from jsp_qna where bCont like ?) where row_num >= ?) where row_num <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);			
			}
		}else if(option.equals("writer")) {
			try {
				openConn();
				sql = "select * from (select * from (select row_number() over(order by bGroup desc, bStep asc) as row_num, jsp_qna.* from jsp_qna where bWriter like ?) where row_num >= ?) where row_num <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);			
			}
		}
		return list;
	}

	//추가됨
	public int getQnaFilterTotal(String option, String keyword) {
		int count = 0;
		openConn();
		String column = "b"+option;		
		try {
			sql = "select count(*) from jsp_qna where ? like ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, column);
			pstmt.setString(2, "%"+keyword+"%");
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return count;
	}
	
	// 고객 qna 키워드 검색
	public List<QnaDTO> searchQnaList(String id, String field, String keyword) {
		
		List<QnaDTO> list = new ArrayList<QnaDTO>();
		openConn();
		
		if(field.equals("title")) {
			
			try {
				sql = "select * from jsp_qna where bgroup in(select bgroup from jsp_qna where bwriter= ?) "
						+ " and btitle in(select btitle from jsp_qna where btitle like ?) order by bgroup desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		
		}else if(field.equals("content")) {
			
			try {
				sql = "select * from jsp_qna where bgroup in(select bgroup from jsp_qna where bwriter = ?) "
						+ " and bcont in(select bcont from jsp_qna where bcont like ?) order by bgroup desc";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, "%"+keyword+"%");
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}else {
			try {
				sql = "select * from jsp_qna where bgroup in(select bgroup from jsp_qna where bwriter = ?) "
						+ " and bcont in(select bcont from jsp_qna where bcont like ?) "
						+ " or btitle in(select btitle from jsp_qna where btitle like ?) order by bgroup desc ";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				pstmt.setString(2, "%"+keyword+"%");
				pstmt.setString(3, "%"+keyword+"%");
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					QnaDTO dto = new QnaDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setbGroup(rs.getInt("bGroup"));
					dto.setbStep(rs.getInt("bStep"));
					dto.setbIndent(rs.getInt("bIndent"));
					list.add(dto);
				}
				
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
		}
		return list;
		
	}	// searchQnaList 메소드 end
	
	// 고객 QnA 수정 
	public int qnaUpdate(QnaDTO dto) {
		int result = 0;
		
		String id1 = "admin";
		
		if(!dto.getbWriter().equals(id1) ) {
			try {
				openConn();
				sql = "update jsp_qna set bCont = ? where bNo = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, dto.getbCont());
				pstmt.setInt(2, dto.getbNo());
				
				result = pstmt.executeUpdate();
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		}else {
			result = 0;
		}
		return result;
		
	}
	
	// 유저 qna 삭제
	public int deleteQna1(int bgroup) {
		int result = 0;		
		try {
			openConn();			
			sql = "delete from jsp_qna where bgroup = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bgroup);
			result = pstmt.executeUpdate();		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	
}
