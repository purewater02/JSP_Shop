<%@page import="com.jsp.model.ReviewDTO"%>
<%@page import="com.jsp.model.ReviewDAO"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String imgPath = request.getContextPath()+"/upload/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body {
	font-family: paybooc-Light;
}
.title {
	margin-top: 120px;
}

#dTitle {
	margin: 50px 0px 20px 150px; 
}

#dLine1 {
	margin-left: 100px; 
	border-style: dashed;
}

#sizeSelect {
	margin-left: 40px;
}

#addCart {
   	font-family: paybooc-Light;
   	font-weight: bold;
   	font-size: 15px;   	
   	width: 120px;
   	height: 45px;
   	color: black;
   	background-color: #8D9BF1;
   	border-color: white;
   	cursor: pointer;
}
   
#buyNow {
	font-family: paybooc-Light;
	font-weight: bold;
	font-size: 15px;
	width: 120px;
	height: 45px;
	color: black;
	background-color: #EA927D;
	border-color: white;
	cursor: pointer;
}
   
#pCont {
	align: center;
}
   
#pImage1 {
	margin: 0 0 50px 500px;
} 

#pImage2 {
	margin-left: 130px;
}
   
#thumbImage {
  	float: left;    	
  } 
   
#contFixed {
	float: left;
  	background-color: white;
  	position: sticky;	
  	padding: 15px;
  	top: 200px;
	left: 1100px;
	border-style: solid;
	border-width: 1px;
	border-color: lightgrey;		
	height: auto;
}
.cont_info_wrapper th {
	padding: 7px 0;	
	width: 50px;
	text-align: left;
}
.cont_info_wrapper td {
	padding: 7px 0;	
	width: 200px;
	text-align: center;
}
.cont_info_wrapper textarea {	
	text-align: left;
	resize: none;
	border: none;
}
.cont_middle_wrapper {
	margin-top: 10px;
	width: 480px;
	height: 140px;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
}
.cont_middle_wrapper a {
	display: inline-block;
	width: 90%;
	text-align: center;
}
.cont_middle_wrapper div:nth-child(2) {
	width: 460px;
}
.cont_middle_wrapper select {
	display: inline-block;
	width: 100px;
}
.cont_middle_wrapper input {
	display: inline-block;
	width: 80px;
}
.cont_middle_wrapper div:nth-child(3) {
	display: flex;
	justify-content: center;
}
   
.reviewDiv {
	position: relative;
	background-color: white;
}

#reviewTable tr td{
	padding: 5px 2px 0 2px;
	text-align: center;
	border-bottom: solid;
	border-bottom-width: 2px;
	border-color: lightgray;
}
   
#recentItems {
   	text-align: center;
   	background-color: white;
   	margin-top: 10px;
   	padding: 10px 5px 10px 5px;
	border-style: solid;
	border-width: 1px;
	border-color: lightgrey;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
	$(function() {
	$("#addCart, #buyNow").click(function(){
		if(${loggedId != null}) {
			if($("#pSize option:selected").val() == "sNone"){
			alert('사이즈를 선택하세요');
			$("#pSize").focus();
			return false;
			} else if($("#pColor option:selected").val() == "cNone"){
			alert('컬러를 선택하세요');
			$("#pColor").focus();
			return false;
			} else if($("#pAmount").val() == "0"){
			alert('수량을 선택하세요.');
			$("#pAmount").focus();
			return false;
			}
		} else if(${loggedId == null}) {
			alert('회원 로그인 후 이용하세요!');
			location.href="<%=request.getContextPath()%>/user/user_login.jsp";
			return false;
		}
		});
	});	
</script>
<c:set var="dto" value="${productCont }"/>
<c:set var="pCode" value="${dto.getPcode() }"/>
<%
	String pcode = (String)pageContext.getAttribute("pCode");
	 
	ReviewDAO dao = ReviewDAO.getInstance();
	List<ReviewDTO> List = dao.getReviewList(pcode);
	int Count = dao.getReviewCount(pcode);
	double Sum = dao.getStarSum(pcode);
	double Avg = Math.round(Sum/Count * 10) / 10.0;
%> 
</head>
<body>
	
	<header>
	<jsp:include page="../include/user_top.jsp"/>
	</header>	
	
	<br>
	<h2 align="center" class="title">상품 상세페이지</h2>
	<hr width="85%" id="dLine1">
	<br><br>
	
