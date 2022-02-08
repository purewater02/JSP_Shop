<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%
	String imgPath = request.getContextPath()+"/upload/";
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	
	.review_image {
		padding: 0px 200px 0px 200px;
		margin: 0px;
		background-color: black;
	}

	.review_detail {
		margin-left: 5vw;
		margin-right: 5vw;
		border: 1px solid lightgray;
		padding: 10px;
		display: flex;
		
		margin-top: 20px;
	}
	
	.review_table {
		padding: 20px 50px 20px 50px;
		margin: 20px 50px 20px 50px;
	}
	
	.td1 {
		background-color: yellow;
		display: flex;
	}
	
	.ta1 {
		font-size: 14px;
		border: 1px solid #E3E9E9;
		resize: none; /* 사용자 임의 변경 불가 */
	}
	
	.button {
		font-size: 18px;
		background-color: white;
		box-shadow: none;
		padding: 10px 70px 10px 70px;
		margin: 35px 140px 145px 3px;
		float: right;
		outline: 0;
		border: 1px solid lightgrey;
	}
</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />
	<c:set var="dto" value="${ReviewList }" />
	<c:set var="dto1" value="${ProductList }" />
	
	
	<h3 style="color: gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REVIEW</h3>
	
	<p align="right" class="p"> <a href="<%=request.getContextPath() %>/main.jsp" class="a">home</a>><a href="<%=request.getContextPath() %>/user_mypage.do?id=${loggedId }" class="a">mypage</a>> review&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
	
	<div class="review_detail">
		<img src="<%=imgPath %>${dto1.getPimage1() } " width="400px" height="600px" class="review_image">
		
		<table class="review_table">
		<tr><td>${dto.getpName() }</td></tr>
			<tr>
				<c:if test="${dto.getBstar() == 1 }">
				<td class="td1">★☆☆☆☆</td>
				</c:if>
				
				<c:if test="${dto.getBstar() == 2 }">
				<td class="td1">★★☆☆☆</td>
				</c:if>
				
				<c:if test="${dto.getBstar() == 3 }">
				<td class="td1">★★★☆☆</td>
				</c:if>
				
				<c:if test="${dto.getBstar() == 4 }">
				<td class="td1">★★★★☆</td>
				</c:if>
				
				<c:if test="${dto.getBstar() == 5 }">
				<td class="td1">★★★★★</td>
				</c:if>
			</tr>					
			<tr> <td> <textarea rows="20" cols="30" class="ta1">${dto.getbCont() }</textarea></td> </tr>
			<tr> <td>NAME&nbsp;&nbsp;${dto.getbWriter().substring(0,2) }****&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${dto.getbDate().substring(0,10) }</td> </tr>
		</table>
	</div>
			<button type="button" class="button" onclick="location.href='user_review_check.do?id=${loggedId }'"> 목록 </button>
	
	
	
				


<br><br><br><br><br><br><br><br><br><br>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>