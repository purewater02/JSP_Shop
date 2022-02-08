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

    #joinForm  {
    	font-family: paybooc-Light;
        margin: 0 200px 0 200px;
        padding: 20px;
        background-color: #F2EEED;
    }

    #title {
       	text-align: left;
       	margin: 20px;
    }
    
    #subTitle {
    	margin-left: 10px;
    }
    
    #agreement {
   		font-family: paybooc-Light;
    	background-color: #F2EEED;
    	padding: 20px;
    }
    
    #alert-success, #alert_Avail, #idcheck_avail {
    	background-color: lightgreen;
    	color: black;
    	text-align: center;
    	font-weight: bold;
    	font-size: 80%;
    }
    
    #alert-danger, #alert-length, #idcheck_inavail {
    	background-color: tomato;
    	color: black;
    	text-align: center;
    	font-weight: bold;
    	font-size: 80%;
    }
    
    #req {
    	color: #E37E68;
    }
    
    #idCheckBtn {
    	font-family: paybooc-Light;
    	font-weight: bold;
    	width: 100px;
    }
    
    #regBtn {
    	font-family: paybooc-Light;
    	font-weight: bold;
    	font-size: 15px;
    	width: 100px;
    	height: 50px;
    }
        
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	$(function() {
	// 회원가입 폼 페이지에서 중복확인 버튼을 클릭하면 실행되는 함수.
	$('.userId').attr("check_result", "notChecked");
	$("#idcheck_btn").click(function() {	
			$("#idcheck_inavail").hide();	// 버튼에 커서가 올라가면 span태그 영역을 숨김
			let userId = $("#userId").val();	// member_id 텍스트박스에 입력된 값(value)을 가져와서 userId 변수에 넣음.
			
			// 입력 길이 체크
			if($.trim($("#userId").val()).length < 4) {	// 입력한 아이디가 네 글자 미만인 경우
				let warningTxt = '<font>아이디는 4자 이상 16자 이하만 입력가능합니다.</font>';
				$("#idcheck_avail").text('');
				$("#idcheck_inavail").text('');	// idcheck 영역을 초기화.
				$("#idcheck_inavail").show();	// span태그 영역을 보여줘라.
				$("#idcheck_inavail").append(warningTxt);
				$('.userId').attr("check_result", "fail");
				return false;
			}
			
			if($.trim($("#userId").val()).length >= 16) {	// 입력한 아이디가 네 글자 미만인 경우
				let warningTxt = '<font>아이디는 4자 이상 16자 이하만 입력가능합니다.</font>';
				$("#idcheck_avail").text('');
				$("#idcheck_inavail").text('');	// idcheck 영역을 초기화.
				$("#idcheck_inavail").show();	// span태그 영역을 보여줘라.
				$("#idcheck_inavail").append(warningTxt);
				$('.userId').attr("check_result", "fail");
				return false;
			}
			
			// 아이디 중복 여부 확인
			$.ajax({
				type:"post",
				url:"idCheck.jsp",
				data:{paramId : userId},
				datatype:"jsp",
				success: function(data){	// 아이디가 있으면 data에 1이라는 값이 반환됨
					if(data == 1) {	// 중복O		
						let warningTxt = '<font>이미 존재하는 아이디입니다.</font>';
						$("#idcheck_avail").text('');	
						$("#idcheck_inavail").text('');	
						$("#idcheck_inavail").show();	
						$("#idcheck_inavail").append(warningTxt);	
						$('.userId').attr("check_result", "fail");
					} else {	// 중복X
						let warningTxt = '<font>사용가능한 아이디입니다.</font>';
						$("#idcheck_inavail").text('');
						$("#idcheck_avail").text('');	
						$("#idcheck_avail").show();	
						$("#idcheck_avail").append(warningTxt);
						$('.userId').attr("check_result", "success");
					}
				},
				error: function(data){
					alert("데이터 통신 오류 발생...!");
				}
			});
	});
});
	// 약관 동의 일괄 체크
	function cAll() {
    if ($("#checkAll").is(':checked')) {
        $("input[type=checkbox]").prop("checked", true);
    } else {
        $("input[type=checkbox]").prop("checked", false);
    }
	};
	
	// 비밀번호 일치 여부 알림
	$(function(){ 
		$("#alert-success").hide(); 
		$("#alert-danger").hide(); 
		$("#alert-length").hide();
		$(".userPwd1, .userPwd2").keyup(function(){ 
			var pwd1=$("#pwd1").val(); 
			var pwd2=$("#pwd2").val(); 
			
			if(pwd1 != "" || pwd2 != ""){ 
				if(pwd1 == pwd2){ 
				$("#alert-success").show();
				$("#alert-danger").hide(); 
				$("#submit").removeAttr("disabled"); 
				$('.userPwd2').attr("check_result", "success");
				}else{ 
					$("#alert-success").hide(); 
					$("#alert-danger").show(); 
					$("#submit").attr("disabled", "disabled"); 
					$('.userPwd2').attr("check_result", "fail");
					} 
					}
			}); 
		});
	
	// 정보 기입 여부
	$(function(){
		$("#regBtn").click(function(){
			var pwd1=$("#pwd1").val(); 
			var pwd2=$("#pwd2").val(); 
			var name=$(".userName").val();
			var phone=$(".userPhone").val();
			var email=$(".userEmail").val();
			var addr=$(".userAddr").val();
			
			if(pwd1 == "") {
				$('#pwd1').attr("check_result", "fail");
			} else if(pwd1 != "") {
				$('#pwd1').attr("check_result", "success");
			}	
			
			if(pwd2 == "") {
				$('#pwd2').attr("check_result", "fail");
			} else if(pwd2 != "") {
				$('#pwd2').attr("check_result", "success");
			}	
			
			if(pwd1 != pwd2) {
				$('#pwd2').attr("check_result", "fail");
			} else if(pwd1 == pwd2) {
				if(pwd2.length < 4 || pwd2.length > 15) {
					$('#pwd2').attr("check_result", "lengthfail");
				} else {
				$('#pwd2').attr("check_result", "success");
			}	
			}

			if(name == "") {
				$('.userName').attr("check_result", "fail");
			} else if(name != "") {
				$('.userName').attr("check_result", "success");
			}	
			
			if(phone == "") {
				$('.userPhone').attr("check_result", "fail");
			} else if(phone != "") {
				$('.userPhone').attr("check_result", "success");
			}
			
			if(email == "") {
				$('.userEmail').attr("check_result", "fail");
			} else if(email != "") {
				$('.userEmail').attr("check_result", "success");
			}
			
			if(addr == "") {
				$('.userAddr').attr("check_result", "fail");
			} else if(addr != "") {
				$('.userAddr').attr("check_result", "success");
			}
		});
	});
	
	// 정보 미기입 시 얼럿창 출력 
	$(function() {
		$("#regBtn").click(function(){
			 if ($('.userId').attr("check_result") == "notChecked"){
				    alert("아이디를 입력하고 중복확인버튼을 눌러주세요.");
				    $('.userId').focus();
				    return false;
				  } else if ($('.userId').attr("check_result") == "fail"){
					    alert("아이디를 다시 입력하세요.");
					    $('.userId').focus();
					    return false;
					  } else if ($('.userPwd1').attr("check_result") == "fail"){
					    alert("비밀번호를 입력하세요.");
					    $('.userPwd1').focus();
					    return false;
					  } else if ($('.userPwd2').attr("check_result") == "fail"){
						    alert("비밀번호가 일치하도록 다시 입력하세요.");
						    $('.userPwd2').focus();
						    return false;  
					  } else if ($('.userPwd2').attr("check_result") == "lengthfail"){
							    alert("비밀번호는 4자 이상 16자 이하로 입력하세요.");
							    $('.userPwd2').focus();
							    return false;  
					  } else if ($('.userName').attr("check_result") == "fail"){
						    alert("이름을 입력하세요.");
						    $('.userName').focus();
						    return false;
						  } else if ($('.userPhone').attr("check_result") == "fail"){
							    alert("전화번호를 입력하세요.");
							    $('.userPhone').focus();
							    return false;
							  } else if ($('.userEmail').attr("check_result") == "fail"){
								    alert("이메일을 입력하세요.");
								    $('.userEmail').focus();
								    return false;
								  } else if ($('.userAddr').attr("check_result") == "fail"){
									    alert("주소를 입력하세요.");
									    $('.userAddr').focus();
									    return false;
									  }
		});
	});
	
	function zipCheck()
	{
		window.open("zipCheck.jsp", "win", "width=650, height=200, scrollbars=yes, status=yes");
	}
	
