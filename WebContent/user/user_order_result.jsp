<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문결과</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
</script>
<style>
body {
	font-family: 'paybooc-Light';
	margin-top: 0;	
}
.overflow {
	overflow-x: visible;
}

.top_header {
		width: 100vw;				
		padding: 0;		
		position: fixed;
}
.bottom_footer {
		width: 100vw;	
		margin: 0 500px 0 0;
		padding: 0;		
}

body {
	font-family: 'paybooc-Light';
	margin-top: 0;	
}

.align_center {
	margin-left: auto;
	margin-right: auto;	
}

.user_info {
	font-size: 13px;
	width: 80vw;
	border: 1px solid lightgray;
	border-collapse: collapse;

}

.user_info td {
	padding: 10px;
	border-top: 1px solid lightgray;
}



.cart_cont {
	width: 80vw;				
	border-top: 1px solid lightgray;
	border-collapse: collapse;
}

.cart_cont th {
	text-align: center;
	font-size: 13px;
	padding: 5px 10px;
	border-bottom: 1px solid lightgray;
}

 .cart_cont td {
 	font-size: 13px;
	padding: 5px 10px;
	border-bottom: 1px solid lightgray;
 }
.cont_table_center td {
	text-align: center;
}

.cart_cont_left {
	text-align: left;
}

.cart_cont_right {
	text-align: right;
}

.cart_cont button {
	background-color: white;
	border: 1px solid lightgray;
	font-size: 12px;
}

.cart_cont button:hover {
	background-color: lightgray;
	
}

.cart_image {
	width: 80px;
	height: 100px;
}

.pName_width {
	width: 600px;
}
.input_amount_size {
	width: 30px;
}

.vertical_align {
	display: flex;
	flex-direction: column;		
	justify-content: space_around;	
}

.cart_subtotal {
	width: 80vw;
	height: 10vw;
	margin-top: 50px;
	border: 1px solid lightgray;
}

.orderBtn_container {
	margin-bottom: 20vh;
	display: flex;
	justify-content: center;
}

.orderBtn_container button {
	width: 160px;
	height: 50px;
	font-size: 15px;	
}
.orderBtn_container button:hover {
	opacity: 0.7;
}

.orderBtn_container button:nth-child(1) {
	background-color: #424242;
	color: white;
	border: medium;
}

.orderBtn_container button:nth-child(2) {
	background-color: #6d6d6d;
	color: white;
	border: medium;
}

.orderBtn_container button:nth-child(3) {
	background-color: lightgray;
	border: medium;
}

h4, h5, h6 {
	margin-left: 9vw;
}

.mileage {
	font-size: 10px;
	font-weight: bold;
	color: white;
	background-color: blue;
	border: 1px outset lightgray;
}
</style>
</head>
<body>
<header class="top_header">
<jsp:include page="../include/user_top.jsp"/>
</header>
<main>
	<h4>Order Result</h4>   
    <table class="user_info align_center">
    	<tr>
    		<td rowspan="2" width="100vw">적립금 변동 내역</td>
    		<td class="cart_cont_left">${loggedName } 회원 님</td>
    	</tr>
    	<tr>
    		<td class="cart_cont_left">사용적립금:&nbsp;${inputMile}&nbsp;&nbsp; 구매적립:&nbsp;${totalMile}&nbsp;&nbsp;가용적립금:&nbsp;${mileage }원</td>
    	</tr>
    </table>
    <h4>주문 내역</h4>    
    	<div class="overflow">   	
    	<table class="cart_cont align_center">    	 
	    	<tr>	    		
	    		<th>이미지</th><th class="pName_width">상품정보</th>
	    		<th class="pQty_width">수량</th><th>상품구매금액</th><th>적립금(개당)</th>   		    		
	    	</tr>
	    	<c:set var="list" value="${cartList}" />	    	
	    	<c:if test="${!empty list }">
	    	<c:forEach items="${list }" var="dto">
		    	<tr class="cont_table_center">
		    		<td><img src="<%=request.getContextPath() %>/upload/${dto.getpImage()}" class="cart_image"></td>
		    		<td>${dto.getpName() }<br><br>
		    		사이즈: ${dto.getpSize() }&nbsp;&nbsp;&nbsp;		    			    				    		
		    		컬러: ${dto.getpColor() }		    		
		    		<br><br>		    		
		    		<td>${dto.getpAmount()}</td>		    		
		    		<c:set var="subtotal" value="${dto.getTotPrice()}"/>
		    		<td>KRW&nbsp;<fmt:formatNumber value="${dto.getTotPrice() }" /></td>
		    		<td><span class="mileage">적</span><fmt:formatNumber value="${dto.getpMileage() }"/>원</td>		    		
		    		<c:set var="totalAll" value="${totalAll + subtotal}" />		
		    	</tr>
	    	</c:forEach>
	    	</c:if>
	    	<c:if test="${empty list } }">
	    		<tr>
					<td colspan="5" align="center">
						<h4>주문 내역이 없습니다...</h4>
					</td>
				</tr>
	    	</c:if>	    		    	
    	</table>
    	</div>    
	    <table class="cart_subtotal align_center">
	    	<tr>
	    		<th>총 상품금액</th> <th></th> <th>적립금 사용</th> <th></th> <th>총 결제금액</th>
	    	</tr>
	    	<tr class="cont_table_center">
	    		<td>KRW&nbsp;<fmt:formatNumber value="${totalAll }" /></td>
	    		<td>-</td>
	    		<td>KRW&nbsp;<fmt:formatNumber value="${inputMile }" /></td>
	    		<td>=</td>
	    		<td>KRW&nbsp;<fmt:formatNumber value="${totOdrPrice }" /></td>
	    	</tr>
	    </table>
	    <br>
	    <div class="orderBtn_container">
	    	<button type="button" onclick="location.href='<%=request.getContextPath()%>/main.jsp'">홈으로</button>&nbsp;&nbsp;
		    
	    </div>	      
   </main>
<footer class="bottom_footer">
<jsp:include page="../include/user_bottom.jsp"/>
</footer>
</body>
</html>