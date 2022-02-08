<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%

	String imgPath = request.getContextPath()+"/upload/";

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	td {
		text-align: center;
	}
	
	.ctgList {
		margin-left: 20%;
		margin-bottom: 15px;
	}
	
	.ctgList #add {
		margin-left: 40%;
	}
</style>
</head>
<body>
	
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="red">
			<h3>전체 상품 리스트</h3>
		<hr width="65%" color="red">
		<br>
	</div>
	<c:set var="list1" value="${categoryList1 }" /> 
	<div class="ctgList">
	<form method="post" 
         action="<%=request.getContextPath()%>/admin_product_ctgList.do">
		카테고리별 검색	
		&nbsp;&nbsp;&nbsp;
		<select name="Ctg1">
			<c:if test="${empty list1 }">
				<option value="">:::없음:::</option>
			</c:if>
							
			<c:if test="${!empty list1 }">
				<c:forEach items="${list1 }" var="dto">
					<option value="${dto.getCtg1() }">[${dto.getCtg1() }] </option>
				</c:forEach>
			</c:if>		
		</select>
		&nbsp;&nbsp;&nbsp;
		<input type="submit" value="검색">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="<%=request.getContextPath() %>/admin_product_control.do">전체리스트</a>
		<input type="button" value="상품 추가" id="add"
						onclick="location.href='admin_product_input.do'">
	</form>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<br>
	</div>
	<div align="center">	

		<table border="1" cellspacing="0" width="65%">
			<tr bgcolor="#ffcc00">
				<th>상품번호</th> <th>카테고리</th> <th>상품코드</th>
				<th>상품 이름</th> <th>상품 가격</th> <th>판매량</th>
				<th>이미지</th> <th>수 정&nbsp;&nbsp;|&nbsp;&nbsp;삭 제</th>
			</tr>
			
			<c:set var="list" value="${productList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td> ${dto.getPno() }</td>
						<td> ${dto.getCtg1() }/${dto.getCtg2() }</td>
						<td> ${dto.getPcode() }</td>
						<td> ${dto.getPname() } </td>
						<td> <fmt:formatNumber value="${dto.getPprice() }" />원 </td>
						<td> ${dto.getPsold() } </td>
						<td> <img src="<%=imgPath %>${dto.getPimage1() }"
									width="60" height="60"> </td>
						<td>
							<a href="<%=request.getContextPath() %>/admin_product_update.do?pNo=${dto.getPno() }">수정</a>
							&nbsp;&nbsp;|&nbsp;&nbsp;
							<a href="<%=request.getContextPath() %>/admin_product_delete.do?pNo=${dto.getPno() }">삭제</a>
						</td>
					</tr>
					
			</c:forEach>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td colspan="9" align="center">
						<h3>등록된 상품 리스트가 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>