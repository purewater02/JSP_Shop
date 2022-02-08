<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	.th1 {
		padding: 20px 0px 30px 0px;
		margin: 5px;
		border-bottom: 1px solid lightgrey;
		
	}
	
	
	#tr1 {
		padding: 15px;
		margin: 15px;
		border-bottom: 1px solid #E3E9E9;
	}
	
	.td1{
		border-bottom: 1px solid lightgrey;
		padding: 30px 0px 30px 0px;
		margin: 20px 10px 20px 5px;
	}
	
	.td2 {
		font-size: 20px;
		font-weight: bolder;
		padding: 200px;
	}

	
</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />

	<h3 style="color: gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;후기내역</h3>
	
	<p align="right" class="p"> <a href="<%=request.getContextPath() %>/main.jsp" class="a">home</a>><a href="<%=request.getContextPath() %>/user_mypage.do?id=${loggedId }" class="a">mypage</a>> review&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
	
	&nbsp;&nbsp;<span class="span">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;REVIEW</span>
	<hr width="95%" style="border: solid 0.1px #E3E9E9">
	
	<div id="layer2">
	<c:set var="list2" value="${ReviewList }" />
		<table cellspacing="0" width="95%" align="center">
			<tr class="tr1">
				<th width="10%" class="th1">번호</th> <th width="10%" class="th1">분류</th>
				<th width="20%" class="th1">제품명</th> <th width="30%" class="th1">제목</th>
				 <th width="10%" class="th1">작성자</th> <th width="10%" class="th1">평점</th> <th width="10%" class="th1">작성일</th>
			</tr>
			<c:if test="${!empty list2 }">
				<c:forEach items="${list2 }" var="dto2">
					<tr align="center" id="tr1">
						<td class="td1">${dto2.getbNo() }</td>
						<td style="color: lightgrey" class="td1">REVIEW</td>
						<td class="td1">${dto2.getpName() } </td>
						<td class="td1"><a href="<%=request.getContextPath() %>/user_review_detail.do?pcode=${dto2.getPcode() }&id=${loggedId }" style="color: gray">${dto2.getbTitle() }</a></td>
						<td class="td1">${dto2.getbWriter().substring(0,2) }*****</td>
						<c:if test="${dto2.getBstar() == 1 }">
								<td class="td1">★☆☆☆☆</td>
								</c:if>
								
								<c:if test="${dto2.getBstar() == 2 }">
								<td class="td1">★★☆☆☆</td>
								</c:if>
								
								<c:if test="${dto2.getBstar() == 3 }">
								<td class="td1">★★★☆☆</td>
								</c:if>
								
								<c:if test="${dto2.getBstar() == 4 }">
								<td class="td1">★★★★☆</td>
								</c:if>
								
								<c:if test="${dto2.getBstar() == 5 }">
								<td class="td1">★★★★★</td>
								</c:if>
						<td class="td1">${dto2.getbDate().substring(0,10) }</td>
					</tr>
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list2 }">
				<tr>
					<td colspan="7" align="center" class="td2"> 작성하신 리뷰가 없습니다. </td>
				</tr>
			</c:if>
			
		</table>
		
		</div>
  

 <br><br><br><br><br><br>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>