<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	ul li {
		list-style: none;
	}

</style>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<ul>
			<li> <a href="<%=request.getContextPath() %>/admin_member_control.do">회원 관리</a> </li>
			
			<li> <a href="<%=request.getContextPath() %>/admin_product_control.do">제품 관리</a> </li>
			
			<li> <a href="<%=request.getContextPath() %>/admin_board_control.do">게시판 관리</a>	</li>
			
			<li> <a href="<%=request.getContextPath() %>/admin_order_control.do">주문 관리</a>	</li>
		</ul>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>