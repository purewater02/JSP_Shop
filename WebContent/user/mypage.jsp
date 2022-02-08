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

	.a {
		color: gray;
	}

	.title {
		text-align: left;
		margin-left: 5vw;
		margin-bottom: 2.5vw;		
	}
	.site_map {
		text-align: right;
		margin-right: 5vw;
		margin-bottom: 2vw;
	}
	.mypage_userinfo {
		margin-left: 5vw;
		margin-right: 5vw;
		border: 1px solid lightgray;
		padding: 10px;
		display: flex;
		align-items: center;
	}
	.menu_container {
		margin: 5vw 10vw 20vw 10vw;
		display: flex;
		justify-content: space-around;
		
	}
	.menus {
		width: 180px;
		height: 200px;
		border: 3px solid gray;
		padding: 20px;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	.menu_title {
		font-size: 20px;
	}	
	.border {
		border: 1px solid lightgrey;
	}
	.mypage_thumb {
		border: 1px solid lightgray;
	}
	
	
</style>
</head>
<body>

<jsp:include page="../include/user_top.jsp" />
<div>
	<h4 class="title">MY PAGE</h4>
	
	<p class="site_map"><a href="<%=request.getContextPath() %>/main.jsp" class="a">home</a>> mypage&nbsp;&nbsp;&nbsp; </p>

	<c:set var="dto" value="${memberList }" />
	<div class="mypage_userinfo">
		<img src="image/mypage_thumb.jpg" width="70px" height="70px" class="mypage_thumb">	
		<span>&nbsp;&nbsp;&nbsp;<strong>${dto.getName() }</strong>님 저희 쇼핑몰을 이용해 주셔서 감사합니다.</span>
	</div>
	<br>
	<div class="menu_container">
		<a href="user_order_check.do?id=${dto.getId() }">
			<div class="menus">				
				<span class="menu_title"><strong>ORDER</strong></span><br>
				<span>주문내역 조회</span><br>
				<span>주문하신 주문내역을<br/>확인하실 수 있습니다.</span>
			</div>
		</a>
		<a href="user_profile_update.do?id=${dto.getId() }">
			<div class="menus">				
				<span class="menu_title"><strong>PROFILE</strong></span><br>
				<span>회원 정보</span><br>
				<span>고객님의 개인정보를<br/>관리하는 공간입니다.</span>
			</div>
		</a>
		<a href="<%=request.getContextPath() %>/user_board_check.do?id=${loggedId }">
			<div class="menus">				
				<span class="menu_title"><strong>QNA</strong></span><br>
				<span>문의 게시판</span><br>
				<span>작성하신 문의글을<br/>관리하는 공간입니다.</span>
			</div>
		</a>
		<a href="<%=request.getContextPath() %>/user_review_check.do?id=${loggedId }">
			<div class="menus">				
				<span class="menu_title"><strong>REVIEW</strong></span><br>
				<span>리뷰 게시판</span><br>
				<span>작성하신 REVIEW를<br/>관리하는 공간입니다.</span>
			</div>
		</a>	
	</div>
</div>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>