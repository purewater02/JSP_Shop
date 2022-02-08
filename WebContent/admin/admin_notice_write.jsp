<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 글쓰기</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<div align="center">
		<hr width="65%" color="maroon">
			<h3>공지사항 글쓰기</h3>
		<hr width="65%" color="maroon">
		<br>
		<form method="post" action="<%=request.getContextPath() %>/admin_notice_write_ok.do">			
			<table border="1" cellspacing="0" width="300">
				<tr>
					<th>글 제목</th>
					<td><input name="bTitle"></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input name="bWriter" value="${loggedId }" readonly></td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea cols="22" rows="8" name="bContent"></textarea></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<input type="submit" value="등록">&nbsp;&nbsp;&nbsp;
						<input type="reset" value="공지 목록" onclick="location.href='admin_notice_list.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../include/admin_bottom.jsp"/>
</body>
</html>