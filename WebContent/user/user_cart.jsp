<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#checkAll").click(function() {
		if($("#checkAll").is(":checked")) $("input[name=chk]").prop("checked", true);
		else $("input[name=chk]").prop("checked", false);
	});
	
	$("input[name=chk]").click(function() {
		var total = $("input[name=chk]").length;
		var checked = $("input[name=chk]:checked").length;
		if(total != checked) $("#checkAll").prop("checked", false);
		else $("#checkAll").prop("checked", true);		
	});	
});
function optionChg(cartNo) {
	var sessionId = "${loggedId}";
	var size = $("#size_select_"+cartNo+" option:selected").val();
	var color = $("#color_select_"+cartNo+" option:selected").val();
	location.href="user_cart_option_update.do?cartNo="+cartNo+"&sessionId="+sessionId+"&pSize="+size+"&pColor="+color;	
}

function amountChg(cartNo, price) {
	var sessionId = "${loggedId}";
	var amount = $("#amount_select_"+cartNo).val();	
	location.href="user_cart_amount_update.do?cartNo="+cartNo+"&pAmount="+amount+"&pPrice="+price+"&sessionId="+sessionId;
}

function checkDel() {	
	var selected = [];
	$("input[name=chk]:checked").each(function(){
		selected.push($(this).val());
	});
	console.log(selected);
	location.href="user_cart_check_delete.do?cartNo="+selected+"&sessionId=${loggedId }";
}

function cartClearAll() {
	$("input[name=chk]").prop("checked", true);
	checkDel();
}

