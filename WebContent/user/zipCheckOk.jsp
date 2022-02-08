<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
	@font-face {
    font-family: 'paybooc-Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}
	
	body {
		font-family: paybooc-Light;
		background-color: #E6EAE9;
		padding: 15px 0 0 0;
	}
	
	table {
		margin: 20px 0 0 0;
	}
	
	 #rt {
		text-decoration: none;
		color: white;
		font-weight: bold;
	}
	
	p {
		background-color: grey;
		width: 20%;
	}
</style>

<script type="text/javascript">
	function sendAddress(zip,s,g,d)
	{
		//시도 구군 동을 합한 주소
		var address =s + " " + g + " " + d;
		 // opener:   open()함수를 호출했던 상위(부모) 윈도우 객체를 의미함
		opener.document.newMem.zipcode.value=zip;
        opener.document.newMem.address1.value=address;
		opener.document.newMem.address2.focus();
		window.close();
	}
</script>


<html>
 <head>
 	<title>우편번호 검색</title>
 </head>
 <body>
  <div align="center">
	<h3>우편번호 검색결과</h3>
	<table border=0>
	  <tr>
		<td><font size=2><b>우편번호</b></font></td><td><font size=2><b>시/도</b></font></td><td><font size=2><b>구/군</b></font></td>
		<td><font size=2><b>동</b></font></td><td><font size=2><b>번지</b></font></td>
	  </tr>
	  <tr>
	  	<td colspan="5"><hr style="border: dotted 0.1px grey"></td>
	  </tr>
	<c:set var="list" value="${ZipList }" />
	<c:if test="${!empty list }">
		<c:forEach items="${list }" var="dto">
			<tr>
		 		<td width=80><a href="javascript:sendAddress('${dto.getZipcode() }','${dto.getSido() }','${dto.getGugun() }','${dto.getDong() }','${dto.getBunji() }')"><font size=2>${dto.getZipcode() }</font></a></td>
		  		<td width=80><font size=2>${dto.getSido() }</font></td>
		  		<td width=120><font size=2>${dto.getGugun() }</font></td>
		  		<td width=280><font size=2>${dto.getDong() }</font></td>
		  		<c:if test="${empty dto.getBunji() }">
		  		<td>&nbsp;</td>
				</c:if>
		  		<c:if test="${!empty dto.getBunji() }">
		  		<td width=150><font size=2>${dto.getBunji() }</font></td>	
		  		</c:if>
	  		</tr>
		</c:forEach>
	</c:if>
	
	<c:if test="${empty list }">
		
			<tr>
		 		<td colspan="5">검색된 지번이 없습니다.</td>
	  		</tr>
	
	</c:if>
	  
	</table>  
	<p><a href="user/zipCheck.jsp" id="rt">다시 입력</a></p>
   </div>
  </body>
</html>
