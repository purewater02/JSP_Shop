<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
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
	height: 100%;
	display: flex;
	justify-content: space-between;
}
.btn_container1 {
	display: flex;
	width: 200px;
	height: 40px;
}
.btn_container2 {
	width: 100px;
	height: 40px;
	display: flex;
	justify-content: flex-end;
}
.btn_submit {
	font-size: 16px;
	width: 110px;	
	border: 1px solid lightgray;
	background-color: black;
	color: white;
	text-align: center;
	cursor: pointer;
}
.btn_normal {
	width: 90px;
	border: 1px solid lightgray;
	text-align: center;	
}

/* 	.p {
		margin: 0px;
		font-size: 13px;
	}

	.border {
		color: gray;
	}
	
	.a {
		color: gray;
	}

	td {
		padding: 20px 10px 20px 10px;
		margin: 20px;
	}
	
	.td0 {
		color: gray;
	}

	.p0 {
		width: 500;
		padding: 50px 0px 50px 0px;
		margin: 20px 200px 20px 20px;
		font-size: 15px;
	}
	
	.button {
		text-align: right;
	} */
</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />
<main>
	<div class="header">
		<span class="header_qna">NOTICE</span>
		<div>
		<span class="header_sitemap">
		<a href="<%=request.getContextPath() %>/main.do" class="a">home </a> > 
		community > notice > notice detail</span>
		</div>
	</div>	
	<c:set var="dto" value="${noticeDetail }" />		
	<div class="table_wrapper1">
		<table class="table_cont">
			<tr>
				<th>제목</th>
				<td>${dto.getbTitle()}</td>				
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.getbWriter()}</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.getbDate().substring(0,10)}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="2"><textarea rows="20" cols="30" name="bContent" readonly>${dto.getbCont() }</textarea></td>
			</tr>			
		</table>		
	</div>
	<div class="btn_wrapper">
		<span class="btn_container1">
		<a href="<%=request.getContextPath() %>/user_notice.do?page=${cPage}" class="btn_normal">목록</a>
		</span>
		<span class="btn_container2">		
		<a href="<%=request.getContextPath() %>/user_notice.do?page=${cPage}" class="btn_normal">뒤로가기</a>
		</span>			
	</div>	
</main>	
<jsp:include page="../include/user_bottom.jsp" />


</body>
</html>