<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">


	function deleteOrder(ono, pno, sid) {
		if(confirm('정말로 주문을 취소하시겠습니까?')) {			
			var ostate = $("#order_status option:selected").val();			
			var date1 = $("input[name=date1]").val();
			var date2 = $("input[name=date2]").val();
			location.href="user_order_check_delete.do?ono="+ono+"&pno="+pno+"&sid="+sid+"&ostate="+ostate+"&date1="+date1+"&date2="+date2;		
			
		} else { return; }
		
	}
	
	$(function() {
		  
		  $.datepicker.setDefaults({
			    dateFormat: 'yy-mm-dd',
			    prevText: '이전 달',
			    nextText: '다음 달',
			    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        changeMonth: true,
			    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
			    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
			    buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			    showOn: "both",
			    buttonText: "선택",
		        buttonImageOnly: true,
			    showMonthAfterYear: true,
			    yearSuffix: '년'
			  });

		 
		  $("#datepicker1, #datepicker2").datepicker();
			 
	
	});

		 


</script>
<style type="text/css">
body {
	font-family: 'paybooc-Light';
	margin-top: 0;	
}
 /*datepicer 버튼 롤오버 시 손가락 모양 표시*/
 .ui-datepicker-trigger{cursor: pointer;}
 /*datepicer input 롤오버 시 손가락 모양 표시*/
 .hasDatepicker{cursor: pointer;}

.span {
	font-size: 14px;
}

.stateSelect {
	border: 1px solid #E3E9E9;
	margin: 20px 37px 10px 37px;
	padding: 15px 20px 45px 20px;
}

.fSelect {
	padding: 5px 10px 5px 10px;
	margin: 0px 0px 0px 15px;
	float: left;
}

.p1 {
	margin: 0px 0px 0px 50px;
	padding: 1px;
	float: left;

}

.date {
	padding: 6px 10px 4px 10px;
}

.tr1 {
	font-size: 13px;
	color: gray;
	text-align:center;
}

.th1 {
	padding: 15px 0px 15px 0px;
	
}

.search {
	padding: 5px;
	margin: 0px 0px 0px 6px;
	background-color: #003333;
	color: white;
}

.order_cont button {
background-color: white;
border: 1px solid lightgray;
font-size: 12px;
}

