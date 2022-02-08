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

public class NoticeDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static NoticeDAO instance = null;
	
	private NoticeDAO() {}
	
	public static NoticeDAO getInstance() {
		if(instance == null) {
			instance = new NoticeDAO();
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
	public int getNoticeTotal( ) {
		int result = 0;		
		try {
			openConn();
			sql = "select count(*) from jsp_notice";
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
	
	public List<NoticeDTO> getNoticeList(Paging paging) {
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();
		
		List<NoticeDTO> list = new ArrayList<NoticeDTO>();		
		try {
			openConn();
			sql = "select * from (select * from (select row_number() over(order by bNo desc) as row_num, jsp_notice.* from jsp_notice) where row_num >= ?) where row_num <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				NoticeDTO dto = new NoticeDTO();
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
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

	public int writeNotice(NoticeDTO dto) {
		int result = 0, count = 0;		
		try {
			openConn();
			sql = "select max(bNo) from jsp_notice";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into jsp_notice values(?, ?, ?, ?, sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getbTitle());
			pstmt.setString(3, dto.getbWriter());
			pstmt.setString(4, dto.getbCont());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public NoticeDTO getNoticeDetail(int bNo) {
		NoticeDTO dto = new NoticeDTO();		
		try {
			openConn();
			sql = "select * from jsp_notice where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int updateNotice(NoticeDTO dto) {		
		int result = 0;
		try {
			openConn();
			sql = "update jsp_notice set bTitle = ?, bWriter = ?, bCont = ?, bDate = sysdate where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getbTitle());
			pstmt.setString(2, dto.getbWriter());
			pstmt.setString(3, dto.getbCont());
			pstmt.setInt(4, dto.getbNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public int deleteNotice(int bNo) {
		int result = 0;		
		try {
			openConn();
			sql = "delete from jsp_notice where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			
			sql = "update jsp_notice set bNo = bNo - 1 where bNo > ?";
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
}
