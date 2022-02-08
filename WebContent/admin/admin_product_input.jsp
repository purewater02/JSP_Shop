<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<jsp:include page="../include/admin_top.jsp"/>
	
	<div align="center">
		<hr width="65%" color="blue">
			<h3>상품 등록 폼 페이지</h3>
		<hr width="65%" color="blue">
		<br>
		
		<form method="post" enctype="multipart/form-data"
			action="<%=request.getContextPath() %>/admin_product_input_ok.do">
		
			<table border="1" cellspacing="0" width="500">
				<c:set var="list1" value="${categoryList1 }" />
				<c:set var="list2" value="${categoryList2 }" />
				<tr>
					<th>카테고리 </th>
					<td>
						<select name="Ctg1">
							<c:if test="${empty list1 }">
								<option value="">:::없음:::</option>
							</c:if>
							
							<c:if test="${!empty list1 }">
								<c:forEach items="${list1 }" var="dto">
									<option value="${dto.getCtg1() }">[${dto.getCtg1() }] </option>
								</c:forEach>
							</c:if>
						</select>
						
						<select name="Ctg2">
							<c:if test="${empty list2 }">
								<option value="">:::없음:::</option>
							</c:if>
							
							<c:if test="${!empty list2 }">
								<c:forEach items="${list2 }" var="dto">
									<option value="${dto.getCtg2() }">[${dto.getCtg2() }] </option>
								</c:forEach>
							</c:if>
						</select>
					</td>
			
					<tr>
						<th>상품코드</th>
						<td> <input name="pCode"> </td>
					</tr>
					
					<tr>
						<th>상품명</th>
						<td> <input name="pName"> </td>
					</tr>
					
					<tr>
						<th>상품 가격</th>
						<td> <input name="pPrice" maxlength="8"> </td>
					</tr>
					
					<tr>
						<th>상품 사이즈</th>
						<td> 
						<input type="checkbox" name="pSize" value="S"> S
						<input type="checkbox" name="pSize" value="M"> M
						<input type="checkbox" name="pSize" value="L"> L
						<input type="checkbox" name="pSize" value="XL"> XL
						<input type="checkbox" name="pSize" value="XXL"> XXL	
						<input type="checkbox" name="pSize" value="FREE"> FREE				
						</td>
					</tr>
					
					<tr>
						<th>신발 사이즈</th>
						<td> 
						<input type="checkbox" name="pShoeSize" value="250"> 250
						<input type="checkbox" name="pShoeSize" value="260"> 260
						<input type="checkbox" name="pShoeSize" value="270"> 270
						<input type="checkbox" name="pShoeSize" value="280"> 280
						<input type="checkbox" name="pShoeSize" value="290"> 290				
						</td>
					</tr>
					
					<tr>
						<th>상품 색상</th>
						<td> <input name="pColor"> </td>
					</tr>
					
					
					<tr>
						<th>상품 소개</th>
						<td> <textarea rows="7" cols="30" name="pContent"></textarea> </td>
					</tr>
					
					<tr>
						<th>상품 마일리지</th>
						<td> <input name="pMileage"> </td>
					</tr>
					
					<tr>
						<th>상품 썸네일 이미지</th>
						<td> <input type="file" name="pImage1"> </td>
					</tr>
					
					<tr>
						<th>상품 내용 이미지</th>
						<td> <input type="file" name="pImage2"> </td>
					</tr>
					
					<tr>
						<td colspan="2" align="center">
							<input type="submit" value="상품등록">&nbsp;&nbsp;&nbsp;
							<input type="reset" value="다시작성">
						</td>
					</tr>
			</table>
		
		</form>
	</div>
	
	<jsp:include page="../include/admin_bottom.jsp"/>
</body>
</html>