.order_cont button:hover {
background-color: lightgray;

}
</style>
</head>
<body>
<jsp:include page="../include/user_top.jsp" />

	<h3 style="color: gray">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주문 조회</h3>
	
	<p align="right" class="p"> <a href="<%=request.getContextPath() %>/main.jsp" class="a">home</a>><a href="<%=request.getContextPath() %>/user_mypage.do?id=${loggedId }" class="a">mypage</a>> order&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </p>
	
	&nbsp;&nbsp;<span class="span">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주문내역조회</span>
	<hr width="95%" style="border: solid 0.1px #E3E9E9">
	
	<div class="stateSelect ">
	<form method="post" 
		action="<%=request.getContextPath()%>/user_order_check_search.do">
		<input type="hidden" name="id" value="${loggedId }">
		
	    	<select id="order_status" name="order_status" class="fSelect" >
	    	<c:if test="${oState.equals('all') }">
				<option value="all" selected>전체 주문처리상태</option>
				<option value="배송 준비 중">배송 준비 중</option>
				<option value="배송 중">배송 중</option>
				<option value="배송 완료">배송 완료</option>
			</c:if>
			<c:if test="${oState.equals('배송 준비 중') }">
				<option value="all">전체 주문처리상태</option>
				<option value="배송 준비 중" selected>배송 준비 중</option>
				<option value="배송 중">배송 중</option>
				<option value="배송 완료">배송 완료</option>
			</c:if>
			<c:if test="${oState.equals('배송 중') }">
				<option value="all">전체 주문처리상태</option>
				<option value="배송 준비 중">배송 준비 중</option>
				<option value="배송 중" selected>배송 중</option>
				<option value="배송 완료">배송 완료</option>
			</c:if>
			<c:if test="${oState.equals('배송 완료') }">
				<option value="all">전체 주문처리상태</option>
				<option value="배송 준비 중">배송 준비 중</option>
				<option value="배송 중">배송 중</option>
				<option value="배송 완료" selected>배송 완료</option>
			</c:if>
			</select>       
	
		<p class="p1"> |&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;조회기간:
  			<input type="text" name="date1" id="datepicker1" class="date" value="${date1 }"> ~ <input type="text" name="date2" id="datepicker2" class="date" value="${date2 }"> <input type="submit" value="조회" class="search">
		</p>
	</form>
	</div>
	 
	 
	<br> <br><br><br>
	<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;주문 상품 정보 </h4>	
	<h6>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;* 개별 배송 중인 상품은 같은 주문번호 물품과 같이 출력됩니다.</h6>
	
	<div id="searchData">
	
	<table class="order_cont" cellspacing="1" width="95%" align="center">
		<td colspan="8" style="padding: 0px 0px 0px 30px">
	<hr width="100%" style="border: solid 0.1px #E3E9E9">
		</td>
		<tr class="tr1">
			<th width="10%" class="th1">주문번호</th> <th width="10%" class="th1">이미지</th> <th width="30%" class="th1">상품정보</th>
			<th width="10%" class="th1">수량</th> <th width="10%" class="th1">상품구매금액</th> <th width="10%" class="th1">주문처리상태</th><th width="10%" class="th1">주문취소</th><th width="10%" class="th1">리뷰</th>
		</tr>
		<td colspan="8" style="padding: 0px 0px 0px 30px">
	<hr width="100%" style="border: solid 0.1px #E3E9E9;">
		</td>
		<c:set var="list" value="${ordList }" />
			<c:if test="${!empty list }">
				<c:forEach items="${list }" var="odto">
					<tr class="tr1">
						<td class="td0">${odto.getOno() }</td>
			 			<td class="td0"><img src="<%=request.getContextPath() %>/upload/${odto.getPimage1()}" width="142" height="171"></td>
			 			<td class="td0"><a href="<%=request.getContextPath()%>/user_product_content.do?pNo=${odto.getPno()}&sessionId=${loggedId}">${odto.getPname() }&nbsp; <br> [${odto.getPsize() }] <br>&nbsp; [${odto.getPcolor() }]</a></td>
			 			<td class="td0">${odto.getPamount() } </td>
			 			<td class="td0">${odto.getTotprice() } </td>
			 			<td class="td0">${odto.getOstate() }</td>
			 			<c:if test="${odto.getOstate().equals('배송 준비 중') }">
			 				<td class="td0"><input type="button" onclick="deleteOrder(${odto.getOno()},${odto.getPno()},'${loggedId}')" value="x 주문 취소"></td>
			 				<td class="td0">수령 후 작성가능</td>
						</c:if>
						<c:if test="${odto.getOstate().equals('배송 중') }">
							<td class="td0">주문 취소 불가</td>
							<td class="td0">수령 후 작성가능</td>
						</c:if>
						<c:if test="${odto.getOstate().equals('배송 완료') }">
							<td class="td0">주문 취소 불가</td>						
							<td class="td0"><input type="button" value="리뷰 쓰기 ✎" onclick="location.href='<%=request.getContextPath() %>/user_review_write.do?pname=${odto.getPname() }&ono=${odto.getOno() }'"></td>		
						</c:if>
					</tr>
					<td colspan="8" style="padding: 0px 0px 0px 30px">
				<hr width="100%" style="border: solid 0.1px #E3E9E9">
					</td>
					
				</c:forEach>
			</c:if>
			
			<c:if test="${empty list}">
				<tr>
					<td colspan="8" style="padding: 0px 0px 0px 30px" align="center">
						<h3>검색된 주문내역이 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		<td colspan="8" style="padding: 0px 0px 0px 30px">
	<hr width="100%" style="border: solid 0.1px #E3E9E9">
		</td>
	</table>
	
	</div>
	
	
	<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>