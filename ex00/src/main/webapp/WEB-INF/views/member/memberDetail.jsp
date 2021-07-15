<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 상세 보기</title>
</head>
<body>
<p>아이디 : ${member.code}</p>
<p>이메일 : ${member.email}</p>
<p>이름 : ${member.name}</p>
<p>가입일 : <fmt:formatDate value="${member.registerDate}" pattern="yyyy-MM-dd"/></p>
<br>
<p><a href="<c:url value='/member/list'/>">[날짜별 회원 정보 보기]</a></p>

</body>
</html>