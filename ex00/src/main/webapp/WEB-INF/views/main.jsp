<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
</head>
<body>

	<c:if test="${empty authInfo}">
		<h2>환영합니다.</h2>
		<p><a href="<c:url value='/login'/>">[로그인]</a>
		<a href="<c:url value='/register/step1'/>">[회원 가입하기]</a></p>
	</c:if>
	<c:if test="${! empty authInfo}">
		<p>${authInfo.name}님 환영합니다.</p>
		<p>
			<a href="<c:url value='/edit/changePassword'/>">[비밀번호 변경]
			<a href="<c:url value='/logout'/>">[로그아웃]
		</p>
	</c:if>

	
	
	<br>
	<br>
	<p><a href="<c:url value='/survey'/>">[설문 조사하기]</a></p>
	<p><a href="<c:url value='/member/list'/>">[날짜별 회원 정보 보기]</a></p>
</body>
</html>