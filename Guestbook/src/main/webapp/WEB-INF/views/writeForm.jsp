<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 글쓰기</title>
</head>
<body>
<div class="wrapper">
	<h1>[ 방명록 글쓰기 ]</h1>
	<form action="write" method="POST">
		<table border="1">
			<tr>
				<th>이름</th>
				<td><input type="text" name="writer" required="required"> </td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="pwd" required="required" maxlength="10"></td>
			</tr>
			<tr>
				<th>글내용</th>
				<td>
					<textarea name="text" required="required" ></textarea>
				</td>
			</tr>
			<tr>
				<th colspan="2">
					<input type="submit" value="글저장">
				</th>
			</tr>
		
		</table>
	
	</form>
</div>
</body>
</html>