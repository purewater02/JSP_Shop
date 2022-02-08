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

public class MemberDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// MemberDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : MemberDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static MemberDAO instance = null;
	
	
	private MemberDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static MemberDAO getInstance() {
		
		if(instance == null) {
			instance = new MemberDAO();
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
	
	// 회원가입 시 중복아이디 체크하는 메서드
		public int checkMemberId(String id) {
			int result = 0;	// 아이디 중복 여부 체크하는 변수
			
			openConn();
			
			try {
				sql="select * from jsp_member where id = ?";	// 해당 아이디의 모든 정보가 조회되면 아이디가 이미 존재한다는 의미.
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					result = 1;	// 커서가 넘어간다는 건 정보가 있다는 의미. 바로 result에 1 넣어줌
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}	// 아이디 중복체크 메서드
		
		// 회원 추가 메서드
		public int insertMember(MemberDTO dto) {
			int result=0;
			
			openConn();
			
			try {
				
				sql = "insert into jsp_member values (?, ?, ?, ?, ?, ?, ?, 0, sysdate, ?)";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPwd());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.getPhone());
				pstmt.setString(5, dto.getEmail());
				pstmt.setString(6, dto.getAddr1());
				pstmt.setString(7, dto.getAddr2());
				pstmt.setString(8, dto.getZipcode());
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		} // 회원 추가 메서드 끝
		
		// 회원 여부 확인 메서드 
		public int userCheck(String id, String pwd) {
			int result = 0;
			
			openConn();
					
			try {
				sql = "select * from jsp_member where id = ?";

				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(pwd.equals(rs.getString("pwd"))) {
						result = 1;
					} else {	// 아이디는 존재하나 비밀번호가 틀린 경우
						result = -1;
					}
				} else {
					sql = "select * from jsp_admin where id = ?";

					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
					
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						if(pwd.equals(rs.getString("pwd"))) {
							result = 1;
						} else {	// 아이디는 존재하나 비밀번호가 틀린 경우
							result = -1;
						}
					}
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return result;
		}	// 회원 여부 확인 메서드 끝
		
		// 회원 아이디 찾기
		public MemberDTO MemberFindId(String name, String phone) {
			
			MemberDTO dto = new MemberDTO();
			
			try {
				openConn();
				
				sql = "select * from jsp_member "
						+ "where name = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					if(phone.equals(rs.getString("phone"))) {
						dto.setId(rs.getString("id"));
						dto.setPwd(rs.getString("pwd"));
						dto.setName(rs.getString("name"));
						dto.setPhone(rs.getString("phone"));
						dto.setEmail(rs.getString("email"));
						dto.setAddr1(rs.getString("addr1"));
						dto.setAddr2(rs.getString("addr2"));
						dto.setMileage(rs.getInt("mileage"));
						dto.setRegdate(rs.getString("regdate"));
					} 
				}
				// 입력한 이름과 일치하는 회원명이 DB에 없으면 result = 0
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return dto;
		} // 회원 아이디 찾기 메서드 끝
		
		
		// 회원 비밀번호 찾기
		public MemberDTO MemberFindPwd(String name, String id) {
		
		MemberDTO dto = new MemberDTO();
					
		try {
			openConn();
						
			sql = "select * from jsp_member "
					+ "where name = ?";
						
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
						
			rs = pstmt.executeQuery();
						
			if(rs.next()) {
				if(id.equals(rs.getString("id"))) {
					dto.setId(rs.getString("id"));
					dto.setPwd(rs.getString("pwd"));
					dto.setName(rs.getString("name"));
					dto.setPhone(rs.getString("phone"));
					dto.setEmail(rs.getString("email"));
					dto.setAddr1(rs.getString("addr1"));
					dto.setAddr2(rs.getString("addr2"));
					dto.setMileage(rs.getInt("mileage"));
					dto.setRegdate(rs.getString("regdate"));
				} 
			}
			// 입력한 이름과 일치하는 회원명이 DB에 없으면 result = 0
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	} // 회원 비밀번호 찾기 메서드 끝
				
	
	public List<MemberDTO> getMemberList() {
		
		List<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_member "
					+ "order by regdate desc";
			
			pstmt = con.prepareStatement(sql);
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setRegdate(rs.getString("regdate"));
	
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
	
	public MemberDTO MemberCont(String id) {
		
		MemberDTO dto = new MemberDTO();
		
		try {
			openConn();
			
			sql = "select * from jsp_member "
					+ "where id = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setPhone(rs.getString("phone"));
				dto.setEmail(rs.getString("email"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setMileage(rs.getInt("mileage"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setZipcode(rs.getString("zipcode"));
				
			} else {
				sql = "select * from jsp_admin "
						+ "where id = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					dto.setId(rs.getString("id"));
					dto.setPwd(rs.getString("pwd"));
					dto.setName(rs.getString("name"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}
		
	public int updateMember(MemberDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "update jsp_member set mileage = ? "
					+ "where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getMileage());
			pstmt.setString(2, dto.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public int deleteMember(String id) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from jsp_member "
					+ "where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}
	
	public List<ZipDTO> zipCheck(String dong) {
			
		List<ZipDTO> list = new ArrayList<ZipDTO>();
		
		try {
			openConn();
			
			String sql = "select * from cl_zipcodet "
					+ "where dong like ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dong+"%");
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ZipDTO dto = new ZipDTO();
				
				dto.setZipcode(rs.getString("zipcode"));
	            dto.setSido(rs.getString("sido"));
				dto.setGugun(rs.getString("gugun"));
				dto.setDong(rs.getString("dong"));					 			
				dto.setBunji(rs.getString("bunji"));
				dto.setSeq(rs.getString("seq"));
				
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

	public void updateMileage(String sessionId, int inputMileage, int totalMile) {
		int formerMile = 0;
		try {
			openConn();
			sql = "select mileage from jsp_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sessionId);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				formerMile = rs.getInt("mileage");
			}
			sql = "update jsp_member set mileage=? where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, (formerMile - inputMileage + totalMile));
			pstmt.setString(2, sessionId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
	}
	
	public int userUpdateMember(MemberDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = " update jsp_member set pwd = ?, phone = ?, email = ?, "
					+ " addr1 = ?, addr2 = ?, zipcode = ? "
					+ " where id = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getPhone());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getAddr1());
			pstmt.setString(5, dto.getAddr2());
			pstmt.setString(6, dto.getZipcode());
			pstmt.setString(7, dto.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}	//userUpdateMember() 메소드 end
}
