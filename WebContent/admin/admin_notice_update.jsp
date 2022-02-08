<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글 수정</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<div align="center">
		<hr width="65%" color="maroon">
			<h3>공지사항 글수정</h3>
		<hr width="65%" color="maroon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/admin_notice_update_ok.do">
		<c:set var="dto" value="${noticeDetail }"></c:set>
			<input type="hidden" name="bNo" value="${dto.getbNo() }">
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>글 제목</th>
					<td><input name="bTitle" value="${dto.getbTitle() }"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input name="bWriter" value="${dto.getbWriter() }" readonly></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="22" rows="8" name="bContent">${dto.getbCont() }</textarea></td>
				</tr>				
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="수정">&nbsp;&nbsp;&nbsp;
						<input type="button" value="공지 목록" onclick="location.href='admin_notice_list.do?page=${cPage}'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../include/admin_bottom.jsp"/>
</body>
</html>