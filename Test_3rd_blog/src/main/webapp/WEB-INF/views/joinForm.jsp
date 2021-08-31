<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script>

//가입폼 확인
function formCheck() {
	var id = document.getElementById('id');
	var password = document.getElementById('password');
	var name = document.getElementById('name');
	
	if (id.value.length < 3 || id.value.length > 10) {
		alert("ID는 3~10자로 입력하세요.");
		return false;
	}
	if (password.value.length < 3 || password.value.length > 10) {
		alert("비밀번호는 3~10자로 입력하세요.");
		return false;
	}
	if (name.value == '') {
		alert("이름을 입력하세요.");
		return false;
	}
	return true;
}
</script>
</head>

<body>
<h1>[ SES Blog 회원 가입 ]</h1>

<form id="joinform" action="join"  method="post" onsubmit="return formCheck();">
<table class="border">
	<tr>
		<td class="bold">ID</td>
		<td>
			<input type="text" name="id" id="id">
		</td>
	</tr>
	<tr>
		<td class="bold">비밀번호</td>
		<td>
			<input type="password" name="password" id="password">
		</td>
	</tr>
	<tr>
		<td class="bold">이름</td>
		<td>
			<input type="text" name="name" id="name">
		</td>
	</tr>
	<tr>
		<td class="bold">사진 선택</td>
		<td>
			<input type="radio" name="photo" value="1" checked>
			<img src="resources/image/photo1.png">
			<input type="radio" name="photo" value="2">
			<img src="resources/image/photo2.png">
			<input type="radio" name="photo" value="3">
			<img src="resources/image/photo3.png">
			<input type="radio" name="photo" value="4">
			<img src="resources/image/photo4.png">
		</td>
	</tr>

	<tr>
		<td colspan="2" class="center">
			<input type="submit" value="가입" />
		</td>
	</tr>
</table>

</form>

</body>
</html>