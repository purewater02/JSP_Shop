<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>우편번호검색/주소자동입력</title></head>
<style>
	@font-face {
    font-family: 'paybooc-Light';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/paybooc-Light.woff') format('woff');
    font-weight: normal;
    font-style: normal;
	}

	body {
	background-color: #E6EAE9;
	font-family: paybooc-Light;
	}
	
	#zForm {
		padding: 40px 0 0 0;
	}
	
	table {
		margin: 20px 0 0 0;
	}
</style>
<script type="text/javascript">	
	function getFocus()     //  로딩되자마자 동을 입력하는 텍스트필드에 커서를 위치시켜 줌
	{
		document.zipForm.dong.focus();      //  zipForm.dong.focus(); 로 사용해도 무방함
	}

	function dongCheck()
	{		
		if(document.zipForm.dong.value =="")
		{
			alert("동이름을 입력하세요");
			document.zipForm.dong.focus();
			return;
		}
		document.zipForm.submit(); 
	}
</script>
<body onLoad="getFocus()"> <!-- 로딩되자마자 동을 입력하는 텍스트필드에 커서를 위치시켜 줌 -->
  <div align="center" id="zForm">
   <b><우편번호검색></b>
   <form name="zipForm" method="post" action="<%=request.getContextPath()%>/user_zipCheckOk.do">
     <table> 
      <tr>                         <!-- ime-mode:active로 설정되면 자동으로 한글입력모드로 전환됨   -->
        <td>동이름 입력 : <input name="dong" type="text" style="ime-mode:active">
          <input type="button" value="검색" onclick= "dongCheck();"></td>        
      </tr>
     </table>
     <hr width="55%" style="border: solid 1px grey">
   </form>
  </div>
 </body>
</html>