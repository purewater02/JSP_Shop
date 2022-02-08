<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
main{
	margin: 2vw 5vw;
}
.a {
	color: gray;
}

.p {
	font-size: 13px;
	padding: 0px;
}

.border {	
	border: 1px solid black;
	margin: 0;
	padding: 20px 30px;
}	

.th {
	padding: 15px 30px 15px 30px;
	font-size: 13px;
	text-align: left;
	border-top: 1px solid lightgrey;
	border-right: 1px solid lightgrey;
	width: 15%;
	margin: 40px;
}

.td {
	padding: 15px 30px 15px 30px;
	border-top: 1px solid lightgrey;
	margin: 40px;
}

.th1 {
	font-size: 13px;
	text-align: left;
	border-right: 1px solid lightgrey;
	border-top: 1px solid lightgrey;
	border-bottom: 1px solid lightgrey;
	width: 15%;
	padding: 15px 30px 15px 30px;
	margin: 40px;
}

.td1 {
	border-top: 1px solid lightgrey;
	border-bottom: 1px solid lightgrey;
	padding: 15px 30px 15px 30px;
	margin: 40px;
}

.table {
	font-size: 13px;
	color: gray;
	
}

.text0 {
	padding: 10px 5px 10px 0px;
	font-size: 13px;
}
	
.deleteMember {
	width: 160px;
	height: 50px;
	font-size: 15px;
	background-color: #424242;
	color: white;
	border: medium;
	float: right;	
	cursor: pointer;	
}
.deleteMember:hover, .btn_submit:hover, .btn_cancel:hover {
	opacity: 0.8;
}
.btn_submit {
	cursor: pointer; 
	background-color: #424242; 
	color: white; 
	border: medium; 
	width: 160px; 
	height: 50px;	
}
.btn_cancel {
	cursor:pointer; 
	width: 160px; 
	height: 50px; 
	background-color: lightgray; 
	border: medium;	
}	
#req {
   	color: #E37E68;
}
#alert-success, #alert_Avail, #idcheck_avail {
	background-color: lightgreen;
	color: black;
	text-align: center;
	font-weight: bold;
	font-size: 80%;
}
   
