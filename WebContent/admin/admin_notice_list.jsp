<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
<style type="text/css">

	ul li {
		list-style: none;
	}
	
	.table_cont {
		width: 50vw;
	}
	
	td {
		text-align: center;
	}
	
	#paging {
		font-size: 20px;
		font-weight: normal;
	}
	
	.currentPage {
		font-size: 24px;
		font-weight: bolder;
		color: red;
	}
	
</style>
<script type="text/javascript">
	function check(no) {
		let res = confirm("정말로 삭제 하시겠습니까?");
		if(res) {
			location.href="admin_notice_delete.do?no="+no;
		}
	}
</script>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp" />
	<c:set var="paging" value="${paging}" />
	<div align="center">
		<hr width="65%" color="maroon">
			<h3>공지사항 전체 리스트</h3>
		<hr width="65%" color="maroon">
		<br><br>		
		<table class="table_cont" border="1" cellspacing="0" width="500">
			<tr bgcolor="#eeffee">
				<th>글 번호</th> <th>글 제목</th> <th>작성자</th> <th>작성일</th> <th>수정  | 삭제</th>
			</tr>
			<c:set var="list" value="${noticeList}"></c:set>
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="dto">
					<tr>
						<td>${dto.getbNo() }</td>
						<td width="50%"><a href="admin_notice_detail.do?no=${dto.getbNo()}&page=${paging.getPage()}">${dto.getbTitle() }</a></td>
						<td>${dto.getbWriter() }</td>
						<td>${dto.getbDate().substring(0,10) }</td>
						<td>
						<input type="button" value="수정" onclick="location.href='admin_notice_update.do?no=${dto.getbNo() }&page=${paging.getPage() }'">
						<input type="button" value="삭제" onclick="check(${dto.getbNo()})">						
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" align="center">
						<h3>검색된 공지사항이 없습니다...</h3>
					</td>
				</tr>
			</c:if>
			<tr>
				<td colspan="5" align="center"><input type = "button" value="글쓰기" 
				onclick="location.href='admin_notice_write.do'"></td>
			</tr>
		</table>
	</div>
	<div id="paging" align="center">	
	<c:url var="loadList" value="/admin_notice_list.do" />
	<c:if test="${paging.isPrev() }">
		<a href="${loadList }?page=${paging.getBeginPage()-1}">◀</a>
	</c:if>
	<c:forEach begin="${paging.getBeginPage() }" end="${paging.getEndPage() }" step="1" var="index">
		<c:choose>
			<c:when test="${paging.getPage() == index }">
				<span class="currentPage">${index}</span>	<!-- 현재 페이지는 링크 없음 -->
			</c:when>		
			<c:otherwise>
				<a href="${loadList }?page=${index}">${index }</a>
			</c:otherwise>
		</c:choose>	
	</c:forEach>
	<c:if test="${paging.isNext() }">
		<a href="${loadList }?page=${paging.getEndPage()+1}">▶</a>
	</c:if>
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>