<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	td {
		text-align: center;
	}
	
	.ordList {
		margin-left: 20%;
		margin-bottom: 15px;
	}
	
</style>
</head>
<body>
	
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="red">
			<h3>전체 주문 리스트</h3>
		<hr width="65%" color="red">
		<br>
	</div>
	<div class="ordList">
	<form method="post"
			action="<%=request.getContextPath() %>/admin_order_search.do">
		
			<select name="search_field">
				<option value="oNo">주문 번호</option>
				<option value="oId">주문자 아이디</option>
				<option value="oReceiver">수령인</option>					
			</select>	&nbsp;&nbsp;
			
			<input type="text" name="search_keyword">&nbsp;
			<input type="submit" value="검색">
			
	</form>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br>
	</div>
	<div align="center">	

		<table border="1" cellspacing="0" width="65%">
			<tr bgcolor="#ffcc00">
				<th>주문번호</th> <th>주문자 ID</th> <th>수령인</th>
				<th>결제 수단</th> <th>주문 상태</th> <th>수 정&nbsp;&nbsp;|&nbsp;&nbsp;삭 제</th>
			</tr>
			
			<c:set var="list" value="${orderList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> <a href="<%=request.getContextPath() %>/admin_order_view.do?oNo=${dto.getOno() }">${dto.getOno() }</a></td>
						<td> <a href="<%=request.getContextPath() %>/admin_order_view.do?oNo=${dto.getOno() }">${dto.getOid() }</a></td>
						<td> ${dto.getOreceiver() }</td>
						<td> ${dto.getOpay() } </td>
						<td> ${dto.getOstate() } </td>
						<td>
							<a href="<%=request.getContextPath() %>/admin_order_update.do?oNo=${dto.getOno() }">수정</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="<%=request.getContextPath() %>/admin_order_delete.do?oNo=${dto.getOno() }">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="9" align="center">
						<h3>등록된 주문 리스트가 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>