<form method="post">
	<input type="hidden" name="sessionId" value="${loggedId }">
	<input type="hidden" name="pNo" value="${dto.getPno() }">
	<input type="hidden" name="pName" value="${dto.getPname() }">
	<input type="hidden" name="pPrice" value="${dto.getPprice() }">
	<input type="hidden" name="pMileage" value="${dto.getPmileage() }">
	<c:if test="${!empty dto }">
	    <div id="thumbImage">
			<img src="<%=imgPath %>${dto.getPimage1() }" width="500px" height="650px" id="pImage1">&nbsp;
			<input type="hidden" name="pImage" value="${dto.getPimage1() }">
		</div>		
		<div id="contFixed">
			<table class="cont_info_wrapper">					
				<tr>
					<th>상   품   명:</th> <td>${dto.getPname() }</td>					
				</tr>													
				<tr>
					<th>가         격:</th> <td><fmt:formatNumber value="${dto.getPprice() }" /> 원</td>
				</tr>										
				<tr>
					<th>상 세 설 명:</th>
				</tr>				
				<tr>
					<td colspan="2"><textarea cols="60" rows="10" readonly>${dto.getPcontent() }</textarea></td>
				</tr>				
				<tr>
					<th>마일리지:</th><td><fmt:formatNumber value="${dto.getPmileage() }" /> 포인트 </td>
				</tr>									
			</table>
			<div class="cont_middle_wrapper">			
				<div>
				<b style="border-style:solid; border-width:2px;"><a href="#review">&nbsp;&nbsp;<%=Avg %> / 5 점 | 리뷰 보기 (<%=Count %>)</a></b>
				</div>
				<div>
				<b>사이즈:</b>
				<select name="pSize" id="pSize">
					<option value="sNone" selected>::::::::
					</option>
				<c:set var="sizeValue" value="${dto.getPsize() }"/>
					<c:forEach items="${fn:split(sizeValue, '|') }" var="size">
						<option value="${size }">${size }
						</option>
					</c:forEach>
				</select>&nbsp;&nbsp;					
				<b>컬러:</b>
				<select name="pColor" id="pColor">
					<option value="cNone" selected>::::::::
					</option>
				<c:set var="colorValue" value="${dto.getPcolor() }"/>
					<c:forEach items="${fn:split(colorValue, '|') }" var="color">
						<option value="${color }">${color }
						</option>
					</c:forEach>
				</select>&nbsp;&nbsp;				
				<b>수량:</b>
				<input type="number" min="0" max="20" value="0" id="pAmount" name="pAmount">
				</div>	
				<div>
					<input type="submit" value="장바구니 추가" id="addCart" formaction="<%=request.getContextPath() %>/user_cart_add.do">&nbsp;
					<input type="submit" value="구입하기" id="buyNow" formaction="<%=request.getContextPath() %>/user_product_cont_order.do">
				</div>									
			</div>
			<div id="recentItems">
			<c:set var="list" value="${recentlyList }" />
			<c:if test="${!empty list }">
			<h4 align="center">최근 본 상품</h4>
			<c:forEach items="${list }" var="rdto">
				<a href="<%=request.getContextPath()%>/user_product_content.do?pNo=${rdto.getPno() }&sessionId=${loggedId}"><img src="<%=imgPath %>${rdto.getPimage1() }" width="70" height="70" id="recentImg"></a>
			</c:forEach>
			</c:if>		
			<c:if test="${empty list }">
			최근 보신 상품이 없습니다..
			</c:if>		
			</div>
		</div>
				
				<br><br><br><br>
					<hr width="85%" id="dLine1">
				<br>
				
				<h2 id="dTitle">상세이미지</h2>
			
				<img src="<%=imgPath %>${dto.getPimage2() }" width="50%" id="pImage2">
				
				<div id="review" class="reviewDiv" align="center" name="review">			
					
					<br>
						<hr width="85%" id="dLine1">
					<br>
				
					<h2 style="border-style:solid; border-width:3px; width:550px; padding:10px; margin-bottom:50px;">[${dto.getPname() }] 상품 리뷰 목록</h2>
					
					<c:set var="list" value="<%=List %>"/>
					<table border="0" align="center" width="1500px" id="reviewTable">
						<tr>
							<th>리뷰제목</th> <th>리뷰내용</th> <th>작성자</th> <th>평점</th> <th>작성일</th>
						</tr> 
						<tr>
							<th><hr width="80%" style="border-width: 2px;"></th> <th><hr width="80%" style="border-width: 2px;"></th> 
							<th><hr width="80%" style="border-width: 2px;"></th> <th><hr width="80%" style="border-width: 2px;"></th> 
							<th><hr width="80%" style="border-width: 2px;"></th>
						</tr>
						<c:forEach items="${list }" var="rv" end="4">
						<tr>
								<td>${rv.getbTitle() }</td>
								<td><br><textarea rows="5" cols="30" style="border: none; font-family: paybooc-Light; resize: none;" readonly>${rv.getbCont() }</textarea></td>
								
								<td>
								${rv.getbWriter().substring(0, 2) }
								<c:forEach items="${list }" var="writer" begin="1" end="${rv.getbWriter().length()-2 }">
								*
								</c:forEach>
								</td>
								
								<c:if test="${rv.getBstar() == 1 }">
								<td>★☆☆☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 2 }">
								<td>★★☆☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 3 }">
								<td>★★★☆☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 4 }">
								<td>★★★★☆</td>
								</c:if>
								
								<c:if test="${rv.getBstar() == 5 }">
								<td>★★★★★</td>
								</c:if>
								
								<td>${rv.getbDate().substring(0, 10) }</td>
							</c:forEach>
						</tr> 
						
						<c:if test="<%=Count == 0 %>">
							<tr>
								<td colspan="5" align="center">
									<h2>아직 이 상품에 대한 리뷰가 없습니다.</h2>
								</td>
							</tr>
						</c:if>
						</table>
						
						<div align="center">
						<c:if test="<%=Count > 5 %>">	
							<b>.</b><br><b>.</b><br><b>.</b><br>
							<input type="button" value="이 제품의 리뷰 <%=Count %> 개 모두 확인" style="margin:30px; width:200px; height:50px; 
							font-family:paybooc-Light;" onclick="location.href='<%=request.getContextPath()%>/user_review_list.do?pcode=${dto.getPcode()}&pname=${dto.getPname() }'"><br>
						</c:if>
				</div>
			</c:if>

			<c:if test="${empty dto }">
					<h3 align="center">검색된 상품 상세정보가 없습니다...</h3>
			</c:if>
	</form>
	
	<footer>
		<jsp:include page="../include/user_bottom.jsp"/>
	</footer>
</body>
</html>