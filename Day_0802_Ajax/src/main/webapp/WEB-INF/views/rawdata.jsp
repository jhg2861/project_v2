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
	$(".btn1").on('click', ajaxReq4);
	$("#btn2").on('click', ajaxReq5);
});

function ajaxReq4() {
	$.ajax({
		url : 'ajaxReq4'
		, method : 'GET'
		, success : function(resp) {
			alert(resp);
			$("#resultHTML").html(resp);
		}
	});
}

function ajaxReq5() {
	$.ajax({
		url : 'ajaxReq5'
		, method : 'GET'
		, success : function(resp) {
			alert(JSON.stringify(resp));
			
			let result = '<b>';
			result += resp["date"] + " : </b>"
			result += "<i>" +resp["play"] + "</i>"
			
			$("#resultJSON").html(result);
		}
	});	
}
</script>
</head>
<body>
<h2>[Ajax를 이용한 데이터 송수신(raw data)]</h2>
<input class="btn1" type="button" value="오늘의 경기(HTML로 수신)"> &nbsp;
<input id="btn2" type="button" value="오늘의 경기(JSON로 수신)"> &nbsp; <br>

<p id="resultHTML"></p>
<p id="resultJSON"></p>
</body>
</html>




