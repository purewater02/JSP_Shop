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


public class ProductDAO {

	Connection con = null;             // DB 연결하는 객체.
	PreparedStatement pstmt = null;    // DB에 SQL문을 전송하는 객체.
	ResultSet rs = null;               // SQL문을 실행 후 결과 값을 가지고 있는 객체.
	
	String sql = null;                 // SQL문을 저장할 객체.
	
	
	// ProductDAO 객체를 싱글톤 방식으로 만들어 보자.
	// 1단계 : 싱글톤 방식으로 객체를 만들기 위해서는 우선적으로
	//        기본 생성자의 접근 제어자를 private 으로 선언해야 함.
	// 2단계 : ProductDAO 객체를 정적 멤버로 선언해야 함. - static으로 선언해야 함.
	private static ProductDAO instance = null;
	
	
	private ProductDAO() {   }  // 기본생성자.
		
	// 3단계 : 기본 생성자 대신에 싱글턴 객체를 return 해 주는 getInstance() 라는
	//        메서드를 만들어서 여기에 접근하게 해야 함.
	public static ProductDAO getInstance() {
		
		if(instance == null) {
			instance = new ProductDAO();
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
	
	public List<ProductDTO> getProductList() {
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "order by pno desc";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setPsold(rs.getInt("psold"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPimage1(rs.getString("pimage1"));
				dto.setPimage2(rs.getString("pimage2"));
				dto.setPcontent(rs.getString("pcontent"));
				dto.setPmileage(rs.getInt("pmileage"));
				
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
	
	public int insertProduct(ProductDTO dto) {
		
		int result = 0, count = 0;
		
		try {
			openConn();
			
			sql = "select max(pno) from jsp_product";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1) + 1;
			}
			
			sql = "insert into jsp_product "
					+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, count);
			pstmt.setString(2, dto.getCtg1());
			pstmt.setString(3, dto.getCtg2());
			pstmt.setString(4, dto.getPcode());
			pstmt.setString(5, dto.getPname());
			pstmt.setInt(6, dto.getPprice());
			pstmt.setInt(7, Integer.parseInt("0"));
			pstmt.setString(8, dto.getPsize());
			pstmt.setString(9, dto.getPcolor());
			pstmt.setString(10, dto.getPimage1());
			pstmt.setString(11, dto.getPimage2());
			pstmt.setString(12, dto.getPcontent());
			pstmt.setInt(13, dto.getPmileage());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
	public ProductDTO productCont(int pno) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "where pno = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pno);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setPsold(rs.getInt("psold"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPimage1(rs.getString("pimage1"));
				dto.setPimage2(rs.getString("pimage2"));
				dto.setPcontent(rs.getString("pcontent"));
				dto.setPmileage(rs.getInt("pmileage"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}
	
	public int updateProduct(ProductDTO dto) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "update jsp_product set pimage1 = ?, "
					+ "pprice = ?, psold = ?, pimage2 = ?, "
					+ "psize = ?, pcolor = ?, "
					+ "pcontent = ?, pmileage = ? where pno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, dto.getPimage1());
			pstmt.setInt(2, dto.getPprice());
			pstmt.setInt(3, dto.getPsold());
			pstmt.setString(4, dto.getPimage2());
			pstmt.setString(5, dto.getPsize());
			pstmt.setString(6, dto.getPcolor());
			pstmt.setString(7, dto.getPcontent());
			pstmt.setInt(8, dto.getPmileage());
			pstmt.setInt(9, dto.getPno());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
	}
	
public int deleteProduct(int pno) {
		
		int result = 0;
		
		try {
			openConn();
			
			sql = "delete from jsp_product "
					+ "where pno = ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return result;
		
	}
	
	
	// 중간의 제품번호가 삭제되었을 때 번호 순번 다시 작업해 주는 메서드.
	public void updateSequence(int pno) {
		
		try {
			openConn();
			
			sql = "update jsp_product set pno = pno - 1 "
					+ "where pno > ?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
	}	// updateSequence() 메서드 end
	
	public List<ProductDTO> getProductList(String ctg1) {
		
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "where ctg1 = ? "
					+ "order by pno desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ctg1);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setPsold(rs.getInt("psold"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPimage1(rs.getString("pimage1"));
				dto.setPimage2(rs.getString("pimage2"));
				dto.setPcontent(rs.getString("pcontent"));
				dto.setPmileage(rs.getInt("pmileage"));
				
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
	
	
	public List<ProductDTO> getProductList2(String pcode) {
		
		List<ProductDTO> list1 = new ArrayList<ProductDTO>();
		
		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "where pcode = ? "
					+ "order by pno desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setPsold(rs.getInt("psold"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPimage1(rs.getString("pimage1"));
				dto.setPimage2(rs.getString("pimage2"));
				dto.setPcontent(rs.getString("pcontent"));
				dto.setPmileage(rs.getInt("pmileage"));
				
				list1.add(dto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return list1;
	}
	
		public ProductDTO getProductList3(String pcode) {
		
		ProductDTO dto = new ProductDTO();
		
		try {
			openConn();
			
			sql = "select * from jsp_product "
					+ "where pcode = ? "
					+ "order by pno desc";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pcode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dto.setPno(rs.getInt("pno"));
				dto.setCtg1(rs.getString("ctg1"));
				dto.setCtg2(rs.getString("ctg2"));
				dto.setPcode(rs.getString("pcode"));
				dto.setPname(rs.getString("pname"));
				dto.setPprice(rs.getInt("pprice"));
				dto.setPsold(rs.getInt("psold"));
				dto.setPsize(rs.getString("psize"));
				dto.setPcolor(rs.getString("pcolor"));
				dto.setPimage1(rs.getString("pimage1"));
				dto.setPimage2(rs.getString("pimage2"));
				dto.setPcontent(rs.getString("pcontent"));
				dto.setPmileage(rs.getInt("pmileage"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn(rs, pstmt, con);
		}
		
		return dto;
	}

	
		// 모든 상품 갯수 확인
		public int getProductCount() {
			int count = 0;
				
			try {	
				openConn();
					
				sql = "select count(*) from jsp_product";
					
				pstmt = con.prepareStatement(sql);
					
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
			
			// 카테고리별 상품 갯수 확인
			public int getSearchProductCount(String keyword) {
				int count = 0;
							
				try {	
					openConn();
								
					sql = "select count(*) from jsp_product where pname like ? order by pno desc";
								
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");

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
			
			// 검색 상품 갯수 확인
			public int getCtgProductCount(String ctg) {
				int count = 0;
										
				try {	
					openConn();
											
					sql = "select count(*) from jsp_product where ctg1 = ? or ctg2 = ?";
											
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, ctg);
					pstmt.setString(2, ctg);
								
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

			
			// All 클릭 시 나오는 전체목록 페이징
			public List<ProductDTO> getAllProductList(int page, int rowsize) {
				
				List<ProductDTO> list = new ArrayList<ProductDTO>();
				
				int startNo = (page * rowsize) - (rowsize - 1);

				int endNo = (page * rowsize);
				
				try {
					openConn();
					
					sql = "select * from (select row_number() over(order by pno desc) rnum, b.* from jsp_product b) where rnum >= ? "
							+ " and rnum <= ?";
					
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, startNo);
					pstmt.setInt(2, endNo);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						ProductDTO dto = new ProductDTO();
						
						dto.setPno(rs.getInt("pno"));
						dto.setCtg1(rs.getString("ctg1"));
						dto.setCtg2(rs.getString("ctg2"));
						dto.setPcode(rs.getString("pcode"));
						dto.setPname(rs.getString("pname"));
						dto.setPprice(rs.getInt("pprice"));
						dto.setPsold(rs.getInt("psold"));
						dto.setPsize(rs.getString("psize"));
						dto.setPcolor(rs.getString("pcolor"));
						dto.setPimage1(rs.getString("pimage1"));
						dto.setPimage2(rs.getString("pimage2"));
						dto.setPcontent(rs.getString("pcontent"));
						dto.setPmileage(rs.getInt("pmileage"));
						
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
			
			// 카테고리별 리스트페이지 페이징
			public List<ProductDTO> getProductListCtg(String ctg, int page, int rowsize) {
				
				List<ProductDTO> list = new ArrayList<ProductDTO>();
				
				int startNo = (page * rowsize) - (rowsize - 1);

				int endNo = (page * rowsize);
				
				try {
					openConn();
					
					sql = "select * from (select row_number() over(order by pno desc) rnum, b.* from jsp_product b where ctg1 = ? or ctg2 = ?) where rnum >= ? "
							+ " and rnum <= ?";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, ctg);
					pstmt.setString(2, ctg);
					pstmt.setInt(3, startNo);
					pstmt.setInt(4, endNo);
					
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						ProductDTO dto = new ProductDTO();
						
						dto.setPno(rs.getInt("pno"));
						dto.setCtg1(rs.getString("ctg1"));
						dto.setCtg2(rs.getString("ctg2"));
						dto.setPcode(rs.getString("pcode"));
						dto.setPname(rs.getString("pname"));
						dto.setPprice(rs.getInt("pprice"));
						dto.setPsold(rs.getInt("psold"));
						dto.setPsize(rs.getString("psize"));
						dto.setPcolor(rs.getString("pcolor"));
						dto.setPimage1(rs.getString("pimage1"));
						dto.setPimage2(rs.getString("pimage2"));
						dto.setPcontent(rs.getString("pcontent"));
						dto.setPmileage(rs.getInt("pmileage"));
						
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
			
			// 상품 검색 결과 페이징
			public List<ProductDTO> searchProductList(String keyword, int page, int rowsize) {
				
				List<ProductDTO> list = new ArrayList<ProductDTO>();
				
				int startNo = (page * rowsize) - (rowsize - 1);

				int endNo = (page * rowsize);
				
				try {
					openConn();
					
					sql = "select * from (select row_number() over(order by pno desc) rnum, b.* from jsp_product b where pname like ?) where rnum >= ? "
							+ " and rnum <= ?";
						
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, "%"+keyword+"%");
					pstmt.setInt(2, startNo);
					pstmt.setInt(3, endNo);

					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						ProductDTO dto = new ProductDTO();
						
						dto.setPno(rs.getInt("pno"));
						dto.setCtg1(rs.getString("ctg1"));
						dto.setCtg2(rs.getString("ctg2"));
						dto.setPcode(rs.getString("pcode"));
						dto.setPname(rs.getString("pname"));
						dto.setPprice(rs.getInt("pprice"));
						dto.setPsold(rs.getInt("psold"));
						dto.setPsize(rs.getString("psize"));
						dto.setPcolor(rs.getString("pcolor"));
						dto.setPimage1(rs.getString("pimage1"));
						dto.setPimage2(rs.getString("pimage2"));
						dto.setPcontent(rs.getString("pcontent"));
						dto.setPmileage(rs.getInt("pmileage"));
						
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
