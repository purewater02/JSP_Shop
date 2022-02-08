<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">

	
.a {
	text-decoration: none;
	color:gray;
}
.bgColor {
	background-color: lightgrey;
}

#bgColor {
	background-color: lightgrey;
}

.bottom_table {
	font-size: 14px;
	
}
.bottom_head {
	font-size: 15px;
}
	
</style>
</head>
<body>
	<table cellspacing="0"  width="100%" class="bgColor">
	
		<tr align="left" height="70" class="bottom_head">
			<th width="50%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JSP</th> <th >고객센터</th> <th>은행정보</th> <th> 커뮤니티 </th>
		</tr>
		
		
		<tr class="bottom_table">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;대표자 : 김쌍용 </td> 
			<td> 070-2677-2200 </td>
			<td> 신한 161617-15-616782 </td> 
			<td><a href="<%=request.getContextPath() %>/user_notice.do" class="a">공지사항</a> </td>
		</tr>
		
		<tr height="30" class="bottom_table">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;사업자등록번호 : 117-15-36811 </td>
			<td> MON-FRI AM09:00 - PM17:00</td> 
			<td> 예금주: 김쌍용(JSP) </td>
			<td> <a href="<%=request.getContextPath() %>/user_qna_list.do" class="a">문의하기</a></td>
		</tr> 
			
		<tr class="bottom_table">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;통신판매업신고번호 : 2021-서울홍대-0153호[사업자정보확인]</td>
			<td> BREAK TIME</td>
			<td class="bottom_td"> 반품계좌정보 </td>
			<td><a href="<%=request.getContextPath() %>/user_community_review.do" class="a">리뷰게시판</a> </td>
		</tr>
		
		<tr height="30" class="bottom_table">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;개인정보책임관리자 : 김쌍용 (twodragons@naver.com) </td>
			<td> PM12:30 - PM13:30 </td>
			<td> 신한 367161-42-517824 </td>
			<td> Official Instargram </td>
		</tr>
		
		<tr class="bottom_table">
			<td> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;교환, 반품주소 : [01716] 서울시 마포구 홍익로 걷고싶은거리 27 2층 JSP </td>
			<td> (WEEKENDS, HOLIDAY OFF) </td>
			<td> 예금주 : 김쌍용(JSP) </td>
			<td> @jsp.official </td>
		</tr>
		
		</table>
		
		
		<table cellspacing="0" width="100%" class="bottom_table" id="bgColor">
		
		<tr height="50">
			<td> </td>
		</tr>
		
		<tr>
			<td align="left"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;copyright ⓒ JSP all rights reserved </td>
			<td align="right">
				<a href="#" class="a">회사소개</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/user/user_agreement.jsp" class="a">이용약관</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/user/user_privacy.jsp" class="a">개인정보취급방침</a>&nbsp;&nbsp;
				<a href="<%=request.getContextPath() %>/user/user_guide.jsp" class="a">이용안내</a>
			</td>
		</tr>
	
		<tr height="30"> </tr>
	</table>
</body>
</html>