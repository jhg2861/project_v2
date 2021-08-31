<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문 정보</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
<h2>[ 주문 정보 ]</h2>
	<table class="table">
		<thead>
			<tr>
				<th>구입일</th>
				<th>상품코드</th>
				<th>제목</th>
				<th>가격</th>
				<th>수량</th>
				<th>합계금액</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${list}">
			<tr>
				<td>2021/08/18</td>
				<td>${book.code}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td>${book.quantity}</td>
				<td></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>