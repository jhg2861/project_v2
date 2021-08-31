<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session과 Cookie</title>
</head>
<body>
<h2>Session과 Cookie</h2>
<ul>
	<li>Session Test-1</li>
	<li>
		<ol>
			<li><a href="sessionSave">Session에 값 저장</a></li>
			<li><a href="sessionDelete">Session의 값 삭제</a></li>
			<li>세션에 저장된 값 : ${sessionScope.value}</li>
			<li><a href="sessionAnnotation">Annotation을 이용한 Session</a></li>
			<li><a href="sessionAnnotationDelete">Annotation으로 저장한 Session의 값 삭제</a></li>
		</ol>
	</li>
	
	<li>Cookie Test-1</li>
	<li>
		<ol>
			<li><a href="cookieSave">Cookie에 값 저장</a></li>
			<li><a href="cookieDelete">Cookie의 값 삭제</a></li>
			<c:if test="${cookie.containsKey('id')}">
			<li>쿠키에 저장된 값 : ${cookie['id'].value} / ${cookie['num'].value}</li>
			</c:if>
			<c:if test="${not cookie.containsKey('id')}">
				<li>쿠키에 정보 없음</li>
			</c:if>
			<li><a href="cookieConsolePrint1">쿠키정보를 콘솔에 출력(Console 확인할 것)</a></li>
			<li><a href="cookieConsolePrint2">쿠키정보를 콘솔에 출력(Annotation)</a></li>
		</ol>
	</li>
</ul>
</body>
</html>