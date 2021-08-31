<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 1) 글쓰기 : 글쓰기 폼이 있는 화면으로 전환 -->
<input type="button" value="글쓰기" onclick="location.href='write';">
<br><br>
 
<!-- 2) 글 내용 전체를 출력 -->
<h3>[ 방명록 ]</h3>
${list}
</body>
</html>