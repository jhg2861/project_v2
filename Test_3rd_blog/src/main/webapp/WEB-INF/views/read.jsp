<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML>
<html>
<head>
<title>[ ${blogId}님의 블로그 ]</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
<script>
$(document).ready(function() {
	$('#btLike').on('click', like);
	$('#btDelete').on('click', deleteBoard);
	$('#btUpdate').on('click', updateBoard);
});

//추천하기
function like() {
	$.ajax({
		url: 'like',
		data: {boardnum: ${board.boardnum}},
		dataType: 'text',
		success: function(n) {
			$('#likeDiv').html(n);
		},
		error: function (e) {
			alert("추천 실패");
		}
	});
}

//삭제하기
function deleteBoard() {
	if (confirm('삭제하시겠습니까?')) {
		location.href = 'delete?boardnum=${board.boardnum}';
	}
}
//수정하기
function updateBoard() {
	location.href = 'update?boardnum=${board.boardnum}';
}
</script>
</head>
<body>

<h1>[ ${blogId}님의 블로그 ]</h1>

<table>

<tr>
<th class="left title">제목 </th>
<td class="border">${board.title}</td>
</tr>
<tr>
<th class="left title">작성일 </th>
<td class="border">${board.inputdate }</td>
</tr>
<tr>
<th class="left title">내용 </th> 
<td class="border"><pre>${board.content}</pre></td>
</tr>
<tr>
<th colspan="2">
	추천수 &nbsp;
	<span id="likeDiv" style="font-size: 30px;">${board.likecnt}</span>
	&nbsp;&nbsp;
	<input id="btLike" type="button" value="추천하기">
</th>
</tr>

<!-- 본인 블로그 글일때만 보임 -->
<c:if test="${sessionScope.loginId == board.id}">
<tr>
<th colspan="2" style="height:50px;">
	<input id="btUpdate" type="button" value="수정">
	<input id="btDelete" type="button" value="삭제">
</th>
</tr>
</c:if>

</table>

</body>
</html>
