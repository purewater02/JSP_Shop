<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

	@font-face {
    font-family: 'paybooc-Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}

    table tr td {
    	padding: 10px;
    }

    #IdResult  {
    	font-family: paybooc-Light;
        margin: 200px;
        padding: 20px;
        background-color: #F2EEED;
    }
    
    #result {
    	font-weight: bold;
    	font-size: 15px;
    }

    #title {
       	text-align: center;
       	margin: 20px;
    }
    
    #goLogin {
    	font-family: paybooc-Light;
    	font-size: 15px;
    	
    }
        
</style>
</head>
<body>

<jsp:include page="../include/user_top.jsp"/>

<c:set var="dto" value="${Id }"/>


<div id="IdResult" align="center">
	<h2 id="title">아이디 찾기 결과</h2><br>
	<hr width="80%" style="border: solid 0.5px #DAD5D4">
	
	<br>

  	
    <table border="0" align="center"> 
    <tr>
        <td align="center" id="result">
        	<c:if test="${empty dto.getId() }">
			이름과 전화번호를 다시 한번 확인해주세요.
			</c:if>
	
	
			<c:if test="${!empty dto.getId() }">
			회원님의 아이디는 ${dto.getId() } 입니다.
			</c:if>
        </td>
   
    </tr>

	<tr>
		<td align="center">
			<input type="button" value="로그인하러 가기" id="goLogin" onclick="location.href='<%=request.getContextPath()%>/user/user_login.jsp'">
		</td>
	</tr>
	
	<tr>
		<td align="center">
			<small>
			<a href="<%=request.getContextPath()%>/user/user_findId.jsp">아이디 찾기</a>&nbsp;|&nbsp;
			<a href="<%=request.getContextPath()%>/user/user_findPwd.jsp">비밀번호 찾기</a>&nbsp;|&nbsp;
			<a href="<%=request.getContextPath()%>/user/user_join.jsp">회원가입</a>
			</small>
		</td>
	</tr>
	
    </table>
  </div>
 </form>
    
	<br><br>

	<jsp:include page="../include/user_bottom.jsp"/>


</body>
</html>