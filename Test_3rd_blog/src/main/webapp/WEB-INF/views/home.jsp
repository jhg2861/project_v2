<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SES Blog</title>
<link rel="stylesheet" type="text/css" href="resources/css/default.css" />
<script src="resources/js/jquery-3.3.1.min.js"></script>
</head>
<body>

<h1>[ SES BLOG ]</h1>
<c:if test="${loginId != null}">
	<h2>
	${sessionScope.loginName}(${sessionScope.loginId})님 환영합니다 !!!<br>
	</h2>
</c:if>

<ul>

<c:if test="${loginId == null}">
	<li><a href="join">회원 가입</a>
	<li><a href="login">로그인</a>
</c:if>
<c:if test="${loginId != null}">
	<li><a href="logout">로그아웃</a>
</c:if>
	<li><a href="listMember">블로그를 개설한 회원 목록 보기</a>
</ul>

</body>
</html>
