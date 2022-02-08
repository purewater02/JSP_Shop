<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	body {
	}
</style>
</head>
<body>

	<div class="top_header">
		<jsp:include page="../include/user_top.jsp"/>
    </div>
	
	<c:set var="pname" value="${pName }"/>
	<c:set var="ono" value="${oNo }"/>
	<div>
		<h2 align="center">[${pname }] 상품 리뷰 작성 페이지</h2>
		
		<form method="post" action="<%=request.getContextPath()%>/user_review_write_ok.do" onsubmit="return confirm('작성하신 리뷰를 등록하시겠습니까?')">
			<input type="hidden" name="bWriter" value="${loggedId }">
			<input type="hidden" name="pName" value="${pname }">
			<input type="hidden" name="oNo" value="${ono }">
		
		<table align="center">
			<tr>
				<th>작성자</th>	
				<td>${loggedName } 님</td>
			</tr>
			
			<tr>
				<th>리뷰 제목</th>	
				<td><input name="bTitle" style="width: 295px;"></td>
			</tr>	
			
			<tr>
				<th>리뷰 내용</th>	
				<td><textarea rows="8" cols=40" name="bContent" style="resize:none;"></textarea></td>
			</tr>
			
			<tr>
				<th>평점</th>
				<td>
					<select name="bStar">
						<option value="5" selected>★★★★★</option>
						<option value="4">★★★★☆</option>
						<option value="3">★★★☆☆</option>
						<option value="2">★★☆☆☆</option>
						<option value="1">★☆☆☆☆</option>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>리뷰 비밀번호</th>
				<td><input type="password" name="bPwd" style="width:50px;"></td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="등록하기">&nbsp;
					<input type="button" value="뒤로가기" onclick="history.back();">
				</td>
			</tr>
		
		</table>
		</form>
	</div>
	
	<br><br>

    <jsp:include page="../include/user_bottom.jsp"/>

</body>
</html>