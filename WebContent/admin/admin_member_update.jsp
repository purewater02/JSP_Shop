<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="blue">
			<h3> 수정 폼 페이지</h3>
		<hr width="65%" color="blue">
	
	
	<form method="post"
		action="<%=request.getContextPath() %>/admin_member_update_ok.do">
		<c:set var="dto" value="${MemberDto }" />
		
		<input type="hidden" name="pwd" value="${dto.getPwd() }">
		<table border="1" cellspacing="0" width="300">
				<tr>
					<th>ID</th>
					<td> <input type="text" name="id"
							value="${dto.getId() }" readonly>
				</tr>
				
				<tr>
					<th>이름</th>
					<td> <input type="text" name="name"
							value="${dto.getName() }" readonly>
				</tr>
				
				<tr>
					<th>연락처</th>
					<td> <input type="text" name="phone"
							value="${dto.getPhone() }" readonly>
				</tr>
				
				<tr>
					<th>이메일</th>
					<td> <input type="text" name="email"
							value="${dto.getEmail() }" readonly>
				</tr>
				
				<tr>
					<th>주소</th>
					<td> <input type="text" name="addr1"
							value="${dto.getAddr1() }" readonly> </td>
				</tr>
				<tr>
					<th></th>
					<td> <input type="text" name="addr2"
							value="${dto.getAddr2() }" readonly> </td>
				</tr>
				
				<tr>
					<th>마일리지</th>
					<td> <input type="text" name="mileage"
							value="${dto.getMileage() }" >
				</tr>
				
				<tr>
					<th>가입일</th>
					<td> <input type="text" name="regdate"
							value="${dto.getRegdate() }" readonly>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<input type ="submit" value="수정">&nbsp;&nbsp;&nbsp;
						<input type ="reset" value="다시작성">
					</td>
				</tr>
			</table>
	</form> 
	
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>