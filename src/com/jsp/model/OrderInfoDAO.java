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

public class OrderInfoDAO {
	
	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// OrderInfoDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : OrderInfoDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static OrderInfoDAO instance = null;
	
	
	private OrderInfoDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static OrderInfoDAO getInstance() {
		
		if(instance == null) {
			instance = new OrderInfoDAO();
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
	
	public List<OrderInfoDTO> getOrderList() {
		
		List<OrderInfoDTO> list = new ArrayList<OrderInfoDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_orderInfo "
					+ "order by ono desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderInfoDTO dto = new OrderInfoDTO();
				
				dto.setOno(rs.getInt("ono"));
				dto.setOid(rs.getString("oid"));
				dto.setOdate(rs.getString("odate"));
				dto.setOreceiver(rs.getString("oreceiver"));
				dto.setOaddr(rs.getString("oaddr"));
				dto.setOphone(rs.getString("ophone"));
				dto.setOpay(rs.getString("opay"));
				dto.setOcard(rs.getString("ocard"));
				dto.setOcardno(rs.getString("ocardno"));
				dto.setOcardpwd(rs.getString("ocardpwd"));
				dto.setOstate(rs.getString("ostate"));
				
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
	
	public List<OrderInfoDTO> searchBoard(String field, String keyword) {
	      
	      List<OrderInfoDTO> searchList = new ArrayList<OrderInfoDTO>();
	      
	      openConn();
	      
	      if(field.equals("oNo")) {   // 주문번호로 검색
	      
	         try {
	            sql = "select * from jsp_orderinfo "
	               + " where ono like ? "
	               + " order by ono desc";
	         
	            pstmt=con.prepareStatement(sql);
	            
	            pstmt.setString(1, "%"+keyword+"%");
	            
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               OrderInfoDTO dto=new OrderInfoDTO();
	               dto.setOno(rs.getInt("ono"));
	               dto.setOid(rs.getString("oid"));
	               dto.setOdate(rs.getString("odate"));
	               dto.setOreceiver(rs.getString("oreceiver"));
	               dto.setOaddr(rs.getString("oaddr"));
	               dto.setOphone(rs.getString("ophone"));
	               dto.setOpay(rs.getString("opay"));
	               dto.setOcard(rs.getString("ocard"));
	               dto.setOcardno(rs.getString("ocardno"));
	               dto.setOcardpwd(rs.getString("ocardpwd"));
	               dto.setOstate(rs.getString("ostate"));
	               
	               searchList.add(dto);
	            }
	            
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      } else if (field.equals("oId")) { // 주문자 아이디로 검색
	      
	         try {
	            sql = "select * from jsp_orderinfo "
	               + " where oid like ? "
	               + " order by ono desc";
	         
	            pstmt=con.prepareStatement(sql);
	            
	            pstmt.setString(1, "%"+keyword+"%");
	            
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               OrderInfoDTO dto=new OrderInfoDTO();
	               dto.setOno(rs.getInt("ono"));
	               dto.setOid(rs.getString("oid"));
	               dto.setOdate(rs.getString("odate"));
	               dto.setOreceiver(rs.getString("oreceiver"));
	               dto.setOaddr(rs.getString("oaddr"));
	               dto.setOphone(rs.getString("ophone"));
	               dto.setOpay(rs.getString("opay"));
	               dto.setOcard(rs.getString("ocard"));
	               dto.setOcardno(rs.getString("ocardno"));
	               dto.setOcardpwd(rs.getString("ocardpwd"));
	               dto.setOstate(rs.getString("ostate"));
					
	               searchList.add(dto);
	            }
	            
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      } 
	      else { // 수령인으로 검색
	         try {
	            sql = "select * from jsp_orderinfo "
	               + " where oReceiver like ? "
	               + " order by ono desc";
	         
	            pstmt=con.prepareStatement(sql);
	            
	            pstmt.setString(1, "%"+keyword+"%");
	            
	            rs = pstmt.executeQuery();
	            
	            while(rs.next()) {
	               OrderInfoDTO dto=new OrderInfoDTO();
	               dto.setOno(rs.getInt("ono"));
					dto.setOid(rs.getString("oid"));
					dto.setOdate(rs.getString("odate"));
					dto.setOreceiver(rs.getString("oreceiver"));
					dto.setOaddr(rs.getString("oaddr"));
					dto.setOphone(rs.getString("ophone"));
					dto.setOpay(rs.getString("opay"));
					dto.setOcard(rs.getString("ocard"));
					dto.setOcardno(rs.getString("ocardno"));
					dto.setOcardpwd(rs.getString("ocardpwd"));
					dto.setOstate(rs.getString("ostate"));
	               
	               searchList.add(dto);
	            }
	            
	         } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	         }
	      }
	      
	      return searchList;
	      
	   }
	
	public OrderInfoDTO getOrderView(int oNo) {
		
		OrderInfoDTO dto = new OrderInfoDTO();
		
		try {
			openConn();
			
			sql = "select * from jsp_orderInfo "
					+ "where ono = ? "
					+ "order by ono desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setOno(rs.getInt("ono"));
				dto.setOid(rs.getString("oid"));
				dto.setOdate(rs.getString("odate"));
				dto.setOreceiver(rs.getString("oreceiver"));
				dto.setOaddr(rs.getString("oaddr"));
				dto.setOphone(rs.getString("ophone"));
				dto.setOpay(rs.getString("opay"));
				dto.setOcard(rs.getString("ocard"));
				dto.setOcardno(rs.getString("ocardno"));
				dto.setOcardpwd(rs.getString("ocardpwd"));
				dto.setOstate(rs.getString("ostate"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public int updateOrder(int oNo, String oState2) {		
		int result = 0;
		try {
			openConn();
			sql = "update jsp_orderinfo set "
					+ "ostate = ? "
					+ "where oNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, oState2);
			pstmt.setInt(2, oNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return result;
	}
	
	public int deleteOrder(int ono) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from jsp_orderinfo "
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

	public int insertOrderInfo(OrderInfoDTO odto) {
		int count = 0;		
		try {			
			openConn();
			sql = "select max(oNo) from jsp_orderinfo";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(!rs.next()) {
				count = 1;
			}else {
				count = rs.getInt(1) + 1;
			}
			sql = "insert into jsp_orderinfo values(?,?,sysdate,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, count);
			pstmt.setString(2, odto.getOid());
			pstmt.setString(3, odto.getOreceiver());
			pstmt.setString(4, odto.getOaddr());
			pstmt.setString(5, odto.getOphone());			
			pstmt.setString(6, odto.getOpay());
			pstmt.setString(7, odto.getOcard());
			pstmt.setString(8, odto.getOcardno());
			pstmt.setNString(9, odto.getOcardpwd());
			pstmt.setString(10, odto.getOstate());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}		
		return count;
	}
}