</script>
</head>
<body>

	<jsp:include page="../include/user_top.jsp"/>
      
<form name="newMem" method="post" action="<%=request.getContextPath()%>/user_join.do" 
	onsubmit="return confirm('가입을 완료하시겠습니까?')">
	<div id="joinForm">
		<h2 id="title">회원가입</h2><br>
		<hr width="80%" align="left" style="border: solid 0.5px #DAD5D4">
		
		<br>
	
		&nbsp;&nbsp;&nbsp;<h3 id="subTitle">기본정보</h3>
		<br>
  	
   		<table border="0"> 
   		<tr>
        	<td>아이디<span id="req">＊</span></td>
        	<td><input name="userId" id="userId" class="userId">&nbsp;
        	<input type="button" value="중복확인" id="idcheck_btn">&nbsp;
        			<span id="idcheck_avail"></span>
        			<span id="idcheck_inavail"></span>
        	</td>
	
    	    <td colspan="4"></td>
    	</tr>
    
   		<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>

   		<tr>
        	<td>비밀번호<span id="req">＊</span></td>
        	<td><input type="password" name="userPwd" id="pwd1" class="userPwd1">
        		<span class="alert-length" id="alert-length">4자 이상 16자 이하로 입력하세요.</span>
        	</td>
        	<td colspan="4"></td>
    	</tr>
    
    	<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>


    	<tr>
        	<td>비밀번호 확인<span id="req">＊</span></td>
        	<td><input type="password" name="userPwd2" id="pwd2" class="userPwd2">
        		<span class="alert-success" id="alert-success">비밀번호가 일치합니다.</span>
    			<span class="alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</span>
    		</td>
        	<td colspan="4"></td>
    	</tr>
    
    	<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>

    	<tr>
        	<td>이름<span id="req">＊</span></td>
         	<td><input name="userName" class="userName" style="width:100px"></td>
         	<td colspan="4"></td>
    	</tr>
    
   		<tr>
				<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
				</td>
		</tr>

	 	<tr>
        	<td>휴대전화<span id="req">＊</span></td>
        	<td>
        		<input name="userPhone" class="userPhone" style="width:150px"> 
        	<td colspan="4"></td>
    	</tr>
    
    	<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>
	
     	<tr>
        	<td>이메일<span id="req">＊</span></td>
        	<td>
        	<input name="userEmail" class="userEmail">
        	<td colspan="4"></td>
    	</tr>
    
    	<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>

		<tr>
			<td>주소 <span id="req">＊</span></td> 
			<td><input type="text" name="zipcode" size="7" readonly> 
			       <input type="button" value="우편번호검색" onClick="zipCheck()"><br>     
                   <input type="text" name="address1" size="40"  readonly><br>
				   <input type="text" name="address2" size="40">&nbsp;<font size =2>(상세주소입력)</font>
			</td>
		</tr>
    
    	<tr>
			<td colspan="6">
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>

     </table>
