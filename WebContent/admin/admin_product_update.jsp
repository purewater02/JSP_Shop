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

	<jsp:include page="../include/admin_top.jsp" />
	
	<div align="center">
		<hr width="65%" color="blue">
			<h3>상품 수정 폼 페이지</h3>
		<hr width="65%" color="blue">
	
	
	<form method="post" enctype="multipart/form-data"
		action="<%=request.getContextPath() %>/admin_product_update_ok.do">
		<c:set var="dto" value="${productDto }" />
		<c:set var="list1" value="${categoryList1 }" />
		<c:set var="list2" value="${categoryList2 }" />
		
		<input type="hidden" name="pNo" value="${dto.getPno() }">
		<table border="1" cellspacing="0" width="600">
			<tr>
				<th>카테고리 코드</th>
				<td>
					<select name="Ctg1">
						<c:if test="${!empty list1 }">
							<c:forEach items="${list1 }" var="i">
								<c:if test="${dto.getCtg1() == i.getCtg1() }">
									<option value="${i.getCtg1() }" selected>[${i.getCtg1() }]
								</c:if>
								
								<c:if test="${dto.getCtg1() != i.getCtg1() }">
									<option value="${i.getCtg1() }" disabled>[${i.getCtg1() }]
								</c:if>
							
							</c:forEach>
						</c:if>
					</select>
					
					<select name="Ctg2">
						<c:if test="${!empty list2 }">
							<c:forEach items="${list2 }" var="i">
								<c:if test="${dto.getCtg2() == i.getCtg2() }">
									<option value="${i.getCtg2() }" selected>[${i.getCtg2() }]
								</c:if>
								
								<c:if test="${dto.getCtg2() != i.getCtg2() }">
									<option value="${i.getCtg2() }" disabled>[${i.getCtg2() }]
								</c:if>
							
							</c:forEach>
						</c:if>
					</select>
				</td>
			</tr>
			
			<tr>
				<th>상품코드</th>
				<td> <input name="pCode" readonly
						value="${dto.getPcode() }"> </td>
			</tr>
			
			<tr>
				<th>상품명</th>
				<td> <input name="pName" readonly
						value="${dto.getPname() }"> </td>
			</tr>
			
			<tr>
				<th>상품 가격</th>
				<td> <input name="pPrice" maxlength="8"
						value="${dto.getPprice() }"> </td>
			</tr>
			
			<tr>
				<th>상품 판매량</th>
				<td> <input name="pSold" maxlength="8"
						value="${dto.getPsold() }"> </td>
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
				<th>상품 색상</th>
				<td> <input name="pColor" value="${dto.getPcolor() }"> </td>
			</tr>
			
			
			<tr>
				<th>상품 썸네일 이미지</th>
				<td>
					
					<input type="file" name="pImage1_new">
					<%-- 이미지를 수정하지 않고 그대로 사용할 경우에는
						  상품 등록 시 입력한 이미지를 그대로 사용하여 히든으로 넘겨줄 예정. --%>
					<input type="hidden" name="pImage1_old"
							value="${dto.getPimage1() }">
				</td>
			</tr>
			
			<tr>
				<th>상품 내용 이미지</th>
				<td>
					
					<input type="file" name="pImage2_new">
					<%-- 이미지를 수정하지 않고 그대로 사용할 경우에는
						  상품 등록 시 입력한 이미지를 그대로 사용하여 히든으로 넘겨줄 예정. --%>
					<input type="hidden" name="pImage2_old"
							value="${dto.getPimage2() }">
				</td>
			</tr>
							
			<tr>
				<th>상품 소개</th>
				<td>
					<textarea rows="7" cols="30" name="pContent">${dto.getPcontent() }</textarea>
				</td>
			</tr>
			
			<tr>
				<th>상품 마일리지</th>
				<td>
					<input name="pMileage" value="${dto.getPmileage() }">
				</td>
			</tr>
			
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="상품수정">&nbsp;&nbsp;&nbsp;
					<input type="reset" value="다시작성">
				</td>
			</tr>
		</table>
	</form> 
	
	</div>
	<jsp:include page="../include/admin_bottom.jsp" />
</body>
</html>