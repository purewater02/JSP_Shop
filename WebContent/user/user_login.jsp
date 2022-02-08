<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    	padding: 5px;
    }

    #LoginForm  {
    	font-family: paybooc-Light;
        margin: 200px;
        padding: 20px;
        background-color: #F2EEED;
    }

    #title {
       	text-align: center;
       	margin: 20px;
    }
    
    #loginBtn {
    	font-family: paybooc-Light;
    	font-weight: bold;
    	font-size: 15px;
    	width: 100%;
    	height: 50px;
    }
        
</style>
</head>
<body>

<jsp:include page="../include/user_top.jsp"/>
      
<form method="post" action="<%=request.getContextPath()%>/user_login.do">
<div id="LoginForm" align="center">
	<h2 id="title">로그인</h2><br>
	<hr width="80%" style="border: solid 0.5px #DAD5D4">
	
	<br>

  	
    <table border="0" align="center"> 
    <tr>
        <td>아이디</td>
        <td><input name="userId" id="userId" class="userId"></td>
    </tr>

    <tr>
        <td>비밀번호</td>
        <td><input type="password" name="userPwd" id="pwd1"></td>
    </tr>

	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="로그인" id="loginBtn">
		</td>
	</tr>
	
	<tr>
		<td colspan="2">
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