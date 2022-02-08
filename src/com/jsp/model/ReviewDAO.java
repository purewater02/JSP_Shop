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

public class ReviewDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = null;
	
	private static ReviewDAO instance = null;
	
	private ReviewDAO() {}
	
	public static ReviewDAO getInstance() {
		if(instance == null) {
			instance = new ReviewDAO();
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
	
	public int getReviewTotal( ) {
		int result = 0;
		try {
			openConn();
			sql = "select count(*) from jsp_review";
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

	public List<ReviewDTO> getReviewList(Paging paging) {
		int startNum = paging.getStartNum();
		int endNum = paging.getEndNum();
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();		
		try {
			openConn();
			sql = "select * from ("
					+ "select * from ("
					+ "select row_number() over(order by bNo desc) as row_num, p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode) "
					+ "where row_num >= ?) "
					+ "where row_num <= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));	
				dto.setpName(rs.getString("pName"));
				dto.setBstar(rs.getInt("bStar"));
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

	public ReviewDTO getReviewDetail(int bNo) {
		ReviewDTO dto = new ReviewDTO();		
		try {
			openConn();
			sql = "select * from jsp_review where bNo = ?";
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
			}			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		return dto;
	}

	public int deleteReview(int bNo) {
		int result = 0;		
		try {
			openConn();
			sql = "delete from jsp_review where bNo = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bNo);
			result = pstmt.executeUpdate();
			
			sql = "update jsp_review set bNo = bNo - 1 where bNo > ?";
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
	
	public List<ReviewDTO> getReviewList1(String id) {
		List<ReviewDTO> list = new ArrayList<ReviewDTO>();		
		try {
			openConn();
			sql = "select bno, btitle, bwriter, bcont, bdate, bpwd, r.pcode, bstar, p.pname "
					+ " from jsp_review r join jsp_product p "
					+ " on r.pcode = p.pcode where bwriter = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReviewDTO dto = new ReviewDTO();
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));
				dto.setPcode(rs.getString("pcode"));
				dto.setBstar(rs.getInt("bstar"));
				dto.setpName(rs.getString("pName"));
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
	
	public ReviewDTO getReviewList2(String id) {
		ReviewDTO dto = new ReviewDTO();	
		try {
			openConn();
			sql = "select bno, btitle, bwriter, bcont, bdate, bpwd, r.pcode, bstar, p.pname "
					+ " from jsp_review r join jsp_product p "
					+ " on r.pcode = p.pcode where bwriter = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto.setbNo(rs.getInt("bNo"));
				dto.setbTitle(rs.getString("bTitle"));
				dto.setbWriter(rs.getString("bWriter"));
				dto.setbCont(rs.getString("bCont"));
				dto.setbDate(rs.getString("bDate"));
				dto.setbPwd(rs.getString("bPwd"));
				dto.setPcode(rs.getString("pcode"));
				dto.setBstar(rs.getInt("bstar"));
				dto.setpName(rs.getString("pName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);			
		}
		return dto;
	}
	
	// 리뷰 갯수 확인
		public int getReviewCount(String pcode) {
			int count = 0;
			
			try {	
				openConn();
				
				sql = "select count(*) from jsp_review where pcode = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pcode);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return count;
		}	
		
		// 상품 상세페이지 리뷰 간단 목록
		public List<ReviewDTO> getReviewList(String pcode) {
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();
			
			try {
				openConn();
				
				sql ="select * from jsp_review where pcode = ?";
							
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, pcode);
							
				rs = pstmt.executeQuery();

				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setPcode(rs.getString("pcode"));
					dto.setBstar(rs.getInt("bstar"));
					
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
		
		
		
		// 상품 상세페이지 -> 리뷰 확인페이지 리뷰목록
		public List<ReviewDTO> getReviewList(String pcode, int page, int rowsize) {
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();		

			int startNo = (page * rowsize) - (rowsize - 1);

			int endNo = (page * rowsize);
			
			try {
				openConn();
				
				// row_number() = 순번을 정해주는 오라클 함수 
				sql = "select * from (select row_number() over(order by bno desc) rnum, b.* from jsp_review b where pcode = ?) where rnum >= ? "
						+ " and rnum <= ?";
							
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, pcode);
				pstmt.setInt(2, startNo);
				pstmt.setInt(3, endNo);
							
				rs = pstmt.executeQuery();

				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));
					dto.setPcode(rs.getString("pcode"));
					dto.setBstar(rs.getInt("bstar"));
					
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
		
		// 유저 리뷰 작성 메서드
	      public int reviewWrite(ReviewDTO dto, String pname, int ono) {
	         int result = 0, bNo = 0;
	         String pcode = "";
	         
	         openConn();

	         try {   
	               sql = "select pcode from jsp_product where pname = ?";
	               pstmt = con.prepareStatement(sql);
	               pstmt.setString(1, pname);
	               rs = pstmt.executeQuery();
	               while(rs.next()) {
	                  pcode = rs.getString("pcode");
	               }
	               
	               sql = "select max(bno) from jsp_review";
	                        
	               pstmt = con.prepareStatement(sql);
	                        
	               rs = pstmt.executeQuery();
	                        
	               if(rs.next()) {
	                  bNo = rs.getInt(1) + 1;
	               }
	                        
	               sql="select * from jsp_review where bwriter = ? and pcode = ?";
	                        
	               pstmt = con.prepareStatement(sql);
	                        
	               pstmt.setString(1, dto.getbWriter());
	               pstmt.setString(2, pcode);
	                     
	               rs = pstmt.executeQuery();
	                        
	               if(rs.next()) {
	                  result = -2;
	               } else {
	                  sql = "insert into jsp_review values(?, ?, ?, ?, sysdate, ?, ?, ?)";
	                           
	                  pstmt = con.prepareStatement(sql);
	                           
	                  pstmt.setInt(1, bNo);
	                  pstmt.setString(2, dto.getbTitle());
	                  pstmt.setString(3, dto.getbWriter());
	                  pstmt.setString(4, dto.getbCont());
	                  pstmt.setString(5, dto.getbPwd());
	                  pstmt.setString(6, pcode);
	                  pstmt.setInt(7, dto.getBstar());
	                  
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
		
		// 별점 평균 확인
		public int getStarSum(String pcode) {
			int sum = 0;
			
			try {	
				openConn();
				
				sql = "select sum(bstar) from jsp_review where pcode = ?";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pcode);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					sum = rs.getInt(1);
				}
				rs.close(); pstmt.close(); con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return sum;
		}

		public int getReviewFilterTotal(String option, String keyword) {
			int count = 0;
			openConn();
			String column = "b"+option;		
			try {
				sql = "select count(*) from jsp_review where ? like ?";
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

		public List<ReviewDTO> getReviewFilterList(Paging paging, String keyword, String option) {
			int startNum = paging.getStartNum();
			int endNum = paging.getEndNum();
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();		
			if(option.equals("title")) {
				try {
					openConn();
					sql = "select * from ("
							+ "select * from ("
							+ "select row_number() over(order by bNo desc) as row_num, p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode and r.bTitle like ?) "
							+ "where row_num >= ?) "
							+ "where row_num <= ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNum);
					pstmt.setInt(3, endNum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						ReviewDTO dto = new ReviewDTO();
						dto.setbNo(rs.getInt("bNo"));
						dto.setbTitle(rs.getString("bTitle"));
						dto.setbWriter(rs.getString("bWriter"));
						dto.setbCont(rs.getString("bCont"));
						dto.setbDate(rs.getString("bDate"));
						dto.setbPwd(rs.getString("bPwd"));
						dto.setpName(rs.getString("pName"));
						dto.setBstar(rs.getInt("bstar"));
						
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
					sql = "select * from ("
							+ "select * from ("
							+ "select row_number() over(order by bNo desc) as row_num, p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode and r.bCont like ?) "
							+ "where row_num >= ?) "
							+ "where row_num <= ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNum);
					pstmt.setInt(3, endNum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						ReviewDTO dto = new ReviewDTO();
						dto.setbNo(rs.getInt("bNo"));
						dto.setbTitle(rs.getString("bTitle"));
						dto.setbWriter(rs.getString("bWriter"));
						dto.setbCont(rs.getString("bCont"));
						dto.setbDate(rs.getString("bDate"));
						dto.setbPwd(rs.getString("bPwd"));
						dto.setpName(rs.getString("pName"));
						dto.setBstar(rs.getInt("bstar"));
						
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
					sql = "select * from ("
							+ "select * from ("
							+ "select row_number() over(order by bNo desc) as row_num, p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode and r.bWriter like ?) "
							+ "where row_num >= ?) "
							+ "where row_num <= ?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNum);
					pstmt.setInt(3, endNum);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						ReviewDTO dto = new ReviewDTO();
						dto.setbNo(rs.getInt("bNo"));
						dto.setbTitle(rs.getString("bTitle"));
						dto.setbWriter(rs.getString("bWriter"));
						dto.setbCont(rs.getString("bCont"));
						dto.setbDate(rs.getString("bDate"));
						dto.setbPwd(rs.getString("bPwd"));
						dto.setpName(rs.getString("pName"));
						dto.setBstar(rs.getInt("bstar"));
						
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

		public ReviewDTO getCommunityReviewDetail(int bNo) {
			ReviewDTO dto = new ReviewDTO();		
			try {
				openConn();
				sql = "select p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode and r.bNo = ?";
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
					dto.setpName(rs.getString("pName"));
					dto.setBstar(rs.getInt("bstar"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				closeConn(rs, pstmt, con);
			}
			return dto;
		}

		public List<ReviewDTO> getMypageReviewList(Paging paging, String id) {
			int startNum = paging.getStartNum();
			int endNum = paging.getEndNum();
			List<ReviewDTO> list = new ArrayList<ReviewDTO>();		
			try {
				openConn();
				sql = "select * from ("
						+ "select * from ("
						+ "select row_number() over(order by bNo desc) as row_num, p.pname, r.* from jsp_product p, jsp_review r where p.pcode = r.pcode and r.bWriter = ?) "
						+ "where row_num >= ?) "
						+ "where row_num <= ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					ReviewDTO dto = new ReviewDTO();
					dto.setbNo(rs.getInt("bNo"));
					dto.setbTitle(rs.getString("bTitle"));
					dto.setbWriter(rs.getString("bWriter"));
					dto.setbCont(rs.getString("bCont"));
					dto.setbDate(rs.getString("bDate"));
					dto.setbPwd(rs.getString("bPwd"));	
					dto.setpName(rs.getString("pName"));
					dto.setBstar(rs.getInt("bStar"));
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
