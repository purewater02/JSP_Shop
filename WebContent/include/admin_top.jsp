<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#title {
		float: right;
		margin-right: 300px;
		word-spacing: 10px;
		list-style: none;
		clear: both;
	}
	
	#header a {
		text-decoration: none;
	}
</style>
</head>
<body>

	<div id="header" align="center">
		<hr width="65%">
		<h2>관리자 페이지</h2>
		
		<div id="title">
			<a href="#">${loggedName }님 환영합니다.</a>
			<a href="<%=request.getContextPath() %>/admin_logout.do">로그아웃</a>
		</div>
		
		<br>
		<hr width="65%">
		
		<table width="800">
			<tr>
				<td>
					 <a href="<%=request.getContextPath() %>/admin_main.do">관리자 홈</a>
				</td>
			
				<td>
					 <a href="<%=request.getContextPath() %>/admin_member_control.do">회원 관리</a>
				</td>
				
				<td>
					 <a href="<%=request.getContextPath() %>/admin_product_control.do">제품 관리</a>
				</td>
				
				<td>
					 <a href="<%=request.getContextPath() %>/admin_board_control.do">게시판 관리</a>
				</td>
				
				<td>
					 <a href="<%=request.getContextPath() %>/admin_order_control.do">주문 관리</a>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>