<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	td {
		text-align: center;
	}
	
</style>
</head>
<body>
	
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="red">
			<h3>회원 목록 리스트</h3>
		<hr width="65%" color="red">
		<br>
		
		<table border="1" cellspacing="0" width="65%">
			<tr bgcolor="#ffcc00">
				<th>아이디</th> <th>이름</th> <th>연락처</th>
				<th>마일리지</th> <th>주소</th> <th>가입일</th> <th>수 정&nbsp;&nbsp;|&nbsp;&nbsp;삭 제</th>
			</tr>
			
			<c:set var="list" value="${memberList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getId() }</td>
						<td> ${dto.getName() }</td>
						<td> ${dto.getPhone() }</td>
						<td> ${dto.getMileage() } </td>
						<td> ${dto.getAddr1() }&nbsp;${dto.getAddr2() }  </td>
						<td> ${dto.getRegdate().substring(0, 10) } </td>
						<td>
							<a href="<%=request.getContextPath() %>/admin_member_update.do?id=${dto.getId() }">수정</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="<%=request.getContextPath() %>/admin_member_delete.do?id=${dto.getId() }">삭제</a>
						</td>
					</tr>
					
			</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="9" align="center">
						<h3>등록된 회원 리스트가 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>