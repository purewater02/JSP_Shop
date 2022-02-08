<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.table_cont th, .table_cont td {
	padding: 20px;
	text-align: center;	
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
.btitle_left {
	text-align: left;
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
	width: 230px;
	height: 40px;
	display: flex;
	justify-content: space-between;
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

</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp"/>
<main>
	<div class="header">
		<span class="header_qna">Q&amp;A</span>
		<div>
		<span class="header_sitemap">home > q&amp;a > q&amp;a write</span>
		</div>
	</div>
	<form method="post" action="<%=request.getContextPath() %>/user_qna_write_ok.do">	
	<div class="table_wrapper1">
		<table class="table_cont">
			<tr>
				<th>제목</th>
				<td class="btitle_left">
					<select name="ctg_select">
						<option value="배송문의" >배송문의</option>
						<option value="상품문의" selected>상품문의</option>
						<option value="입금확인문의" >입금확인문의</option>
						<option value="교환환불문의" >교환/환불문의</option>
						<option value="기타문의" >기타문의</option>						
					</select>
					<input class="btitle" type="text" name="bTitle" value="Hello! JSP SHOP :)">
				</td>				
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="bWriter" value="${loggedId}" readonly></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="2"><textarea rows="20" cols="30" name="bContent" ></textarea></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="bPwd"></td>
			</tr>
		</table>		
	</div>
	<div class="btn_wrapper">
		<span class="btn_container1">
		<a href="<%=request.getContextPath() %>/user_qna_list.do" class="btn_normal">목록</a>
		</span>
		<span class="btn_container2">		
		<button type="submit" class="btn_submit">등록</button>
		<a href="javascript:history.back()" class="btn_normal">취소</a>
		</span>			
	</div>
	</form>
</main>
<jsp:include page="../include/user_bottom.jsp"/>
</body>
</html>