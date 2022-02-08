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

public class CartDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// CartDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : CartDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static CartDAO instance = null;
	
	
	private CartDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static CartDAO getInstance() {
		
		if(instance == null) {
			instance = new CartDAO();
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

	public int insertCart(CartDTO dto) {
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select pno, psize, pcolor from jsp_cart where sessionid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getSessionId());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt(1) == dto.getpNo() && rs.getString(2).equals(dto.getpSize()) && rs.getString(3).equals(dto.getpColor()) ) {
					sql = "update jsp_cart set pamount = pamount + ? "
							+ "where pno = ? and psize = ? and pcolor = ? and sessionid = ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, dto.getpAmount());
					pstmt.setInt(2, dto.getpNo());
					pstmt.setString(3, dto.getpSize());
					pstmt.setString(4, dto.getpColor());
					pstmt.setString(5, dto.getSessionId());
					
					result = pstmt.executeUpdate();
				} else {
					sql = "select max(cartNo) from jsp_cart";
					pstmt = con.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						count = rs.getInt(1) + 1;
					}
					sql = "insert into jsp_cart values(?,?,?,?,?,?,?,?,?,?,?)";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, count);
					pstmt.setString(2, dto.getSessionId());
					pstmt.setInt(3, dto.getpNo());
					pstmt.setString(4, dto.getpName());
					pstmt.setInt(5, dto.getpAmount());
					pstmt.setInt(6, dto.getpPrice());
					pstmt.setInt(7, dto.getTotPrice());
					pstmt.setString(8, dto.getpSize());
					pstmt.setString(9, dto.getpColor());
					pstmt.setString(10, dto.getpImage());
					pstmt.setInt(11, dto.getpMileage());			
					result = pstmt.executeUpdate();
				}
			} else {
				String sql = "select max(cartNo) from jsp_cart";
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1) + 1;
				}
				sql = "insert into jsp_cart values(?,?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, count);
				pstmt.setString(2, dto.getSessionId());
				pstmt.setInt(3, dto.getpNo());
				pstmt.setString(4, dto.getpName());
				pstmt.setInt(5, dto.getpAmount());
				pstmt.setInt(6, dto.getpPrice());
				pstmt.setInt(7, dto.getTotPrice());
				pstmt.setString(8, dto.getpSize());
				pstmt.setString(9, dto.getpColor());
				pstmt.setString(10, dto.getpImage());
				pstmt.setInt(11, dto.getpMileage());			
				result = pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public List<CartDTO> getCartList(String sessionId) {
		List<CartDTO> list = new ArrayList<CartDTO>();			
		try {
			openConn();			
			sql = "select * from jsp_cart where sessionId = ? order by cartNo desc";					
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sessionId);
			rs = pstmt.executeQuery();		
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCartNo(rs.getInt("cartNo"));
				dto.setpNo(rs.getInt("pNo"));
				dto.setpName(rs.getString("pName"));
				dto.setpAmount(rs.getInt("pAmount"));
				dto.setpPrice(rs.getInt("pPrice"));
				dto.setTotPrice(rs.getInt("totPrice"));
				dto.setpSize(rs.getString("pSize"));
				dto.setpColor(rs.getString("pColor"));
				dto.setpImage(rs.getString("pImage"));
				dto.setpMileage(rs.getInt("pMileage"));
				
				ProductDAO dao = ProductDAO.getInstance();
				ProductDTO pdto = dao.productCont(rs.getInt("pNo"));
				dto.setpSizeList(pdto.getPsize());
				dto.setpColorList(pdto.getPcolor());				
								
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

	public int updateCartOption(int cartNo, String pSize, String pColor) {
		int result = 0;		
		try {
			openConn();
			sql = "update jsp_cart set pSize=?, pColor=? where cartNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pSize);
			pstmt.setString(2, pColor);
			pstmt.setInt(3, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int updateCartOption(int cartNo, int pAmount, int pPrice) {
		int result = 0;		
		try {
			openConn();
			sql = "update jsp_cart set pAmount=?, totPrice=? where cartNo=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pAmount);
			pstmt.setInt(2, (pAmount*pPrice));
			pstmt.setInt(3, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteSelectedCart(int cartNo) {
		int result = 0;		
		try {
			openConn();
			sql = "delete from jsp_cart where cartNo=?";
			pstmt = con.prepareStatement(sql);						
			pstmt.setInt(1, cartNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<CartDTO> getCartList(int cartNo) {
		List<CartDTO> list = new ArrayList<CartDTO>();			
		try {
			openConn();			
			sql = "select * from jsp_cart where cartNo = ? "
					+ "order by cartNo desc";					
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartNo);
			rs = pstmt.executeQuery();		
			while(rs.next()) {
				CartDTO dto = new CartDTO();
				dto.setCartNo(rs.getInt("cartNo"));
				dto.setpNo(rs.getInt("pNo"));
				dto.setpName(rs.getString("pName"));
				dto.setpAmount(rs.getInt("pAmount"));
				dto.setpPrice(rs.getInt("pPrice"));
				dto.setTotPrice(rs.getInt("totPrice"));
				dto.setpSize(rs.getString("pSize"));
				dto.setpColor(rs.getString("pColor"));
				dto.setpImage(rs.getString("pImage"));
				dto.setpMileage(rs.getInt("pMileage"));
				
				ProductDAO dao = ProductDAO.getInstance();
				ProductDTO pdto = dao.productCont(rs.getInt("pNo"));
				dto.setpSizeList(pdto.getPsize());
				dto.setpColorList(pdto.getPcolor());				
								
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
	
	public List<CartDTO> getCartCheckList(int[] cartNoArr, int length) {
		List<CartDTO> list = new ArrayList<CartDTO>();				
		try {
			openConn();
			for(int i=0; i<length; i++) {
				sql = "select * from jsp_cart where cartNo = ?";								
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cartNoArr[i]);					
				rs = pstmt.executeQuery();		
				while(rs.next()) {
					CartDTO dto = new CartDTO();
					dto.setCartNo(rs.getInt("cartNo"));
					dto.setpNo(rs.getInt("pNo"));
					dto.setpName(rs.getString("pName"));
					dto.setpAmount(rs.getInt("pAmount"));
					dto.setpPrice(rs.getInt("pPrice"));
					dto.setTotPrice(rs.getInt("totPrice"));
					dto.setpSize(rs.getString("pSize"));
					dto.setpColor(rs.getString("pColor"));
					dto.setpImage(rs.getString("pImage"));
					dto.setpMileage(rs.getInt("pMileage"));							
									
					list.add(dto);
				};
			};
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return list;	
	}

	public void deleteCartAll(String sessionId) {
		try {
			openConn();
			sql = "delete from jsp_cart where sessionId=?";
			pstmt = con.prepareStatement(sql);						
			pstmt.setString(1, sessionId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