#alert-danger, #alert_Length, #idcheck_inavail {
	background-color: tomato;
	color: black;
	text-align: center;
	font-weight: bold;
	font-size: 80%;
}
   
   
</style>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript">

	//비밀번호 일치 여부 알림
	$(function(){ 
		$("#alert-success").hide(); 
		$("#alert-danger").hide(); 
		$("input").keyup(function(){ 
			var pwd1=$("#pwd1").val(); 
			var pwd2=$("#pwd2").val(); 
			if(pwd1 != "" || pwd2 != ""){ 
				if(pwd1 == pwd2){ $("#alert-success").show();
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
					  } else if ($('.userPwd2').attr("check_result") == "fail"){
					    alert("비밀번호가 일치하도록 다시 입력하세요.");
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
		window.open("user/zipCheck.jsp", "win", "width=600, height=200, scrollbars=yes, status=yes");
	}
	
	
	 // 로그인 클릭
    $("#newLogin").click(function(){
		var ww=400;    //띄울 창의 넓이
		var wh=250;    //띄울 창의 높이
		
		// 새창의 중앙 좌표
		var top=(screen.availHeight-wh)/2;
		var left=(screen.availWidth-ww)/2;
		// 새창 띄움
		window.open("/expro/NewLoginForm.do", "window", "width="+ww+", height="+wh+", top="+top+", left="+left+", toolbar=no, menubar=no, scrollbars=no, resizable=no");
    });

	
	
</script>
</head>
<body>

	<jsp:include page="../include/user_top.jsp" />
<main>
	<c:set var="dto" value="${memberList }" />
	<h3 style="color: darkgray">회원 정보 수정</h3>
	 
	<p align="right" class="p"> <a href="<%=request.getContextPath() %>/main.jsp" class="a">home</a>><a href="<%=request.getContextPath() %>/user_mypage.do?id=${dto.getId() }" class="a">mypage</a>> profile </p>
	<br> <br>
	
	<div class="border"><strong>${dto.getName() }</strong>님 저희 쇼핑몰을 이용해주셔서 감사합니다.</div>
	
	<br> <br><br><br>
	
	<h4>기본정보</h4>
	
	
	
	
	<div align="center">
		<form name="newMem" method="post" action="<%=request.getContextPath() %>/user_profile_update_ok.do?id=${dto.getId() }" onsubmit="return confirm('수정 하시겠습니까?')">
		<table cellspacing="0" width="100%" class="table">
			<tr>
				<th class="th">아이디<span id="req">＊</span>  </th>
				<td class="td"><input type="text" name="userId" value="${dto.getId() }" readonly class="text0" >(영문소문자/숫자,4~16자)</td>
			</tr>
			
			
			
			<tr>
				<th class="th">비밀번호<span id="req">＊</span> </th>
				<td class="td"><input type="password" name="userPwd" id="pwd1" ></td>
			</tr>
			
			
			<tr>
				<th class="th">비밀번호 확인<span id="req">＊</span></th>
				<td class="td"><input type="password" name="userPwd2" id="pwd2" class="userPwd2" class="text0"> 
					<span class="alert-success" id="alert-success">비밀번호가 일치합니다.</span>
	    			<span class="alert-danger" id="alert-danger">비밀번호가 일치하지 않습니다.</span>
	    		</td>
			</tr>
			
			<tr>
				<th class="th">이름<span id="req">＊</span> </th>
				<td class="td"><input type="text" name="userName" value="${dto.getName() }" readonly class="text0"> </td>
			</tr>
			
			<tr>
				<th class="th">휴대전화<span id="req">＊</span></th>
				<td class="td"><input type="text" name="userPhone"  value="${dto.getPhone() }" class="text0"> </td>	
			</tr>
			
			<tr>
				<th class="th">SMS 수신여부<span id="req">＊</span></th>
				<td class="td"><input type="radio" name="sms" /><label for="is_sms0" >수신함</label>
								<input type="radio"   checked="checked" name="sms" /><label for="is_sms1" >수신안함</label>
								<p>쇼핑몰에서 제공하는 유익한 이벤트 소식을 SMS로 받으실 수 있습니다.</p>
				 </td>
			</tr>
			
			<tr>
				<th class="th">이메일<span id="req">＊</span></th>
				<td class="td"><input type="text" name="userEmail" value="${dto.getEmail() }" class="text0"> </td>
			</tr>
			
			<tr>
				<th class="th">이메일 수신여부<span id="req">＊</span></th>
				<td class="td">	<input type="radio" name="email" /><label for="is_news_mail0" >수신함</label>
								<input type="radio" name="email" checked="checked"  /><label for="is_news_mail1" >수신안함</label>
								<p>쇼핑몰에서 제공하는 유익한 이벤트 소식을 이메일로 받으실 수 있습니다.</p>
 				</td>
			</tr>
				
				
				
			<tr>
				<th class="th1">주소<span id="req">＊</span></th>
				<td class="td1"><input type="text" name="zipcode" size="7" value="${dto.getZipcode() }" readonly class="text0">
				       <input type="button" value="우편번호검색" onClick="zipCheck()"><br><br>
	                   <input type="text" name="address1" value="${dto.getAddr1() }" size="60"  readonly class="text0"><br><br>
					   <input type="text" name="address2" value="${dto.getAddr2() }" size="60" class="text0"><font size =2>(상세주소입력)</font>
				</td>
			</tr>
		
			
			
			
		</table>
		
			<br>
		
				<input class="btn_submit" type="submit" value="회원정보수정">
				<input class="btn_cancel" type="button" value="취소" onclick="location.href='user_mypage.do?id=${dto.getId() }'">
		</form>
		
		
	
	
	
	</div>
		<form method="post" action="<%=request.getContextPath() %>/user_profile_delete.do?id=${dto.getId() }" 
					onsubmit="return confirm('탈퇴하면 적립금이 삭제 됩니다.\n현재 적립금 : ${dto.getMileage() }\n정말 탈퇴 하시겠습니까?')">
			<input type="submit" value="회원탈퇴" class="deleteMember">
			
		</form>
		
	<br> <br> <br> <br> <br>
</main>
	<jsp:include page="../include/user_bottom.jsp" />
</body>
</html>