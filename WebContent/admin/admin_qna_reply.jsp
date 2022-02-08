<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&amp;A 답변하기</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<div align="center">
		<hr width="65%" color="blue">
			<h3>Q&amp;A 답변하기</h3>
		<hr width="65%" color="blue">
		<br><br>
		<c:set var="dto" value="${reply}" />
		<form method="post" action="<%=request.getContextPath() %>/admin_qna_reply_ok.do">
			<input type="hidden" name="bNo" value="${dto.getbNo() }">
			<input type="hidden" name="bGroup" value="${dto.getbGroup() }">
			<input type="hidden" name="bStep" value="${dto.getbStep() }">
			<input type="hidden" name="bIndent" value="${dto.getbIndent() }">
			<table border="1" cellspacing="0" width="450">				
				<tr>
					<th>작성자</th>
					<td> <input name="bWriter" value="${loggedId }" readonly> </td>
				</tr>
				<tr>
					<th>글 제목</th>
					<td> <input name="bTitle" value="(답변완료)${dto.getbTitle() }"> </td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td> 
					<textarea rows="7" cols="22" name="bCont">
(원글 내용)
${dto.getbCont() }
(답변 내용)
</textarea>			 
					</td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td> <input type="password" name="bPwd"> </td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="submit" value="답변하기">&nbsp;&nbsp;&nbsp;
						<input type="button" value="Q & A목록" onclick="location.href='admin_qna_list.do?page=${cPage}'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>