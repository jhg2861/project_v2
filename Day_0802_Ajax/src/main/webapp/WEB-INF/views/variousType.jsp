<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajax 연습</title>
<style>
	span {
		color : red;
	}
</style>
<script src="resources/script/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$("#send1").on('click', send1);
	$("#send2").on('click', send2);
	$("#send3").on('click', send3);
	$("#send4").on('click', send4);
});

function send1() {
	$.ajax({
		url : "send1"
		, method   : "POST"
		, data     : "username=홍길동&userid=Hong"
		, dataType : "text"
		, success : function(resp) {
			$("#result1").text(resp);
		}
	});
}

function send2() {
	$.ajax({
		url : "send2"
		, method   : "POST"
		, data     : JSON.stringify({"username" : "임꺽정" , "userid" : "Lim"})
		, dataType : "JSON"
		, contentType : "application/json;charset=utf-8"
		, success : function(resp) {
			$("#result2").text(JSON.stringify(resp));
		}
	});
}

function send3() {
	$.ajax({
		url : "send3"
		, method   : "POST"
		, data     : {"username" : "손오공" , "userid" : "Son"}
		, dataType : "JSON"
		
		, success : function(resp) {
			$("#result3").text(JSON.stringify(resp));
		}
	});	
}

function send4() {
	$.ajax({
		url : "send4"
		, method   : "GET"
		, success : function(resp) {
			$.each(resp, function(index, item) {
				$("#result4").append(JSON.stringify(item)+"<br>");
			})
		}
	});	
}
</script>
</head>
<body>
<h2>[ 다양한 종류의 데이터 송수신 ]</h2>
	<ul>
		<li>
			<input type="button" id="send1" value="String -- String"><br>
			<span id="result1"></span>
		</li>
		<li>
			<input type="button" id="send2" value="JSON -- Map"><br>
			<span id="result2"></span>
		</li>
		<li>
			<input type="button" id="send3" value="JSON -- VO"><br>
			<span id="result3"></span>
		</li>
		<li>
			<input type="button" id="send4" value="None -- ArrayList"><br>
			<span id="result4"></span>
		</li>					
	</ul>
</body>
</html>




