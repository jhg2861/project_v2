<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심 상품</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	$('.btDelete').on('click', deleteBoard);
});	

//삭제하기
function deleteBoard() {
	if (confirm('삭제하시겠습니까?')) {
		let a = $(this).attr('data-num')
		location.href = 'deleteboard?code=' + a;
	}
}
</script>
</head>
<body>
<div class="container">
<h2>[ ${sessionScope.loginName}(${sessionScope.loginId})님의 WishList ]</h2>
	<table class="table">
		<thead>
			<tr>
				<th>상품코드</th>
				<th>제목</th>
				<th>가격</th>
				<th>관심상품 삭제하기</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="book" items="${list}">
			<tr>
				<td>${book.code}</td>
				<td>${book.title}</td>
				<td>${book.price}</td>
				<td><input class="btDelete" type="button" value="삭제" data-num = "${book.code}"></td>
				<td></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>