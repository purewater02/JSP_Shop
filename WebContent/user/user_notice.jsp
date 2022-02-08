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
</head>
<body>
<jsp:include page="../include/user_top.jsp" />
<main>
	<c:set var="paging" value="${paging}" />
	<div class="header">
		<span class="header_qna">NOTICE</span>
		<div>
		<span class="header_sitemap">
		<a href="<%=request.getContextPath() %>/main.do" class="a">home</a> >  
		<a href="<%=request.getContextPath() %>/community.do" class="a">community</a> > 
		q&amp;a write</span>
		</div>
	</div>	
	<div>JSP의 공지사항입니다. :)</div>
	<br><br>
	<div class="table_wrapper1">		
		<table class="table_cont">
			<tr>
				<th width="7%">글 번호</th> <th class="th2">제목</th> <th width="6%">작성자</th> <th width="10%">작성일</th>
			</tr>			
			<c:set var="list" value="${noticeList}"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getbNo() }</td>
			 			<td id="indent"><a href="<%=request.getContextPath() %>/user_notice_detail.do?no=${dto.getbNo()}&page=${paging.getPage()}">${dto.getbTitle() }</a></td>
			 			<td>${dto.getbWriter() } </td>
			 			<td>${dto.getbDate().substring(0,10) } </td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list}">
				<tr>
					<td colspan="4" align="center">
						<h3>검색된 공지사항이 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
	
	<br>
	<div id="paging" align="center">	
	<c:url var="loadList" value="/user_notice.do" />
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
				<a class="otherPage" href="${loadList }?page=${index}">[${index }]</a>
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
	<br><br><br>	
</main>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>