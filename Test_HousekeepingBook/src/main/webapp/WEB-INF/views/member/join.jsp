<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <title>회원가입</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <style>
  	span {
  		color : red;
  		font-size : 0.8em;
  	}
  </style>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

  <script>
  	$(function() {
  		$("#acc_id").on('keyup', idCheck);
  		$("#joinBtn").on('click',joinSubmit);
  	});
  	function joinSubmit() {
  		let acc_id = $("#acc_id").val();
  		let acc_pw  = $("#acc_pw").val(); // R, W
  		let acc_pwd2  = $("#acc_pwd2").val();
  		let acc_nm = $("#acc_nm").val(); // R, W
  		
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
  		if(acc_pwd2 != acc_pwd) {
  			alert("동일한 비밀번호를 입력해주세요");
  			return;
  		}
  		if(acc_nm.trim().length == 0 ) {
  			alert("이름을 입력해주세요");
  			return;
  		}
  		
  		$("#joinForm").submit();
  	}
  	
  	// 사용가능한 아이디인지 체크하는 함수(ajax)
  	function idCheck(){
		let acc_id = $("#acc_id").val(); 
		
		if(acc_id.trim().length < 3 || acc_id.trim().length > 8 ) {
  			$("#idresult").text("아이디는 3~8사여여야합니다.");
			return;
  		}
		$("#idresult").text("");
		
  		$.ajax({
  			url : 'idCheck'
  			, method : 'get'
  			, data : "acc_id=" + $("#acc_id").val()
  			, success : function(resp) { // var resp = "success"
  				if(resp == "success") {
  					$("#idresult").css("color", "blue");
  					$("#idresult").text("사용가능한 아이디입니다.");
  				} else {
  					$("#idresult").css("color", "red");
  					$("#idresult").text("사용 불가능한 아이디입니다.");
  				}
  			}
  		}); 
	}
 </script>

</head>
<body>

<div class="container">
  <h2>회원가입</h2>
  <form id="joinForm" action="join" method="POST">
    <div class="form-group">
      <label for="acc_id">아이디:</label>
      <input type="text" class="form-control" id="acc_id" name="acc_id" placeholder="Enter userid">
      <span id="idresult"></span>
    </div>
    <div class="form-group">
      <label for="acc_pwd">비밀번호:</label>
      <input type="password" class="form-control" id="acc_pw" name="acc_pw" placeholder="Enter password">
    </div>
    <div class="form-group">
      <label for="acc_pwd2">비밀번호 확인:</label>
      <input type="password" class="form-control" id="acc_pwd2" name="acc_pwd2" placeholder="Enter password">
    </div>
    
    <div class="form-group">
      <label for="acc_nm">이름:</label>
      <input type="text" class="form-control" id="acc_nm" name="acc_nm" placeholder="Enter your name">
    </div>

    
    <button id="joinBtn" type="submit" class="btn btn-primary">가입하기</button>
  </form>
</div>

</body>
</html>