function checkOrder() {	
	var selected = [];
	if($("input[name=chk]:checked").val() == null) {
		alert("주문할 상품을 체크해주세요!");
	}else {	
		$("input[name=chk]:checked").each(function(){
			selected.push($(this).val());
		});
		console.log(selected);
		location.href="user_cart_check_order.do?cartNo="+selected+"&sessionId=${loggedId }";
	}	
}
</script>
<style>
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
	cursor: pointer;
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
	cursor: pointer;	
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
	<h4>SHOPPING CART</h4>   
    <table class="user_info align_center">
    	<tr>
    		<td rowspan="2" width="100vw">혜택정보</td>
    		<td class="cart_cont_left">${loggedName } 회원 님</td>
    	</tr>
    	<tr>
    		<td class="cart_cont_left">가용적립금:&nbsp;${mileage }원</td>
    	</tr>
    </table>
    <h6>일반상품</h6>    
    	<div class="overflow">   	
    	<table class="cart_cont align_center">    	 
	    	<tr>
	    		<th><input type="checkbox" id="checkAll"></th>
	    		<th>이미지</th><th class="pName_width">상품정보</th>
	    		<th class="pQty_width">수량</th><th>상품구매금액</th>
	    		<th>할인금액</th><th>적립금</th><th>배송구분</th><th>배송비</th><th>선택</th>    		    		
	    	</tr>
	    	<c:set var="list" value="${cartList}" />	    	
	    	<c:if test="${!empty list }">
	    	<c:forEach items="${list }" var="dto">
		    	<tr class="cont_table_center">	    		
		    		<td><input type="checkbox" name="chk" value="${dto.getCartNo() }"></td>
		    		<td><img src="<%=request.getContextPath() %>/upload/${dto.getpImage()}" class="cart_image"></td>
		    		<td>${dto.getpName() }<br><br>
		    		사이즈:
		    		<select name="pSize" id="size_select_${dto.getCartNo() }">
		    		<c:set var="sizeValue" value="${dto.getpSizeList() }" />    			    				    		
		    		<c:forEach items="${fn:split(sizeValue, '|')}" var="size">
		    			<c:choose>
				    		<c:when test="${size eq dto.getpSize() }">
				    		<option value="${size}" selected>${size }</option>
				    		</c:when>
				    		<c:otherwise>
				    		<option value="${size}">${size }</option>
				    		</c:otherwise>
			    		</c:choose>
		    		</c:forEach>	
		    		</select>		    		
		    		컬러:
		    		<select name="pColor" id="color_select_${dto.getCartNo() }">
		    		<c:set var="colorValue" value="${dto.getpColorList() }" />
		    		<c:forEach items="${fn:split(colorValue, '|') }" var="color">	    		
			    		<c:choose>
				    		<c:when test="${color eq dto.getpColor() }">
				    		<option value="${color}" selected>${color }</option>
				    		</c:when>
				    		<c:otherwise>
				    		<option value="${color}">${color }</option>
				    		</c:otherwise>
			    		</c:choose>
		    		</c:forEach>
		    		</select>
		    		<br><br>
		    		<button onclick="optionChg(${dto.getCartNo()})">옵션변경</button></td>
		    		<td><input id="amount_select_${dto.getCartNo() }" type="number" value="${dto.getpAmount() }" min="1" class="input_amount_size"><br>
		    		<button onclick="amountChg(${dto.getCartNo()},${dto.getpPrice()})">변경</button></td>
		    		<td>KRW&nbsp;<fmt:formatNumber value="${dto.getpPrice() }" /></td>
		    		<td>-</td>
		    		<td><span class="mileage">적</span><fmt:formatNumber value="${dto.getpMileage() }"/>원</td>
		    		<td>기본배송</td>
		    		<td>무료</td>
		    		<td class="vertical_align">
		    		<br>
		    		<button onclick="location.href='user_cart_order_selected.do?cartNo=${dto.getCartNo()}&sessionId=${loggedId }'">주문하기</button>
		    		<br>
		    		<br>	    		
		    		<button onclick="location.href='user_cart_delete.do?cartNo=${dto.getCartNo()}&sessionId=${loggedId }'">× 삭제</button>
		    		<br>    		
		    		</td>
		    		<c:set var="amount" value="${dto.getpAmount() }"/>
		    		<c:set var="price" value="${dto.getpPrice() }"/>
		    		<c:set var="subtotal" value="${amount * price}"/>
		    		<c:set var="totalAll" value="${totalAll + subtotal}" />		
		    	</tr>
	    	</c:forEach>
	    	</c:if>
	    	<c:if test="${empty list } }">
	    		<tr>
					<td colspan="10" align="center">
						<h4>장바구니에 상품이 없습니다...</h4>
					</td>
				</tr>
	    	</c:if>
	    	<tr>
	    		<td colspan="5" class="cart_cont_left">[기본배송]</td>
	    		<td colspan="5" class="cart_cont_right">
	    		상품구매금액 <fmt:formatNumber value="${totalAll }" /> + 배송비 0 (무료) = 합계: 
	    		KRW&nbsp;<fmt:formatNumber value="${totalAll }" /></td>
	    	</tr>
	    	<tr>
	    		<td colspan="5" class="cart_cont_left">선택상품을&nbsp;<button onclick="checkDel()">× 삭제하기</button></td>
	    		<td colspan="5" class="cart_cont_right">
	    		<button onclick="cartClearAll()">장바구니비우기</button>
	    		</td>
	    	</tr>
    	</table>
    	</div>    
	    <table class="cart_subtotal align_center">
	    	<tr>
	    		<th>총 상품금액</th> <th></th> <th>총 배송비</th> <th></th> <th>결제예정금액</th>
	    	</tr>
	    	<tr class="cont_table_center">
	    		<td>KRW&nbsp;<fmt:formatNumber value="${totalAll }" /></td>
	    		<td>+</td>
	    		<td>KRW&nbsp;<fmt:formatNumber value="0" /></td>
	    		<td>=</td>
	    		<td>KRW&nbsp;<fmt:formatNumber value="${totalAll }" /></td>
	    	</tr>
	    </table>
	    <br>
	    <div class="orderBtn_container">
	    	<button type="button" onclick="location.href='user_cart_order.do?sessionId=${loggedId }'">전체상품주문</button>&nbsp;&nbsp;
		    <button type="button" onclick="checkOrder()">선택상품주문</button>&nbsp;&nbsp;
		    <button type="button" onclick="history.back()">쇼핑계속하기</button>
	    </div>
	    <div class="cart_caution align_center">
	    	<h4>장바구니 이용안내</h4>
	    		<h6>1. 선택하신 상품의 수량을 변경하시려면 수량변경 후 [변경] 버튼을 누르시면 됩니다.</h6>
	    		<h6>2. [쇼핑계속하기] 버튼을 누르시면 쇼핑을 계속 하실 수 있습니다.</h6>
	    		<h6>3. 장바구니와 관심상품을 이용하여 원하시는 상품만 주문하거나 관심상품으로 등록하실 수 있습니다.</h6>
	    		<h6>4. 파일첨부 옵션은 동일상품을 장바구니에 추가할 경우 마지막에 업로드 한 파일로 교체됩니다.</h6>
	    	<h4>무이자할부 이용안내</h4>
	    		<h6>1. 상품별 무이자할부 혜택을 받으시려면 무이자할부 상품만 선택하여 [주문하기] 버튼을 눌러 주문/결제 하시면 됩니다.</h6>
	    		<h6>2. [전체 상품 주문] 버튼을 누르시면 장바구니의 구분없이 선택된 모든 상품에 대한 주문/결제가 이루어집니다.</h6>
	    		<h6>3. 단, 전체 상품을 주문/결제하실 경우, 상품별 무이자할부 혜택을 받으실 수 없습니다.</h6>
	    		<h6>4. 무이자할부 상품은 장바구니에서 별도 무이자할부 상품 영역에 표시되어, 무이자할부 상품 기준으로 배송비가 표시됩니다.<br>
	    		실제 배송비는 함께 주문하는 상품에 따라 적용되오니 주문서 하단의 배송비 정보를 참고해주시기 바랍니다.</h6>
	    </div>    
   </main>
<footer class="bottom_footer">
<jsp:include page="../include/user_bottom.jsp"/>
</footer>
</body>
</html>