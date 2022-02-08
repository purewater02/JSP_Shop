<%@page import="java.util.ArrayList"%>
<%@page import="com.jsp.model.CartDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.model.MemberDTO"%>
<%@page import="com.jsp.model.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%	
	MemberDAO dao = MemberDAO.getInstance();
	MemberDTO mdto = dao.MemberCont((String)session.getAttribute("loggedId"));
	int availMileage = mdto.getMileage();	
%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../reset.css">
<title>주문하기</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#paytype_card").hide();
		$("#paytype_mutong").hide();

		$("#card_btn").click(function(){
			$("#paytype_card").show();
			$("#paytype_mutong").hide();	
		});
		$("#mutong_btn").click(function(){
			$("#paytype_card").hide();
			$("#paytype_mutong").show();
		});
	});
	
	function zipCheck_order()
	{
		window.open("user/zipCheck.jsp", "win", "width=600, height=200, scrollbars=yes, status=yes");
	}
	
	function useMileage(totalAll, availMileage) {		
		var inputMileage = $("#inputMileage").val();
		var reducedTotal = totalAll - inputMileage;	
		if(inputMileage > availMileage) {
			alert("가용 마일리지보다 큰 값을 입력할 수 없습니다.")
		}else{
			$("#totOdrPrice").val(reducedTotal);
			$("#showTotal").text(reducedTotal);	
		}				
	}
</script>
<style>
table { 
border-collapse: collapse; 
}
.table_cont_style tr { 
	border-top : 1px solid #cccccc;
	border-bottom : 1px solid #cccccc;
}
.a	td{
	padding-left: 20px;
}

.a tr font {
	font-size: 13px;
	font-weight: bold;
}
.wrap {
	width:50vw;
	margin: 0 auto;
	margin-bottom: 100px;
}
.container{	
	width:100%;
}
.form_txtInput  {    
    padding: 5px;
    text-align: left;    
}

.todoOrder{
	width:100%;
	max-width:50vw;	
}

#card_btn, #mutong_btn {
	border: 1px solid  black;
	background-color: lightgray;
}
.todoOrder table {
	border-spacing: 0;
	margin:0;
	padding:0;
	border:0;
	width:100%;
}
.todoOrder table input{ 
	border:1px solid #ececec;
	font-size:13px;
	color:#4c4c4c;
	height:25px;
	width:100%;
}
.todoOrder table th span{
	color:#000;
	font-size:13px; 
	display:inline-block;
	position:relative;
	padding:0 10px 0 0; 
	text-indent:20px;
}
.todoOrder table th{
	text-align:left;
}
.todoOrder table td{
	padding:6px 0;
	position: relative;
}
.todoOrder table td a.btn_confirm{
	display: inline-block; 
	float:right;
	width:115px;
	height:33px;
	margin:5px 0 0 0px;
	border:1px solid #cfcfcf;
	background:#dedede;
	color:#626262;
	text-align:center; 
	vertical-align: top;
	line-height: 33px;
}
.button{
	height: 50px;
	background-color: #fff;
	font-family: "Noto Sans CJK KR Medium";
}

tr td img {
	object-fit: contain;
}
.payB{
	border: none;
	background-color: #fff;
	outline: none;
	float: left;
	padding: 0 20px;
}
.input123{
	text-align: center;
	width: 100%;
}
.table_card{
	position: relative;
	top:50px;
}
.mileage {
	font-size: 10px;
	font-weight: bold;
	color: white;
	background-color: blue;
	border: 1px outset lightgray;
}
.table_cont_style {
	width: 50vw;
	font-size:10pt;
	font-family:맑은 고딕;
}
</style>
<body>
<div class="top_header">
	<jsp:include page="../include/user_top.jsp"/>
</div>
<div align="center">
<br><br>
<c:set var="list" value="${cartList}" />
<table class="table_cont_style">		
<tr>
	<th>이미지</th><th>상 품</th><th>판매가</th><th>수 량</th><th>적립금</th><th>총 액</th>
