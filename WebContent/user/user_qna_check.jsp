<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
main {
	margin: 2.5vw 5vw;
}
.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 2.5vw 5vw;
}

.header_qna {
	font-size: 24px;
	font-weight: bold;
}
.header_sitemap {
	color: lightgray;
}
.header_sitemap_current {
	color: black;
	font-weight: bold;
}
.table_wrapper1 {
	margin: 2.5vw 5vw;
}
.table_cont {	
	width: 100%;	
	font-size: 13px;
	border: 1px solid lightgray;
	border-collapse: collapse;
}
.table_cont th {
	padding: 20px;
	text-align: center;	
	border-bottom: 1px solid lightgray;
	border-right: 1px solid lightgray;
}
.table_cont td {
	padding: 20px;
	text-align: left;	
	border-bottom: 1px solid lightgray;
	border-right: 1px solid lightgray;
}
main input, textarea {
	width: 100%;
	border: 1px solid lightgray;
	resize: none;
}
.ctg_select {	
	border: 1px solid lightgray;
}
.btitle {
	width: 87%;
}
.btn_wrapper {
	margin: 2.5vw 5vw 10vw 5vw;	
	height: 30px;
	display: flex;
	justify-content: flex-end;
}
.btn_submit {
	margin-right: 5px;
	width: 90px;	
	border: 1px solid lightgray;
	background-color: black;
	color: white;
	text-align: center;
	cursor: pointer;
}
.btn_normal {
	width: 90px;
	border: 1px solid lightgray;
	background-color: lightgray;
	text-align: center;
	cursor: pointer;
}

</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />
<c:set var="page" value="${Page }" />
<main>
	<div class="header">
		<span class="header_qna">Q&amp;A</span>
		<div>
		<span class="header_sitemap">
		<a href="<%=request.getContextPath() %>/main.do" class="a">home</a> >
		<a href="<%=request.getContextPath() %>/user_mypage.do?id=${loggedId }">mypage</a> > q&amp;a detail</span>
		</div>
	</div>
	<c:set var="dto" value="${QnaDetail }" />	
	
	<form method="post">
	<input type="hidden" id="" name="writer" value="${dto.getbWriter() }">
	<div class="table_wrapper1">
		<table class="table_cont">
			<tr>
				<th>제목</th>
				<td>${dto.getbTitle() }</td>
			</tr>			
			<tr>
				<th>작성자</th>
				<td>${dto.getbWriter() }</td>				
			</tr>
			<tr>
				<th>작성일</th> 
				<td>${dto.getbDate().substring(0,10) }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3"><textarea rows="20" cols="30" name="QnaCont">${dto.getbCont() }</textarea></td>
			</tr>
		</table>
	</div>
	<div class="btn_wrapper">
		<c:if test="${loggedId eq dto.getbWriter() }">
			<input type="submit" value="수정" class="btn_submit" formaction="<%=request.getContextPath() %>/user_qna_update.do?bno=${dto.getbNo() }&id=${id }&page=${Page }"
				onclick="return confirm('수정하시겠습니까?')">
			<input type="submit" value="삭제" class="btn_submit" formaction="<%=request.getContextPath() %>/user_qna_delete.do?bno=${dto.getbNo() }&id=${id }&page=${Page }" 
				onclick="return confirm('정말로 삭제하시겠습니까?')">
			<button type="button" class="btn_normal" onclick="location.href='user_board_check.do?page=${Page }&id=${id }'"> 목록 </button>
		</c:if>
			
		<c:if test="${loggedId != dto.getbWriter() }">
			<button type="button" class="btn_normal" onclick="location.href='user_board_check.do?page=${Page }&id=${id }'"> 목록 </button>
		</c:if>
	</div>	
	</form>
	
</main>	
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>