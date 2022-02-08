<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	td {
		text-align: center;
	}
	
	.ordList {
		margin-left: 20%;
		margin-bottom: 15px;
	}
	
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script>
function submit() {
	var pNoList = [];
	$("input#pNos").each(function(){
		pNoList.push($(this).val());	
	});
	var oState1List = [];	
	for(var i=0;i<pNoList.length;i++){		
		oState1List.push($("select#oState1List_"+pNoList[i]+" option:selected").val());		
	}
	console.log(oState1List);
	var oState2 = $("#oState2 option:selected").val();
	var oNo = $("input#oNo").val();
	location.href="admin_order_update_ok.do?pNoList="+pNoList+"&oState1List="+oState1List+"&oState2="+oState2+"&oNo="+oNo;
}
</script>
</head>
<body>
	
	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="red">
			<h3>주문 상세 정보</h3>
		<hr width="65%" color="red">
		<br>
	</div>
	
	<div align="center">	
	<%-- <form method="post" 
		action="<%=request.getContextPath() %>/admin_order_update_ok.do"> --%>	
		<table id="a" border="0" cellspacing="10" style="font-size:10pt;">
			<c:set var="dto" value="${cont }" />
			<c:if test="${!empty dto }">	
			<tr>
				<td rowspan = 7 width="155"align="center" bgcolor="#ffcc00">
					<font size="2" ><strong>주문 정보</strong></font>
				</td>
				<td align="center" width=110 bgcolor="#ffcc00">
					<font size="2" ><strong>주문 번호</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input id="oNo" type="hidden" name="oNo" size=40 value="${dto.getOno() }">${dto.getOno() }</td>
			</tr>
			<tr>
				<td align="center" width=110 bgcolor="#ffcc00">
					<font size="2" ><strong>주문 날짜</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input type="hidden" name="oDate" size=40 value="${dto.getOdate() }">${dto.getOdate() }</td>
			</tr>
			<tr>
				<td align="center" width=110 bgcolor="#ffcc00">
				<font size="2" ><strong>주문자 ID</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input type="hidden" name="oId" size=40 value="${dto.getOid() }">${dto.getOid() }</td>
			</tr>
			<tr>
				<td align="center" width=110 bgcolor="#ffcc00">
					<font size="2" ><strong>수령인</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input type="hidden" name="oReceiver" size=40 value="${dto.getOreceiver() }">${dto.getOreceiver() }</td>
			</tr>
			<tr>
				<td align="center" width=110 bgcolor="#ffcc00">
					<font size="2" ><strong>주 소</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input type="hidden" name="oAddr" size=40 value="${dto.getOaddr() }">${dto.getOaddr() }</td>
			</tr>
			<tr>
				<td align="center" width=110 bgcolor="#ffcc00">
					<font size="2" ><strong>전 화</strong></font>
				</td>
				<td width=470 bgcolor="#eeeede"><input type="hidden" name="oPhone" size=40 value="${dto.getOphone() }">${dto.getOphone() }</td>
			</tr>
			
		</c:if>
		
		<c:if test="${empty cont }">
				<tr>
					<td colspan="10" align="center">
						<h3>등록된 주문 정보가 없습니다...</h3>
					</td>
				</tr>
		</c:if>
	</table>
	<br><br>
		
	<table id="b" border=0 style="font-size:10pt;font-family:맑은 고딕">
		<tr>  
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>상품번호</strong></font></p></th> 
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>상품명</strong></font></p></th>
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>사이즈</strong></font></p></th> 
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>색상</strong></font></p></th> 
			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>주문수량(개)</strong></font></p></th>
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>상품단가(원)</strong></font></p></th>  
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>주문액(원)</strong></font></p></th> 
 			<th bgcolor="#ffcc00" width = 120 height="30" align="center"><p><font size="2" color="#000000"><strong>주문 상태</strong></font></p></th> 
 		</tr>
 	<c:set var="list" value="${oProdList }" />
	<c:if test="${!empty list }">
		<c:forEach items="${list }" var="dto2">
			<tr>  
 				<td bgcolor="#eeeede" height="30" align="center"><font size="2"><input id="pNos" type="hidden" name="pNo" size=40 value="${dto2.getPno() }">${dto2.getPno() }</font></td> 
				<td bgcolor="#eeeede" height="30" align="center"><font size="2">${dto2.getPname() }</font></td> 
				<td bgcolor="#eeeede" height="30" align="center"><font size="2">${dto2.getPsize() }</font></td>
				<td bgcolor="#eeeede" height="30" align="center"><font size="2">${dto2.getPcolor() }</font></td>
 				<td bgcolor="#eeeede" height="30" align="center"><font size="2">${dto2.getPamount() }</font></td>
				<td bgcolor="#eeeede" height="30" align="right"><font size="2"><fmt:formatNumber value="${dto2.getPprice() }" />원</font></td> 
				<td bgcolor="#eeeede" height="30" align="right"><font size="2"><fmt:formatNumber value="${dto2.getTotprice() }" />원</font></td> 
				<td bgcolor="#eeeede" height="30" align="right">
					<select id="oState1List_${dto2.getPno()}" name="oState1">
						<option value="배송 준비 중">배송 준비 중</option>
						<option value="배송 중">배송 중</option>
						<option value="배송 완료">배송 완료</option>					
					</select>
				</td> 
 			</tr>
 			 
		</c:forEach>
	</c:if>
	<c:if test="${empty list }">
		<tr>
			<td colspan="7"> 검색된 주문 정보가 없습니다.</td>
		</tr>
	</c:if>
	</table>
	<br><br>
	<table id="a" border="0" cellpadding="10" style="font-size:10pt;font-family:맑은 고딕">
		<tr>
			<td rowspan = 2 align="center" width="155" bgcolor="#002C57">
				<font size="2" color="#ECFAE4"><strong>결제 방법</strong></font>
			</td>
			<td rowspan = 2 bgcolor="#eeeede"> 
				<input type="hidden" name="oPay" value="${dto.getOpay() }">${dto.getOpay() } 
			</td>
			<td align="center" width=110 bgcolor="#002C57">
				<font size="2" color="#ECFAE4"><strong>카드사 / 입금자명</strong></font>
			</td>
			<td bgcolor="#eeeede">
				<input type="hidden" name="oCard" value="${dto.getOcard() }">${dto.getOcard() } 
			</td>
			<td align="center" width=110 bgcolor="#002C57">
				<font size="2" color="#ECFAE4"><strong>신용카드 번호</strong></font>
			</td>
			<td width=120 bgcolor="#eeeede">
				<input type="hidden" name="oCardNo" value="${dto.getOcardno() }">${dto.getOcardno() }
			</td>
			<td align="center" width=112 bgcolor="#002C57">
				<font size="2" color="#ECFAE4"><strong>카드 비밀번호</strong></font>
			</td>
			<td width=120 bgcolor="#eeeede">
				<input type="hidden" name="oCardPwd" value="${dto.getOcardpwd() }">${dto.getOcardpwd() }
			</td>
		</tr>
		<tr>
			<td align="center" width=110 bgcolor="#002C57">
				<font size="2" color="#ECFAE4"><strong>주문 상태</strong></font>
			</td>
			<td colspan=5 width=474 bgcolor="#eeeede">
				<select id="oState2" name="oState2">
					<option value="배송 준비 중">배송 준비 중</option>
					<option value="배송 중">배송 중</option>
					<option value="배송 완료">배송 완료</option>					
				</select>
			</td>
		</tr>
		
		<tr>
			<td colspan="9" align="center">
				<input type="button" onclick="submit()" value="수정">&nbsp;&nbsp;&nbsp;
				<input type="button" value="돌아가기" onclick="history.back()">
			</td>
		</tr>
	</table>	
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>