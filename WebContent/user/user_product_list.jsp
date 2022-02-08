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
<style>	
	body {
		font-family: paybooc-Light;
		margin-top: 30px;
	}
	
	.allList {
		margin: -30px 0 50px 0;
	}
	
	#pList {
		display: flex;
 		flex-wrap: wrap;
  		justify-content: center;
		text-align: center;
		margin: 30px 50px 50px 50px;
	}
	
	#pList a {
		text-decoration: none;
	}
	
	#item {
		display: flex;	
		justify-content: center;	
		height: 700px;
		margin: 50px;					
		align-items: center;	
		padding: 0 15px 0 15px;
		border: 2px solid black;
	}	

</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>

	<jsp:include page="../include/user_top.jsp"/>

		<h2 align="center">전체상품</h2>

		<c:set var="list" value="${productList }"/>
		
	<div align="center" class="allList">
		<table border="0" align="center" id="items">
			<c:if test="${!empty list }">
			<tr id="pList">
				<c:forEach items="${list }" var="dto">
						<td id="item"><a href="<%=request.getContextPath()%>/user_product_content.do?pNo=${dto.getPno() }&sessionId=${loggedId}">
						<img src="<%=imgPath %>${dto.getPimage1() }" width="400px" height="500px"><br> 
						${dto.getPname() }<br>
						₩&nbsp;<fmt:formatNumber value="${dto.getPprice() }" /><hr width="50%"></a><br>
						</td>
				</c:forEach>
			</tr>
			</c:if>
		</table>
		
		<c:if test="${empty list }">
			<h3 align="center">검색된 상품 리스트가 없습니다...</h3>
		</c:if>
		
		<c:if test="${page > block }">	<!-- 현재 페이지가 페이지 아래 보여지는 페이지 최대 수 보다 크다면, -->
			<a href="user_product_list.do?page=1">◀◀</a>	<!-- 맨 첫 페이지로 이동 -->
			&nbsp;
			<a href="user_product_list.do?page=${startBlock - 1}">◀</a>	 <!-- 현재 블럭 - 1 -->
			</c:if>
		
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="user_product_list.do?page=${i}">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="user_product_list.do?page=${i}">[${i }]</a>
			</c:if>
		</c:forEach>
		
		
		<c:if test="${endBlock < allPage }">	<!-- 4번 페이지에서 보이는 끝 페이지 번호인 6보다 모든 페이지 수가 크다면 -->
			<a href="user_product_list.do?page=${endBlock + 1}">▶</a>	<!-- 맨 끝 페이지로 이동 -->
			&nbsp;
			<a href="user_product_list.do?page=${allPage}">▶▶</a>	 <!-- 현재 블럭 - 1 -->
		</c:if>
	</div>
	
	<jsp:include page="../include/user_bottom.jsp"/>

</body>
</html>