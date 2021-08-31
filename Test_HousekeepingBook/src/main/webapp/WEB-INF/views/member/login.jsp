<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>로그인</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  	$(function() {
  		$("#loginBtn").on('click',loginSubmit);
  	});
  	function loginSubmit() {
  		let acc_id  = $("#acc_id").val();
  		let acc_pw  = $("#acc_pw").val(); // R, W
  		
  		if(acc_id.trim().length == 0 ) {
  			alert("아이디를 입력해주세요");
  			return;
  		}
  		if(acc_id.trim().length < 3 || acc_id.trim().length > 8 ) {
  			alert("아이디는 3~8사여여야합니다.");
			return;
  		}
  		
  		if(acc_pw.trim().length == 0 ) {
  			alert("비밀번호를 입력해주세요");
  			return;
  		}
  		
  		if(acc_pw.trim().length < 5 || acc_pw.trim().length >10 ) {
  			alert("비밀번호는 5~10자로 입력해주세요");
  			return;
  		}

  		$("#loginForm").submit();
  	}
  </script>

</head>
<body>

<div class="container">
  <h2>로그인</h2>
  <form id="loginForm" action="login" method="POST">
    <div class="form-group">
      <label for="acc_id">아이디:</label>
      <input type="text" class="form-control" id="acc_id" name="acc_id" placeholder="Enter userid">
    </div>
    <div class="form-group">
      <label for="acc_pw">비밀번호:</label>
      <input type="password" class="form-control" id="acc_pw" name="acc_pw" placeholder="Enter password">
    </div>
    
    <button id="loginBtn" type="submit" class="btn btn-primary">로그인</button>
  </form>
</div>
</body>
</html>
