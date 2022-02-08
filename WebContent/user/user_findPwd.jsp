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

    #FindPwdForm  {
    	font-family: paybooc-Light;
        margin: 200px;
        padding: 20px;
        background-color: #F2EEED;
    }

    #title {
       	text-align: center;
       	margin: 20px;
    }
    
    #findBtn {
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
      
<form method="post" action="<%=request.getContextPath()%>/user_findpwd.do">
<div id="FindPwdForm" align="center">
	<h2 id="title">비밀번호 찾기</h2><br>
	<hr width="80%" style="border: solid 0.5px #DAD5D4">
	
	<br>

  	
    <table border="0" align="center"> 
    <tr>
        <td>이름</td>
        <td><input name="userName" id="userName"></td>
    </tr>

    <tr>
        <td>아이디</td>
        <td><input name="userId" id="userId"></td>
    </tr>

	<tr>
		<td colspan="2" align="center">
			<input type="submit" value="찾기" id="findBtn">
		</td>
	</tr>
	
    </table>
  </div>
 </form>
    
	<br><br>

	<jsp:include page="../include/user_bottom.jsp"/>
	
</body>
</html>
	
