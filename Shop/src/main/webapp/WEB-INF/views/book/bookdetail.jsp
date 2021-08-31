<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script>
  $(function(){
	  $("#btn_purchase").on('click', purchase);
	  $("#btn_wishlist").on('click', wishlist);
  });
  
  function purchase() {
	 // 어떤책(code)을 얼만큼(count), 어떤사용자(userid)
	 let count = $("#count").val();
	 let quantity = '${book.quantity}';
	 
	 if( parseInt(count) > parseInt(quantity) ) {
		alert("재고 수량이 부족합니다");
		$("#count").focus();
		
		return;
	 }
	 
	 if(count.trim().length == 0) {
		alert("구매 수량을 입력해 주세요");
		$("#count").focus();
			
		return;
	 }
	 
	 let data = "orderlist?code="+${book.code}+"&count="+count;
	 location.href = data;
  }
  
  function wishlist() {
	 let data = "wishlist?code="+${book.code};
	 location.href = data;
  }
  
  </script>
	
</head>
<body>
<div class="container">
	<h2>[상품 상세 정보]</h2>
	<table class="table">
			<tr>
				<th>상품코드</th>
				<td>${book.code}</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${book.title}</td>
			</tr>
			<tr> 
				<th>가격</th>
				<td>${book.price}</td>
			</tr>
			<tr>
				<th>재고수량</th>
				<td>${book.quantity}</td>
			</tr>
			<tr>
				<th colspan="2">
					<label for="count">구매수량</label>
					<input type="number" id="count" name="count">
					<input type="button" id="btn_purchase" value="구입하기">
					<input type="button" id="btn_wishlist" value="관심상품저장">
				</th>
			</tr>
	</table>
</div>
</body>
</html>



