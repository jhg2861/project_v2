<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 연습</title>
<script src="resources/script/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	// false 역할을 하는 값 : null(f), undefind(f), 0(f), false, "" 
	// true 역할을 하는 값 : true, 1(t), [](t), {}(t), "data"
	$("#reqBtn1").on('click', ajaxReq1);
	$("#reqBtn2").on('click', ajaxReq2);
	$("#reqBtn3").on('click', ajaxReq3);
});

function ajaxReq1() {
	$.ajax({
		url : 'ajaxReq1'
		, method  : 'GET'
	});
}

function ajaxReq2() {
	let name  = 'HongGildong';
	let phone = '010-1111-2222';
	
	let myData = {"name" : name, "phone" : phone};
	// myData = "name="+name+"&phone="+phone;
	
	$.ajax({
		url : 'ajaxReq2'
		, method  : 'GET'
		, data    : myData
		, success : function(resp) { //  let resp = {"name" : name, "phone" : phone}
			 let tag = '되돌아온 이름 : ' + resp['name'] + '<br>';
			 tag    += '되돌아온 번호 : ' + resp['phone'] + '<br>';
			 $("#req2Result").html(tag);
		}
	});
}

function ajaxReq3() {
	let name  = $("#username").val();
	let phone = $("#phone").val();
	
	let myData = {"name" : name, "phone" : phone};
	
	$.ajax({
		url : 'ajaxReq3'
		, method  : 'POST'
		, data    : myData
		, success : function(resp) {
			 let tag = '되돌아온 이름 : ' + resp['name'] + '<br>';
			 tag    += '되돌아온 번호 : ' + resp['phone'] + '<br>';
			 $("#req3Result").html(tag);
		}
	});
}

</script>
</head>
<body>
<h2>[Ajax를 이용한 데이터 송수신]</h2>
<ul>
	<li><input type="button" id="reqBtn1" value="1_Ajax로 요청보내기"><br></li>
	<li>
		<input type="button" id="reqBtn2" value="2_Ajax로 데이터보내기">
		<p id="req2Result"></p>
	</li>
	<li>
		이름 : <input type="text" id="username"><br>
		번호 : <input type="text" id="phone"><br>
		<input type="button" id="reqBtn3" value="3_Ajax로 데이터보내기(입력받아서)">
		<p id="req3Result"></p>
	</li>
	<li>
		<a href="rawdata">4_Raw 데이터의 수신을 위한 화면 이동</a>
	</li>
	
	<li>
		<a href="variousType">5_다양한 형태의 데이터 수신</a>
	</li>
	<li>
		<a href="reply">6_댓글 연습 화면</a>
	</li>	
</ul>
</body>
</html>




