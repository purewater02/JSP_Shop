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

public class OrderProductDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// OrderProductDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : OrderProductDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static OrderProductDAO instance = null;
	
	
	private OrderProductDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static OrderProductDAO getInstance() {
		
		if(instance == null) {
			instance = new OrderProductDAO();
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
	
	public List<OrderProductDTO> getOrderProductList(int ono) {
		
		List<OrderProductDTO> list = new ArrayList<OrderProductDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_orderProduct "
					+ "where ono = ? "
					+ "order by ono desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ono);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderProductDTO dto = new OrderProductDTO();
				
				dto.setOno(rs.getInt("ono"));
				dto.setPno(rs.getInt("pno"));
				dto.setPname(rs.getString("pname"));
				dto.setPamount(rs.getInt("pamount"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setTotprice(rs.getInt("totprice"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				 
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
	
	public int deleteOrder(int ono) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from jsp_orderproduct "
					+ "where ono = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ono);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}
	
	public int deleteOrder(int ono, int pno) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from jsp_orderproduct "
					+ "where ono = ? and pno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ono);
			pstmt.setInt(2, pno);
			
			result = pstmt.executeUpdate();
			
			sql = "select ono from jsp_orderproduct "
					+ "where ono = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, ono);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				sql = "delete from jsp_orderinfo "
						+ "where ono = ?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, ono);
				
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}
	
	public int updateOrder(OrderProductDTO dto) {		
		int result = 0;
		try {
			openConn();
			sql = "update jsp_orderproduct set "
					+ "ostate = ? "
					+ "where oNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getOstate());
			pstmt.setInt(2, dto.getOno());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}

	public void insertOP(List<CartDTO> list, int count, int length) {		
		try {			
			openConn();
			for(int i=0; i<length; i++) {
				sql = "insert into jsp_orderproduct values(?,?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, count);
				pstmt.setInt(2, list.get(i).getpNo());
				pstmt.setString(3, list.get(i).getpName());
				pstmt.setInt(4, list.get(i).getpAmount());
				pstmt.setInt(5, list.get(i).getpPrice());
				pstmt.setInt(6, list.get(i).getTotPrice());
				pstmt.setString(7, list.get(i).getpSize());
				pstmt.setString(8, list.get(i).getpColor());
				pstmt.setString(9, list.get(i).getpImage());
				pstmt.setString(10, "배송 준비 중");
				pstmt.executeUpdate();
				
				sql = "update jsp_product set pSold=pSold+? where pNo=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, list.get(i).getpAmount());
				pstmt.setInt(2, list.get(i).getpNo());
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
	}
	
	public List<OrderProductDTO> searchOrder(String id, String ordState, String date1, String date2) {
		
		List<OrderProductDTO> list = new ArrayList<OrderProductDTO>();
		
		try {
			openConn();
			
			if (!ordState.equals("all")) {
				System.out.println("if문이 실행됨");
				
				sql = "select ono from jsp_orderinfo "
						+ "where oid = ? and ostate = ? and to_char(odate, 'yyyy-mm-dd') between ? and ?";
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, ordState);
				pstmt.setString(3, date1);
				pstmt.setString(4, date2);
			
				rs = pstmt.executeQuery();
				int count=0;
				while(rs.next()) {
					count++;
				}
				
				System.out.println("count>>>"+count);
				
				int[] onoList = new int[count];
				int i=0;
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int ono = rs.getInt(1);
					System.out.println("rs.ono"+ono);
					onoList[i] = ono;
					i++;
				}
			
				for(int j=0; j<count; j++) {
					System.out.println("for문 실행>>>"+onoList[j]);
					sql = "select * from jsp_orderproduct "
							+ "where ono = ?";
				
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, onoList[j]);				
					rs = pstmt.executeQuery();					
					while(rs.next()) {
						OrderProductDTO dto = new OrderProductDTO();
					
						dto.setOno(rs.getInt("ono"));
						dto.setPno(rs.getInt("pno"));
						dto.setPname(rs.getString("pname"));
						dto.setPamount(rs.getInt("pamount"));
						dto.setTotprice(rs.getInt("totprice"));
						dto.setPsize(rs.getString("psize"));
						dto.setPcolor(rs.getString("pcolor"));
						dto.setPimage1(rs.getString("pimage1"));
						dto.setOstate(rs.getString("ostate"));
					
						list.add(dto);						
					}				
				}
			} else {
				sql = "select ono from jsp_orderinfo "
						+ "where oid = ? and to_char(odate, 'yyyy-mm-dd') between ? and ?";				
			
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);				
				pstmt.setString(2, date1);
				pstmt.setString(3, date2);
			
				rs = pstmt.executeQuery();
				int count=0;
				while(rs.next()) {
					count++;
				}
				
				System.out.println("count>>>"+count);
				
				int[] onoList = new int[count];
				int i=0;
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int ono = rs.getInt(1);
					System.out.println("rs.ono"+ono);
					onoList[i] = ono;
					i++;
				}
			
				for(int j=0; j<count; j++) {
					System.out.println("for문 실행>>>"+onoList[j]);
					sql = "select * from jsp_orderproduct "
							+ "where ono = ?";
				
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, onoList[j]);				
					rs = pstmt.executeQuery();					
					while(rs.next()) {
						OrderProductDTO dto = new OrderProductDTO();
					
						dto.setOno(rs.getInt("ono"));
						dto.setPno(rs.getInt("pno"));
						dto.setPname(rs.getString("pname"));
						dto.setPamount(rs.getInt("pamount"));
						dto.setTotprice(rs.getInt("totprice"));
						dto.setPsize(rs.getString("psize"));
						dto.setPcolor(rs.getString("pcolor"));
						dto.setPimage1(rs.getString("pimage1"));
						dto.setOstate(rs.getString("ostate"));
					
						list.add(dto);
					}				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return list;
	}
	
	public int updateOrder(int oNo, int[] pNoArr, String[] oState1Arr, int length) {      
	      int result = 0;
	      try {
	         openConn();
	         for(int i=0; i<length; i++) {
	        	 sql = "update jsp_orderproduct set "
	  	               + "ostate = ? "
	  	               + "where oNo = ? and pNo =?";
	  	         pstmt = con.prepareStatement(sql);
	  	         pstmt.setString(1, oState1Arr[i]);
	  	         pstmt.setInt(2, oNo);
	  	         pstmt.setInt(3, pNoArr[i]);

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
}