</tr>
<c:forEach items="${list}" var="dto">
<tr>
	<td width="142"> <img src="<%=request.getContextPath() %>/upload/${dto.getpImage()}" width="142" height="171"></td>
	<c:set var="pImage" value="${dto.getpImage() }"/>   
	<td width="150" height="30"align="center"  bgcolor="white"><font size="2">${dto.getpName() }<br>사이즈 : [${dto.getpSize()}]<br>색상 : [${dto.getpColor() }]<br></font></td> 
	<c:set var="pName" value="${dto.getpName() }"/>	<c:set var="pSize" value="${dto.getpSize() }"/> <c:set var="pColor" value="${dto.getpColor() }"/>
	<td width="150" bgcolor="white" height="30"align="center" align=right><font size="2">KRW&nbsp;<fmt:formatNumber value="${dto.getpPrice() }" /></font></td> 
	<td width="150" bgcolor="white" height="30"align="center" align=right><font size="2">${dto.getpAmount() }</font></td>
	<td width="150" bgcolor="white" height="30"align="center" align=right><font size="2"><span class="mileage">적</span><fmt:formatNumber value="${dto.getpMileage() }"/></font></td>
    <td width="150" bgcolor="white" height="30"align="center"><font size="2">KRW&nbsp;<fmt:formatNumber value="${dto.getTotPrice() }" /></font></td>
    <c:set var="totPrice" value="${dto.getTotPrice() }" />
 </tr>
 	<c:set var="amount" value="${dto.getpAmount() }"/>
	<c:set var="price" value="${dto.getpPrice() }"/>
	<c:set var="eachMile" value="${dto.getpMileage() }"/>
	<c:set var="subtotalMile" value="${amount * eachMile}"/>
	<c:set var="totalMile" value="${totalMile + subtotalMile}"/>
	<c:set var="subtotal" value="${amount * price}"/>
	<c:set var="totalAll" value="${totalAll + subtotal}" />
 </c:forEach>
 </table>
 
<br><br><br>

<form name="newMem" method="post" action="user_order_direct_ok.do">     <!--  폼의 이름이 form으로 지정됨 -->
	<input type="hidden" name="sessionId" value="${loggedId}">
	<input type="hidden" name="image" value="${pImage}">
	<input type="hidden" name="name" value="${pName}">
	<input type="hidden" name="size" value="${pSize}">
	<input type="hidden" name="color" value="${pColor}">
	<input type="hidden" name="totPrice" value="${totPrice}">
	<input type="hidden" name="amount" value="${amount}">
	<input type="hidden" name="mileage" value="${eachMile}">		
	<input type="hidden" name="totalMile" value="${totalMile }">
	<font><strong>주문 정보</strong></font>
	<br><br>
<table class="table_cont_style">	
	<tr>
		<td   align="center" width=110 height="40" bgcolor="#d0d0d0">
			<font  size="2" color="black">
			<strong>구매자</strong>
			</font>
		</td>
		<td width=470>
		    &nbsp;&nbsp;&nbsp; ${loggedName}
		</td>
	</tr>
	<tr>
		<td   align="center" width=110 height="40" bgcolor="#d0d0d0">
			<font  size="2" color="black">
			<strong>구매자 연락처</strong></font></td>
		<td width=470>&nbsp;&nbsp;&nbsp;
		<input type="text" name="memTel" size=40 value="${phone }"></td>
	</tr>
</table>
<br>
<font><strong>배송 정보</strong></font>
	<br><br>
