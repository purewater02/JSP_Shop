<%@page import="com.jsp.model.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.jsp.model.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
	<%
	String imgPath = request.getContextPath()+"/upload/";
	%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP main page</title>
<style>

	@font-face {
    font-family: 'paybooc-Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}
	
	.top_header {
		width: 100%;
		position: fixed;
		margin: 0 500px 0 0;
		padding: 0;
	}
		
	body {
		margin-top: 10px;
	}
	
	#pList {
		text-align: center;
		display: flex;
		flex-wrap: wrap;
		justify-content: flex-start;
		margin: 30px 50px 50px 90px;
		width: 90%;
	}
	
	#item {
		flex: 33% 33% 33%;
		flex-basis: 450px;
		margin-bottom: 50px;
	}
	
	#items tr td {
		border-style: solid;
		border-width: 2px;
		height: auto;
		padding-top: 50px;
		padding-right: 40px;
		margin: 30px;
	}	

</style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<%
	ProductDAO dao = ProductDAO.getInstance();
	List<ProductDTO> list = dao.getProductList();
%>
</head>
<body>

	<div class="top_header">
		<jsp:include page="include/user_top.jsp"/>
    </div>    
    <article id="banners" align="center">
    	<img src="image/eventBanner.png" width="1500">
    <br>

    </article>
   		
    	<c:set var="list" value="<%=list %>"/>
		
	<div align="center">
		<table border="0" align="center" id="items">
			<c:if test="${!empty list }">
			<tr id="pList">
				<c:forEach items="${list }" var="dto">
						<td id="item"><a href="<%=request.getContextPath()%>/user_product_content.do?pNo=${dto.getPno() }">
						<img src="<%=imgPath %>${dto.getPimage1() }" width="90%" height="auto"><br> 
						${dto.getPname() }<br>												
						₩&nbsp;<fmt:formatNumber value="${dto.getPprice() }" /></a><br>
						<hr width="50%">
						</td>
				</c:forEach>
			</tr>
			</c:if>
			
			<c:if test="${empty list }">
				<tr>
					<td align="center">
						<h3>검색된 상품 리스트가 없습니다...</h3>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
    
    <br><br>

    <jsp:include page="include/user_bottom.jsp"/>
    
</body>
</html>