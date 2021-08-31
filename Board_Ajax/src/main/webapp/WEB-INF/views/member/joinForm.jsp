<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입</title>
<style>
	span {
		color:red;
		fond-size : 0.8em;
	}
</style>
<script src="resources/script/jquery-3.6.0.min.js"></script>
<script>
let sendOK = false; // true일 때만 전송되도록 할 것

$(function(){
	$("#userid").on('keyup', idcheck);
	$("#userpwd").on('keyup', pwdcheck);
});

function idcheck(){
		// 글자수 3~5
		let userid = $("#userid").val();
		if(userid.trim().length < 3 || userid.trim().length>5) {
			$("#idresult").text("아이디는 3~5자로 입력해주세요");
			sendOK = true;
			return;
		}
		$("#idresult").text("");
		
		$.ajax({
			url : 'idCheck'
			, method : 'POST'
			, data : {"userid" : userid}
			, success : function(resp) { // success, fail
				if(resp == 'success') {
					$("#idresult").css("color", "blue"); 
					$("#idresult").text("사용 가능한 아이디입니다.");
					sendOK = true;
				} else {
					$("#idresult").css("color", "red");
					$("#idresult").text("사용 불가능한 아이디입니다.");
					sendOK = false;
			}
		}
	})
}

function pwdcheck() {
	let userpwd = $("#userpwd").val();
	
	if(userpwd.trim().length < 3 || userpwd.trim().length>5) {
		$("#pwdresult").css("color", "red");
		$("#pwdresult").text("비밀번호는 3~5자로 입력해주세요");
		sendOK = false;
		return;
	}
	sendOK = true;
	$("#pwdresult").text("");
}

function memberCheck() {
	if(sendOK) {
		alert("회원가입 가능");
		return true;
	} else {
		alert("회원 가입 불가");
		return false;
	}
}
</script>
</head>
<body>
<div class="wrapper">
	<h2>[ 회원가입 ]</h2>
	<form id="join" action="join" method="POST">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" id="userid" name="userid">
					<input type="button" value ="ID 중복확인"> 
					<span id="idresult"></span>
				</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" id="userpwd" name="userpwd">
					<span id="pwdresult"></span>
				</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>
					<input type="text" id="username" name ="username">
				</td>
			</tr>
			<tr>
				<th>Email</th>
				<td>
					<input type="email" id="email" name="email">
				</td>
			</tr>
			<tr>
				<th>생년월일</th>
				<td>
					<input type="date" id="birth" name="birth">
				</td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio"  name="gender" value="남자" checked>남자
					<input type="radio"  name="gender" value="여자">여자
				</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>
					<select id="phone1">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="019">019</option>
						<option value="018">018</option>
					</select>
					<input type="text" id="phone2" placeholder="-없이 숫자만 입력">
					<input type="hidden" id="phone" name="phone" value="" >
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="회원가입" onclick="return memberCheck();" >
					<input type="reset"  value="지우기">
				</th>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
