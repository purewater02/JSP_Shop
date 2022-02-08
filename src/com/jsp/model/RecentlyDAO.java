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

public class RecentlyDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// RecentlyDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : RecentlyDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static RecentlyDAO instance = null;
	
	
	private RecentlyDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static RecentlyDAO getInstance() {
		
		if(instance == null) {
			instance = new RecentlyDAO();
		}
		return instance;
		
	}  // getInstance() 메서드 end
	
	
	// DB를 연동하는 작업을 진행하는 메서드
	public void openConn() {
		
		try {
			// 1단계 : JNDI 서버 객체 생성
			Context ctx = new InitialContext();
			
			// 2단계 : lookup() 메서드를 이용하여 매칭되는 커넥션을 찾는다.
			DataSource ds =
					(DataSource)ctx.lookup("java:comp/env/jdbc/myoracle");
			
			// 3단계 : DataSource 객체를 이용하여 커넥션 객체를 하나 가져온다.
			con = ds.getConnection();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}  // openConn() 메서드 end
	
	// DB에 연결된 객체를 종료하는 메서드.
	public void closeConn(ResultSet rs,
			PreparedStatement pstmt, Connection con) {
		
		try {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertRecently(int pno, String sId) {
		

		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "where pno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				String ctg1 = rs.getString("ctg1");
				String ctg2 = rs.getString("ctg2");
				String pcode = rs.getString("pcode");
				String pname = rs.getString("pname");
				String pimage1 = rs.getString("pimage1");
				
				sql = "select * from jsp_recently "
						+ "where pno = ? and sid = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pno);
				pstmt.setString(2, sId);
				
				rs = pstmt.executeQuery();
				
				if(!rs.next()) {
					int count = 0;
					
					sql = "select count(rno) from jsp_recently "
							+ "where sid = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sId);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1) + 1;
					}
					
					if(count >= 6) {
						sql = "delete from jsp_recently "
								+ "where rno = ? and sid = ?";
						
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, 1);
						pstmt.setString(2, sId);
						
						pstmt.executeUpdate();
						
						sql = "update jsp_recently set rno = rno - 1 "
								+ "where rno > ? and sid = ?";
						
						pstmt = con.prepareStatement(sql);
						
						pstmt.setInt(1, 1);
						pstmt.setString(2, sId);
						
						pstmt.executeUpdate();
						
						count = 5;
					}
					
					sql = "insert into jsp_recently "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, sId);
					pstmt.setInt(2, count);
					pstmt.setInt(3, pno);
					pstmt.setString(4, ctg1);
					pstmt.setString(5, ctg2);
					pstmt.setString(6, pcode);
					pstmt.setString(7, pname);
					pstmt.setString(8, pimage1);
					
					pstmt.executeUpdate();
					
				} else {
					int rno = 0, count = 0;
					
					sql = "select rno from jsp_recently where pno = ? and sid = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, pno);
					pstmt.setString(2, sId);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						rno = rs.getInt(1);
					}
					
					sql = "delete from jsp_recently "
							+ "where rno = ? and sid = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, rno);
					pstmt.setString(2, sId);
					
					pstmt.executeUpdate();
					
					sql = "update jsp_recently set rno = rno - 1 "
							+ "where rno > ? and sid = ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, rno);
					pstmt.setString(2, sId);
					
					pstmt.executeUpdate();
					
					sql = "select count(rno) from jsp_recently "
							+ "where sid = ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, sId);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						count = rs.getInt(1) + 1;
					}
					
					sql = "insert into jsp_recently "
							+ "values(?, ?, ?, ?, ?, ?, ?, ?)";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, sId);
					pstmt.setInt(2, count);
					pstmt.setInt(3, pno);
					pstmt.setString(4, ctg1);
					pstmt.setString(5, ctg2);
					pstmt.setString(6, pcode);
					pstmt.setString(7, pname);
					pstmt.setString(8, pimage1);
					
					pstmt.executeUpdate();
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}
	
	public List<RecentlyDTO> getRecently(String sid) {
		
		List<RecentlyDTO> list = new ArrayList<RecentlyDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_recently "
					+ "where sid = ? "
					+ "order by rno desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				RecentlyDTO dto = new RecentlyDTO();
				
				dto.setSid(rs.getString("sid"));
				dto.setRno(rs.getInt("rno"));
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPimage1(rs.getString("pimage1"));
				
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
}