<table class="table_cont_style">
	<tr>
		<td   align="center" width=110 height="40" bgcolor="#d0d0d0">
			<font  size="2" color="black">
			<strong>수령인</strong></font></td>
		<td width=470>&nbsp;&nbsp;&nbsp;<input type="text" name="receiver" size=40></td>
	</tr>
	<tr>
		<td   align="center" width=110 height="40" bgcolor="#d0d0d0">
			<font  size="2" color="black">
			<strong>주 소</strong></font></td>
		<td width=470>&nbsp;&nbsp;&nbsp;<input type="text" name="zipcode" size="7" readonly> 
			       &nbsp;&nbsp;&nbsp;<input type="button" value="우편번호검색" onClick="zipCheck_order()"><br>     
                   &nbsp;&nbsp;&nbsp;<input type="text" name="address1" size="40"  readonly><br>
				   &nbsp;&nbsp;&nbsp;<input type="text" name="address2" size="40">&nbsp;<font size =2>(상세주소입력)</font></td>
	</tr>
	<tr>
		<td   align="center" width=110 height="40" bgcolor="#d0d0d0">
			<font  size="2" color="black">
			<strong>전 화</strong></font></td>
		<td width=470>&nbsp;&nbsp;&nbsp;<input type="text" name="rcvPhone" size=40></td>
	</tr>
</table>

<br>
	<font><strong>결제 수단</strong></font>
	<br><br>
	<div class="wrap">
		<div class="container">
			<div class="form_txtInput">
		        <div class="todoOrder">
					<div class="input123">
						<input type="button" id="card_btn" value="카드결제" onclick="paytype2()" class="payB">
						<input type="button" id="mutong_btn" value="무통장입금" onclick="paytype3()" class="payB">							
					</div>
					<div class="pay_type1" id="paytype_card">
						<table class="table_card table_cont_style">
							<tr>
								<th><span>카드사[예)현대, 삼성...]</span></th>
					            <td><input type="text" name="cardCo" class="cardno"></td>
								<th><span>신용카드 번호</span></th>
					            <td><input type="text" name="cardNo" class="cardno"></td>
								<th><span>비밀번호</span></th>
					            <td><input type="password" name="cardPwd" class="passwd"></td>
				            </tr>
						</table>
					</div>				
					<div class="pay_type2" id="paytype_mutong">
						<table class="table_card table_cont_style">
							<tr>
								<th><span>무통장 입금</span></th>
								<td>
									<select name="bank" class="bank">
										<option value="0" selected>다음 중 선택</option>
      									<option value="신한은행">신한은행 ( 161617-15-616782 / 김쌍용(JSP))</option>
									</select>
								</td>
							</tr>
							<tr>
								<th><span>입금자 명</span></th>
			            		<td><input type="text" name="payerName" class="payerName"></td>
			            	</tr>
						</table>
					</div>						
				</div><!-- todoOrder E  -->
			</div> <!-- form_txtInput E -->
		 </div> <!-- container E -->
	</div> <!-- 668 E -->
<br>
<table class="table_cont_style">
	<tr>
		<td colspan = 2   align="center" width="275"  height="40" bgcolor="#d0d0d0">
			<font  color="black">
			<strong>사용할 적립금(원)</strong></font></td>

		<td width=470 align=right>
		<input type="button" onclick="useMileage(${totalAll}, <%=availMileage %>)" value="마일리지 사용">
		<input id="inputMileage" type="text" name="inputMileage" placeholder="가용 마일리지: <%=availMileage %>원">&nbsp;(원)</td>   
	</tr>
	<tr>
		<td colspan = 2   align="center" width="275"  height="40" bgcolor="#d0d0d0">
			<font  color="black">
			<strong>전체 주문 총액(원)</strong></font></td>		
		<td width=470 align=right><input id="totOdrPrice" type="hidden" name="totOdrPrice" value="${totalAll}"><font  color="black"><span id="showTotal">${totalAll}</span></font>&nbsp;(원)</td>		   
	</tr>
</table>
<br>

<table>
	<tr>     
		<td><input type="submit" value="주문확인" onclick="return confirm('정말로 주문하시겠습니까?')"></td> 
		<td><input type="button" value="주문취소" onclick="history.back()"></td>
	</tr>
</table>                 

</form>    
</div>
<footer class="bottom_footer">
<jsp:include page="../include/user_bottom.jsp"/>
</footer>         
</body>
</html> 