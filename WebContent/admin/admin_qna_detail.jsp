<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q&amp;A 글 상세</title>
</head>
<body>
	<jsp:include page="../include/admin_top.jsp" />
	<div align="center">
		<hr width="65%" color="maroon">
			<h3>Q&amp;A 글 상세</h3>
		<hr width="65%" color="maroon">
		<br>		
		<c:set var="dto" value="${qnaDetail }" />			
		<table border="1" cellspacing="0" width="300">
			<tr>
				<th>글 번호</th>
				<td>${dto.getbNo() }</td>
			</tr>
			<tr>
				<th>글 제목</th>
				<td>${dto.getbTitle() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.getbWriter() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea cols="22" rows="8" name="bContent" readonly>${dto.getbCont() }</textarea></td>
			</tr>
			<tr>
				<th>작성일자</th>
				<td>${dto.getbDate().substring(0,10) }</td>
			</tr>			
			<tr>
				<td colspan="5" align="center">					
					<input type="button" value="Q&amp;A 목록" onclick="location.href='admin_qna_list.do?page=${cPage}'">
				</td>
			</tr>
		</table>		
	</div>
	<jsp:include page="../include/admin_bottom.jsp"/>
</body>
</html>