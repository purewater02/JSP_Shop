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
		z-index: 999;
	}

	body {
		font-family: paybooc-Light;
		margin-top: 30px;
	}
	
	.allList {
		margin: -30px 0 50px 0;
	}
	
	#pList {
		display: flex;
 		flex-wrap: wrap;
  		justify-content: center;
		text-align: center;
		margin: 30px 50px 50px 50px;
	}
	
	#item {
		display: flex;	
		justify-content: center;	
		height: 700px;
		margin: 50px;					
		align-items: center;	
		padding: 0 15px 0 15px;
		border: 2px solid black;
	}	
	
	#items tr td {
		border-style: solid;
		border-width: 2px;
		height: 700px;
		margin: 50px;
		
	}	
	
	#banners {
		align:center;
		margin:0 auto;
		width:1500px;
		height:500px;
		position: relative;
	}
	
	#prdCont a {
		text-decoration: none;
	}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>	
<script src="js/bootstrap.js"></script>
<script src="js/jquery.bxslider.js"></script>
<%
	ProductDAO dao = ProductDAO.getInstance();
	List<ProductDTO> list = dao.getProductList();
%>
</head>
<body>

	<div class="top_header">
		<jsp:include page="include/user_top.jsp"/>
    </div>
    
    <div id="banners">
    	<iframe src="banner.html" frameborder="0" width=1500  height=500 scrolling=auto name=banner ></iframe>
    <br>

    </div>
   		
   		<c:set var="list" value="<%=list %>"/>
	
	<div id="prdCont" align="center">
		<table border="0" align="center" id="items">
			<c:if test="${!empty list }">
			<tr id="pList">
				<c:forEach items="${list }" end="5" var="dto">
						<td id="item">
						<a href="<%=request.getContextPath()%>/user_product_content.do?pNo=${dto.getPno() }&sessionId=${loggedId}">
						<img src="<%=imgPath %>${dto.getPimage1() }" width="400px" height="500px"><br>
						${dto.getPname() }<br>
						₩&nbsp;<fmt:formatNumber value="${dto.getPprice() }" /><hr width="50%">
						</a>												
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
		<h2 style="width: 200px; border-bottom: solid 3px gray;"><a href="<%=request.getContextPath()%>/user_product_list.do">모든 상품 보기</a></h2>
	</div>
    
    <br><br>

    <jsp:include page="include/user_bottom.jsp"/>
    
</body>
</html>