<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@font-face {
    font-family: 'paybooc-Medium';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Medium.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
	
	body {
		font-family: paybooc-Medium;
	}
	 
	#topBody {		
		padding-top: 170px;
	}
	
	#topFixed {
    	position: relative;
    	top: 10px; 	 	
  		bottom: 1000px;
  		width: 100%;
  		background-color: #dcdcdc;
  		opacity: 0.9;	
    }
	
	header {
		position: fixed;		
		top: 0;
  		bottom: 1000px;
		text-align: center;
		padding: 5px;
		width: 100%;
		height: 150px;
		background-color: #dcdcdc;
		opacity: 0.9;
		transition: top 1s ease-out;
	}
	
	.nav-up { 
		top: -170px;
	 }
	 
	
	#login {
		float: right;
		margin: 0 180px 0 20px;
		font-family: paybooc-Medium;
		display: block;
	}
	
	#topFixed h2 {
		text-align: center;
		clear: both;
		font-family: paybooc-Medium;
	}
	
	#navBar {
		clear: both;
		text-align: center;
		font-family: paybooc-Medium;
		padding-left: 18%;
	}
	
	#navBar ul {
		list-style:none;
		position:relative;
	}
	#navBar ul li {
		float:left;
		text-decoration:none;
		vertical-align: middle;
		text-align: center;
		position: relative;
	}
	
	#navBar ul li a {
		padding:10px;
		text-decoration: none;
		display:block;
		color: black;
		width:200px;
		height:50px; 
		line-height:50px;
	}
	.searchForm {		
		width: 220px;
		height: 50px;
		line-height:70px;
		display: block;						
	}
	
	#navBar ul ul{
    	display: none; 
    	padding-left:-7%;
    	position: absolute;
    	vertical-align: middle;
		text-align: center;
    	background-color: #dcdcdc;    	
    	z-index: 99999;
	}
	
	#navBar ul ul li{ 
		width:150px;
		margin-left:-20%;
		height:auto;
		line-height:20px;
	}
	
	#navBar ul ul li a{
	}
	
	#navBar ul li:hover > ul {
		display:block;	
	}
	
	#navBar ul li:hover > a {
		display:block;
		font-weight: bold;
	}
	
	#navBar ul li ul li:hover > a {
		display:block;	
		font-weight: bold;
	} 

	
	.logo, a{
		padding:10px;
		text-decoration: none;
		color: black;
	}
	
	.srcBox {
		padding-top: 6px;
	}
	
</style>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript">
var didScroll; 
var lastScrollTop = 0; 
var delta = 5; 
var navbarHeight = $('header').outerHeight(); 

	$(window).scroll(function(event){ 
	didScroll = true; 
	}); 
	
	setInterval(function() { 
		if (didScroll) { 
			hasScrolled(); 
			didScroll = false; 
			} 
		}, 250); 
	
	function hasScrolled() { 
		var st = $(this).scrollTop(); 
		
		if(Math.abs(lastScrollTop - st) <= delta) 
			return; 
		if (st > lastScrollTop && st > navbarHeight){ 
			$('header').removeClass('nav-down').addClass('nav-up'); 
		} else {
			if(st + $(window).height() < $(document).height()) { 
				$('header').removeClass('nav-up').addClass('nav-down'); 
				} 
		} 
		lastScrollTop = st; 
	}
	
	$.noConflict();
	var _$ = jQuery //식별자 변경 
</script>

</head>
<body id="topBody">
	<header class="nav-down">
	  <div id="topFixed">
		<div id="login">
		
			<c:if test="${!empty loggedName }">
				
				<c:if test="${loggedName.equals('admin') }">
				<a href="#">${loggedName }님 환영합니다!</a>&nbsp;|
				<a href="<%=request.getContextPath()%>/admin_main.do">관리자 페이지</a>&nbsp;|
        		<a href="<%=request.getContextPath()%>/user_logout.do">로그아웃</a>
				</c:if>
				
				<c:if test="${!loggedName.equals('admin') }">
				${loggedName }님 환영합니다.&nbsp;&nbsp;|
        		<a href="<%=request.getContextPath()%>/user_cart_list.do?sessionId=${loggedId}">장바구니</a>&nbsp;|
        		<a href="<%=request.getContextPath()%>/user_mypage.do?id=${loggedId}">마이페이지</a>&nbsp;|
        		<a href="<%=request.getContextPath()%>/user_logout.do">로그아웃</a>
        		</c:if>
			</c:if>
			
			<c:if test="${empty loggedName }">
        		<a href="<%=request.getContextPath()%>/user/user_login.jsp">로그인</a>&nbsp;|
        		<a href="<%=request.getContextPath()%>/user/user_join.jsp">회원가입</a>
        	</c:if>
        </div>
		
       <h2><a class="logo" href="<%=request.getContextPath()%>/main.jsp">JSP SHOP</a></h2>
  	
    <nav id="navBar">    
    	<ul>
    		<li><a href="<%=request.getContextPath()%>/user_product_list.do">ALL</a></li>
       		<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=OUTER">OUTER</a>
       			<ul>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=JACKET">JACKET</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=PADDING">PADDING</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=COAT">COAT</a></li>
    			</ul>
       		</li>
     		<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=TOP">TOP</a>
     			<ul>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=SHIRT">SHIRT</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=MTOM">MTOM</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=LSLEEVE">L-SLEEVE</a></li>
    			</ul>
     		</li>
        	<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=BOTTOM">BOTTOM</a>
        		<ul>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=SLACKS">SLACKS</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=JEANS">JEANS</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=COTTON">COTTON</a></li>
    			</ul>
        	</li>
        	<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=ACC/SHOES">ACC/SHOES</a>
        		<ul>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=ACC">ACC</a></li>
    				<li><a href="<%=request.getContextPath()%>/user_product_list_ctg.do?ctg=SHOES">SHOES</a></li>
    			</ul>    				
        	</li>
        	<li>
        	<form method="post" action="<%=request.getContextPath()%>/user_product_search.do" class="searchForm">
					<input name="searchKeyword" class="srcBox">&nbsp;&nbsp;<input type="image" src="<%=request.getContextPath() %>/image/searchBtn.png" width="9%" height="20px" class="srcBtn">
			</form>
			</li>     	
        </ul>
    </nav>
    </div>
    </header>
		
</body>
</html>