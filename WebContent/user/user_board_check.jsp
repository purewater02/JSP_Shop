<%@page import="com.jsp.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#indent {
	text-align: left;
	padding-left: 7vw;
}
main {
	margin: 2.5vw 5vw;
}
.header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin: 2.5vw 0vw;
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
.header a {
	padding: 0;
}

.table_wrapper1 {
	margin-bottom: 0.5vw;
}
.table_cont {	
	width: 100%;	
	font-size: 13px;
	border-top: 1px solid lightgray;
	border-collapse: collapse;
}
.table_cont th, .table_cont td {
	padding: 20px;
	text-align: center;	
	border-bottom: 1px solid lightgray;	
}
main input {
	margin: 0 5px;
	width: 80%;
	border: 1px solid lightgray;	
}
main select {
	border: 1px solid lightgray;
}
.ctg_select {	
	border: 1px solid lightgray;
}
.btitle {
	width: 87%;
}
.btitle_left {
	text-align: left;
}
.btn_wrapper {
	margin-top: 0.5vw;	
	height: 100%;
	display: flex;
	justify-content: space-between;
}
.btn_container1 {
	display: flex;
	justify-content: space-between;
	width: 350px;
	height: 30px;
}
.btn_container2 {
	width: 100px;
	height: 30px;
	display: flex;
	justify-content: flex-end;
}
.btn_submit {
	width: 80px;
	border: 1px solid lightgray;
	background-color: black;
	color: white;
	text-align: center;
	padding: 3px;	
}
.btn_normal {
	width: 90px;
	border: 1px solid lightgray;
	background-color: white;
	text-align: center;
	margin-left: 0;
	padding: 2.5px;
	cursor: pointer;
}
#paging {
	margin-bottom: 10vw;
	font-size: 20px;
	font-weight: normal;
}
.currentPage {
	padding: 0;
	font-weight: bold;
	
}
.otherPage {
	text-align: center;
	margin: 0;
	color: lightgray;
	padding: 0;
	
}
.otherPage:hover {
	color: gray;
	font-weight: bold;
}
#paging a {
	padding: 0;
}
</style>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function(){
	$("#selectBox").change(function() {
		if($(this).find('option:selected').val() == "date") {
			$('#layer').show();
			$('#layer2').hide();
		}else {
			$('#layer2').show();
			$('#layer').hide();
		}
	});
});
</script>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />
<br>
<main>
	<c:set var="paging" value="${paging }" />
	<c:set var="dto" value="${list }" />
	<div class="header">
		<span class="header_qna">Q&amp;A</span>
		<div>
		<span class="header_sitemap">
		<a href="<%=request.getContextPath() %>/main.do" class="a">home</a> >
		<a href="<%=request.getContextPath() %>/user_mypage.do?id=${loggedId }">mypage</a> > q&amp;a</span>
		</div>
	</div>
	<div>작성하신 문의를 확인하실 수 있습니다. :)</div>
	<br><br>
	<div class="table_wrapper1">	
	<table class="table_cont">
		<tr>
			<th width="7%">번호</th> <th>분류</th> <th>제목</th>
			 <th width="6%">작성자</th> <th width="10%">작성일</th>
		</tr>
		<c:set var="list" value="${QnaList }" />
		<c:if test="${!empty list }">
			<c:forEach items="${list }" var="dto1">
				<tr>
					<td>${dto1.getbNo() }</td>
					<td>Q&amp;A</td>
					<td id=indent>
					<c:if test="${dto1.getbIndent() != 0 }">
							<c:forEach begin="1" end="${dto1.getbIndent() }">
							&nbsp;
							</c:forEach>
							└▶
					</c:if>
					<a href="<%=request.getContextPath() %>/user_qna_check.do?bno=${dto1.getbNo() }&page=${paging.getPage() }&id=${loggedId }">${dto1.getbTitle() }</a></td>
					<td>${dto1.getbWriter() }</td>
					<td>${dto1.getbDate().substring(0,10) }</td>
				</tr>
			</c:forEach>
		</c:if>
		
		<c:if test="${empty list }">
			<tr>
				<td colspan="5">작성하신 게시물이 없습니다.</td>
			</tr>
		</c:if>		
	</table>
	</div>
	<div class="btn_wrapper">
		<form method="post" class="form1"
			action="<%=request.getContextPath() %>/user_qna_mypage_search.do?id=${loggedId }">
			<span class="btn_container1">
			<select name="search_field">
				<option value="title">제목</option>
				<option value="content">내용</option>
				<option value="title_content">제목+내용</option>
			</select>			
			<input type="text" name="search_keyword">&nbsp;
			<input type="submit" class="btn_normal" value="찾기">
			</span>	
		</form>
	</div>
	<div id="paging" align="center">	
	<c:url var="loadList" value="/user_board_check.do?id=${loggedId }" />
	<c:if test="${paging.isPrev() }">
		<a href="${loadList }?page=${paging.getBeginPage()-1}">◀</a>
	</c:if>
	<c:if test="${!paging.isPrev() }">
		<span>◀</span>
	</c:if>
	<c:forEach begin="${paging.getBeginPage() }" end="${paging.getEndPage() }" step="1" var="index">
		<c:choose>
			<c:when test="${paging.getPage() == index }">
				<span class="currentPage">[${index}]</span>	<!-- 현재 페이지는 링크 없음 -->
			</c:when>		
			<c:otherwise>
				<a class="otherPage" href="${loadList }&page=${index}">[${index }]</a>
			</c:otherwise>
		</c:choose>	
	</c:forEach>
	<c:if test="${paging.isNext() }">
		<a href="${loadList }?page=${paging.getEndPage()+1}">▶</a>
	</c:if>
	<c:if test="${!paging.isNext() }">
		<span>▶</span>
	</c:if>
	</div>		
<br><br><br><br><br><br>
</main>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>