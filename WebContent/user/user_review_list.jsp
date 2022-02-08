<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	@font-face {
    font-family: 'paybooc-Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}

	body {
		font-family: paybooc-Light;
	}
	
	 #reviewTable tr td{
    	padding: 5px 2px 0 2px;
    	text-align: center;
    	border-top: solid;
    	border-color: lightgray;
    }
</style>

</head>
<body>
	
	<div class="top_header">
		<jsp:include page="../include/user_top.jsp"/>
    </div>
    
    <div id="review" align="center" name="review">
					
					<c:set var="pname" value="${pName }"/>
					<c:set var="pcode" value="${pCode }"/>
					<h2 style="border-style:solid; border-width:3px; width:500px; padding:10px;">[${pname }] 상품 리뷰 전체 목록</h2>
					<br>
					
					<table border="0" align="center" width="1500px" id="reviewTable">
						<tr>
							<th>리뷰제목</th> <th>리뷰내용</th> <th>작성자</th> <th>평점</th> <th>작성일</th>
						</tr> 
						
						<c:set var="list" value="${List }"/>
						
						<c:forEach items="${list }" var="rv">
						<tr>
								<td>${rv.getbTitle() }</td>
								<td><br><textarea rows="5" cols="30" style="border: none; font-family: paybooc-Light; resize: none;" readonly>${rv.getbCont() }</textarea>
								</td>
								<td>${rv.getbWriter().substring(0, 1) }**</td>
								<c:if test="${rv.getBstar() == 1 }">
								<td>★☆☆☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 2 }">
								<td>★★☆☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 3 }">
								<td>★★★☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 4 }">
								<td>★★★★☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 5 }">
								<td>★★★★★</td>
								</c:if>
								
								<td>${rv.getbDate().substring(0, 10) }</td>
								
							</tr>
							</c:forEach>
					</table>
					
		<c:if test="${page > block }">	<!-- 현재 페이지가 페이지 아래 보여지는 페이지 최대 수 보다 크다면, -->
			<a href="user_review_list.do?page=1&pcode=${pcode}&pname=${pname}">◀◀</a>	<!-- 맨 첫 페이지로 이동 -->
			&nbsp;
			<a href="user_review_list.do?page=${startBlock - 1}&pcode=${pcode}&pname=${pname}">◀</a>	 <!-- 현재 블럭 - 1 -->
		</c:if>
		
		
		<c:forEach begin="${startBlock }" end="${endBlock }" var="i">
			<c:if test="${i == page }">
				<b><a href="user_review_list.do?page=${i}&pcode=${pcode}&pname=${pname}">[${i }]</a></b>
			</c:if>
			
			<c:if test="${i != page }">
				<a href="user_review_list.do?page=${i}&pcode=${pcode}&pname=${pname}">[${i }]</a>
			</c:if>
		</c:forEach>
		
		
		<c:if test="${endBlock < allPage }">	<!-- 4번 페이지에서 보이는 끝 페이지 번호인 6보다 모든 페이지 수가 크다면 -->
			<a href="user_review_list.do?page=${endBlock + 1}&pcode=${pcode}&pname=${pname}">▶</a>	<!-- 맨 끝 페이지로 이동 -->
			&nbsp;
			<a href="user_review_list.do?page=${allPage}&pcode=${pcode}&pname=${pname}">▶▶</a>	 <!-- 현재 블럭 - 1 -->
		</c:if>
	</div>
	
	<br><br>
    
    
     <jsp:include page="../include/user_bottom.jsp"/>

</body>
</html>