</div>

<br><br>

	<table align="center" id="agreement" width="1500">
		<tr>
			<td>
			<input type="checkbox" name="agreeAll" id="checkAll" onclick="cAll();"><label for="c0">이용약관 및 개인정보 수집 및 이용에 모두 동의합니다.
			</td>
		</tr>
		
		<tr>
			<td>
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>
		
		<tr>
			<td>
			<small>[필수] 이용약관 동의<span id="req">＊</span></small><br><br>
			<textarea rows="10" cols=200"><jsp:include page="agreement1.jsp"/></textarea>
			<br>
			<input type="checkbox" id="c1" required="required"><label for="c1"><small>이용약관에 동의하십니까?</small>
			</td>
		</tr>
		<tr>
			<td>
				<hr style="border: solid 0.5px #DAD5D4">
			</td>
		</tr>
		<tr>
			<td>
			<small>[필수] 개인정보 수집 및 이용 동의<span id="req">＊</span></small><br><br>
			<textarea rows="10" cols=200"><jsp:include page="agreement2.jsp"/></textarea>
			<br>
			<input type="checkbox" id="c2" required="required"><label for="c2"><small>개인정보 수집 및 이용에 동의하십니까?</small>
			</td>
		</tr>
		
	</table>
	<br>
	
	<div colspan="4" align="center">
          <input type="submit" value="회원가입" id="regBtn">
     </div>
    </form>

	<br><br>

	<jsp:include page="../include/user_bottom.jsp"/>

</body>
</html>