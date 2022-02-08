<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자_게시판 관리</title>
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
			<li> <a href="<%=request.getContextPath() %>/admin_notice_list.do">공지사항 목록</a> </li>
			
			<li> <a href="<%=request.getContextPath() %>/admin_qna_list.do">Q &amp; A 목록</a> </li>
			
			<li> <a href="<%=request.getContextPath() %>/admin_review_list.do">Review 목록</a> </li>
		</ul>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>